package gui;


import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Reserva;
import domain.Utilidades;

public class ModeloTablaMisReservas extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Reserva> lMisReservas;
	private List<String> lTitulos = Arrays.asList("FECHA DE ENTRADA","FECHA DE SALIDA","NOMBRE PARKING", "PRECIO");
	
	public ModeloTablaMisReservas(List<Reserva> lMisReservas) {
		super();
		this.lMisReservas = lMisReservas;
	}

	public List<Reserva> getlReservas() {
		return lMisReservas;
	}

	public void setlReservas(List<Reserva> lMisReservas) {
		this.lMisReservas = lMisReservas;
	}

	@Override
	public int getRowCount() {
		if(lMisReservas == null)
			return 0;
		return lMisReservas.size();
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
		Reserva r = lMisReservas.get(row);
		
		switch (column) {
		case 0:  return Utilidades.dateToString(r.gethLlegada());
		case 1: return Utilidades.dateToString(r.gethSalida());
		case 2: return r.getNomParking();
		case 3: return r.getPrecioTotal();
		default:
			return null;
		}

	}
}