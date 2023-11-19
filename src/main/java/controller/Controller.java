package controller;

import java.io.IOException;
import java.sql.Connection;
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
    private DAOMenuItem DAOMenuItem;

    public Controller() {
        super();
        DAOMenuItem = new DAOMenuItem();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try (Connection con = DAOMenuItem.connect()) {
            System.out.println("Conexão estabelecida com o banco de dados.");

            switch (action) {
                case "/main":
                    displayMenu(request, response);
                    break;
                case "/insert":
                    insertMenuItem(request, response);
                    break;
                case "/update":
                    updateMenuItem(request, response);
                    break;
                case "/delete":
                    deleteMenuItem(request, response);
                    break;
                default:
                    response.sendRedirect("villaHome.jsp");
                    break;
            }
        } catch (Exception e) {
            System.out.println("A conexão não pôde ser estabelecida com o banco de dados.");
            e.printStackTrace();
        }
    }

    private void displayMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        // Retrieve menu items from the database using DAOMenuItem
        List<MenuItem> menuItems = DAOMenuItem.getMenuItems();

        // Set menuItems as an attribute in the request
        request.setAttribute("menuItems", menuItems);

        // Forward the request to the JSP page for displaying menu items
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayMenu.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMenuItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        // Retrieve form data
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String type = request.getParameter("type");

        // Create a MenuItem object with the form data
        MenuItem newMenu = new MenuItem(name, ingredients, type);

        // Insert the new menu item into the database using DAOMenuItem
        DAOMenuItem.insertMenuItem(newMenu);

        // Redirect to the main page
        response.sendRedirect("main");
    }

    private void updateMenuItem(HttpServletRequest request, HttpServletResponse response) {
        // Implement the logic to update a menu item
    }

    private void deleteMenuItem(HttpServletRequest request, HttpServletResponse response) {
        // Implement the logic to delete a menu item
    }
}