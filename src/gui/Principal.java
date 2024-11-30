package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import domain.BD;
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
