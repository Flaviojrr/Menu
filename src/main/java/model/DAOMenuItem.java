package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMenuItem {
	
	//Adjust database information accordingly
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/VillaEleganza";
    private String user = "postgres";
    private String password = "kerous";

    public DAOMenuItem() {
        try {
            // Load the JDBC driver
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Establish a database connection
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Test the database connection
    public void testConnection() {
        try (Connection con = connect()) {
            System.out.println("O banco de dados está conectado.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    // Insert a new menu item into the database
    public void insertMenuItem(MenuItem novoPrato) {
        try (Connection con = connect();
             PreparedStatement statement = con.prepareStatement("INSERT INTO menu (name, ingredients, type) VALUES (?, ?, ?)")) {

            // Set parameters for the SQL query
            statement.setString(1, novoPrato.getName());
            statement.setString(2, novoPrato.getIngredients());
            statement.setString(3, novoPrato.getType());

            // Execute the SQL query to insert the new menu item
            statement.executeUpdate();

            System.out.println("Novo prato inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve menu items from the database
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
            e.printStackTrace();
        }

        return menuItems;
    }

    // We are adding other methods for CRUD operations can be added here
}
