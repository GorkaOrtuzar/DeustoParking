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