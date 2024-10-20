package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VentanaInicio extends JFrame{
	
	// Declarar los valores
	private JPanel pCentro, pNorte,pTabla,pImagen;
	private JLabel lblDesutoCar;
	
	//tabla
	private ModeloTablaInicio modeloTabla; 
	private JTable tabla; 
	private JScrollPane scrollTabla;
	
	private JFrame vActual;
	
	//Tabla
	
	public VentanaInicio() {
		super();
		vActual = this;
		
		//Tamaño de la ventana
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Instanciar los paneles
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(2, 1));
		pNorte = new JPanel();
		pImagen = new JPanel();
		pTabla =  new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		lblDesutoCar = new JLabel("DeustoCar");
		lblDesutoCar.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		
		
		//Añadir los paneles al panel central
		pNorte.add(lblDesutoCar);
		pCentro.add(pTabla);
		pCentro.add(pImagen);
		
		
		// Creacion de la tabla
		modeloTabla = new ModeloTablaInicio(null);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		
		//Renderer de la tabla
		
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = null;
				/*Queremos que la columna 0 salga justificada a la izquierda y que la columna 1 salga justificada en el centro*/
				/*En el caso de la columna 2 queremos motrar una barra de progreso*/
				if(column == 0) {
					 String[] ciudades = {"Ciudad1", "Ciudad2", "Ciudad3"};
				        JComboBox<String> CBCiudades = new JComboBox<>(ciudades);
				        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(CBCiudades));
					c = CBCiudades;
				}else if(column == 1) {
					 	JSpinner SFechaEntrada = new JSpinner(new SpinnerDateModel());
				        SFechaEntrada.setEditor(new JSpinner.DateEditor(SFechaEntrada, "dd/MM/yyyy"));
				        c = SFechaEntrada;
				}else if(column == 2) {
					JSpinner SFechaSalida = new JSpinner(new SpinnerDateModel());
			        SFechaSalida.setEditor(new JSpinner.DateEditor(SFechaSalida, "dd/MM/yyyy"));
			        c = SFechaSalida;
				}else if(column == 2) {
					JButton btnBuscar = new JButton("BUSCAR");
					c = btnBuscar;
				}
				return c;
			}
		});
		
		modeloTabla = (ModeloTablaInicio) tabla.getModel();
		tabla.setModel(modeloTabla);
		pTabla.add(scrollTabla);
		
		//imagen
		ImageIcon imagen = new ImageIcon("imagenes/logoProvisional.jpeg");
		JLabel lblImagen = new JLabel(imagen);
		pImagen.add(lblImagen);
		
	setVisible(true);	
	
	}
	
	public static void main(String[] args) {
		VentanaInicio v = new VentanaInicio();
	}

	

}
