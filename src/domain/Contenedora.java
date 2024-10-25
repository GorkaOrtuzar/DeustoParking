package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	public static void aniadirReservaUsuario(Usuario u, Reserva r) {
		if(!mapaReservasPorUsuario.containsKey(u)) {
			mapaReservasPorUsuario.put(u, new ArrayList<Reserva>());
		}
		mapaReservasPorUsuario.get(u).add(r);
	}
	
	
	
}
