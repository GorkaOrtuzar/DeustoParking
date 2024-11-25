package domain;

import java.sql.Statement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
	private static Connection con;
	
	public static Connection initBD(String nombreBD) {
		con= null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
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
	
	public static void crearTablas(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Usuario(nombre String, apellido String,tlf String,dni String,contrasenia String)";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Reserva(Ciudad String, dni String, nombreParking String, hllegada Date, hsalida Date, numeroPlaza int, precioTotal float)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Parking(Parking String,precioHora float, plazasLibres int)";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarTabla(Connection con) throws SQLException{
		String sqlUsuario = "DROP TABLE IF EXISTS Usuario";
		String sqlReservas = "DROP TABLE IF EXISTS Reserva";
		String sqlParking = "DROP TABLE IF EXISTS Parking";
		
		try {
			java.sql.Statement stmt = con.createStatement();
			stmt.executeUpdate(sqlUsuario);
			stmt.executeUpdate(sqlReservas);
			stmt.executeUpdate(sqlParking);
			stmt.close();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void cerrarBD(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
}
