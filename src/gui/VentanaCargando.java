package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class VentanaCargando extends JFrame{
	private JProgressBar bProgreso;
	private JLabel lbl;
	private JFrame vActual;
	
	public VentanaCargando() {
		super();
		vActual = this;
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Cargando...");
		
		lbl = new JLabel(new ImageIcon("imagenes\\LogoDeustoParking.png"));
		lbl.setLayout(new BorderLayout());
		
		bProgreso = new JProgressBar();
		bProgreso.setStringPainted(true);
		bProgreso.setForeground(Color.YELLOW);
		
		bProgreso.setUI(new BasicProgressBarUI() {
			 protected Color getSelectionBackground() {
                 return new Color(0,0,0); 
             }
             
             protected Color getSelectionForeground() {
                 return new Color(0,0,0);
             }
		});
		
		lbl.add(bProgreso, BorderLayout.SOUTH);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().add(lbl);
		setVisible(true);
		
		Thread hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i=0; i<101; i++) {
					final int progrressValue = i;
					try {
						Thread.sleep(35);
						
						SwingUtilities.invokeLater(new Runnable() {
							
							@Override
							public void run() {
								bProgreso.setValue(progrressValue);					
								
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				dispose();
				new VentanaInicio(vActual);
				vActual.setVisible(false);
				
			}
		});hilo.start();
		
		
		
	}
	public static void main(String[] args) {
		VentanaCargando vc = new VentanaCargando();
	}

}
