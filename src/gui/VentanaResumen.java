package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Reserva;
import domain.Usuario;

public class VentanaResumen extends JFrame{
	private JPanel pNorte, pSur, pCentro, pCentroN, pCentroS, pCalendarios, pTitulo;
	private JLabel lblIntroDatos,lblConfirma,lblPago, lblTitulo, lblFechaEntrada, lblFechaSalida,
	lblInfo, lblVacia, lblNomParking, lblNomPlaza, lblPrecioTotal, lblNomParkingRe, lblNomPlazaRe, lblPrecioTotalRe;
	private JButton btnAtras, btnConfirReserva;
	
	private JFrame vActual;
	private JFrame vAnterior;
	
	@SuppressWarnings("unchecked")
	public VentanaResumen(JFrame va ,Reserva r) {
		super();
		vActual = this;
		vAnterior = va;
		setBounds(300, 200, 300, 300);
		
		pNorte = new JPanel();
		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
		
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(2, 0));
		pCentroN = new JPanel();
		pCentroN.setLayout(new GridLayout(2, 0));
		pCentroS = new JPanel();
		pCentroS.setLayout(new GridLayout(4, 2));
		pTitulo = new JPanel();
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
		pTitulo.setBorder(new EmptyBorder(40,20,20,20));
		pCentroN.add(pTitulo);
		
		lblFechaEntrada = new JLabel("Fecha Entrada");
		lblFechaEntrada.setBorder(new EmptyBorder(0,0,0,200));
		lblFechaSalida = new JLabel("Fecha Salida");
		pCalendarios.add(lblFechaEntrada);
		pCalendarios.add(lblFechaSalida);
		pCentroN.add(pCalendarios);

		pCentro.add(pCentroS);
		lblInfo = new JLabel("Información");
		Font letra = new Font(Font.DIALOG, Font.BOLD, 20);
		@SuppressWarnings("rawtypes")
		Map atributos = letra.getAttributes();
		atributos.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblInfo.setFont(letra.deriveFont(atributos));
		lblInfo.setBorder(new EmptyBorder(0,0,0,1));
		lblVacia = new JLabel("      ");
		lblNomParking = new JLabel("Nombre Parking: ");
		lblNomParking.setBorder(new EmptyBorder(0,0,0,1));
		lblNomParkingRe = new JLabel(r.getNomParking());
		lblNomParkingRe.setForeground(Color.RED);
		lblNomPlaza = new JLabel("Número Plaza: ");
		lblNomPlaza.setBorder(new EmptyBorder(0,0,0,1));
		lblNomPlazaRe = new JLabel(String.valueOf(r.getNumPlaza()));
		lblPrecioTotal = new JLabel("Precio Total: ");
		lblPrecioTotal.setBorder(new EmptyBorder(0,0,0,1));
		lblPrecioTotalRe = new JLabel(String.valueOf(r.getPrecioTotal()));
		
		pCentroS.add(lblInfo);
		pCentroS.add(lblVacia);
		pCentroS.add(Box.createHorizontalStrut(0));

		pCentroS.add(lblNomParking);
		pCentroS.add(lblNomParkingRe);
		pCentroS.add(Box.createHorizontalStrut(0));

		pCentroS.add(lblNomPlaza);
		pCentroS.add(lblNomPlazaRe);
		pCentroS.add(Box.createHorizontalStrut(0));

		pCentroS.add(lblPrecioTotal);
		pCentroS.add(lblPrecioTotalRe);
		pCentroS.add(Box.createHorizontalStrut(0));
		
		pCentroS.setBorder(new EmptyBorder(0, 450, 40, 0));
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
		setTitle("Reservar Plaza de Parking");
		setVisible(true);	
	}

}
