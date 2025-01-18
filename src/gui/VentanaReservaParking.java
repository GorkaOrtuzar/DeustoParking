package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import db.BD;
import domain.Reserva;
import domain.Utilidades;
import main.Principal;

public class VentanaReservaParking extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	
	//Declaramos valores
	private JPanel pCentro, pCentroN, pCentroS, pSur, pNorte,pEste;
	private JButton btnAtras, btnReservar;
	private JTextArea txtReserva;
	private JLabel lblMenor20,lblentre20y50,lblentre50y100,lblmas100,lblLibre;
	
	//Declaramos tabla
	private ModeloTablaReservaParking modeloTabla; 
	private JTable tabla; 
	private JScrollPane scrollTabla;
	
	private JFrame vActual;
	private JFrame vAnterior;
	
	private static List<Reserva> listaReservas = new ArrayList<>();
	
	
	public VentanaReservaParking(JFrame va, Reserva r) {
		super();
		vActual = this;
		vAnterior = va;
		
		
		//Tamaño Ventana
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Instanciar Paneles, TextArea y Botones
		pNorte = new JPanel();
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(2, 1)); //2 filas, 1 columna
		pSur = new JPanel();
		//pSur.setLayout(new GridLayout(1, 2));
		
		
		pCentroN = new JPanel();
		pCentroS = new JPanel();
		
		txtReserva = new JTextArea();
		txtReserva.setColumns(40);
		txtReserva.setEditable(false);

		pEste = new JPanel();
		pEste.setLayout(new BoxLayout(pEste,BoxLayout.Y_AXIS));
		getContentPane().add(pEste, BorderLayout.EAST);

		
		btnAtras = new JButton("Atrás");
		
		btnAtras.addActionListener((e)->{
			vActual.dispose();
			vAnterior.setVisible(true);
			
		});
		
		btnReservar = new JButton("Reservar");
		
		btnReservar.addActionListener((e)-> {
			int fila = tabla.getSelectedRow();
			if(fila!=-1) {
				String nomParking = tabla.getValueAt(fila, 0).toString();
				int numPlazasLibre = Integer.parseInt(tabla.getValueAt(fila, 1).toString());
				float precio = Float.parseFloat(tabla.getValueAt(fila, 2).toString());
				
				String ciudad = r.getCiudad();
				String fechaEntrada = Utilidades.dateToString(r.gethLlegada());
				String fechaSalida = Utilidades.dateToString(r.gethSalida());
				
				Reserva reservaFinal = new Reserva(ciudad, fechaEntrada, fechaSalida, nomParking, numPlazasLibre, precio);
				listaReservas.add(reservaFinal);
				
//				for (Reserva reserva : listaReservas) {
//					System.out.println(reserva);
//				}
//				
				vActual.dispose();
				new VentanaReservarPlaza(vActual, reservaFinal);
			}else {
				JOptionPane.showMessageDialog(vActual, "Por favor, seleccione un parking","MENSAJE IMPORTANTE", JOptionPane.WARNING_MESSAGE);
			}
			
			
		});
		
		
		//Añadimos paneles al panel central
		
		//Creamos Tabla
		modeloTabla = new ModeloTablaReservaParking(null);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		scrollTabla.setBorder(BorderFactory.createEmptyBorder());
		tabla.setDefaultRenderer(Object.class, new RendererTablaReservaParking());
		cargarTablaDatos();
		getContentPane().add(scrollTabla, BorderLayout.CENTER);
		
		//Renderer Tabla
		modeloTabla = (ModeloTablaReservaParking) tabla.getModel();
		tabla.setModel(modeloTabla);
		pCentroN.add(scrollTabla);
		
		tabla.setDefaultRenderer(Object.class, new RendererTablaReservaParking());
		tabla.getTableHeader().setDefaultRenderer(new RendererCabeceraTabla());
		pCentro.add(pCentroN);

		pCentro.add(pCentroS);
		pCentroS.add(txtReserva);
		pCentroS.add(btnReservar);
		pSur.add(btnAtras);
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		//Leyenda de Colores del Parking
		//Para hacer reborde, siguente método:
