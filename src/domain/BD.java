package domain;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BD {
	
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
			System.out.println("Nombre: "+nombreBD);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeBD(Connection con) {
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
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sqlUsuario);
			stmt.executeUpdate(sqlReservas);
			stmt.executeUpdate(sqlParking);
			stmt.close();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	//Metodos usuario
	//buscar usuario
	public static Usuario buscarUsario(Connection con, String DNI) {
		String sql = String.format("SELECT * FROM Usuario WHERE dni = '%s'", DNI);
		Usuario usuario= null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			if(rs.next()) {
				String nombre = rs.getString("Nombre");
				String apellido = rs.getString("Apellido");
				String tlf = rs.getString("tlf");
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
	
	// Insertar Usuario
	public static void insertarUsuario(Connection con, Usuario usuario){
		if(buscarUsario(con,usuario.getDni())==null){
			String sql = "INSERT INTO Usuario (nombre, apellido, tlf, dni, contrasenia) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
			    ps.setString(1, usuario.getNombre());
			    ps.setString(2, usuario.getApellido());
			    ps.setString(3, usuario.getTlf());
			    ps.setString(4, usuario.getDni());
			    ps.setString(5, usuario.getContrasenia());
			    ps.executeUpdate();
			    System.out.println("Usuario insertado correctamente");
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
		}
	// Obtener lista de Usuarios
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
	
	//Metodos reservar
	// buscarReserva
	public static Reserva buscarReserva(Connection con, String dni) {
		String sql = String.format("SELECT * FROM Reserva WHERE dni = '%s'", dni);
		Reserva reserva= null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			if(rs.next()) {
				String ciudad = rs.getString("Ciudad");
				String DNI = rs.getString("dni");
				String matricula = rs.getString("matricula");
				String nombreParking = rs.getString("nombreParking");
				String llegada = rs.getString("hllegada");
				String salida = rs.getString("hsalida");
				String numeroPlaza = rs.getString("numeroPlaza");
				String precioTotal = rs.getString("precioTotal");
				reserva = new Reserva( ciudad, DNI, matricula,nombreParking, llegada, salida, Integer.parseInt(numeroPlaza), Float.parseFloat(precioTotal));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reserva;	
	}
	
	//Insertar Reserva
	public static void insertarReserva(Connection con, Reserva reserva){
			String sql = String.format("INSERT INTO Reserva VALUES('%s','%s','%s','%s','%s','%s','%s','%s')", 
					reserva.getCiudad(), reserva.getDni(),reserva.getMatricula(), reserva.getNomParking(), reserva.gethLlegada(),reserva.gethSalida(), reserva.getNumPlaza(),reserva.getPrecioTotal());
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	//Obtener lista reservas
	public static List<Reserva> obtenerListaReservas(Connection con){
		String sql = "SELECT * FROM Reserva";
		List<Reserva> l = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String ciudad = rs.getString("Ciudad");
				String DNI = rs.getString("dni");
				String matricula = rs.getString("matricula");
				String nombreParking = rs.getString("nombreParking");
				String llegada = rs.getString("hllegada");
				String salida = rs.getString("hsalida");
				String numeroPlaza = rs.getString("numeroPlaza");
				String precioTotal = rs.getString("precioTotal");
				Reserva reserva = new Reserva(ciudad, DNI, matricula,nombreParking, llegada, salida, Integer.parseInt(numeroPlaza), Float.parseFloat(precioTotal));
				l.add(reserva);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;	
	}
	
	// obtner lista reservas por dni
	public static List<Reserva> obtenerListaReservasPorDNI(Connection con, String dni){
		String sql = String.format("SELECT * FROM Reserva WHERE dni = '%s'", dni);
		List<Reserva> l = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String ciudad = rs.getString("Ciudad");
				String DNI = rs.getString("dni");
				String matricula = rs.getString("matricula");
				String nombreParking = rs.getString("nombreParking");
				String llegada = rs.getString("hllegada");
				String salida = rs.getString("hsalida");
				String numeroPlaza = rs.getString("numeroPlaza");
				String precioTotal = rs.getString("precioTotal");
				Reserva reserva = new Reserva(ciudad, DNI, matricula,nombreParking, llegada, salida, Integer.parseInt(numeroPlaza), Float.parseFloat(precioTotal));
				l.add(reserva);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;	
	}
	public static void insertarParking(Connection con, Parking parking) {
		if(buscarParking(con,parking.getParking())==null) {
			String sql = String.format("INSERT INTO Parking Values('%s','%s','%s')", parking.getParking(),parking.getPrecioHora(),parking.getPlazasLibres());
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Parking buscarParking(Connection con, String nparking) {
		String sql = String.format("SELECT * FROM Reserva WHERE parking = '%s'", nparking);
		Parking parking= null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			if(rs.next()) {
				String Parking = rs.getString("parking");
				String precioHora = rs.getString("precioHora");
				String plazasLibres = rs.getString("plazasLibres");
				parking = new Parking(Parking, Float.parseFloat(precioHora), Integer.parseInt(plazasLibres));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parking;	
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
