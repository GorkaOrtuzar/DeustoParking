package gui;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import domain.Plaza;

public class RendererTablaReservarPlaza extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		JLabel l = new JLabel();
//		l.setOpaque(true);
		l.setHorizontalAlignment(JLabel.CENTER);
		
		if(column!=0 && (row==1 || row==4 || row==7)) {
	    	imagen("imagenes/carretera.jpg", l, 210, 190);
	    }
		if((column ==1 || column==2 || column==7 || column == 8)&&(row==8)){
	    	imagen("imagenes/azulArriba.png", l, 180, 180);
	    }
		if (value == null) {
	        l.setText("");
	    } else if (value instanceof Plaza) {
	        Plaza plaza = (Plaza) value;
	        if (plaza.isOcupada()) {
	        	if(row==0 || row==3 || row==6) {
	        		imagen("imagenes/rojoAbajo.png", l, 180, 180);
	        	}else if(row==2 || row==5) {
	        		imagen("imagenes/rojoArriba.png", l, 180, 180);
	        	}
	        } else {
	        	if(row==0 || row ==3 || row==6) {
	        		imagen("imagenes/verdeAbajo.png", l, 180, 180);
	        	}else if(row==2 || row==5) {
	        		imagen("imagenes/verdeArriba.png", l, 180, 180);
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