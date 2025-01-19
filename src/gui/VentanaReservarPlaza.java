package gui;

import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.BD;
import domain.Contenedora;
import domain.Parking;
import domain.Plaza;
import domain.Reserva;
import main.Principal;

public class VentanaReservarPlaza extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel pCentro, pNorte, pSur, pCont, pPrincipal, pIzq, pComboBox;
	private JLabel lblReservarPlaza, lblParkings;
	private JButton btnSiguiente, btnVolver, btnRecursivo;
	private JFrame vActual;
	
	private ModeloTablaReservarPlaza modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
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
		} else if(r.getNomParking().equals("ParkingCentral")) {
			modeloCombo.addElement("-1");
			modeloCombo.addElement("-2");
			modeloCombo.addElement("-3");
		} else {
			modeloCombo.addElement("0");
			modeloCombo.addElement("1");
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
		btnRecursivo = new JButton("Simular Combinaciones");
		pSur.add(btnVolver);
		pSur.add(btnSiguiente);
		pSur.add(btnRecursivo);
		
		btnVolver.addActionListener((e)->{
			vActual.dispose();
			new VentanaReservaParking(vActual, r);
			
		});
		
		//Creación tabla
		Parking pa = new Parking(r.getNomParking());
		modeloTabla = new ModeloTablaReservarPlaza(pa);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		tabla.setDefaultRenderer(Object.class, new RendererTablaReservarPlaza(pa));
		pCentro.add(scrollTabla);
		
		tabla.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int fila = tabla.getSelectedRow();
		        int columna = tabla.getSelectedColumn();

		        if (fila != -1 && columna !=-1) {
		        	int i = modeloTabla.getPlaza(fila);
		        	if (i ==-1) { 
		            	JOptionPane.showMessageDialog(vActual, "La fila seleccionada no corresponde a una plaza válida.",
								"Error", JOptionPane.ERROR_MESSAGE);
							return;
		            }
		        	
		        	
		            Plaza p = (Plaza) modeloTabla.getValueAt(fila, columna);

		            if (p != null) { 
		                System.out.println(p.getNumPlaza() + p.getSeccion());

		                int opcion = JOptionPane.showOptionDialog(vActual,"¿Desea reservar esta plaza?",
		                    "Reservar Plaza",
		                    JOptionPane.YES_NO_OPTION,
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    new Object[]{"Sí", "No"},
		                    null
		                );

		                if (opcion == JOptionPane.YES_OPTION) {
		                	p.setOcupada(true);
		                	BD.actualizarEstadoPlaza(Principal.con, p.getPiso(),
									p.getSeccion(), i, true, p.isMinusvalido());
								r.setNumPlaza(i);
								r.setSeccion(p.getSeccion());
		                    JOptionPane.showConfirmDialog(vActual,"Plaza reservada correctamente.",
		                        "Éxito",JOptionPane.YES_NO_OPTION);
		                    
		                    modeloTabla.actualizarTablaPlaza();
							vActual.dispose();
							new VentanaIngresarDatos(vActual, r);
		                }
		            } else {
		            	JOptionPane.showMessageDialog(vActual, "No se seleccionó una plaza válida.",
								"Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		
		btnRecursivo.addActionListener((e)->{
			String s = JOptionPane.showInputDialog("Introduce el número de plazas que quieres combinar: ");
			if(s!=null) {
			    //int plazasLibres = 3; // Número de plazas libres en el parking
			    int plazasLibres = Integer.parseInt(s);
				List<String> combinaciones = Contenedora.obtenerCombinacionesAparcamiento(plazasLibres);
				//
				StringBuilder sb = new StringBuilder();
				for (String combinacion : combinaciones) {
				    sb.append(combinacion).append("\n"); // Añade la combinación seguida de un salto de línea
				}

				// Mostrar las combinaciones en una ventana de diálogo
				JOptionPane.showMessageDialog(null, sb.toString(),"COMBINACIONES",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});


		
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
        setTitle("Plazas");
        ImageIcon icono = new ImageIcon("resources/imagenes/logito.png");
		setIconImage(icono.getImage());
        setVisible(true);
		
		}
	
	//método filtrar plazas por planta
	private void filtrarPlazasPorPlanta(String planta) {
	    List<Plaza> plazasFiltradas = new ArrayList<>();
	    
	    //Recorremos la lista de plazas
	    for (Plaza plaza : BD.obtenerListaPlaza(Principal.con)) {
	    	
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