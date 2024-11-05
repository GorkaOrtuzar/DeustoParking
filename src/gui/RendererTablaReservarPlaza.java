package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import domain.Plaza;

public class RendererTablaReservarPlaza implements TableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		ModeloTablaReservarPlaza modelo =  (ModeloTablaReservarPlaza) table.getModel();
		Plaza p = modelo.getlPlazas().get(row);
		
		if (column %2 !=0) {
			label.setBackground(Color.gray);
		}else {
			if(p.isOcupada()) {
				label.setBackground(Color.RED);
			}else {
				label.setBackground(Color.GREEN);
			}
		}
		
		return label;
	}
	

}
