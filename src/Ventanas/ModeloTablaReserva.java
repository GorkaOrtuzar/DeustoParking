package Ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Reserva;

public class ModeloTablaReserva extends DefaultTableModel {
	private List<Reserva> lReservas;
	private List<String> lTitulos = Arrays.asList("Nombre Parking" ,"Plazas Disponibles" ,"Precio");

	public ModeloTablaReserva(List<Reserva> lReservas) {
		this.lReservas = lReservas;
		
	}

	@Override
	public int getRowCount() {
		if(lReservas == null)
			return 0;
		return lReservas.size();
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
		Reserva r = lReservas.get(row);
		
		switch (column) {
//			case 0: return r
//			case 1:
//			case 2:
			default: return null;
		}
	}

}
