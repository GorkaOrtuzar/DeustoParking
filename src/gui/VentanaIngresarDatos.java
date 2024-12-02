package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import domain.BD;
import domain.Contenedora;
import domain.Reserva;
import domain.Usuario;
import domain.Utilidades;

public class VentanaIngresarDatos extends JFrame {
	
	private JPanel pNorte,pCentro,pSur,pDatosPersonales,pInfoParking;
	//info pNorte
	private JLabel lblIntroDatos,lblConfirma,lblPago;
	//info pDatosPersonales
	private JLabel lblNombre,lblApellido,lblTlf,lblDni,lblMatricula,lblContrasenia;
	private JTextField txtNombre,txtApellido,txtTlf, txtDni,txtMatricula,txtContrasenia;

	//info pInfoParking
	private JLabel lblLLegada,lblSalida,lblNombreDelParking,lblPlaza;
	private JButton btnSiguiente;
	
	private JFrame vActual;
	
	
	public VentanaIngresarDatos(JFrame va, Reserva r){
		
		super();
		vActual = this;
		
		
		
		pCentro = new JPanel(new BorderLayout());
		pCentro.setLayout(new GridLayout(1, 2));
		pNorte = new JPanel();
		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
		pSur = new JPanel();
		pDatosPersonales = new JPanel();
		pDatosPersonales.setLayout(new BoxLayout(pDatosPersonales,BoxLayout.Y_AXIS));
		pDatosPersonales.setBorder(new EmptyBorder(150, 150, 150, 150 ));
		
		
		pInfoParking =  new JPanel();
		pInfoParking.setLayout(new BoxLayout(pInfoParking,BoxLayout.Y_AXIS));
		pInfoParking.setLayout(new GridLayout(5,0));
		pInfoParking.setBorder(new EmptyBorder(150, 150, 150, 150 ));
       

		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		//PANEL NORTE
		lblIntroDatos = new JLabel("1. Introduce Datos");
		lblIntroDatos.setForeground(Color.BLUE);
		lblConfirma = new JLabel("2. Confirma Reserva");
		lblPago = new JLabel("3. Pago Final");
		
		pNorte.add(lblIntroDatos);
		pNorte.add(lblConfirma);
		pNorte.add(lblPago);
		
		//PANEL SUR
		btnSiguiente = new JButton("Siguiente");
		pSur.add(btnSiguiente);
		
		btnSiguiente.addActionListener((e)->{
			String Nombre = txtNombre.getText();
			String Apellido = txtApellido.getText();
			String matricula = txtMatricula.getText();
			String tlf = txtTlf.getText();
			String dni = txtDni.getText();
			String contrasenia = txtContrasenia.getText();
			if(Nombre == null) {
				JOptionPane.showMessageDialog(null, "Nombre no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
			}else if(Apellido == null){
				JOptionPane.showMessageDialog(null, "Apellido no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
				
			}else if(tlf == null){
				JOptionPane.showMessageDialog(null, "Telefono no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else if(tlf.length()!= 9) {
				JOptionPane.showMessageDialog(null, "Telefono no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else if(dni == null) {
				JOptionPane.showMessageDialog(null, "DNI no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else if(dni.length()!= 9) {
				JOptionPane.showMessageDialog(null, "DNI no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else if(contrasenia == null) {
				JOptionPane.showMessageDialog(null, "Contraseña no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else {
			Usuario u = new Usuario(Nombre, Apellido,tlf, dni, contrasenia);
			Reserva re = new Reserva(r.getCiudad(),dni,matricula,r.getNomParking(),Utilidades.dateToString(r.gethLlegada()),Utilidades.dateToString(r.gethSalida()),r.getNumPlaza(),r.getPrecioTotal());
			BD.insertarUsuario(Principal.con, u);
			
			vActual.dispose(); 
			new VentanaResumen(vActual, re, u);
			}
			
		});
		
		//PANEL CENTRO
		//Zona del panel de ingresar datos
		lblNombre = new JLabel("Nombre: ");
		txtNombre = new JTextField();
		lblApellido = new JLabel("Apellido: ");
		txtApellido = new JTextField();
		lblTlf = new JLabel("tlf: ");
		txtTlf = new JTextField();
		lblDni = new JLabel("DNI: ");
		txtDni = new JTextField();
		lblMatricula = new JLabel("Matrícula: ");
		txtMatricula = new JTextField();
		lblContrasenia = new JLabel("Contraseña: ");
		txtContrasenia = new JTextField();
		
		
		
		pDatosPersonales.add(lblNombre);
		pDatosPersonales.add(txtNombre);
		pDatosPersonales.add(lblApellido);
		pDatosPersonales.add(txtApellido);
		pDatosPersonales.add(lblTlf);
		pDatosPersonales.add(txtTlf);
		pDatosPersonales.add(lblMatricula);
		pDatosPersonales.add(txtMatricula);
		pDatosPersonales.add(lblDni);
		pDatosPersonales.add(txtDni);
		pDatosPersonales.add(lblContrasenia);
		pDatosPersonales.add(txtContrasenia);
	
		
		//dimensiones de los txt
		txtNombre.setMaximumSize(new Dimension (500, 20));
		txtApellido.setMaximumSize(new Dimension (500, 20));
		txtDni.setMaximumSize(new Dimension (500, 20));
		txtMatricula.setMaximumSize(new Dimension (500, 20));
		txtTlf.setMaximumSize(new Dimension (500, 20));
		txtContrasenia.setMaximumSize(new Dimension (500, 20));

		//zona del panel de info de la reserva
		lblLLegada = new JLabel("Fecha de Ingreso: "+Utilidades.dateToString(r.gethLlegada()));
		lblSalida = new JLabel("Fecha de Salida: "+ Utilidades.dateToString(r.gethSalida()));
		lblNombreDelParking = new JLabel("Nombre del Parking: "+ r.getNomParking());
		lblPlaza = new JLabel("Plaza: "+ r.getNumPlaza());
		
		pInfoParking.add(lblLLegada);
		pInfoParking.add(lblSalida);
		pInfoParking.add(lblNombreDelParking);
		pInfoParking.add(lblPlaza);
		
		
		pCentro.add(pDatosPersonales, BorderLayout.CENTER);
		pCentro.add(pInfoParking, BorderLayout.CENTER);
		

		//Tamaño de la ventana
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Introduce los Datos");
		setVisible(true);
	}
	
	
	
	
	
	
	
}
