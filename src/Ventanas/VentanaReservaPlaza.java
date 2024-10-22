package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class VentanaReservaPlaza extends JFrame{
	
	//Declaramos valores
	private JPanel pCentro, pCentroN, pCentroS, pSur, pNorte;
	private JButton btnAtras, btnReservar, btnSiguiente;
	private JTextArea txtReserva;
	
	//Declaramos tabla
	private ModeloTablaReserva modeloTabla; 
	private JTable tabla; 
	private JScrollPane scrollTabla;
	
	
	
	public VentanaReservaPlaza() {
		super();
		
		//Tamaño Ventana
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Instanciar Paneles, TextArea y Botones
		pNorte = new JPanel();
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(2, 1)); //2 filas, 1 columna
		pSur = new JPanel();
		//pSur.setLayout(new GridLayout(1, 2));
		
		pCentroN = new JPanel();
		pCentroS = new JPanel();
		
		txtReserva = new JTextArea();
		
		btnAtras = new JButton("Atrás");
		btnReservar = new JButton("Reservar");
		btnSiguiente = new JButton("Siguiente");
		
		
		
		
		//Añadimos paneles al panel central
		
		//Creamos Tabla
		modeloTabla = new ModeloTablaReserva(null);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		
		//Renderer Tabla
		modeloTabla = (ModeloTablaReserva) tabla.getModel();
		tabla.setModel(modeloTabla);
		pCentroN.add(scrollTabla);
		
		pCentro.add(pCentroN);

		pCentro.add(pCentroS);
		pCentroS.add(txtReserva);
		pCentroS.add(btnReservar);
		pSur.add(btnAtras);
		pSur.add(btnSiguiente);
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
	setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		VentanaReservaPlaza v = new VentanaReservaPlaza();
	}

}
