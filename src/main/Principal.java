package main;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import db.BD;
import domain.Parking;
import domain.Plaza;
import domain.Reserva;
import domain.Usuario;
import gui.VentanaCargando;

public class Principal {

	public static Connection con;
	private static Thread t;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String nombreBD;
		nombreBD = "resources/db/deustoParking.db";
		@SuppressWarnings("unused")
		VentanaCargando va = new VentanaCargando();
		con = BD.initBD(nombreBD);
		
		try {
			BD.borrarTabla(con);
			BD.crearTablas(con);
			Usuario u1 = new Usuario("Gorka","Ortuzar","123456789","12345678A","a1");
			Usuario u2 = new Usuario("Ainara","Maroto","987654321","87654321B","b2");
			BD.insertarUsuario(con,u1);
			BD.insertarUsuario(con,u2);
			
			Reserva r1 = new Reserva("Ciudad2","12345678A","1234ABC","parkingCentral","11/10/2023","17/10/2023",4, "G",30);
			Reserva r2 = new Reserva("Ciudad3","12345678A","1234ABC","parkingVIP","11/01/2023","21/01/2023",16,"A",60);
			Reserva r3 = new Reserva("Ciudad2","87654321B","2468BBB","parkingVIP","11/01/2023","21/01/2023",16,"C",60);
			Reserva r4 = new Reserva("Ciudad3","12345678A","1234ABC","parkingVIP","11/01/2023","21/01/2023",16,"D",60);

			BD.insertarReserva(con, r1);
			BD.insertarReserva(con, r2);
			BD.insertarReserva(con, r3);
			BD.insertarReserva(con, r4);
			
			BD.insertarParking(con, new Parking("ParkingVIP", 30f, 43));
			BD.insertarParking(con, new Parking("ParkingCentral", 25.5f, 64));
			BD.insertarParking(con, new Parking("ParkingTechado", 17.90f, 93));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	@SuppressWarnings("unused")
	private static void hiloSumarNumPlazas() {
		try {
			t = new Thread(()->{
				long fechaHoy = System.currentTimeMillis();

				for(Reserva r : BD.obtenerListaReservas(con)){
					for(Plaza p : BD.obtenerListaPlaza(con)) {
							if(r.getNumPlaza()==p.getNumPlaza() &&  r.getSeccion().equals(p.getSeccion())) {
								if(p.isOcupada()) {
									if(r.gethSalida().getTime()<fechaHoy) {
										p.setOcupada(false);
										BD.sumarPlazasLibresParking(con, r.getNomParking());
									}
								}
						}
					}
				}
			
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		t.start();
	}
}