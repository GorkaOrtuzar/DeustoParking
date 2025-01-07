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
			sql = "CREATE TABLE IF NOT EXISTS Reserva(Ciudad String, dni String, matricula String,nombreParking String, hllegada String, hsalida String, numeroPlaza int, seccion String, precioTotal float)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Parking(nomParking String,precioHora float, plazasLibres int)";
			stmt.executeUpdate(sql);
			//crear TablaPlazas
			sql = "CREATE TABLE IF NOT EXISTS Plaza(pisoPlaza String, secPlaza String, numPlaza int, ocupado int, minusvalido int)";
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
	
	//Actualizar datos del usuario
	public static void updateUsuario(Connection con, Usuario usuario) {
	    String sql = "UPDATE Usuario SET nombre = ?, apellido = ?, tlf = ?, contrasenia = ? WHERE dni = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, usuario.getNombre());
	        ps.setString(2, usuario.getApellido());
	        ps.setString(3, usuario.getTlf());
	        ps.setString(4, usuario.getDni());
	        ps.setString(5, usuario.getContrasenia());
	        ps.executeUpdate();
	        System.out.println("Usuario actualizado correctamente");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	//METODOS PARKING
	//obtenerParking
	
	public static List<Parking> obtenerListaParking(Connection con){
		String sql = "SELECT * FROM Parking";
		List<Parking> lp = new ArrayList<Parking>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				//nomParking String,precioHora float, plazasLibres int
				String nomParking = rs.getString("nomParking");
				float precioHora = rs.getFloat("precioHora");
				int plazasLibres = rs.getInt("plazasLibres");
				Parking parking = new Parking(nomParking, precioHora, plazasLibres);
				lp.add(parking);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lp;
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
				String seccion = rs.getString("seccion");
				String precioTotal = rs.getString("precioTotal");
				reserva = new Reserva(ciudad, DNI, matricula,nombreParking, llegada, salida, Integer.parseInt(numeroPlaza), seccion, Float.parseFloat(precioTotal));
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
			String sql = String.format("INSERT INTO Reserva VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s')", 
					reserva.getCiudad(), reserva.getDni(),reserva.getMatricula(), reserva.getNomParking(), Utilidades.dateToString(reserva.gethLlegada()),Utilidades.dateToString(reserva.gethSalida()), reserva.getNumPlaza(),reserva.getSeccion(), reserva.getPrecioTotal());
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
				String seccion = rs.getString("seccion");
				String precioTotal = rs.getString("precioTotal");
				Reserva reserva = new Reserva(ciudad, DNI, matricula,nombreParking, llegada, salida, Integer.parseInt(numeroPlaza), seccion, Float.parseFloat(precioTotal));
				l.add(reserva);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;	
	}
	
	// obtener lista reservas por dni
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
				String seccion = rs.getString("seccion");
				String precioTotal = rs.getString("precioTotal");
				Reserva reserva = new Reserva(ciudad, DNI, matricula,nombreParking, llegada, salida, Integer.parseInt(numeroPlaza), seccion, Float.parseFloat(precioTotal));
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
		String sql = String.format("SELECT * FROM Parking WHERE nomParking = '%s'", nparking);
		Parking parking= null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			if(rs.next()) {
				String nomParking = rs.getString("nomParking");
				String precioHora = rs.getString("precioHora");
				String plazasLibres = rs.getString("plazasLibres");
				parking = new Parking(nomParking, Float.parseFloat(precioHora), Integer.parseInt(plazasLibres));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parking;	
	}
	
	//MÉTODO PLAZAS
	// obtener lista Plazas
	public static List<Plaza> obtenerListaPlaza(Connection con){
		String sql = "SELECT * FROM Plaza";
		List<Plaza> lp = new ArrayList<>();
			
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				//pisoPlaza String, secPlaza String, numPlaza int, ocupado boolean, minusvalido boolean
				String pisoPlaza = rs.getString("pisoPlaza");
				String secPlaza = rs.getString("secPlaza");
				int numPlaza = rs.getInt("numPlaza");
				
				int ocupadoInt = rs.getInt("ocupado");
				boolean ocupado = (ocupadoInt == 1);
				
				int minusvalidoInt = rs.getInt("minusvalido");
				boolean minusvalido = (minusvalidoInt == 1);
				
				Plaza plaza = new Plaza(pisoPlaza, secPlaza, numPlaza, ocupado, minusvalido);
				lp.add(plaza);
			}
			rs.close();
			st.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return lp;
	}
	
	//UPDATE PLAZA
	public static void actualizarEstadoPlaza(Connection con, String pisoPlaza, String seccion, int numPlaza, boolean ocupada, boolean min) {
	    String q = "UPDATE Plaza SET ocupado = ? WHERE secPlaza = ? AND numPlaza = ?";
	    
	    try (PreparedStatement ps = con.prepareStatement(q)) {
	    	ps.setBoolean(1, ocupada); 
	        ps.setString(2, seccion);
	        ps.setInt(3, numPlaza);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
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
