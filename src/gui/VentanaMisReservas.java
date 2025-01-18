package gui;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import db.BD;
import domain.Reserva;
import domain.Usuario;
import main.Principal;

public class VentanaMisReservas extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel pCentro, pSur, pNorte, pDatos;
	private JButton btnCerrar,btnEditable;
	private JLabel lblReserva;
	private JLabel lblNombre, lblApellido,lbltlf,lbldni,lblContrasenia;
	private JTextField txtNombre,txtApellido,txttlf,txtdni,txtContrasenia;
	private ModeloTablaMisReservas modeloTabla; 
	private JTable tabla; 
	private JScrollPane scrollTabla;
	@SuppressWarnings("unused")
	private JFrame vActual, vAnterior;
	private boolean editable;
	public VentanaMisReservas(JFrame va, String dni ) {
		vActual = this;
		vAnterior = va;
		editable = true;
		
		//Instanciar los paneles
		pNorte = new JPanel();
		pSur = new JPanel();
		pCentro = new JPanel();
		pCentro.setBorder(new EmptyBorder(100, 100, 100, 100));
		pCentro.setLayout(new GridLayout(1, 2));
		pDatos = new JPanel();
		pDatos.setLayout(new BoxLayout(pDatos,BoxLayout.Y_AXIS));
		pDatos.setBorder(new EmptyBorder(50, 200, 50, 200));
		pDatos.setLayout(new GridLayout(5, 1));
		
		 pDatos.setBorder(BorderFactory.createCompoundBorder(
	                BorderFactory.createEmptyBorder(0, 100, 0, 100),
	                BorderFactory.createTitledBorder(
	                        BorderFactory.createLineBorder(Color.GRAY), "Datos Personales",
	                        TitledBorder.LEFT, TitledBorder.CENTER, new Font("Arial", Font.BOLD, 14), Color.BLACK
	                )
	        ));

		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		

		//Titulo
		lblReserva = new JLabel("MIS RESERVAS");
		lblReserva.setFont(new Font(Font.DIALOG, Font.ITALIC, 18));
		pNorte.add(lblReserva);
		
		//datos del usuario
		lblNombre = new JLabel(" - NOMBRE: " );
		txtNombre = new JTextField(BD.buscarUsario(Principal.con,dni).getNombre());
		lblApellido = new JLabel(" - APELLIDO: " );
		txtApellido = new JTextField( BD.buscarUsario(Principal.con,dni).getApellido());
		lbldni = new JLabel(" - DNI: " );
		txtdni = new JTextField(BD.buscarUsario(Principal.con,dni).getDni());
		lbltlf = new JLabel(" - TLF: ");
		txttlf = new JTextField(BD.buscarUsario(Principal.con,dni).getTlf());
		lblContrasenia = new JLabel(" - CONTRASEÑA: ");
		txtContrasenia = new JTextField(BD.buscarUsario(Principal.con,dni).getContrasenia());
		pDatos.add(lblNombre);
		pDatos.add(txtNombre);
		pDatos.add(lblApellido);
		pDatos.add(txtApellido);
		pDatos.add(lbldni);
		pDatos.add(txtdni);
		pDatos.add(lbltlf);
		pDatos.add(txttlf);
		pDatos.add(lblContrasenia);
		pDatos.add(txtContrasenia);
		pCentro.add(pDatos);
		
		//Caracteristicas de los txtFields
		
		txtNombre.setMaximumSize(new Dimension(100, 20));
		txtApellido.setMaximumSize(new Dimension (100, 20));
		txtdni.setMaximumSize(new Dimension (100, 20));
		txttlf.setMaximumSize(new Dimension (100, 20));
		txtContrasenia.setMaximumSize(new Dimension (100, 20));
		
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		txtdni.setEditable(false);
		txttlf.setEditable(false);
		txtContrasenia.setEditable(false);
		
		txtNombre.setBorder(null);
		txtApellido.setBorder(null);
		txtdni.setBorder(null);
		txttlf.setBorder(null);
		txtContrasenia.setBorder(null);
		

		//cambiar el txt de estado
		btnEditable = new JButton("Editar los datos");
		pSur.add(btnEditable);
		btnEditable.addActionListener((e)-> {
			txtNombre.setEditable(editable);
			txtApellido.setEditable(editable);
			txtdni.setEditable(editable);
			txttlf.setEditable(editable);
			txtContrasenia.setEditable(editable);
		
			if(editable) {
				editable = false;
				btnEditable.setText("Guardar los datos");
			}
			else {
				String Nombre = txtNombre.getText();
				String Apellido = txtApellido.getText();
				String Dni = txtdni.getText();
				String tlf = txttlf.getText();
				String contrasenia = txtContrasenia.getText();
				Usuario u = new Usuario(Nombre, Apellido, tlf, Dni, contrasenia);
				BD.updateUsuario(Principal.con, u);
				btnEditable.setText("Editar los datos");
				editable = true;
			}
		});
		
		//Tabla
		modeloTabla = new ModeloTablaMisReservas(null);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		scrollTabla.setBorder(BorderFactory.createEmptyBorder()); 	
		tabla.setPreferredScrollableViewportSize(new Dimension(700, 300));
		tabla.getTableHeader().setReorderingAllowed(false);
		List<Reserva> reservas = BD.obtenerListaReservasPorDNI(Principal.con, dni);
		modeloTabla.setlReservas(reservas);
		pCentro.add(scrollTabla);
		
				
		//renderer de las celdas	
		tabla.setDefaultRenderer(Object.class, (table,value,isSelected,hasFocus,row,column)->{
			JLabel l = new JLabel();
			l.setHorizontalAlignment(JLabel.CENTER);
			l.setOpaque(true);
			if(row % 2 == 0) {
				l.setText(value.toString());
				l.setBackground( Color.LIGHT_GRAY); 
				
			}
			else {
				l.setText(value.toString());
				l.setBackground(table.getBackground());
			}
			
			return l;
		});
		
		
		//renderer de la cabecera
		tabla.getTableHeader().setDefaultRenderer((table,value,isSelectd,hasFocus,row,column)->{
			JLabel l = new JLabel();
			l.setText(value.toString());
			l.setOpaque(true);
			l.setHorizontalAlignment(JLabel.CENTER);
			l.setBackground(Color.ORANGE);
			
			
			return l;
		});
		
		
		//Boton cerrar
		btnCerrar = new JButton("Cerrar");
		pSur.add(btnCerrar);
		btnCerrar.addActionListener((e)->{
			vActual.dispose();
			new VentanaInicio(null);
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
		ImageIcon icono = new ImageIcon("resources/imagenes/logito.png");
		setIconImage(icono.getImage());
		setVisible(true);
	}
		
	
}
			