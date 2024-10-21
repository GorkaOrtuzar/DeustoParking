package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaIngresarDatos extends JFrame {
	
	private JPanel pNorte,pCentro,pSur,pDatosPersonales,pInfoParking;
	private JLabel lblIntroDatos,lblConfirma,lblPago;
	private JLabel lblNombre,lblApellido,lblTlf,lblDni,lblMatricula,lblContrasenia;
	private JTextField txtNombre,txtApellido,txtTlf, txtDni,txtMatricula,txtContrasenia;
	private JButton btnSiguiente;
	
	private JFrame vActual;
	
	public VentanaIngresarDatos(){
		
		super();
		vActual = this;
		
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(1, 2));
		pNorte = new JPanel();
		pSur = new JPanel();
		pDatosPersonales = new JPanel();
		pDatosPersonales.setLayout(new BoxLayout(pDatosPersonales,BoxLayout.Y_AXIS));
		pInfoParking =  new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		//PANEL NORTE
		lblIntroDatos = new JLabel("1. Introduce tus datos");
		lblIntroDatos.setForeground(Color.BLUE);
		lblConfirma = new JLabel("2. Confirma",SwingConstants.CENTER);
		lblPago = new JLabel("3. Pago",SwingConstants.RIGHT);
		
		pNorte.add(lblIntroDatos, BorderLayout.WEST);
		pNorte.add(lblConfirma, BorderLayout.CENTER);
		pNorte.add(lblPago, BorderLayout.EAST);
		
		//PANEL SUR
		btnSiguiente = new JButton("Siguente");
		pSur.add(btnSiguiente);
		
		btnSiguiente.addActionListener((e)->{
			vActual.dispose(); //Cierro la ventana actual
			//new(vActual); //Abrimos la ventana tabla
		});
		
		//PANEL CENTRO
		lblNombre = new JLabel("Nombre: ");
		txtNombre = new JTextField();
		lblApellido = new JLabel("Apellido: ");
		txtApellido = new JTextField();
		lblTlf = new JLabel("tlf: ");
		txtTlf = new JTextField();
		lblDni = new JLabel("DNI: ");
		txtDni = new JTextField();
		lblMatricula = new JLabel("Matricula: ");
		txtMatricula = new JTextField();
		lblContrasenia = new JLabel("Contrasenia: ");
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
		
		pCentro.add(pDatosPersonales);
		pCentro.add(pInfoParking);
		
		
		
		
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		VentanaIngresarDatos vid =  new VentanaIngresarDatos();
	}
	
}
