package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import domain.BD;
import domain.Usuario;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String nombreBD;
		nombreBD = "db/deustoParking.db";
		VentanaCargando va = new VentanaCargando();
		Connection con = BD.initBD(nombreBD);
		try {
			BD.borrarTabla(con);
			//Usuario u1 = new Usuario("Gorka","Ortuzar","123456789","12345678A","a1");
			//Usuario u2 = new Usuario("Ainara","Maroto","987654321","87654321B","b2");
			//BD.insertarUsuario(con,u1);
			//BD.insertarUsuario(con,u2);
			BD.crearTablas(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BD.cerrarBD(con);
		
	}

}
