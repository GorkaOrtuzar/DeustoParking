package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Date;
import java.util.Properties;

import domain.Reserva;

public class VentanaInicio extends JFrame{
	
	// Declarar los valores
	private JPanel pCentro, pNorte,pTabla,pImagen, pMisReservas,pBloque1;
	private JLabel lblDesutoCar;
	
	private JFrame vActual;
	
	public VentanaInicio( JFrame va) {
		super();
		vActual = this;
		
		//Instanciar los paneles
		pCentro = new JPanel();
		pCentro.setLayout(new BoxLayout(pCentro,BoxLayout.Y_AXIS));
		pNorte = new JPanel();
		pBloque1 = new JPanel();
		pBloque1.setLayout(new GridLayout(2,1));
		pImagen = new JPanel();
		pTabla =  new JPanel();
		pMisReservas = new JPanel(new BorderLayout());
				
		
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//titulo
		lblDesutoCar = new JLabel("DeustoParking");
		lblDesutoCar.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		
		
		//Añadir los paneles al panel central
		pNorte.add(lblDesutoCar);
		pCentro.add(pMisReservas);
		pBloque1.add(pTabla);
		pBloque1.add(pImagen);
		pCentro.add(pBloque1);
		
		//Boton mis reservas
		JPanel pMisRes = new JPanel();
		JButton btnMisReservas = new JButton("Mis reservas");
		Dimension dimensionBtnMisRes = new Dimension(200, 25);
		btnMisReservas.setPreferredSize(dimensionBtnMisRes);
		pMisRes.add(btnMisReservas);
		btnMisReservas.setBackground(Color.ORANGE);
		pMisReservas.add(pMisRes, BorderLayout.EAST);
		
		//Action listener del boton misReservas
		btnMisReservas.addActionListener((e)->{
			JOptionPane.showMessageDialog(null, "Has tenido que reservar con notros anteriormente \n para acceder a esta seccion","MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
			new VentanaInicioSesion(vActual);
		});
		
		
		//ComboBox
		JPanel pComboBox = new JPanel();
		pComboBox.setBounds(20,20,50,20);
		pComboBox.setLayout(new GridLayout(2, 1));
		JLabel Ciudades= new  JLabel("Ciudades: ");
		String[] ciudades = {"Bilbao", "Barcelona", "Madrid"};
	    JComboBox<String> ComboCiudades = new JComboBox<>(ciudades);
	    pComboBox.add(Ciudades);
	    pComboBox.add(ComboCiudades);
	    pTabla.add(pComboBox);
	    
	    
	    //calendarios
	    JLabel lblEntrada = new JLabel("Fecha de entrada: ");
	    lblEntrada.setBounds(20, 20, 120, 25);
	    
	    Properties properties = new Properties();
        properties.put("text.today", "Hoy");
        properties.put("text.month", "Mes");
        properties.put("text.year", "Año");

        //CalendarioEntrada
        UtilDateModel modeloEntrada = new UtilDateModel();
        JDatePanelImpl panelFechaEntrada = new JDatePanelImpl(modeloEntrada, properties);
        JDatePickerImpl DPentrada = new JDatePickerImpl(panelFechaEntrada, null);
        DPentrada.setBounds(150, 20, 200, 30);
        JPanel pPickerEntrada = new JPanel();
        pPickerEntrada.setLayout(new GridLayout(2, 1));
        pPickerEntrada.add(lblEntrada);
        pPickerEntrada.add(DPentrada);
        pTabla.add(pPickerEntrada);
        
        //Calendario Salida
        JLabel lblSalida = new JLabel("Fecha de salida: ");
	    lblEntrada.setBounds(20, 20, 120, 25);
        UtilDateModel modeloSalida = new UtilDateModel();
        JDatePanelImpl panelFechaSalida = new JDatePanelImpl(modeloSalida, properties);
        JDatePickerImpl DPSalida = new JDatePickerImpl(panelFechaSalida, null);
        DPentrada.setBounds(150, 20, 200, 30);
        JPanel pPickerSalida = new JPanel();
        pPickerSalida.add(lblSalida);
        pPickerSalida.setLayout(new GridLayout(2, 1));
        pPickerSalida.add(DPSalida);
        pTabla.add(pPickerSalida);
        
	    

	    //JButton
	    JPanel pJButtonBuscar = new JPanel();
		pJButtonBuscar.setBounds(20,20,50,20);
		JLabel lblVacia = new JLabel("");
		JButton btnBuscar = new JButton("BUSCAR");
		Dimension dimension = new Dimension(100, 50);
		btnBuscar.setBackground(Color.ORANGE);
		btnBuscar.setPreferredSize(dimension);
		pJButtonBuscar.add(lblVacia);
	    pJButtonBuscar.add(btnBuscar);
	    pTabla.add(pJButtonBuscar);
	    
		//Action listener del boton buscar
		btnBuscar.addActionListener((e)->{
			String ciudad = (String) ComboCiudades.getSelectedItem();
			Date fechaEntrada = (Date) DPentrada.getModel().getValue();
			Date fechaSalida = (Date) DPSalida.getModel().getValue();
			Reserva r = new Reserva(ciudad, fechaEntrada, fechaSalida);
			//System.out.println(r);
			new VentanaReservaParking(vActual, r);
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

}