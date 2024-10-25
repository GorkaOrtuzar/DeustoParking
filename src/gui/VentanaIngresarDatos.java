package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
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
import domain.Reserva;
import domain.Usuario;

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
		
		
		
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(1, 2));
		pNorte = new JPanel();
		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		pSur = new JPanel();
		pDatosPersonales = new JPanel();
		pDatosPersonales.setLayout(new BoxLayout(pDatosPersonales,BoxLayout.Y_AXIS));
		pInfoParking =  new JPanel();
		pInfoParking.setLayout(new BoxLayout(pInfoParking,BoxLayout.Y_AXIS));
		pInfoParking.setLayout(new GridLayout(5,0));

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
		//	Contenedora.aniadirUsuario(u);
			vActual.dispose(); 
			new VentanaPago(vActual, r);
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
		
		//Separador de lineas
		String saltoDeLinea = System.lineSeparator();

	
		//zona del panel de info de la reserva
		lblLLegada = new JLabel("Fecha de ingreso: "+r.gethLlegada());
		lblSalida = new JLabel("Fecha de salida: "+ r.gethSalida());
		lblNombreDelParking = new JLabel("Nombre del parking: "+ r.getNomParking());
		lblPlaza = new JLabel("Plaza: "+ r.getNumPlaza());
		
		pInfoParking.add(lblLLegada);
		pInfoParking.add(lblSalida);
		pInfoParking.add(lblNombreDelParking);
		pInfoParking.add(lblPlaza);
		
		
		pCentro.add(pDatosPersonales);
		pCentro.add(pInfoParking);
		
		//Dimesiones de los txt
		
		txtNombre.setPreferredSize(new Dimension(400,30));
		txtApellido.setPreferredSize(new Dimension(400,30));
		txtContrasenia.setPreferredSize(new Dimension(400,30));
		txtDni.setPreferredSize(new Dimension(400,30));
		txtMatricula.setPreferredSize(new Dimension(400,30));
		txtTlf.setPreferredSize(new Dimension(400,30));



		
		//Tamaño de la ventana
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Introduce los datos");
		setVisible(true);
	}
	
	
	
	
	
	
	
}
