package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import domain.Plaza;

public class ModeloTablaReservarPlaza extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	private List<Plaza> listaPlazas;
	private List<String> lTitulos; 
	
	public ModeloTablaReservarPlaza() {
		this.lTitulos = Arrays.asList(" ", "A", "B", "C", "D", "E", "F", "G", "H");
		this.listaPlazas = new ArrayList<>();
        super.setColumnIdentifiers(lTitulos.toArray());
		
	}

	
	@Override
	public int getRowCount() {
			return 9;
	}
	
  @Override
  public int getColumnCount() {
      return 9;
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
	        if (column == 0) {
	        	switch (row) {
	        	
	            	case 0: return 1;
	            	case 2: return 2;
	            	case 3: return 3;
	            	case 5: return 4;
	            	case 6: return 5;
	            	case 8: return 6;
	            	default: return "";
	            }
	        }

	        if (column > 0) {
	            String seccion = lTitulos.get(column);
	            
	            for(Plaza p: listaPlazas) {
	            	if(p.getSeccion().equals(seccion) && p.getNumPlaza()== (row+1)) {
	            		return p;
	            	}
	            }
	        }

	        return null;  
	    }
	 
	 public void actualizarDatos(List<Plaza> plazasFiltradas) {
		 this.listaPlazas.clear();
		 this.listaPlazas.addAll(plazasFiltradas);
		 fireTableDataChanged(); // Sacado de Proyectos del Año pasado --> Actualiza la tabla
	 }
		
	}