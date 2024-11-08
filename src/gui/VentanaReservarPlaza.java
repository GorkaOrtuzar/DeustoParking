package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import domain.Plaza;
import domain.Reserva;

public class VentanaReservarPlaza extends JFrame{
	private JPanel pCentro, pNorte, pSur, pTabla, pIzq;
	private JLabel lblReservarPlaza;
	private JButton btnSiguiente;
	private JFrame vActual;
	private JFrame vAnterior;
	
	private ModeloTablaReservarPlaza modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	
	
	public VentanaReservarPlaza(JFrame va, Reserva r) {
		super();
		
		vActual = this;
		vAnterior = va;
		
		setBounds(300,200,600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Paneles
		pCentro = new JPanel(new GridLayout(2,1));
		pNorte = new JPanel();
		pSur = new JPanel();
		pIzq = new JPanel();
		pTabla = new JPanel();
		
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pIzq, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		//Label 
		lblReservarPlaza = new JLabel("Reservar Plaza");
		lblReservarPlaza.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		pNorte.add(lblReservarPlaza);
		
		//ComboBox
		JPanel pComboBox = new JPanel();
		JLabel Parkings = new JLabel("Plantas: ");
		String[] parkings = {"0", "-1", "-2", "-3"};
		JComboBox<String> comboParking = new JComboBox<String>(parkings);
		pComboBox.add(Parkings);
		pComboBox.add(comboParking);
		pIzq.add(pComboBox);
		
		//Creación botón
		btnSiguiente = new JButton("SIGUIENTE");
		pSur.add(btnSiguiente);
		
		btnSiguiente.addActionListener((e)->{
			vActual.dispose();
			new VentanaIngresarDatos(vActual, r);
			
			
		});
		
		
		//Creación de la lista
		List<Plaza> lPlazas = Arrays.asList(
				new Plaza(1, "A", 1, false),   
	            new Plaza(1, "A", 2, true),
	            new Plaza(1, "B", 3, false),   
	            new Plaza(1, "B", 4, false) 
		);
		
		
		//Creación tabla
		modeloTabla = new ModeloTablaReservarPlaza(lPlazas);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		tabla.setDefaultRenderer(Object.class, new RendererTablaReservarPlaza());
		pCentro.add(scrollTabla);
		
		//Quitar bordes a las celdas
		tabla.setIntercellSpacing(new Dimension(0, 0));  
		tabla.setBorder(null);
		
		//Quitar bordes a la cabecera
		tabla.getTableHeader().setBorder(null);
		
		//Espacio de las celdas
		tabla.setRowHeight(30);
	     

		setVisible(true);
		
		
		}
	

}