package Ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import domain.Reserva;

public class ModeloTablaInicio extends DefaultTableModel  {
	private List<String> lTitulos = Arrays.asList("Ciudad","fecha de entrada","fecha de salida","");

	public ModeloTablaInicio(Object object) {
	
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return super.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return lTitulos.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return lTitulos.get(column);
	}

	//terminarlo cunado jacqueline suba la clase reserva
	@Override
	public Object getValueAt(int row, int column) {
		/*Reserva r = reserva.get(row);
		 * swich(column){
		 * case 0: return r.getCiudad();
		 * case 1: return r.getFechaEntrada();
		 * case 2: return r.getFechaSalida();
		 * default return null;
		 */
		return super.getValueAt(row, column);
	}
	
	
}