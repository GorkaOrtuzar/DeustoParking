package gui;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import domain.Parking;
import domain.Plaza;

public class RendererTablaReservarPlaza extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private Parking parking;
	
	public RendererTablaReservarPlaza(Parking parking) {
        this.parking = parking;
    }
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		JLabel l = new JLabel();
		l.setHorizontalAlignment(JLabel.CENTER);
		
		if (column != 0) {
	        if (parking.getParking().equals("ParkingVIP")) {
	            if (row == 1 || row == 3 || row == 5 || row == 7) {
	                imagen("resources/imagenes/carretera.jpg", l, 210, 190);
	                return l;
	            }
	        } else if (parking.getParking().equals("ParkingCentral") || parking.getParking().equals("ParkingTechado")) {
	            if (row == 1 || row == 4 || row == 7) {
	                imagen("resources/imagenes/carretera.jpg", l, 210, 190);
	                return l;
	            }
	        }
	    }
		

		if (value == null) {
	        l.setText("");
	    } else if (value instanceof Plaza) {
	        Plaza plaza = (Plaza) value;
	        
	        if(parking.getParking().equals("ParkingCentral") || parking.getParking().equals("ParkingTechado")) {
	        	if (plaza.isOcupada()) {
	        	if(row % 3 == 0) { // linea creada por IA Generativa - ChatGPT 
	        		imagen("resources/imagenes/rojoAbajo.png", l, 180, 180);
	        	} else if((row + 1) % 3 == 0) {
	        		imagen("resources/imagenes/rojoArriba.png", l, 180, 180);
	        	}
	        	
	        }else if(plaza.isMinusvalido()){
	        	if(row % 3 == 0) {
	        		imagen("resources/imagenes/azulAbajo.png", l, 180, 180);

	        	}else if((row + 1) % 3 == 0) {
	        		imagen("resources/imagenes/azulArriba.png", l, 180, 180);

	        	}
	        }else if(!plaza.isOcupada() && !plaza.isMinusvalido()) {
	        	if(row % 3 == 0 ) {
	        		imagen("resources/imagenes/verdeAbajo.png", l, 180, 180);

	        	}else if((row + 1) % 3 == 0) {
	        		imagen("resources/imagenes/verdeArriba.png", l, 180, 180);

	        	}
	        
	        }
	        	
	      } else if(parking.getParking().equals("ParkingVIP")) {
	    	  if (plaza.isOcupada()) {
  	            if (row % 2 == 0) { 
  	                imagen("resources/imagenes/rojoArriba.png", l, 180, 180);
  	            }
	    	  } else if (plaza.isMinusvalido()) {
  	            if (row % 2 == 0) { 
  	                imagen("resources/imagenes/azulArriba.png", l, 180, 180);
  	            }
	    	  } else if (!plaza.isOcupada() && !plaza.isMinusvalido()) {
  	            if (row % 2 == 0) { 
  	                imagen("resources/imagenes/verdeArriba.png", l, 180, 180);
  	            }
	    	  }
	      }
	        
	    } else {
	        l.setText(value.toString());
	    }
		return l;
	}	
	public void imagen(String ruta, JLabel l, int ancho, int alto) {
		ImageIcon im = new ImageIcon(ruta);
        Image imgEscalada = im.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        l.setIcon(new ImageIcon(imgEscalada));
	}

}