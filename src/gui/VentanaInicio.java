package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

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

import domain.Reserva;

public class VentanaInicio extends JFrame{
	
	// Declarar los valores
	private JPanel pCentro, pNorte,pTabla,pImagen;
	private JLabel lblDesutoCar;
	
	private JFrame vActual;
	
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
		
		//ComboBox
		JPanel pComboBox = new JPanel();
		pComboBox.setBounds(20,20,50,20);
		pComboBox.setLayout(new GridLayout(2, 1));
		JLabel Ciudades= new  JLabel("Ciudades: ");
		String[] ciudades = {"Ciudad1", "Ciudad2", "Ciudad3"};
	    JComboBox<String> ComboCiudades = new JComboBox<>(ciudades);
	    pComboBox.add(Ciudades);
	    pComboBox.add(ComboCiudades);
	    pTabla.add(pComboBox);
	    
	    //JSpinner Salida
	    JPanel pJSpinnerEntrada = new JPanel();
		pJSpinnerEntrada.setBounds(20,20,50,20);
		pJSpinnerEntrada.setLayout(new GridLayout(2, 1));
		JLabel FechaEntrada= new  JLabel("Fecha de entrada: ");
		JSpinner SFechaEntrada = new JSpinner(new SpinnerDateModel());
        SFechaEntrada.setEditor(new JSpinner.DateEditor(SFechaEntrada, "dd/MM/yyyy"));
	    pJSpinnerEntrada.add(FechaEntrada);
	    pJSpinnerEntrada.add(SFechaEntrada);
	    pTabla.add(pJSpinnerEntrada);
		
	    //JSpinner Salida
	    JPanel pJSpinnerSalida = new JPanel();
		pJSpinnerSalida.setBounds(20,20,50,20);
		pJSpinnerSalida.setLayout(new GridLayout(2, 1));
		JLabel FechaSalida= new  JLabel("Fecha de salida: ");
		JSpinner SFechaSalida = new JSpinner(new SpinnerDateModel());
        SFechaSalida.setEditor(new JSpinner.DateEditor(SFechaSalida, "dd/MM/yyyy"));
	    pJSpinnerSalida.add(FechaSalida);
	    pJSpinnerSalida.add(SFechaSalida);
	    pTabla.add(pJSpinnerSalida);
		
	    //JButton
	    JPanel pJButtonBuscar = new JPanel();
		pJButtonBuscar.setBounds(20,20,50,20);
		JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBounds(0,0,pJButtonBuscar.getWidth(), pJButtonBuscar.getHeight());  

	    pJButtonBuscar.add(btnBuscar);
	    pTabla.add(pJButtonBuscar);
		
		btnBuscar.addActionListener((e)->{
			String Ciudad = (String) ComboCiudades.getSelectedItem();
			LocalDate fechaEntrada = (LocalDate) SFechaEntrada.getValue();
			LocalDate fechaSalida = (LocalDate) SFechaSalida.getValue();
			
			Reserva r = new Reserva(Ciudad, fechaEntrada, fechaSalida);
			
			new VentanaIngresarDatos(vActual);
			vActual.dispose();
		});
		
		
		//imagen
		ImageIcon imagen = new ImageIcon("imagenes/logoDeustoParking.png");
		JLabel lblImagen = new JLabel(imagen);
		lblImagen.setBounds(0, 0, 100, 100);
		pImagen.add(lblImagen);
		
	setVisible(true);	
	
	}
	
	public static void main(String[] args) {
		VentanaInicio v = new VentanaInicio();
	}

	

}
