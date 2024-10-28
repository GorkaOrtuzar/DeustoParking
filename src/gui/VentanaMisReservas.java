package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaMisReservas extends JFrame {

	private JPanel pCentro, pSur, pNorte;
	private JButton btnCerrar;
	private JLabel lblReserva;
	
	private DefaultTableModel modeloTabla; 
	private JTable tabla; 
	private JScrollPane scrollTabla;
	
	private JFrame vActual;
	public VentanaMisReservas(JFrame va) {
		
		pNorte = new JPanel();
		pSur = new JPanel();
		pCentro = new JPanel();
		pCentro.setBorder(new EmptyBorder(100, 100, 100, 100 ));

		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		

		//Titulo
		lblReserva = new JLabel("MIS RESERVAS");
		lblReserva.setFont(new Font(Font.DIALOG, Font.ITALIC, 18));
		pNorte.add(lblReserva);
		
		//Tabla
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		String [] titulos = {"FECHA DE ENTRADA","FECHA DE SALIDA","NOMBRE DEL PARKING", "PRECIO"};
		modeloTabla.setColumnIdentifiers(titulos);
		tabla.getTableHeader().setReorderingAllowed(false);
		cargarTabla();
		
		
		pCentro.add(scrollTabla);
		
		
		
		
		//Boton cerrar
		btnCerrar = new JButton("Cerrar");
		pSur.add(btnCerrar);
		btnCerrar.addActionListener((e)->{
			new VentanaInicio(vActual);
			vActual.dispose();
			
		});
	}
		
		private void cargarTabla() {
			//Vamos a cargar a tabla con la información del fichero asignaturas.txt
			File f = new File("ficheros/reservasEjemplo.txt");
			
			if(f.exists()) {
				try {
					Scanner sc = new Scanner(f);
					while(sc.hasNextLine()) { 
						String linea = sc.nextLine(); 
						String [] datos = linea.split(",");
						Object [] fila = {datos[4], datos[5], datos[3],datos[7]};
						modeloTabla.addRow(fila);
					}
					sc.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		
		
		
		
		
		
		//Tamaño de la ventana
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Mis reservas");
		setVisible(true);
		
	}
	
	
	
	
	
}
			