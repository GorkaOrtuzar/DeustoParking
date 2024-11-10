package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererCabeceraTabla extends DefaultTableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel l = new JLabel(value.toString());
		l.setOpaque(true);
		l.setBackground(new Color(186, 197, 202));
		l.setPreferredSize(new java.awt.Dimension(0, 50));;
		l.setHorizontalAlignment(JLabel.CENTER);
		return l;
	}
	

}
