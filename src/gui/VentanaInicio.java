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
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Date;
import java.util.Properties;

import domain.Reserva;

public class VentanaInicio extends JFrame{
	
	// Declarar los valores
	private JPanel pCentro, pNorte,pTabla,pImagen, pMisReservas,pBloque1;
	private JLabel lblDesutoCar;
	//private JDatePanelImpl dpaFechaEntradaPanel, dpaFechaSalidaPanel;
	private UtilDateModel modeloEntrada, modeloSalida;


	
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
		
//		modeloEntrada = new UtilDateModel();
//		modeloSalida = new UtilDateModel();
//		
//		configurarModeloFecha(modeloEntrada, r.gethLlegada());
//		configurarModeloFecha(modeloSalida, r.gethSalida());
//		
//		Properties p = new Properties();
//		p.put("text.today", "Hoy");
//		p.put("text.month", "Mes");
//		p.put("text.year", "Año");
		
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
		String[] ciudades = {"Ciudad1", "Ciudad2", "Ciudad3"};
	    JComboBox<String> ComboCiudades = new JComboBox<>(ciudades);
	    pComboBox.add(Ciudades);
	    pComboBox.add(ComboCiudades);
	    pTabla.add(pComboBox);
	    
	    //JSpinner Entrada
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
	    
//	    dpaFechaEntradaPanel = new JDatePanelImpl(modeloEntrada, p);
//		dpaFechaEntradaPanel.addActionListener(e -> {
//			modeloEntrada.setValue(r.gethLlegada());
//			modeloEntrada.setSelected(true);
//	    	pJSpinnerSalida.add(dpaFechaSalidaPanel);
//		});
//		
		
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
	    
//	    dpaFechaSalidaPanel = new JDatePanelImpl(modeloSalida, p);
//		dpaFechaSalidaPanel.addActionListener(e -> {
//			modeloSalida.setValue(r.gethSalida());
//			modeloSalida.setSelected(true);
//      pJSpinnerSalida.add(dpaFechaSalidaPanel);
	    
//		});
		
	    //JButton
	    JPanel pJButtonBuscar = new JPanel();
	    pJSpinnerSalida.setLayout(new GridLayout(2, 1));
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
			Date fechaEntrada = (Date) SFechaEntrada.getValue();
			Date fechaSalida = (Date) SFechaSalida.getValue();
			Reserva r = new Reserva(ciudad, fechaEntrada, fechaSalida);
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
