package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import domain.Reserva;
import domain.Usuario;

public class VentanaResumen extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pNorte, pSur, pCentro, pCentroN, pCentroS, pCalendarios, pTitulo, pFechaEntrada, pFechaSalida,
	pContCalendario, pInfoReserva, pInfoUsuario, pContDatos, pCont, pfr1, pfr2, pfr3, pContR, pfu1, pfu2, pfu3,
	pContU;
	private JLabel lblIntroDatos,lblConfirma,lblPago, lblTitulo, lblFechaEntrada, lblFechaSalida,
	lblInfo, lblVacia, lblNomParking, lblNomPlaza, lblPrecioTotal, lblNomParkingRe, lblNomPlazaRe, lblPrecioTotalRe,
	lblNombreApellidoUsu, lblNombreApellidoUsuRe, lblTelefonoUsu, lblTelefonoUsuRe, lblMatriculaUsu,
	lblMatriculaUsuRe;
	private JButton btnAtras, btnConfirReserva;
	private JDatePanelImpl dpaFechaEntradaPanel, dpaFechaSalidaPanel;
	private UtilDateModel modeloEntrada, modeloSalida;
	
	private JFrame vActual, vAnterior;
	
	public VentanaResumen(JFrame va ,Reserva r, Usuario u) {
		super();
		vActual = this;
		vAnterior = va;
		setBounds(300, 200, 300, 300);
		
		pNorte = new JPanel();
		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
		
		pCentro = new JPanel();
		pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
		
		pCentroN = new JPanel();
		pCentroN.setLayout(new BoxLayout(pCentroN, BoxLayout.Y_AXIS));
		
		pCentroS = new JPanel();
		
		pTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 60));
		
		pCalendarios = new JPanel();
		
		pSur = new JPanel();
		pSur.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		
		//Panel Norte
		lblIntroDatos = new JLabel("1. Introduce Datos");
		lblConfirma = new JLabel("2. Confirma Reserva");
		lblPago = new JLabel("3. Pago Final");
		lblConfirma.setForeground(Color.BLUE);
		
		pNorte.add(lblIntroDatos);
		pNorte.add(lblConfirma);
		pNorte.add(lblPago);
		
		
		//Panel Centro
		pCentro.add(pCentroN);
		
		lblTitulo = new JLabel("DETALLES DE LA RESERVA");
		lblTitulo.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
		pTitulo.add(lblTitulo);
		pCentroN.add(pTitulo);
		
		modeloEntrada = new UtilDateModel();
		modeloSalida = new UtilDateModel();
		
		configurarModeloFecha(modeloEntrada, r.gethLlegada());
		configurarModeloFecha(modeloSalida, r.gethSalida());
		
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Año");
		
		dpaFechaEntradaPanel = new JDatePanelImpl(modeloEntrada, p);
		dpaFechaEntradaPanel.addActionListener(e -> {
			modeloEntrada.setValue(r.gethLlegada());
			modeloEntrada.setSelected(true);
		});
		
		dpaFechaSalidaPanel = new JDatePanelImpl(modeloSalida, p);
		dpaFechaSalidaPanel.addActionListener(e -> {
			modeloSalida.setValue(r.gethSalida());
			modeloSalida.setSelected(true);
		});
		
		//Contenedores para Selectores Fecha
		pFechaEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pFechaSalida = new JPanel(new FlowLayout(FlowLayout.LEFT));
				
		dpaFechaEntradaPanel.setPreferredSize(new Dimension(320, 320));
		dpaFechaSalidaPanel.setPreferredSize(new Dimension(320, 320));
				
		pFechaEntrada.add(dpaFechaEntradaPanel);
		pFechaSalida.add(dpaFechaSalidaPanel);
		
		lblFechaEntrada = new JLabel("Fecha Entrada");
		lblFechaEntrada.setFont(new Font("Arial", Font.BOLD, 14));
		lblFechaSalida = new JLabel("Fecha Salida");
		lblFechaSalida.setFont(new Font("Arial", Font.BOLD, 14));
		
		pFechaEntrada.setLayout(new BoxLayout(pFechaEntrada, BoxLayout.Y_AXIS));
		pFechaEntrada.add(lblFechaEntrada);
		pFechaEntrada.add(Box.createVerticalStrut(15));
		pFechaEntrada.add(dpaFechaEntradaPanel);
		dpaFechaEntradaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		pFechaSalida.setLayout(new BoxLayout(pFechaSalida, BoxLayout.Y_AXIS));
		pFechaSalida.add(lblFechaSalida);
		pFechaSalida.add(Box.createVerticalStrut(15));
		pFechaSalida.add(dpaFechaSalidaPanel);
		dpaFechaSalidaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		pCalendarios.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pCalendarios.add(pFechaEntrada);
		pCalendarios.add(Box.createHorizontalStrut(100));
		pCalendarios.add(pFechaSalida);
		
		pContCalendario = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pContCalendario.add(pCalendarios);
		
		pCentroN.add(pContCalendario);
		
		pCentroS.setPreferredSize(new Dimension(700, 300));
		pCentro.add(pCentroS);
		lblInfo = new JLabel("Información");
		lblInfo.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		lblNomParking = new JLabel("Nombre Parking: ");
		cambiarLetra(lblNomParking);
		lblNomParkingRe = new JLabel(r.getNomParking());
		cambiarLetraRespuesta(lblNomParkingRe);
		lblNomPlaza = new JLabel("Número Plaza: ");
		cambiarLetra(lblNomPlaza);
		lblNomPlazaRe = new JLabel(String.valueOf(r.getNumPlaza()));
		cambiarLetraRespuesta(lblNomPlazaRe);
		lblPrecioTotal = new JLabel("Precio Total: ");
		cambiarLetra(lblPrecioTotal);
		lblPrecioTotalRe = new JLabel(String.valueOf(r.getPrecioTotal())+ "€");
		cambiarLetraRespuesta(lblPrecioTotalRe);
		
		pfr1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		pfr2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		pfr3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		pInfoReserva = new JPanel();
		pInfoReserva.setLayout(new BoxLayout(pInfoReserva, BoxLayout.Y_AXIS));
		pfr1.add(lblNomParking);
		pfr1.add(lblNomParkingRe);
		pfr2.add(lblNomPlaza);
		pfr2.add(lblNomPlazaRe);
		pfr3.add(lblPrecioTotal);
		pfr3.add(lblPrecioTotalRe);
		
		pInfoReserva.add(pfr1);
		pInfoReserva.add(pfr2);
		pInfoReserva.add(pfr3);
		
		pContR = new JPanel(new BorderLayout());
        pContR.setPreferredSize(new Dimension(400, 150));
        pContR.add(pInfoReserva, BorderLayout.CENTER);
        
        lblNombreApellidoUsu = new JLabel("Nombre y Apellido: ");
        cambiarLetra(lblNombreApellidoUsu);
        lblNombreApellidoUsuRe= new JLabel(u.getNombre() + " " + u.getApellido());
        cambiarLetraRespuesta(lblNombreApellidoUsuRe);
        lblTelefonoUsu = new JLabel("Teléfono: ");
        cambiarLetra(lblTelefonoUsu);
        lblTelefonoUsuRe = new JLabel(u.getTlf());
        cambiarLetraRespuesta(lblTelefonoUsuRe);
        lblMatriculaUsu = new JLabel("Matrícula: ");
        cambiarLetra(lblMatriculaUsu);
        lblMatriculaUsuRe = new JLabel(r.getMatricula());
        cambiarLetraRespuesta(lblMatriculaUsuRe);
		
		pfu1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pfu2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pfu3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        pInfoUsuario = new JPanel();
        pInfoUsuario.setLayout(new BoxLayout(pInfoUsuario, BoxLayout.Y_AXIS));
        pfu1.add(lblNombreApellidoUsu);
        pfu1.add(lblNombreApellidoUsuRe);    
        pfu2.add(lblTelefonoUsu); 
        pfu2.add(lblTelefonoUsuRe); 
        pfu3.add(lblMatriculaUsu);               
        pfu3.add(lblMatriculaUsuRe); 
        
        pInfoUsuario.add(pfu1);
        pInfoUsuario.add(pfu2);
        pInfoUsuario.add(pfu3);
        
        pContU = new JPanel(new BorderLayout());
        pContU.setPreferredSize(new Dimension(new Dimension(400, 150)));
        pContU.add(pInfoUsuario, BorderLayout.CENTER);
        
        pContDatos = new JPanel();
        pContDatos.setLayout(new BoxLayout(pContDatos, BoxLayout.Y_AXIS));
        pContDatos.add(Box.createVerticalStrut(10));
        pContDatos.add(lblInfo);
        pContDatos.add(Box.createVerticalStrut(30));
        
        pCont = new JPanel();
        pCont.setLayout(new BoxLayout(pCont, BoxLayout.X_AXIS));
        pCont.add(pContR);
        pCont.add(Box.createHorizontalStrut(30));
        pCont.add(pContU);
        pContDatos.add(pCont);
        pCentroS.add(pContDatos);
		
		//Panel Sur
		btnAtras = new JButton("Atrás");
		btnAtras.addActionListener((e)->{
			vActual.dispose();
			vAnterior.setVisible(true);
			
		});
		

		btnConfirReserva = new JButton("Confirmar Reserva");
		btnConfirReserva.addActionListener((e)->{
			vActual.dispose(); 
			new VentanaPago(vActual, r);
		});

		pSur.add(btnAtras);
		pSur.add(btnConfirReserva);
		
		
		//Tamaño de la ventana
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1600, 800);
		setTitle("Resumen");
		setVisible(true);	
	}
	
	private void configurarModeloFecha(UtilDateModel modelo, Date fecha) {
		if (fecha != null) {
			// Convierte un objeto Date en un LocalDate con la zona horaria predeterminada
			// del sistema, extrayendo solo el año, mes y día.
			LocalDate localFecha = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //
			modelo.setDate(localFecha.getYear(), localFecha.getMonthValue() - 1, // Enero pasa de la pos=1 a pos=0
					localFecha.getDayOfMonth());
			modelo.setSelected(true);
		}
	}

	private void cambiarLetra(JLabel lb) {
		lb.setFont(new Font("Arial", Font.BOLD, 18));
	}

	private void cambiarLetraRespuesta(JLabel lb) {
		lb.setFont(new Font("Arial", Font.BOLD, 16));
		lb.setForeground(new Color(141, 140, 144));
	}

}
