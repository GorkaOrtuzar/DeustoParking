package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import domain.BD;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String nombreBD;
		nombreBD = "db/deustoParking.db";
		VentanaCargando va = new VentanaCargando();
		Connection con = BD.initBD(nombreBD);
		try {
			BD.borrarTabla(con);
			BD.crearTablas(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BD.cerrarBD(con);
		
	}

}
