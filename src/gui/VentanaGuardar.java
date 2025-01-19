package gui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Reserva;

public class VentanaGuardar extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pSur,pCentro;
	private JButton btnSi, btnNo;
	private JLabel lblPregunta;
	private JFrame vActual;
	
	public VentanaGuardar(JFrame va, Reserva r) {
		
		vActual = this;
		pSur = new JPanel();
		pCentro = new JPanel();
		
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		btnSi = new JButton("SI");
		btnNo = new  JButton("NO");
		
		pSur.add(btnSi);
		pSur.add(btnNo);
		
		lblPregunta = new JLabel("¿Quieres guardar tu reserva?");
		pCentro.add(lblPregunta);
		
		btnSi.addActionListener(e->{
			JFileChooser fileChooser = new JFileChooser();
			int var = fileChooser.showSaveDialog(null);
			if(var == JFileChooser.APPROVE_OPTION) {
				File FileSeleccionado = fileChooser.getSelectedFile();
				try {
					PrintWriter pw = new PrintWriter(FileSeleccionado);
					pw.println("DeustoParking\n"+ "Dni: "+ r.getDni()+ "\n"+ "Matricula de Coche: "+ r.getMatricula()+ "\n"+ "Ciudad: "+ r.getCiudad()+ "\n"+ "Fecha de Entrada: " + r.gethLlegada()+ "\n"+ "Fecha de Salida: " + r.gethSalida() + "\n"+ "Nombre del Parking: " + r.getNomParking()+ "\n"+ "Numero de Plaza: " + r.getNumPlaza()+ "\n"+ "Precio Total: "+ r.getPrecioTotal());
					pw.flush();
					pw.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Archivo guardado en: " + FileSeleccionado.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Reserva hecha correctamente");
			
			}else {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun archivo para guardar ");
				

			}
			vActual.dispose();
		});
		
		btnNo.addActionListener(e->{
			JOptionPane.showConfirmDialog(null, "Reserva hecha correctamente");
			vActual.dispose();
		});
		
		setBounds(500, 200, 200, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Guardar");
		ImageIcon icono = new ImageIcon("resources/imagenes/logito.png");
		setIconImage(icono.getImage());
		setVisible(true);
	}
}