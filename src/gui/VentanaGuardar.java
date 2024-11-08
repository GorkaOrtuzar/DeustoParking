package gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaGuardar extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel pSur,pCentro;
	private JButton btnSi, btnNo;
	private JLabel lblPregunta;
	private JFrame vActual;
	
	public VentanaGuardar(JFrame va) {
		
		vActual = this;
		pSur = new JPanel();
		pCentro = new JPanel();
		
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//botones
		btnSi = new JButton("SI");
		btnNo = new  JButton("NO");
		
		pSur.add(btnSi);
		pSur.add(btnNo);
		
		//label
		lblPregunta = new JLabel("¿Quieres guardar tu reserva?");
		pCentro.add(lblPregunta);
		
		//ActionListeners
		btnSi.addActionListener(e->{
			//JFileChooser
			JFileChooser fileChooser = new JFileChooser();
			int var = fileChooser.showSaveDialog(null);
			if(var == JFileChooser.APPROVE_OPTION) {
				File FileSeleccionado = fileChooser.getSelectedFile();
				JOptionPane.showMessageDialog(null, "Archivo guardado en: " + FileSeleccionado.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Reserva hecha corrctamente");
			
			}else {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun archivo para guardar ");
				

			}
			vActual.dispose();
		});
		
		btnNo.addActionListener(e->{
			JOptionPane.showConfirmDialog(null, "Reserva hecha corrctamente");
			vActual.dispose();
		});
		
		setBounds(500, 200, 200, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Guardar");
		setVisible(true);
	}
	
	
}
