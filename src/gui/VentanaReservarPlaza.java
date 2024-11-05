package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.Plaza;

public class VentanaReservarPlaza extends JFrame{
	private JPanel pCentro, pNorte, pTabla, pIzq;
	private JLabel lblReservarPlaza;
	
	private ModeloTablaReservarPlaza modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	
	
	public VentanaReservarPlaza() {
		super();
		
		setBounds(300,200,600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Paneles
		pCentro = new JPanel(new GridLayout(2,1));
		pNorte = new JPanel();
		pIzq = new JPanel();
		pTabla = new JPanel();
		
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pIzq, BorderLayout.WEST);
		
		//Label 
		lblReservarPlaza = new JLabel("Reservar Plaza");
		lblReservarPlaza.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		pNorte.add(lblReservarPlaza);
		
		//ComboBox
		JPanel pComboBox = new JPanel();
		JLabel Parkings = new JLabel("Parkings: ");
		String[] parkings = {"-2", "-1", "0"};
		JComboBox<String> comboParking = new JComboBox<String>(parkings);
		pComboBox.add(Parkings);
		pComboBox.add(comboParking);
		pIzq.add(pComboBox);
		
		//Creación de la lista
		List<Plaza> lPlazas = Arrays.asList(
				new Plaza(1, "A", 1, true),   
	            new Plaza(1, "A", 2, false),
	            new Plaza(1, "B", 3, true),   
	            new Plaza(1, "B", 4, false) 
		);
		
		
		
		modeloTabla = new ModeloTablaReservarPlaza(lPlazas);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		tabla.setDefaultRenderer(Object.class, new RendererTablaReservarPlaza());
		pCentro.add(scrollTabla);
		
		setVisible(true);
		
		
		}
	
	public static void main(String[] args) {
			VentanaReservarPlaza v = new VentanaReservarPlaza();
		}

}