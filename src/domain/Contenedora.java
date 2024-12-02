package domain;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;


public class Contenedora {

	private static List<Usuario> lUsuarios;
	private static List<Reserva> lReservas;
	
	public static List<Usuario> getlUsuarios() {
		return lUsuarios;
	}
	
	public static List<Reserva> getlReservas() {
		return lReservas;
	}

	public static void setlReservas(List<Reserva> lReservas) {
		Contenedora.lReservas = lReservas;
	}

	public static void aniadirUsuario(Usuario u) {
		lUsuarios.add(u);
	}
	
	public static void aniadirReserva(Reserva r) {
		lReservas.add(r);
	}
	
	//Guardar UsuariosEnCSV
	public static void guardarUsuarioEnCSV(String nomfich) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomfich)));
			oos.writeObject(lUsuarios);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void cargarUsuarioEnLista(String nomFich) {
		try {
			Scanner sc = new Scanner(new FileReader(nomFich));
			String linea = sc.next();
			while(sc.hasNext()) {
				linea = sc.nextLine();
				String[] partes = linea.split(";");
				String Nombre = partes[0];
				String Apellido = partes[1];
				String tlf= partes[2];
				String dni = partes[3];
				String contrasenia = partes[4];
				Usuario u = new Usuario(Nombre, Apellido, tlf, dni, contrasenia);
				if (buscarUsuario(dni) == null) {
					lUsuarios.add(u);
				}
			}
			sc.close();
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 //Metodo que vuelca todos los Usuarios del fichero a la base de datos
	public static void volcado_FichCSV_Usuarios_a_BD(Connection con, String nomFich) {
	try {
		Scanner sc = new Scanner(new FileReader(nomFich));
		String linea;
		while (sc.hasNext()) {
			linea = sc.nextLine();
			String[] partes = linea.split(";");
			String Nombre = partes[0];
			String Apellido = partes[1];
			String tlf= partes[2];
			String dni = partes[3];
			String contrasenia = partes[4];
			Usuario u = new Usuario(Nombre, Apellido, tlf, dni, contrasenia);
			BD.insertarUsuario(con, u);
		}
		sc.close();
	}catch (FileNotFoundException e) {
		e.printStackTrace();
		}

	}

	
	//Buscar usuario
	public static Usuario buscarUsuario(String DNI) {
		boolean enc = false;
		int pos = 0;
		Usuario u = null;
		while (!enc && pos < lUsuarios.size()) {
			u = lUsuarios.get(pos);
			if (u.getDni().equals(DNI)) {
				enc = true;
			} else {
				pos++;
			}
		}
		if (enc) {
			return u;
		} else {
			return null;
		}
	}
	
}