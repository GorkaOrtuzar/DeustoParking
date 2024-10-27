package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.logging.Level;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.Reserva;

public class VentanaInicioSesion extends JFrame {
	private JPanel pCentro, pNorte,pSur;
	private JLabel lblDni,lblContrasenia;
	private JTextField txtDNI;
	private JPasswordField txtContrasenia;
	private JButton btnVer;
	
	private JFrame vActual;
	private JFrame vAnterior;

	
	 public VentanaInicioSesion(JFrame va) {
		 super();
		vActual = this;
		vAnterior = va;
		setBounds(300, 200, 300, 300);

		
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
		String con = txtContrasenia.getText();
		 if(dni.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Inserte un Usuario","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		 //Mira si la contraseña esta vacia
		 else if (con.isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Inserte Contraseña","ERROR",JOptionPane.ERROR_MESSAGE);
			 }
		 else {
			 vActual.dispose();
			 vAnterior.dispose();

		 }
		});
		setTitle("Inicio Sesion");
		setVisible(true);	
	}
		
	 		
		
}
