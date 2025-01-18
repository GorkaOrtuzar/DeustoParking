package gui;

import java.util.Arrays;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Parking;

public class ModeloTablaReservaParking extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Parking> lParkings;
	private List<String> lTitulos = Arrays.asList("Nombre Parking" ,"Plazas Disponibles" ,"Precio");

	public ModeloTablaReservaParking(List<Parking> lParkings) {
		this.lParkings = lParkings;
		
	}
	

	public List<Parking> getlParkings() {
		return lParkings;
	}



	public void setlParkings(List<Parking> lParkings) {
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
	public boolean isCellEditable(int row, int column) {
		return false;
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
