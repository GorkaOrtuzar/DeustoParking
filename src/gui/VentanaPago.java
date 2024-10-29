package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.Reserva;
import domain.Usuario;

public class VentanaPago extends JFrame{

	private JPanel pNorte,pCentro,pSur,pDatosPago;
	private JLabel lblIntroDatos,lblConfirma,lblPago;
	private JLabel lblNTarjeta, lblFechExpiracion,lblCVV;
	private JTextField txtNTarjeta,txtFechaExpiracion,txtCVV;
	private JButton btnSiguiente;
	
	private JFrame vActual, vAnterior;
	
	public VentanaPago(JFrame va, Reserva r) {
		
		super();
		vActual = this;
		vAnterior=va;
		
		pCentro = new JPanel();
		pNorte = new JPanel();
		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
		pSur = new JPanel();
		pDatosPago = new JPanel();
		pDatosPago.setLayout(new GridLayout(6, 0));
		pDatosPago.setBorder(new EmptyBorder(150, 150, 150, 150 ));


		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		
		//Panel Norte
		lblIntroDatos = new JLabel("1. Introduce Datos");
		lblConfirma = new JLabel("2. Confirma Reserva");
		lblPago = new JLabel("3. Pago Final");
		lblPago.setForeground(Color.BLUE);
		
		pNorte.add(lblIntroDatos);
		pNorte.add(lblConfirma);
		pNorte.add(lblPago);
		
		
		lblNTarjeta = new JLabel("Nº de tarjeta(Sin espacios)");
		txtNTarjeta = new JTextField();
		lblFechExpiracion = new JLabel("Fecha de Expiracion");
		txtFechaExpiracion = new JTextField();
		lblCVV = new JLabel( "CVV");
		txtCVV = new JTextField();
		
		pDatosPago.add(lblNTarjeta);
		pDatosPago.add(txtNTarjeta);
		pDatosPago.add(lblFechExpiracion);
		pDatosPago.add(txtFechaExpiracion);
		pDatosPago.add(lblCVV);
		pDatosPago.add(txtCVV);



		pCentro.add(pDatosPago);
		
		//Boton
		btnSiguiente = new JButton("Finalizar Compra");
		pSur.add(btnSiguiente);
				
		btnSiguiente.addActionListener((e)->{
			String NTarjeta = txtNTarjeta.getText();
			String FechaExpiracion = txtFechaExpiracion.getText();
			String CVV = txtCVV.getText();
			
			 if(NTarjeta.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Inserte el numero de tarjeta","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			 //Mira si la contraseña esta vacia
			 else if (FechaExpiracion.isEmpty()) {
					 JOptionPane.showMessageDialog(null, "Inserte la fecha de expiracion","ERROR",JOptionPane.ERROR_MESSAGE);
				 }
			 else if (CVV.isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Inserte el CVV","ERROR",JOptionPane.ERROR_MESSAGE);
			 }
			 else {
			JOptionPane.showMessageDialog(null, "Confirmar pago");
			vActual.dispose();
			 }
		});
				
				
				
				
				
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
		setSize(anchoP, altoP);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Pago");
		setVisible(true);			
		
	}
	
	
}