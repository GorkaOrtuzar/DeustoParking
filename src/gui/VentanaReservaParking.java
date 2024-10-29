package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import domain.Parking;
import domain.Reserva;

public class VentanaReservaParking extends JFrame{
	
	//Declaramos valores
	private JPanel pCentro, pCentroN, pCentroS, pSur, pNorte;
	private JButton btnAtras, btnReservar, btnSiguiente;
	private JTextArea txtReserva;
	
	//Declaramos tabla
	private ModeloTablaReserva modeloTabla; 
	private JTable tabla; 
	private JScrollPane scrollTabla;
	
	private JFrame vActual;
	private JFrame vAnterior;
	
	
	
	public VentanaReservaParking(JFrame va, Reserva r) {
		super();
		vActual = this;
		vAnterior = va;
		
		
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
		
		btnAtras.addActionListener((e)->{
			vActual.dispose();
			vAnterior.setVisible(true);
			
		});
		
		btnReservar = new JButton("Reservar");
		
		
		//Añadimos paneles al panel central
		
		//Creamos Tabla
		modeloTabla = new ModeloTablaReserva(null);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		cargarTablaDatos();
		getContentPane().add(scrollTabla, BorderLayout.CENTER);
		
		//Renderer Tabla
		modeloTabla = (ModeloTablaReserva) tabla.getModel();
		tabla.setModel(modeloTabla);
		pCentroN.add(scrollTabla);
		
		pCentro.add(pCentroN);

		pCentro.add(pCentroS);
		pCentroS.add(txtReserva);
		pCentroS.add(btnReservar);
		pSur.add(btnAtras);
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		tabla.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tabla.getSelectedRow();
				if(fila!=-1) {
					String nomParking = (String) tabla.getValueAt(fila, 0);
					int numPlazasLibres = (int) tabla.getValueAt(fila, 1);
					float precio = (float) tabla.getValueAt(fila, 2);
				}
				
			}
		});
		
		//btnRservar guarda los datos seleccionados de la tabla, y los guarda en r (Reserva)  -- TERMINAR --
		btnReservar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vActual.dispose(); 
				new VentanaIngresarDatos(vActual, r);
				
			}
		});
		

		
		//Tamaño de la ventana
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Reservar plaza de parking");
		setVisible(true);
	}
	

	private void cargarTablaDatos() {
		List<Parking> lp = new ArrayList<>();
		lp.add(new Parking("Parking1", 12.3f, 26));
		lp.add(new Parking("Parking2", 19f, 20));
		lp.add(new Parking("Parking3", 10f, 59));
		lp.add(new Parking("ParkingVIP", 30f, 17));
		lp.add(new Parking("ParkingCentral", 25.5f, 105));
		lp.add(new Parking("ParkingTechado", 17.90f, 237));
		modeloTabla = (ModeloTablaReserva) tabla.getModel();
		modeloTabla.setlParkings(lp);
		tabla.setModel(modeloTabla);
		
		
	}

}
