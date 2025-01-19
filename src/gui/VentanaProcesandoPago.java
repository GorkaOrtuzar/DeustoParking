package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import domain.Reserva;

public class VentanaProcesandoPago extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblGif;
	private JProgressBar pbEspera;
	private JFrame vActual;
	
	public VentanaProcesandoPago(JFrame va, Reserva r) {
		super();
		vActual = this;
		
		lblGif = new JLabel(new ImageIcon("resources/imagenes/cocheGif.gif"));
		lblGif.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblGif, BorderLayout.CENTER);
		
		pbEspera = new JProgressBar();
		pbEspera.setForeground(Color.ORANGE);
		pbEspera.setIndeterminate(true);
		add(pbEspera, BorderLayout.SOUTH);
		
		SwingWorker<Void, Void> barra = new SwingWorker<>() {
			@Override
			protected Void doInBackground() throws Exception {
				
				Thread.sleep(5000);
				
				SwingUtilities.invokeLater(() -> {
                    lblGif.setIcon(new ImageIcon("resources/imagenes/procesandoParado.jpg"));
                    JOptionPane.showMessageDialog(null, "¡La compra se ha realizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    pbEspera.setIndeterminate(false);
                    vActual.dispose(); 
					new VentanaGuardar(vActual ,r);
                });
				
				return null;
			}
			
			@Override
			protected void done() {
				pbEspera.setIndeterminate(false);
			}
			
		};
		
		barra.execute();
		
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50, 50, 1600, 800);
        setTitle("Procesando Pago...");
        ImageIcon icono = new ImageIcon("resources/imagenes/logito.png");
		setIconImage(icono.getImage());
        setVisible(true);
	}
}