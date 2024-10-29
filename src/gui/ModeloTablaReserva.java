package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Parking;
import domain.Reserva;

public class ModeloTablaReserva extends DefaultTableModel {
	private List<Parking> lParkings;
	private List<String> lTitulos = Arrays.asList("Nombre Parking" ,"Plazas Disponibles" ,"Precio");

	public ModeloTablaReserva(List<Parking> lParkings) {
		this.lParkings = lParkings;
		
	}

	@Override
	public int getRowCount() {
		if(lParkings == null)
			return 0;
		return lParkings.size();
	}

	@Override
	public int getColumnCount() {
		return lTitulos.size();
	}

	@Override
	public String getColumnName(int column) {
		return lTitulos.get(column);
	}

	@Override
	public Object getValueAt(int row, int column) {
		Parking p = lParkings.get(row);
		
		switch (column) {
			case 0: return p.getParking();
			case 1: return p.getPlazasLibres();
			case 2: return p.getPrecioHora();
			default: return null;
		}
	}

}
