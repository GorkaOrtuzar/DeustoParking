package gui;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import db.BD;
import domain.Reserva;
import domain.Usuario;
import domain.Utilidades;

public class VentanaIngresarDatos extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel pNorte,pCentro,pSur,pDatosPersonales,pInfoParking;
	private JLabel lblIntroDatos,lblConfirma,lblPago;
	private JLabel lblNombre,lblApellido,lblTlf,lblDni,lblMatricula,lblContrasenia;
	private JTextField txtNombre,txtApellido,txtTlf, txtDni,txtMatricula,txtContrasenia;

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
		
		lblIntroDatos = new JLabel("1. Introduce Datos");
		lblIntroDatos.setForeground(Color.BLUE);
		lblConfirma = new JLabel("2. Confirma Reserva");
		lblPago = new JLabel("3. Pago Final");
		
		pNorte.add(lblIntroDatos);
		pNorte.add(lblConfirma);
		pNorte.add(lblPago);

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
				JOptionPane.showMessageDialog(null, "Teléfono no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else if(tlf.length()!= 9) {
				JOptionPane.showMessageDialog(null, "Teléfono no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else if(matricula== null) {
				JOptionPane.showMessageDialog(null, "Matrícula no valida ", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
			}else if(!matricula.matches("^\\d{4}[A-Za-z]{3}$")) { // linea creada por ChatGPT
				JOptionPane.showMessageDialog(null, "Matrícula no valida ", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else if(dni == null) {
				JOptionPane.showMessageDialog(null, "DNI no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			
			}else if(dni.length()!= 9) {
				JOptionPane.showMessageDialog(null, "DNI no valido", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else if(contrasenia == null) {
				JOptionPane.showMessageDialog(null, "Contraseña no valida", "MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);

			}else {
			Usuario u = new Usuario(Nombre, Apellido,tlf, dni, contrasenia);
			Reserva re = new Reserva(r.getCiudad(),dni,matricula,r.getNomParking(),Utilidades.dateToString(r.gethLlegada()),Utilidades.dateToString(r.gethSalida()),r.getNumPlaza(), r.getSeccion(), r.getPrecioTotal());
			BD.insertarUsuario(BD.initBD("resources/db/deustoParking.db"), u);
			
			vActual.dispose(); 
			new VentanaResumen(vActual, re, u);
			}
			
		});
		
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
	
		txtNombre.setMaximumSize(new Dimension (500, 20));
		txtApellido.setMaximumSize(new Dimension (500, 20));
		txtDni.setMaximumSize(new Dimension (500, 20));
		txtMatricula.setMaximumSize(new Dimension (500, 20));
		txtTlf.setMaximumSize(new Dimension (500, 20));
		txtContrasenia.setMaximumSize(new Dimension (500, 20));

		lblLLegada = new JLabel("Fecha de Ingreso: "+Utilidades.dateToString(r.gethLlegada()));
		lblSalida = new JLabel("Fecha de Salida: "+ Utilidades.dateToString(r.gethSalida()));
		lblNombreDelParking = new JLabel("Nombre del Parking: "+ r.getNomParking());
		lblPlaza = new JLabel("Plaza: "+ r.getNumPlaza()+ r.getSeccion());
		
		pInfoParking.add(lblLLegada);
		pInfoParking.add(lblSalida);
		pInfoParking.add(lblNombreDelParking);
		pInfoParking.add(lblPlaza);

		pCentro.add(pDatosPersonales, BorderLayout.CENTER);
		pCentro.add(pInfoParking, BorderLayout.CENTER);
		
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Introduce los Datos");
		ImageIcon icono = new ImageIcon("resources/imagenes/logito.png");
		setIconImage(icono.getImage());
		setVisible(true);
	}
}