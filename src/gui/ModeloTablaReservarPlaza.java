package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import domain.Plaza;

public class ModeloTablaReservarPlaza extends DefaultTableModel{
	private List<Plaza> lPlazas;
	private List<String> lTitulos = Arrays.asList("A", " ", "B", " ", "C", " ", "D");
	
	public ModeloTablaReservarPlaza(List<Plaza> lPlazas) {
		super();
		this.lPlazas = lPlazas;
		
	}

	public List<Plaza> getlPlazas() {
		return lPlazas;
	}

	public void setlPlazas(List<Plaza> lPlazas) {
		this.lPlazas = lPlazas;
	}
	
	@Override
	public int getRowCount() {
		if ( lPlazas == null) {
			return 0;
		}
		return lPlazas.size();
	}
	
	@Override
	public int getColumnCount() {
		return lTitulos.size();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		Plaza p = lPlazas.get(row);
		
		switch (column) {
			default: return null;
		}
	}
	
	
	
	
	

}
