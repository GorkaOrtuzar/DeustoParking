package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import db.BD;
import domain.Reserva;
import main.Principal;


public class VentanaPago extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pNorte, pCentro, pSur, pTarjeta, pFecha, pCVV, pTipoPago, pAceptarTerminos, pContTarjeta, pCombos, pContComboMes;
    private JLabel lblIntroDatos, lblConfirma, lblTitularTarjeta, lblPago, lblNumTarjeta, lblVisa, lblGooglePay, lblApplePay, lblMasterCard;
    private JTextField txtTitularTarjeta, txtNumTarjeta, txtCVV;
    private JComboBox<String> comboMes, comboAnio;
    private JButton btnSiguiente, btnVolver;
	private JRadioButton rbtnVisa, rbtnMaster, rbtnGoogle, rbtnApple;
    private JCheckBox cbtnTerminos;
    private ButtonGroup btnGrupo;
	private JFrame vActual, vAnterior;
	
	public VentanaPago(JFrame va, Reserva r) {
		super();
		
		vActual = this;
		vAnterior=va;
		
        pNorte = new JPanel();
		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
		
		lblIntroDatos = new JLabel("1. Introduce Datos");
		lblConfirma = new JLabel("2. Confirma Reserva");
		lblPago = new JLabel("3. Pago Final");
		lblPago.setForeground(Color.BLUE);
		
		pNorte.add(lblIntroDatos);
		pNorte.add(lblConfirma);
		pNorte.add(lblPago);
        
        pCentro = new JPanel();
        pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
        pCentro.add(Box.createVerticalStrut(40));
        
        pContTarjeta = new JPanel();
        pTarjeta = new JPanel();
        pTarjeta.setLayout(new GridLayout(2, 2, 100, 15)); 

        pContTarjeta.setPreferredSize(new Dimension(300, 80));
        pContTarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "1. Datos de la Tarjeta",
                        TitledBorder.LEFT, TitledBorder.CENTER, new Font("Arial", Font.BOLD, 14), Color.BLACK
                )
        ));

        lblTitularTarjeta = new JLabel("Titular de la Tarjeta ");
        txtTitularTarjeta = new JTextField("Ainara Maroto Fernández");
        txtTitularTarjeta.setForeground(Color.GRAY);
        txtTitularTarjeta.setPreferredSize(new Dimension(220, 30));

        txtTitularTarjeta.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTitularTarjeta.getText().equals("Ainara Maroto Fernández")) {
                    txtTitularTarjeta.setText("");
                    txtTitularTarjeta.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTitularTarjeta.getText().isEmpty()) {
                    txtTitularTarjeta.setText("Ainara Maroto Fernández");
                    txtTitularTarjeta.setForeground(Color.GRAY);
                }
            }
        });

        lblNumTarjeta = new JLabel("Número de Tarjeta");
        txtNumTarjeta = new JTextField("0000 0000 0000 0000");
        txtNumTarjeta.setForeground(Color.GRAY);
        txtNumTarjeta.setPreferredSize(new Dimension(220, 30));

        txtNumTarjeta.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtNumTarjeta.getText().equals("0000 0000 0000 0000")) {
                    txtNumTarjeta.setText("");
                    txtNumTarjeta.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtNumTarjeta.getText().isEmpty()) {
                    txtNumTarjeta.setText("0000 0000 0000 0000");
                    txtNumTarjeta.setForeground(Color.GRAY);
                }
            }
        });
        
        txtNumTarjeta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtNumTarjeta.getText().replaceAll("\\D", ""); 
                StringBuilder formattedText = new StringBuilder();
                
                for (int i = 0; i < text.length(); i++) {
                    if (i > 0 && i % 4 == 0) {
                        formattedText.append(" ");
                    }
                    formattedText.append(text.charAt(i));
                }
                
                txtNumTarjeta.setText(formattedText.toString());
            }
        });

        pTarjeta.add(lblTitularTarjeta);
        pTarjeta.add(lblNumTarjeta);
        pTarjeta.add(txtTitularTarjeta);
        pTarjeta.add(txtNumTarjeta);

        pContTarjeta.add(pTarjeta);

        pFecha = new JPanel();
        pFecha.setPreferredSize(new Dimension(300, 60));
        pFecha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "2. Fecha de Expiración",
                        TitledBorder.LEFT, TitledBorder.CENTER, new Font("Arial", Font.BOLD, 14), Color.BLACK
                )
        ));

        pCombos = new JPanel();
        pContComboMes = new JPanel();
        String[] meses = {"Mes", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        comboMes = new JComboBox<>(meses);
        comboMes.setPreferredSize(new Dimension(150, 40));
        String[] anios = {"Año", "2025", "2026", "2027", "2028", "2029"};
        comboAnio = new JComboBox<>(anios);
        comboAnio.setPreferredSize(new Dimension(150, 40));

        comboMes.addActionListener((e) -> {
            if (comboMes.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Por Favor, Seleccione un Mes", "ALERTA - Seleccione Mes", JOptionPane.WARNING_MESSAGE);
                comboMes.setSelectedIndex(1);
            }
        });

        comboAnio.addActionListener((e) -> {
            if (comboAnio.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Por Favor, Seleccione un Año", "ALERTA - Seleccione Año", JOptionPane.WARNING_MESSAGE);
                comboAnio.setSelectedIndex(1);
            }
        });

        pContComboMes.add(comboMes);
        pContComboMes.add(comboAnio);

        pCombos.add(pContComboMes);
        pFecha.add(pCombos);
        pFecha.add(Box.createHorizontalStrut(100));

        pCVV = new JPanel();
        pCVV.setLayout(new FlowLayout());
        txtCVV = new JTextField("CVV");
        txtCVV.setForeground(Color.GRAY);
        txtCVV.setPreferredSize(new Dimension(150, 40));
        txtCVV.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtCVV.getText().equals("CVV")) {
                    txtCVV.setText("");
                    txtCVV.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtCVV.getText().isEmpty()) {
                    txtCVV.setText("CVV");
                    txtCVV.setForeground(Color.GRAY);
                }
            }
        });
        pCVV.add(txtCVV);
        pFecha.add(pCVV);

        pTipoPago = new JPanel();
        pTipoPago.setLayout(new GridLayout(2, 4));
        pTipoPago.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "3. Seleccionar la forma de pago",
                        TitledBorder.LEFT, TitledBorder.CENTER, new Font("Arial", Font.BOLD, 14), Color.BLACK
                )
        ));

        btnGrupo = new ButtonGroup();

        cargarImg("resources/imagenes/visa.png", lblVisa, pTipoPago);
        rbtnVisa = new JRadioButton("Tarjeta Visa");
        btnGrupo.add(rbtnVisa);
        pTipoPago.add(rbtnVisa);

        cargarImg("resources/imagenes/masterCard.png", lblMasterCard, pTipoPago);
        rbtnMaster = new JRadioButton("Tarjeta MasterCard");
        btnGrupo.add(rbtnMaster);
        pTipoPago.add(rbtnMaster);

        cargarImg("resources/imagenes/googlePay.png", lblGooglePay, pTipoPago);
        rbtnGoogle = new JRadioButton("GooglePay");
        btnGrupo.add(rbtnGoogle);
        pTipoPago.add(rbtnGoogle);

        cargarImg("resources/imagenes/applePay.png", lblApplePay, pTipoPago);
        rbtnApple = new JRadioButton("ApplePay");
        btnGrupo.add(rbtnApple);
        pTipoPago.add(rbtnApple);

        pAceptarTerminos = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 40));
		
		cbtnTerminos = new JCheckBox("He leído y acepto los Términos y Condiciones de Uso de DeustoParking.");
		pAceptarTerminos.add(cbtnTerminos);

        btnSiguiente = new JButton("Finalizar Compra");
		btnSiguiente.addActionListener((e)->{
		
		if(txtTitularTarjeta.getForeground().equals(Color.BLACK)) {
			if(txtNumTarjeta.getText().matches("\\d{4} \\d{4} \\d{4} \\d{4}") && txtNumTarjeta.getForeground().equals(Color.BLACK)) {
				if(!(comboMes.getSelectedIndex()==0)) {
					if(!(comboAnio.getSelectedIndex()==0)) {
						if(txtCVV.getForeground().equals(Color.BLACK) && txtCVV.getText().length()== 3) {
							if ((rbtnVisa.isSelected()||rbtnMaster.isSelected()||rbtnGoogle.isSelected()||rbtnApple.isSelected())){
								if(cbtnTerminos.isSelected()) {
									vActual.dispose(); 
									new VentanaProcesandoPago(vActual, r);
								}else {
									JOptionPane.showMessageDialog(null, "Acepte los Términos y Condiciones de Uso", "ERROR - Términos y Condiciones de Uso", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Seleccione un Método de Pago", "ERROR - Forma de Pago", JOptionPane.ERROR_MESSAGE);		
							}				
						}else {
							JOptionPane.showMessageDialog(null, "CVV Incorrecto", "ERROR - CVV", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Año Incorrecto", "ERROR - AÑO", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Mes Incorrecto", "ERROR - MES", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Número de Tarjeta Incorrecto", "ERROR - Número de Tarjeta", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Introduzca al Titular de la Tarjeta", "ERROR - Titular de la Tarjeta", JOptionPane.ERROR_MESSAGE);
		}	
		BD.insertarReserva(Principal.con, r);
	});
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener((e)->{
			vActual.dispose();
			vAnterior.setVisible(true);
		});
		
        pSur = new JPanel();
        pSur.setLayout(new FlowLayout(FlowLayout.CENTER));
        pSur.add(btnVolver);
        pSur.add(Box.createHorizontalStrut(30));
        pSur.add(btnSiguiente);
        
        pCentro.add(pContTarjeta);
        pCentro.add(pFecha);
        pCentro.add(pTipoPago);
        pCentro.add(pAceptarTerminos);

        setLayout(new BorderLayout());
        add(pNorte, BorderLayout.NORTH);
        add(pCentro, BorderLayout.CENTER);
        add(pSur, BorderLayout.SOUTH);

        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50, 50, 1600, 800);
        setTitle("Pago");
        ImageIcon icono = new ImageIcon("resources/imagenes/logito.png");
		setIconImage(icono.getImage());
        setVisible(true);
	}
	public void cargarImg(String ruta, JLabel lbl, JPanel p) {
        ImageIcon icon = new ImageIcon(ruta);
        lbl = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        p.add(lbl);
    }
	
}