//		pDatosPago.setBorder(BorderFactory.createTitledBorder(
//                BorderFactory.createLineBorder(Color.GRAY),"Leyenda de Colores",
//                TitledBorder.LEFT,TitledBorder.CENTER,new Font("Arial", Font.BOLD, 14),Color.BLACK
//        ));
		
		tabla.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tabla.getSelectedRow();
				if(fila!=-1) {
					String nomParking = tabla.getValueAt(fila, 0).toString();
					int numPlazasLibres = Integer.parseInt(tabla.getValueAt(fila, 1).toString());
					float precio = Float.parseFloat(tabla.getValueAt(fila, 2).toString()) ;
					
					txtReserva.setText("Nombre Parking: " + nomParking + "  " + "Plazas Libres: " + numPlazasLibres + "  " + "Precio: " + precio +"€");
					
				}
				
			}
		});
		
		//
		 JPanel pMenor20 = new JPanel();
		 pMenor20.setPreferredSize(new Dimension(5,5));
		 pMenor20.setBackground(new Color(251, 53, 29));
		 lblMenor20 = new JLabel("Parking que tiene menos de 20 plazas libres");
		 JPanel pEntre20y50 = new JPanel();
		 pEntre20y50.setPreferredSize(new Dimension(5,5));
		 pEntre20y50.setBackground(new Color(248,190,56));
		 lblentre20y50 = new JLabel("Parking que tiene entre 20 y 50 plazas libres");
		 JPanel pEntre50y100 = new JPanel();
		 pEntre50y100.setPreferredSize(new Dimension(5,5));
		 pEntre50y100.setBackground(new Color(250,238,88));
		 lblentre50y100 = new JLabel("Parking que tiene entre 50 y 100 plazas libres");
		 JPanel pMas100 = new JPanel();
		 pMas100.setPreferredSize(new Dimension(5,5));
		 pMas100.setBackground(new Color(27, 221, 11));
		 lblmas100 = new JLabel("Parking que tiene mas de 100 plazas libres");
		 JPanel pLibre = new JPanel();
		 pLibre.setPreferredSize(new Dimension(5,5));
		 pLibre.setBackground(Color.BLUE);
		 lblLibre = new JLabel("Parkig vacio");
			
		pEste.setPreferredSize(new Dimension(300, 10));
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(5, 1));
		JPanel p2 = new JPanel(new GridLayout(5, 1));
		p.add(p1, BorderLayout.WEST);
		p.add(p2, BorderLayout.CENTER);
		//getContentPane().add(pEste, BorderLayout.EAST);
		pEste.add(p);	

			p1.add(pMenor20);
			p2.add(lblMenor20);
			p1.add(pEntre20y50);
			p2.add(lblentre20y50);
			p1.add(pEntre50y100);
			p2.add(lblentre50y100);
			p1.add(pMas100);
			p2.add(lblmas100);
			p1.add(pLibre);
			p2.add(lblLibre);
		
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());

		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		pEste.add(new JPanel());
		
		//Tamaño de la ventana
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Reservar plaza de parking");
		ImageIcon icono = new ImageIcon("resources/imagenes/logito.png");
		setIconImage(icono.getImage());
		setVisible(true);
	}
	

	private void cargarTablaDatos() {
//		List<Parking> lp = new ArrayList<>();
//		lp.add(new Parking("ParkingVIP", 30f, 17));
//		lp.add(new Parking("ParkingCentral", 25.5f, 105));
//		lp.add(new Parking("ParkingTechado", 17.90f, 237));
		modeloTabla = (ModeloTablaReservaParking) tabla.getModel();
		modeloTabla.setlParkings(BD.obtenerListaParking(Principal.con));
		tabla.setModel(modeloTabla);
	}

}
