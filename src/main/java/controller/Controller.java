package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOMenuItem;
import model.MenuItem;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/update", "/delete" })
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOMenuItem daoMenuItem;

    public Controller() {
        super();
        daoMenuItem = new DAOMenuItem();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	response.setContentType("text/html; charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");

        String action = request.getServletPath();

        try (Connection con = daoMenuItem.connect()) {
            System.out.println("Conexão com o banco de dados estabelecida.");

            switch (action) {
                case "/main":
                    displayMenu(request, response);
                    break;
                case "/insert":
                    insertMenuItem(request, response);
                    break;
                case "/update":
                    showUpdateForm(request, response);
                    break;
                case "/delete":
                    deleteMenuItem(request, response);
                    break;
                default:
                    response.sendRedirect("villaHome.jsp");
                    break;
            }
        } catch (SQLException e) {
            handleSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8"); 

        String action = request.getParameter("action");

        switch (action) {
            case "update":
                try {
                    updateMenuItem(request, response);
                } catch (ServletException | IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                deleteMenuItem(request, response);
                break;
            default:
                response.sendRedirect("villaHome.jsp");
                break;
        }
    }

    private void displayMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MenuItem> menuItems = daoMenuItem.getMenuItems();
        request.setAttribute("menuItems", menuItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayMenu.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMenuItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String type = request.getParameter("type");

        MenuItem newMenu = new MenuItem(name, ingredients, type);
        daoMenuItem.insertMenuItem(newMenu);

        response.sendRedirect("main");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idToUpdate = request.getParameter("idToUpdate");
        MenuItem menuItem = daoMenuItem.getMenuItemById(idToUpdate);
        request.setAttribute("menuItem", menuItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateMenu.jsp");
        dispatcher.forward(request, response);
    }

    private void updateMenuItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setCharacterEncoding("UTF-8");

        String idToUpdate = request.getParameter("idToUpdate");
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String type = request.getParameter("type");

        MenuItem updatedMenuItem = new MenuItem(name, ingredients, type);
        updatedMenuItem.setId(idToUpdate);

        try {
            int itemId = Integer.parseInt(idToUpdate);
            boolean success = daoMenuItem.updateMenuItem(itemId, updatedMenuItem);

            if (success) {
                request.setAttribute("updateSuccess", true);
                System.out.println("Prato atualizado com sucesso.");
            } else {
                System.err.println("Erro na atualização do prato.");
            }
        } catch (NumberFormatException e) {
            handleException(e, "Erro na atualização do prato: " + e.getMessage());
        }

        response.sendRedirect("main");
    }

    private void deleteMenuItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idToDelete = request.getParameter("idToDelete");

        try {
            int itemId = Integer.parseInt(idToDelete);
            daoMenuItem.deleteMenuItem(itemId);
            System.out.println("Prato excluído com sucesso.");
        } catch (NumberFormatException | SQLException e) {
            handleException(e, "Erro na exclusão do prato: " + e.getMessage());
        }

        response.sendRedirect("main");
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
        System.out.println("Não foi possível estabelecer conexão com o banco de dados.");
    }

    private void handleException(Exception e, String message) {
        e.printStackTrace();
        System.err.println(message);
    }
}
