package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.logging.Level;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.BD;
import domain.Contenedora;
import domain.Reserva;
import domain.Usuario;

public class VentanaInicioSesion extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel pCentro, pNorte,pSur;
	private JLabel lblDni,lblContrasenia;
	private JTextField txtDNI;
	private JPasswordField txtContrasenia;
	private JButton btnVer;
	
	private JFrame vActual;
	private JFrame vAnterior;
	private Connection con;
	

	
	 public VentanaInicioSesion(JFrame va) {
		 super();
		vActual = this;
		vAnterior = va;
	

		
		pCentro = new JPanel();
		pCentro.setLayout(new BoxLayout(pCentro,BoxLayout.Y_AXIS));

		pNorte = new JPanel();
		pSur = new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		lblDni = new JLabel("Insertar Dni");
		txtDNI = new JTextField();
		lblContrasenia = new JLabel("Insertar contraseña");
		txtContrasenia = new JPasswordField();
		txtDNI.setMaximumSize(new Dimension (500, 20));
		txtContrasenia.setMaximumSize(new Dimension(500, 20));
		
		
		pCentro.add(lblDni);
		pCentro.add(txtDNI);
		pCentro.add(lblContrasenia);
		pCentro.add(txtContrasenia);
		
		btnVer = new JButton("Ver mis reservas");
		pSur.add(btnVer);
		btnVer.addActionListener((e)->{
			
		String dni = txtDNI.getText();
		String contr = txtContrasenia.getText();
		 if(dni.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Inserte un Usuario","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		 else if(dni.length()!= 9){
				JOptionPane.showMessageDialog(null, "DNI no valido","ERROR",JOptionPane.ERROR_MESSAGE);

		 }
		 //Mira si la contraseña esta vacia
		 else if (contr.isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Inserte Contraseña","ERROR",JOptionPane.ERROR_MESSAGE);
			 }
		
		 else {
				
		 		 BD.buscarUsario(Principal.con, dni);
				 vActual.dispose();
				 vAnterior.dispose();
				 new VentanaMisReservas(vActual ,dni);
				 txtDNI.setText("");
				 txtContrasenia.setText("");
		
				}
			 
			 	 
		});
		setBounds(300, 200, 300, 300);
		setTitle("Inicio Sesion");
		setVisible(true);	
	}

}
		
	 		
		

