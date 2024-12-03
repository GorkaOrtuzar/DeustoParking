package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.BD;
import domain.Plaza;
import domain.Reserva;

public class VentanaReservarPlaza extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel pCentro, pNorte, pSur, pCont, pPrincipal, pIzq, pComboBox;
	private JLabel lblReservarPlaza, lblParkings;
	private JButton btnSiguiente, btnVolver;
	private JFrame vActual;
	
	private ModeloTablaReservarPlaza modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	private List<Plaza> lPlazas;
	private JComboBox<String> comboParking;
	private DefaultComboBoxModel<String> modeloCombo;
	
	
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
		pComboBox = new JPanel();
		lblParkings = new JLabel("Planta: ");
		comboParking = new JComboBox<>();
		modeloCombo  = new DefaultComboBoxModel<>();
		comboParking.setModel(modeloCombo);
		
		if(r.getNomParking().equals("ParkingTechado")) {
			modeloCombo.addElement("Izquierda");
			modeloCombo.addElement("Centro");
			modeloCombo.addElement("Derecha");
		} else {
			modeloCombo.addElement("-1");
			modeloCombo.addElement("-2");
			modeloCombo.addElement("-3");
		}
		
		pComboBox.add(lblParkings);
		pComboBox.add(comboParking);
		pIzq.add(pComboBox);
		
		comboParking.addActionListener((e)-> {
			String plantaSeleccionada = (String) comboParking.getSelectedItem();
			filtrarPlazasPorPlanta(plantaSeleccionada);
		});
		
		//Creación botón
		btnSiguiente = new JButton("Siguiente");
		btnVolver = new JButton("Volver");
		pSur.add(btnSiguiente);
		pSur.add(btnVolver);
		
		btnSiguiente.addActionListener((e)->{
			vActual.dispose();
			new VentanaIngresarDatos(vActual, r);
			
		});
		
		btnVolver.addActionListener((e)->{
			vActual.dispose();
			new VentanaReservaParking(vActual, r);
			
		});
		
		//Creación tabla
		modeloTabla = new ModeloTablaReservarPlaza();
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
	
	//método filtrar plazas por planta
	private void filtrarPlazasPorPlanta(String planta) {
	    List<Plaza> plazasFiltradas = new ArrayList<>();
	    
	    //Recorremos la lista de plazas
	    for (Plaza plaza : BD.obtenerListaPlaza(BD.initBD("db/deustoParking.db"))) {
	    	
	    	if(planta.equals("-1")&&plaza.getPiso().equals("-1")) {
	    		plazasFiltradas.add(plaza);
	    		
	    	}else if(planta.equals("Izquierda") && plaza.getPiso().equals("Izquierda")) {
	    		plazasFiltradas.add(plaza);
	    		
	    	}else if(planta.equals("-2") && plaza.getPiso().equals("-2")) {
	    		plazasFiltradas.add(plaza);
	    		
	    	}else if(planta.equals("Centro") && plaza.getPiso().equals("Centro")) {
	    		plazasFiltradas.add(plaza);
	    		
	    	}else if(planta.equals("-3") && plaza.getPiso().equals("-3")) {
	    		plazasFiltradas.add(plaza);
	    		
	    	}else if(planta.equals("Derecha") && plaza.getPiso().equals("Derecha")) {
	    		plazasFiltradas.add(plaza);
	    	}
	    }
	    
	    modeloTabla.actualizarDatos(plazasFiltradas); 
	}
	
	

}