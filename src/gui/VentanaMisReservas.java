package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class VentanaMisReservas extends JFrame {

	private JPanel pCentro, pCentroN, pCentroS, pSur, pNorte;
	private JButton btnCerrar;
	private JTextArea txtReserva;
	
	private ModeloTablaReserva modeloTabla; 
	private JTable tabla; 
	private JScrollPane scrollTabla;
	
	private JFrame vActual;
	public VentanaMisReservas(JFrame va) {
		
		pSur = new JPanel();
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		//Boton cerrar
		btnCerrar = new JButton("Cerrar");
		pSur.add(btnCerrar);
		btnCerrar.addActionListener((e)->{
			new VentanaInicio(vActual);
			vActual.dispose();
			
		});
		//Tamaño de la ventana
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Mis reservas");
		setVisible(true);
		
	}
	
	
	
	
	
}
			