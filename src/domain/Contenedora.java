package domain;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Contenedora {

	private static List<Usuario> lUsuarios;
	private static List<Reserva> lReservas;
	private static HashMap<Usuario, ArrayList<Reserva>>mapaReservasPorUsuario;
	
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
	
	//Añadir una reserva a un usuario
	public static void aniadirReservaUsuario(Usuario u, Reserva r) {
		if(!mapaReservasPorUsuario.containsKey(u)) {
			mapaReservasPorUsuario.put(u, new ArrayList<Reserva>());
		}
		mapaReservasPorUsuario.get(u).add(r);
	}
	//Devuelve la lista de reservas que tiene hechas un usuario 
	public static ArrayList<Reserva> buscarListaDeReservasPorUsuario(String DNI, HashMap<Usuario, ArrayList<Reserva>>mapaReservasPorUsuario) {
		ArrayList<Reserva> lReservaUS = new ArrayList<Reserva>();
		for (Usuario u : mapaReservasPorUsuario.keySet()) {
			if(u.getDni().equals(DNI)) {
				lReservaUS.add((Reserva) mapaReservasPorUsuario.values());
			}
		}
		return lReservaUS;
	}
	
	public static Usuario buscarUsuario(String dni) {
		boolean enc = false;
		int pos = 0;
		Usuario u = null;
		while(!enc && pos < lUsuarios.size()) {
			u = lUsuarios.get(pos);
			if(u.getDni().equals(dni)) {
				enc = true;
			}else {
				pos++;
			}
		}
		if(enc) {
			return u;
		}else {
			return null;
		}
	}
		
}
