package domain;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
	public static Usuario buscarUsario(Connection con, String DNI) {
		String sql = String.format("SELECT * FROM Usuario WHERE CorreoElectronico = '%s'", DNI);
		Usuario usuario= null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			if(rs.next()) {
				String nombre = rs.getString("Nombre");
				String apellido = rs.getString("Apellido");
				String tlf = rs.getString("Telefono");
				String dni = rs.getString("dni");
				String contrasenia = rs.getString("Contrasenia");
				usuario = new Usuario(nombre, apellido, tlf, dni, contrasenia);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public static void insertarUsuario(Connection con, Usuario usuario){
		if(buscarUsario(con,usuario.getDni())==null){
			String sql = String.format("INSERT INTO Usuario VALUES('%s','%s','%s','%s','%s','%s','%s')", 
					usuario.getNombre(), usuario.getApellido(), usuario.getTlf(), usuario.getDni(),usuario.getContrasenia());
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<Usuario> obtenerListaUsario(Connection con){
		String sql = "SELECT * FROM Usuario";
		List<Usuario> l = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String nombre = rs.getString("Nombre");
				String apellido = rs.getString("Apellido");
				String tlf = rs.getString("Telefono");
				String dni = rs.getString("dni");
				String contrasenia = rs.getString("Contrasenia");
				Usuario usuario = new Usuario(nombre, apellido, tlf, dni, contrasenia);
				l.add(usuario);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
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
