package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	private String driver ="org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost:5432/Menu";
	private String user = "postgres";
	private String password = "merg01";
	
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e + " O banco de dados estar conectado");
			return null;
		}
	}
	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
