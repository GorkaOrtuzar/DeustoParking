package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
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
		
		
		
		pTabla.setBackground(Color.CYAN);
		
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
		Dimension spinner1 = new Dimension(10, 25);
		SFechaEntrada.setPreferredSize(spinner1);
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
		Dimension spinner2 = new Dimension(10, 25);
		SFechaSalida.setPreferredSize(spinner2);
        SFechaSalida.setEditor(new JSpinner.DateEditor(SFechaSalida, "dd/MM/yyyy"));
	    pJSpinnerSalida.add(FechaSalida);
	    pJSpinnerSalida.add(SFechaSalida);
	    pTabla.add(pJSpinnerSalida);
		
	    //JButton
	    JPanel pJButtonBuscar = new JPanel();
		pJButtonBuscar.setBounds(20,20,50,20);
		JButton btnBuscar = new JButton("BUSCAR");
		Dimension dimension = new Dimension(100, 30);
		btnBuscar.setPreferredSize(dimension);
        btnBuscar.setBounds(0,0,pJButtonBuscar.getWidth(), pJButtonBuscar.getHeight());  

	    pJButtonBuscar.add(btnBuscar);
	    pTabla.add(pJButtonBuscar);
		
		btnBuscar.addActionListener((e)->{
			String Ciudad = (String) ComboCiudades.getSelectedItem();
			String fechaEntrada = SFechaEntrada.getValue().toString();
			String fechaSalida = SFechaSalida.getValue().toString();
			Reserva r = new Reserva(Ciudad, fechaEntrada, fechaSalida);
			new VentanaIngresarDatos(vActual, r);
			vActual.dispose();
		});
		
		
		//imagen
		ImageIcon imagen = new ImageIcon("imagenes/logoDeustoParking.png");
		JLabel lblImagen = new JLabel(imagen);
		lblImagen.setBounds(0, 0, 100, 100);
		pImagen.add(lblImagen);
		
		
		
		
		
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
	
	public static void main(String[] args) {
		VentanaInicio v = new VentanaInicio();
	}

	

}