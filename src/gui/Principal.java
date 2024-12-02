package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import domain.BD;
import domain.Plaza;
import domain.Reserva;
import domain.Usuario;

public class Principal {

	static Connection con;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String nombreBD;
		nombreBD = "db/deustoParking.db";
		VentanaCargando va = new VentanaCargando();
		con = BD.initBD(nombreBD);
		
		try {
			BD.borrarTabla(con);
			BD.crearTablas(con);
			Usuario u1 = new Usuario("Gorka","Ortuzar","123456789","12345678A","a1");
			Usuario u2 = new Usuario("Ainara","Maroto","987654321","87654321B","b2");
			BD.insertarUsuario(con,u1);
			BD.insertarUsuario(con,u2);
			
			Reserva r1 = new Reserva("Ciudad2","12345678A","1234ABC","parking1","11/10/2023","17/10/2023",4,30);
			Reserva r2 = new Reserva("Ciudad3","12345678A","1234ABC","parkingVIP","11/1/2023","21/1/2023",16,60);
			Reserva r3 = new Reserva("Ciudad2","87654321B","2468BBB","parkingVIP","11/1/2023","21/1/2023",16,60);
			Reserva r4 = new Reserva("Ciudad3","12345678A","1234ABC","parkingVIP","11/1/2023","21/1/2023",16,60);

			BD.insertarReserva(con, r1);
			BD.insertarReserva(con, r2);
			BD.insertarReserva(con, r3);
			BD.insertarReserva(con, r4);
			
			for (Plaza p : BD.obtenerListaPlaza(con)) {
				System.out.println(p);
				
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
