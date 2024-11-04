package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RendererTablaReserva implements TableCellRenderer {
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		table.setRowHeight(32);
		JLabel l = new JLabel();
		l.setText(value.toString());
		l.setHorizontalAlignment(JLabel.CENTER);
		
		String numplazasLibres = table.getValueAt(row, 1).toString();
		int numplazasLibresInt = Integer.parseInt(numplazasLibres);
		
		if(column==1) {
			if(numplazasLibresInt <= 20) {
				l.setHorizontalAlignment(JLabel.CENTER);
	            l.setOpaque(true);
	            l.setBackground(new Color(251, 53, 29));
	            l.setForeground(Color.BLACK);
			} else if (numplazasLibresInt > 20 && numplazasLibresInt <= 50){
				l.setHorizontalAlignment(JLabel.CENTER);
	            l.setOpaque(true);
	            l.setBackground(new Color(248,190,56));
	            l.setForeground(Color.BLACK);
			} else if (numplazasLibresInt > 50 && numplazasLibresInt <= 100) {
				l.setHorizontalAlignment(JLabel.CENTER);
	            l.setOpaque(true);
	            l.setBackground(new Color(250,238,88));
	            l.setForeground(Color.BLACK);
			} else if (numplazasLibresInt > 100) {
				l.setHorizontalAlignment(JLabel.CENTER);
	            l.setOpaque(true);
	            l.setBackground(new Color(27, 221, 11));
	            l.setForeground(Color.BLACK);
				
			}
		}
		
		return l;
		

	}

}
