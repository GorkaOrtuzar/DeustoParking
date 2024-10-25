package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import domain.Contenedora;
import domain.Usuario;

public class VentanaIngresarDatos extends JFrame {
	
	private JPanel pNorte,pCentro,pSur,pDatosPersonales,pInfoParking;
	//info pNorte
	private JLabel lblIntroDatos,lblConfirma,lblPago;
	//info pDatosPersonales
	private JLabel lblNombre,lblApellido,lblTlf,lblDni,lblMatricula,lblContrasenia;
	private JTextField txtNombre,txtApellido,txtTlf, txtDni,txtMatricula,txtContrasenia;

	//info pInfoParking
	private JLabel lblLLegada,lblSalida,lblNombreDelParking,lblinforDelParking;
	private JTextField txtLlegada,txtSalida,txtNombreDelParking,txtinforDelParking;
	private JButton btnSiguiente;
	
	private JFrame vActual;
	
	public VentanaIngresarDatos(JFrame va){
		
		super();
		vActual = this;
		
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(1, 2));
		pNorte = new JPanel();
		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		pSur = new JPanel();
		pDatosPersonales = new JPanel();
		pDatosPersonales.setLayout(new BoxLayout(pDatosPersonales,BoxLayout.Y_AXIS));
		pInfoParking =  new JPanel();
		pInfoParking.setLayout(new BoxLayout(pInfoParking,BoxLayout.Y_AXIS));

		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		//PANEL NORTE
		lblIntroDatos = new JLabel("1. Introduce tus datos");
		lblIntroDatos.setForeground(Color.BLUE);
		lblConfirma = new JLabel("2. Confirma");
		lblPago = new JLabel("3. Pago");
		
		pNorte.add(lblIntroDatos);
		pNorte.add(lblConfirma);
		pNorte.add(lblPago);
		
		//PANEL SUR
		btnSiguiente = new JButton("Siguente");
		pSur.add(btnSiguiente);
		
		btnSiguiente.addActionListener((e)->{
			String Nombre = txtNombre.getText();
			String Apellido = txtApellido.getText();
			String tlf = txtTlf.getText();
			String dni = txtDni.getText();
			String contrasenia = txtContrasenia.getText();
			Usuario u = new Usuario(Nombre, Apellido, tlf, dni, contrasenia);
			/*for (Usuario us : Contenedora.getlUsuarios()) {
				if(us.getDni().equals(u.getDni())) {
					JOptionPane.showMessageDialog(null, "Usuario regitrado con anterioridad, "
												+ "se te guardara la reserva en un a "
													+ "lista de reservas previamenten adjudicada");
				}else {
					Contenedora.aniadirUsuario(u);
					JOptionPane.showMessageDialog(null, "Usuario guardado");
				}
			}*/
			Contenedora.aniadirUsuario(u);
			vActual.dispose(); 
			//new(vActual); //Abrimos la ventana tabla
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
		
		
	
		//zona del panel de info de la reserva
		lblLLegada = new JLabel("Fecha de ingreso");
		txtLlegada = new JTextField();
		lblSalida = new JLabel("Fecha de salida");
		txtSalida = new JTextField();
		lblNombreDelParking = new JLabel("Nombre del parking");
		txtNombreDelParking = new JTextField();
		lblinforDelParking = new JLabel("Plaza");
		txtinforDelParking = new JTextField();
		
		pInfoParking.add(lblLLegada);
		pInfoParking.add(lblSalida);
		pInfoParking.add(lblNombreDelParking);
		pInfoParking.add(lblinforDelParking);
		
		
		pCentro.add(pDatosPersonales);
		pCentro.add(pInfoParking);
		
		
		
		
		
		setVisible(true);
	}
	
	
	
}
