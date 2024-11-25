package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import domain.Plaza;

public class ModeloTablaReservarPlaza extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
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
		return lPlazas.size()/4;
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
		if(column % 2 != 0) {
			return "";
		}else {
		
			Plaza p = lPlazas.get(row*4+column/2);
			return p;
		}
		
	}

}
