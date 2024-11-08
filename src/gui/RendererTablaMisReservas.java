
package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class RendererTablaMisReservas  implements TableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		JLabel l = new JLabel();
		l.setOpaque(true);
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setForeground(Color.BLACK);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();

		
		if (row %2 !=0) {
			l.setBackground(Color.LIGHT_GRAY);
		}else {
			l.setBackground(table.getTableHeader().getBackground());
		}
		
		
		return l;
	}

}
