package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.Plaza;
import domain.Reserva;

public class VentanaReservarPlaza extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel pCentro, pNorte, pSur, pCont, pPrincipal, pIzq;
	private JLabel lblReservarPlaza;
	private JButton btnSiguiente;
	private JFrame vActual;
	
	private ModeloTablaReservarPlaza modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	
	
	public VentanaReservarPlaza(JFrame va, Reserva r) {
		super();
		
		vActual = this;
		
		setBounds(300,200,600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Paneles
		pCont = new JPanel();
		pCont.setLayout(new BorderLayout());
		
		pPrincipal = new JPanel();
        pPrincipal.setLayout(new BorderLayout());
		pCentro = new JPanel();
        pCentro.setLayout(new BorderLayout());
        
		pNorte = new JPanel();
		pSur = new JPanel();
		pIzq = new JPanel();
		
		//Label 
		lblReservarPlaza = new JLabel("Reservar Plaza");
		lblReservarPlaza.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		pNorte.add(lblReservarPlaza);
		
		//ComboBox
		JPanel pComboBox = new JPanel();
		JLabel Parkings = new JLabel("Planta: ");
		String[] parkings = {"0", "-1", "-2", "-3"};
		JComboBox<String> comboParking = new JComboBox<String>(parkings);
		pComboBox.add(Parkings);
		pComboBox.add(comboParking);
		pIzq.add(pComboBox);
		
		//Creación botón
		btnSiguiente = new JButton("Siguiente");
		pSur.add(btnSiguiente);
		
		btnSiguiente.addActionListener((e)->{
			vActual.dispose();
			new VentanaIngresarDatos(vActual, r);
			
			
		});
		
		
		//Creación de la lista
		List<Plaza> lPlazas = Arrays.asList(
				new Plaza(1, "A", 1, false),   
	            new Plaza(1, "B", 1, true),   
	            new Plaza(1, "A", 3, true) 
		);
		
		
		//Creación tabla
		modeloTabla = new ModeloTablaReservarPlaza(lPlazas);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		tabla.setDefaultRenderer(Object.class, new RendererTablaReservarPlaza());
		pCentro.add(scrollTabla);
		
		//Quitar bordes a las celdas
		tabla.setIntercellSpacing(new Dimension(0, 0));  
		tabla.setBorder(null);
		tabla.setRowHeight(80);
	     
		//Quitar bordes a las celdas
		tabla.setIntercellSpacing(new Dimension(0, 0));  
		tabla.setBorder(null);
		tabla.setRowHeight(80);
		
		pCont.add(pIzq, BorderLayout.WEST);
		pCont.add(pNorte, BorderLayout.NORTH);
		pCont.add(pCentro, BorderLayout.CENTER);
		pCont.add(pSur, BorderLayout.SOUTH);
		pPrincipal.add(pCont);
		
		getContentPane().add(pPrincipal, BorderLayout.CENTER);
		
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