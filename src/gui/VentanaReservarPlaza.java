package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaReservarPlaza extends JFrame{
	private JPanel pCentro, pNorte, pTabla, pIzq;
	private JLabel lblReservarPlaza;
	
	private DefaultTableModel modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	
	
	public VentanaReservarPlaza() {
		super();
		
		setBounds(300,200,600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pCentro = new JPanel(new GridLayout(2,1));
		pNorte = new JPanel();
		pIzq = new JPanel();
		pTabla = new JPanel();
		
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pIzq, BorderLayout.WEST);
		
		lblReservarPlaza = new JLabel("Reservar Plaza");
		lblReservarPlaza.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		pNorte.add(lblReservarPlaza);
		
		JPanel pComboBox = new JPanel();
		JLabel Parkings = new JLabel("Parkings: ");
		String[] parkings = {"-2", "-1", "0"};
		JComboBox<String> comboParking = new JComboBox<String>(parkings);
		pComboBox.add(Parkings);
		pComboBox.add(comboParking);
		pIzq.add(pComboBox);
		
		
		
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		String [] titulos = {"A", "B", "C", "D", "E", "F", "G"};
		modeloTabla.setColumnIdentifiers(titulos);
		
		pCentro.add(scrollTabla);
		
	setVisible(true);
		
		
		}
	
	public static void main(String[] args) {
			VentanaReservarPlaza v = new VentanaReservarPlaza();
		}

}