package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Date;
import java.util.Properties;

import domain.Reserva;
import domain.Utilidades;

public class VentanaInicio extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel pCentro, pNorte,pTabla,pImagen, pMisReservas,pBloque1;
	private JLabel lblDesutoCar;
	
	private JFrame vActual;
	
	public VentanaInicio(JFrame va) {
		super();
		vActual = this;
		
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
		
		lblDesutoCar = new JLabel("DeustoParking");
		lblDesutoCar.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		
		pNorte.add(lblDesutoCar);
		pCentro.add(pMisReservas);
		pBloque1.add(pTabla);
		pBloque1.add(pImagen);
		pCentro.add(pBloque1);
		
		JPanel pMisRes = new JPanel();
		JButton btnMisReservas = new JButton("Mis reservas");
		Dimension dimensionBtnMisRes = new Dimension(200, 25);
		btnMisReservas.setPreferredSize(dimensionBtnMisRes);
		pMisRes.add(btnMisReservas);
		btnMisReservas.setBackground(Color.ORANGE);
		pMisReservas.add(pMisRes, BorderLayout.EAST);
		
		btnMisReservas.addActionListener((e)->{
			JOptionPane.showMessageDialog(null, "Has tenido que reservar con notros anteriormente \n para acceder a esta sección","MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
			new VentanaInicioSesion(vActual);
		});
		
		JPanel pComboBox = new JPanel();
		pComboBox.setBounds(20,20,50,20);
		pComboBox.setLayout(new GridLayout(2, 1));
		JLabel Ciudades= new  JLabel("Ciudades: ");
		String[] ciudades = {"Bilbao", "Barcelona", "Madrid"};
	    JComboBox<String> ComboCiudades = new JComboBox<>(ciudades);
	    pComboBox.add(Ciudades);
	    pComboBox.add(ComboCiudades);
	    pTabla.add(pComboBox);
	    
	    JLabel lblEntrada = new JLabel("Fecha de entrada: ");
	    lblEntrada.setBounds(20, 20, 120, 25);
	    
	    Properties properties = new Properties();
        properties.put("text.today", "Hoy");
        properties.put("text.month", "Mes");
        properties.put("text.year", "Año");
        
        UtilDateModel modeloEntrada = new UtilDateModel();
        JDatePanelImpl panelFechaEntrada = new JDatePanelImpl(modeloEntrada, properties);
        JDatePickerImpl DPentrada = new JDatePickerImpl(panelFechaEntrada, null);
        DPentrada.setBounds(150, 20, 200, 30);
        DPentrada.addActionListener((e)->{
        	Date fechaEntradaSelect = (Date) DPentrada.getModel().getValue();
        	if(fechaEntradaSelect!= null) {
        		if(fechaEntradaSelect.before(new Date())) {
        			JOptionPane.showMessageDialog(null, "No puedes seleccionar una fecha anterior a hoy", "Fecha inválida", JOptionPane.WARNING_MESSAGE);
                    DPentrada.getModel().setSelected(false);
        		}else {
	        		String fechaEntradaSelectString = Utilidades.dateToString(fechaEntradaSelect);
	        		lblEntrada.setText("Fecha de Entrada: " + fechaEntradaSelectString);
        		}
        	}else {
        		JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fecha","MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
        	}
        });
        
        JPanel pPickerEntrada = new JPanel();
        pPickerEntrada.setLayout(new GridLayout(2, 1));
        pPickerEntrada.add(lblEntrada);
        pPickerEntrada.add(DPentrada);
        pTabla.add(pPickerEntrada);
        
        JLabel lblSalida = new JLabel("Fecha de Salida: ");
	    lblSalida.setBounds(20, 20, 120, 25);
        UtilDateModel modeloSalida = new UtilDateModel();
        JDatePanelImpl panelFechaSalida = new JDatePanelImpl(modeloSalida, properties);
        JDatePickerImpl DPSalida = new JDatePickerImpl(panelFechaSalida, null);
        DPSalida.setBounds(150, 20, 200, 30);
        
        DPSalida.addActionListener((e)->{
        	Date fechaSalidaSelect = (Date) DPSalida.getModel().getValue();
        	if(fechaSalidaSelect!= null) {
        		if(fechaSalidaSelect.before(new Date())) {
        			 JOptionPane.showMessageDialog(null, "No puedes seleccionar una fecha anterior a hoy", "Fecha inválida", JOptionPane.WARNING_MESSAGE);
                     DPentrada.getModel().setSelected(false); 
        		}else {
        			String fechaSalidaSelectString = Utilidades.dateToString(fechaSalidaSelect);
            		lblSalida.setText("Fecha de Salida: " + fechaSalidaSelectString);
        		}
        		
        	}else {
        		JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fecha","MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
        	}
        });
        
        JPanel pPickerSalida = new JPanel();
        pPickerSalida.add(lblSalida);
        pPickerSalida.setLayout(new GridLayout(2, 1));
        pPickerSalida.add(DPSalida);
        pTabla.add(pPickerSalida);
        
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
	    
		btnBuscar.addActionListener((e)->{
			String ciudad = (String) ComboCiudades.getSelectedItem();
			Date fechaEntrada = (Date) DPentrada.getModel().getValue();
			Date fechaSalida = (Date) DPSalida.getModel().getValue();
			Reserva r = new Reserva(ciudad, fechaEntrada, fechaSalida);
			
			if(!ciudad.equals(null)|| !(fechaEntrada == null)||!(fechaSalida == null)) {
			new VentanaReservaParking(vActual, r);
			vActual.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Los campos no han sido rellenados correctamente","MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		ImageIcon imagen = new ImageIcon("resources/imagenes/logoDeustoParking.png");
		JLabel lblImagen = new JLabel(imagen);
		lblImagen.setBounds(0, 0, 100, 100);
		ImageIcon icono = new ImageIcon("resources/imagenes/logito.png");
		setIconImage(icono.getImage());
		pImagen.add(lblImagen);
		
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Inicio");
		setVisible(true);
	}
}