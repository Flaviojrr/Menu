package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMenuItem {

    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/VillaEleganza";
    private String user = "postgres";
    private String password = "kerous";

    public DAOMenuItem() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url + "?charset=utf8", user, password);
    }


    public void testConnection() {
        try (Connection con = connect()) {
            System.out.println("Conexão com o banco de dados estabelecida.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void insertMenuItem(MenuItem novoPrato) {
        try (Connection con = connect();
             PreparedStatement statement = con.prepareStatement("INSERT INTO menu (name, ingredients, type) VALUES (?, ?, ?)")) {

            statement.setString(1, novoPrato.getName());
            statement.setString(2, novoPrato.getIngredients());
            statement.setString(3, novoPrato.getType());

            statement.executeUpdate();

            System.out.println("Novo prato inserido com sucesso!");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        try (Connection con = connect();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM menu");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                MenuItem menu = new MenuItem(
                        rs.getString("name"),
                        rs.getString("ingredients"),
                        rs.getString("type"));
                menu.setId(rs.getString("id"));
                menuItems.add(menu);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return menuItems;
    }

    public void deleteMenuItem(int id) throws SQLException {
        try (Connection connection = connect()) {
            String sql = "DELETE FROM menu WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public MenuItem getMenuItemById(String id) {
        try (Connection con = connect();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM menu WHERE id = ?")) {

            int itemId = Integer.parseInt(id);
            statement.setInt(1, itemId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    MenuItem menuItem = new MenuItem(
                            rs.getString("name"),
                            rs.getString("ingredients"),
                            rs.getString("type"));
                    menuItem.setId(String.valueOf(rs.getInt("id")));
                    return menuItem;
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public boolean updateMenuItem(int id, MenuItem updatedMenuItem) {
        try (Connection connection = connect()) {
            String sql = "UPDATE menu SET name=?, ingredients=?, type=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, updatedMenuItem.getName());
                statement.setString(2, updatedMenuItem.getIngredients());
                statement.setString(3, updatedMenuItem.getType());
                statement.setInt(4, id);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
        System.out.println("Erro na execução do SQL: " + e.getMessage());
    }
}
