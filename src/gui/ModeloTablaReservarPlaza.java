package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import domain.Parking;
import domain.Plaza;

public class ModeloTablaReservarPlaza extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	private List<Plaza> listaPlazas;
	private List<String> lTitulos; 
	private Parking parking;
	
	public ModeloTablaReservarPlaza(Parking p) {
		this.lTitulos = Arrays.asList(" ", "A", "B", "C", "D", "E", "F", "G", "H");
		this.listaPlazas = new ArrayList<>();
        super.setColumnIdentifiers(lTitulos.toArray());
		this.parking = p;
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
		 if (parking.getParking().equals("ParkingVIP")) {
		        	if (column == 0) {
			        	switch (row) {
		            	case 0: return 1;
		            	case 2: return 2;
		            	case 4: return 3;
		            	case 6: return 4;
		            	case 8: return 5;
		            	default: return "";
		            }
		        }
		            
		 } else if (parking.getParking().equals("ParkingCentral") || parking.getParking().equals("ParkingTechado")) {
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
	        }

	        if (column > 0) {
	        	if ((parking.equals("ParkingVIP") && (row == 1 || row == 3 || row == 5 || row == 7)) ||
	        		    ((parking.equals("ParkingCentral") || parking.equals("ParkingTechado")) &&
	        		    (row == 1 || row == 4 || row == 7))) {
	        		    return null; 
	        		}

	            String seccion = lTitulos.get(column);
	            for (Plaza p : listaPlazas) {
	                if (p.getSeccion().equals(seccion) && p.getNumPlaza() == (row + 1)) {
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
	 public void actualizarTablaPlaza() {
		 fireTableDataChanged(); // Sacado de Proyectos del Año pasado --> Actualiza la tabla
	 }
	 
	 public Plaza getPlazaRow(int row) {
		 return listaPlazas.get(row);
	 }
	 
	 public int getPlaza(int row) {
		    int i = 0;

		    if (parking.equals("ParkingVIP")) {
		        if (row > 1) i++;
		        if (row > 3) i++;
		        if (row > 5) i++;
		        if (row > 7) i++;
		    } else if (parking.equals("ParkingCentral") || parking.equals("ParkingTechado")) {
		        if (row > 1) i++;
		        if (row > 4) i++;
		        if (row > 7) i++;
		    }

		    return row - i; 
	}
		
	}