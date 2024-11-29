package gui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import domain.Plaza;

public class ModeloTablaReservarPlaza extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	private Map<String, Plaza> mPlazas;
	private List<String> lTitulos; 
	
	public ModeloTablaReservarPlaza(List<Plaza> lPlazas) {
		this.lTitulos = Arrays.asList(" ", "A", "B", "C", "D", "E", "F", "G", "H");
		this.mPlazas = new HashMap<>();
		
		for (Plaza plaza : lPlazas) {
            String clave = plaza.getSeccion() + "-" + plaza.getNumPlaza();
            mPlazas.put(clave, plaza);
        }

        super.setColumnIdentifiers(lTitulos.toArray());
		
	}

	
	@Override
	public int getRowCount() {
			return 9;
	}
	
    //ERROR
//  @Override
//  public int getColumnCount() {
//      return lTitulos.size(); // Número de columnas
//  }
	
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
	            if (row == 0) {
	                return 1;
	            }
	            else if (row == 1) {
	                return "";  
	            }
	            else if (row == 2) {
	                return 2;
	            }
	            else if (row == 3) {
	                return 3;
	            }
	            else if(row==4) {
	            	return "";
	            }
	            else if(row==5) {
	            	return 4;
	            }
	            else if(row==6) {
	            	return 5;
	            }
	            else if(row==7) {
	            	return "";
	            }
	            else if(row==8) {
	            	return 6;
	            }
	        }

	        if (column != 0) {
	            String seccion = lTitulos.get(column);
	            int numPlaza = row + 1; 
	            String clave = seccion + "-" + numPlaza; 
	            return mPlazas.getOrDefault(clave, null); 
	        }

	        return null;  
	    }
		
	}