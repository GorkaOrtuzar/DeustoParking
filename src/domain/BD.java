package domain;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
	private static Connection con;
	
	public static void initBD(String nombreBD) {
		con= null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeBD() {
		if(con!= null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	public static void crearTablas() {
//		String sql = "CREATE TABLE IF NOT EXISTS Usuario(nombre String, apellido String,tlf String,dni String,contrasenia String)";
//		try {
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate(sql);
//			sql = "CREATE TABLE IF NOT EXISTS Reserva";
//			stmt.executeUpdate(sql);
//			sql = "CREATE TABLE IF NOT EXISTS Parking";
//			stmt.executeUpdate(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
