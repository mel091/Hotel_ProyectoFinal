package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controllers.ClientesController;
import controllers.HabitacionesController;
import controllers.InicioController;
import controllers.RentasController;
import controllers.TarifasController;
import controllers.TiposController;
import models.Habitacion;
import models.HabitacionesModel;
import models.RentasModel;

public class RentasView {
	private JFrame frame;
	private JDialog emergente;
	private InicioController inicio;
	private RentasController renta;
	private RentasModel model;
	public TarifasController tarifa;
	public TiposController tipo;
	public HabitacionesController room;
	public ClientesController cliente;
	public JTextField fechaInicialResp = new JTextField();;
	public JTextField fechaFinalResp = new JTextField();;
	
	JTextField idClienteResp = new JTextField();
	private int cambio1; /////////////////////////////////
	
	private String idRenta;
	
	JComboBox<String> tarifasResp = new JComboBox<>();
	
	JCheckBox wifi = new JCheckBox("Wi-Fi");
	JCheckBox restaurante = new JCheckBox("Restaurante");
	JCheckBox recreativos = new JCheckBox("Espacios recreativos");
	JCheckBox lavanderia = new JCheckBox("Lavandería");
	JTextField costoFinalResp = new JTextField();
	JTextField infoCambio = new JTextField();
	JTextField subtotalResp = new JTextField();
	String[] posibles= {"Check-In","Cancelada", "Check-Out"};
	JComboBox estatusResp = new JComboBox(posibles);
	
	public JPanel panelMovil;
	JTextField infoIdRenta = new JTextField();
	JTextField infoNombre = new JTextField();
	JTextField infoCorreo = new JTextField();
	JTextField infoFechaInicial = new JTextField();
	JTextField infoFechaFinal = new JTextField();
	JTextField infoNomEmerg = new JTextField();
	JTextArea infoAmenidades = new JTextArea();
	JTextField infoSubtotal = new JTextField();
	JTextField infoTotal = new JTextField();
	JTextField infoEstatus = new JTextField();  /////////////////////////////////
	
	//JTextField idClienteResp = new JTextField();
	
	JLabel nombreRenta = new JLabel("");
	
	public RentasView() {
		frame = new JFrame();
		frame.setBounds(10, 5, 1350, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		ImageIcon icono = new ImageIcon(getClass().getResource("/contenido/castleIcon2.png"));
		frame.setIconImage(icono.getImage());
		
		emergente=new JDialog(frame,"Emergente", true);
		emergente.setSize( 560, 290);
		emergente.setResizable(false);
		
	}
	public void consultar()
	{
		frame.getContentPane().removeAll();
		frame.repaint();
		//Panel principal
		JPanel panelConsultar=new JPanel();
		panelConsultar.setBackground(Color.white);
		panelConsultar.setBounds(0, 0, 1200, 700);
		panelConsultar.setLayout(null);
		
		//Panel de la cabecera
		JLabel disneyFondo = new JLabel();
		disneyFondo.setBounds(10, 10, 1313, 90);
		disneyFondo.setOpaque(true);
		disneyFondo.setBackground(new Color(0,73,102));
		disneyFondo.setLayout(null);
		panelConsultar.add(disneyFondo);
		
	
		JButton btnDisney = new JButton();
		btnDisney.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				inicio = new InicioController();
				inicio.inicio();
			}
		});
		btnDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/imgCabecera.png")));
		btnDisney.setBorderPainted(false);
		btnDisney.setContentAreaFilled(false);
		btnDisney.setBounds(25, 16, 250, 56);
		disneyFondo.add(btnDisney);
		
		//Labels
		JLabel habitaciones = new JLabel("Habitaciones");
		habitaciones.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		habitaciones.setForeground(Color.white);
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(new Color(255, 255, 255));
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(new Color(252, 210, 87));
		rentas.setBounds(700, 36, 120, 40);
		disneyFondo.add(rentas);
		
		JLabel tarifas = new JLabel("Tarifas");
		tarifas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tarifas.setForeground(Color.white);
		tarifas.setBounds(840, 36, 130, 40);
		disneyFondo.add(tarifas);
		
		JLabel tipos = new JLabel("Tipos de habitaciones");
		tipos.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tipos.setForeground(Color.white);
		tipos.setBounds(985, 36, 310, 40);
		disneyFondo.add(tipos);
		
		
		JButton btnHabitaciones = new JButton();
		btnHabitaciones.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Habitaciones");
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		btnHabitaciones.setBorderPainted(false);
		btnHabitaciones.setContentAreaFilled(false);
		btnHabitaciones.setBounds(315, 36, 180, 30);
		disneyFondo.add(btnHabitaciones);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Clientes");
				frame.dispose();
				cliente = new ClientesController();
				cliente.crear();
			}
		});
		btnClientes.setBorderPainted(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBounds(540, 36, 110, 30);
		disneyFondo.add(btnClientes );
		
		JButton btnRentas= new JButton("");
		btnRentas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		btnRentas.setBorderPainted(false);
		btnRentas.setContentAreaFilled(false);
		btnRentas.setBounds(700, 36, 93, 30);
		disneyFondo.add(btnRentas);
		
		JButton btnTarifas= new JButton("");
		btnTarifas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tarifas");
				frame.dispose();
				tarifa = new TarifasController();
				tarifa.crear();
			}
		});
		btnTarifas.setBorderPainted(false);
		btnTarifas.setContentAreaFilled(false);
		btnTarifas.setBounds(840, 36, 95, 30);
		disneyFondo.add(btnTarifas);
		
		
		JButton btnTipos= new JButton("");
		btnTipos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("tipos");
				frame.dispose();
				tipo = new TiposController();
				tipo.crear();
			}
		});
		btnTipos.setBorderPainted(false);
		btnTipos.setContentAreaFilled(false);
		btnTipos.setBounds(985, 36, 295, 30);
		disneyFondo.add(btnTipos);
		
		//Panel vertical (Consultar)
		JPanel panelVertical1=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical1.setBounds(10,110,130, 277);
		panelVertical1.setBackground(new Color(0,73,102));
		panelVertical1.setLayout(null);
		panelConsultar.add(panelVertical1);
		
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(255, 255, 255));
		panelVertical1.add(crear);
		
		
		JButton crearBtn = new JButton();
		crearBtn .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		crearBtn.setBounds(0, 0, 130, 277);
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		panelVertical1.add(crearBtn);
		
		//Panel vertical (Crear)
		
		JPanel panelVertical2=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical2.setBounds(10,393,130, 277);
		panelVertical2.setBackground(new Color(0,73,102));
		panelVertical2.setLayout(null);
		panelConsultar.add(panelVertical2);
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(new Color(252,210,87));
		panelVertical2.add(consultar);
		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.consultar();
			}
		});
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		consultarBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(consultarBtn);
		
		//panel central
		JPanel panelCentral=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/centralDegradado.jpg"));
					g2d.drawImage(image, 0,0, 1174, 560, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelCentral.setBounds(150,110,1175, 560);
		panelCentral.setLayout(null);
		panelConsultar.add(panelCentral);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(0, 73, 102));
		panelAzul.setBounds(50, 50, 1075, 366);
		panelCentral.add(panelAzul);
		panelAzul.setLayout(null);
		
		JPanel panelDeTabla = new JPanel();
		panelDeTabla.setBounds(25, 25, 1023, 316);
		panelAzul.add(panelDeTabla);
		
		model = new RentasModel();
		DefaultTableModel datosHab = model.tablaRentas();	
		
		JTable productoTable= new JTable(datosHab); //dentro de los parentesis mete "datosClientes" 
		// del DefaultTable arriba
		productoTable.setFont(new Font("Palatino Linotype", Font.PLAIN, 12));
		productoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
		{
		@Override
		public void valueChanged(ListSelectionEvent e) 
		{
			if (!e.getValueIsAdjusting()) 
			{
				int selectedRow = productoTable.getSelectedRow();
				if (selectedRow != -1) 
				{ 
					idRenta = (String) productoTable.getValueAt(selectedRow, 0);
					System.out.println("ID seleccionado: " + idRenta);
				}
			}
		}
		});
		panelDeTabla.setLayout(null);
		
		JScrollPane tableScroll=new JScrollPane(productoTable);
		tableScroll.setBounds(0, 0, 1023, 316);
		panelDeTabla.removeAll();
		panelDeTabla.add(tableScroll);
		
		JButton detallesBtn= new JButton();
		detallesBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/detalles.png")));
		detallesBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(idRenta != null && !idRenta.isEmpty())
				{
					System.out.println(idRenta);
					model = new RentasModel();
					
					model.textField(infoIdRenta, infoNombre, infoCorreo, infoFechaInicial, infoFechaFinal, 
							infoNomEmerg, infoAmenidades, infoSubtotal, infoTotal, infoEstatus, nombreRenta);
					
					model.mostrarDetalles2(idRenta);
					detalles();
				}
			}
		});
		detallesBtn.setBorderPainted(false);
		detallesBtn.setContentAreaFilled(false);
		detallesBtn.setBounds(143, 470, 328, 45);
		panelCentral.add(detallesBtn);
		
		//botones del panel central
		JButton editarBtn= new JButton();
		editarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/editar.png")));
		editarBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idRenta != null && !idRenta.isEmpty()) 
				{ 
  		            model = new RentasModel();
  		            model.textField2(idClienteResp, fechaInicialResp,  fechaFinalResp, tarifasResp, wifi, restaurante, 
  		            		recreativos, lavanderia, estatusResp, subtotalResp, costoFinalResp);
  	       
  		           model.recuperaDatos(idRenta);
  		           frame.dispose();
  		          
  		            editar();
  		        }
			}
		});
		editarBtn.setBorderPainted(false);
		editarBtn.setContentAreaFilled(false);
		editarBtn.setBounds(690, 470, 328, 45);
		panelCentral.add(editarBtn);
		
		frame.getContentPane().add(panelConsultar);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
	}
	public void crear()
	{
		frame.getContentPane().removeAll();
		frame.repaint();
		//Panel principal
		JPanel panelCrear=new JPanel();
		panelCrear.setBackground(Color.white);
		panelCrear.setBounds(0, 0, 1200, 700);
		panelCrear.setLayout(null);
	
		//Panel de la cabecera
		JLabel disneyFondo = new JLabel();
		disneyFondo.setBounds(10, 10, 1313, 90);
		disneyFondo.setOpaque(true);
		disneyFondo.setBackground(new Color(0,73,102));
		disneyFondo.setLayout(null);
		panelCrear.add(disneyFondo);
		
	
		JButton btnDisney = new JButton();
		btnDisney.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				inicio = new InicioController();
				inicio.inicio();
			}
		});
		btnDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/imgCabecera.png")));
		btnDisney.setBorderPainted(false);
		btnDisney.setContentAreaFilled(false);
		btnDisney.setBounds(25, 16, 250, 56);
		disneyFondo.add(btnDisney);
		
		//Labels
		
		JLabel habitaciones = new JLabel("Habitaciones");
		habitaciones.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		habitaciones.setForeground(Color.white);
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(new Color(255, 255, 255));
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(new Color(252, 210, 87));
		rentas.setBounds(700, 36, 120, 40);
		disneyFondo.add(rentas);
		
		JLabel tarifas = new JLabel("Tarifas");
		tarifas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tarifas.setForeground(Color.white);
		tarifas.setBounds(840, 36, 130, 40);
		disneyFondo.add(tarifas);
		
		JLabel tipos = new JLabel("Tipos de habitaciones");
		tipos.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tipos.setForeground(Color.white);
		tipos.setBounds(985, 36, 310, 40);
		disneyFondo.add(tipos);
		
		
		JButton btnHabitaciones = new JButton();
		btnHabitaciones.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Habitaciones");
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		btnHabitaciones.setBorderPainted(false);
		btnHabitaciones.setContentAreaFilled(false);
		btnHabitaciones.setBounds(315, 36, 180, 30);
		disneyFondo.add(btnHabitaciones);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Clientes");
				frame.dispose();
				cliente = new ClientesController();
				cliente.crear();
			}
		});
		btnClientes.setBorderPainted(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBounds(540, 36, 110, 30);
		disneyFondo.add(btnClientes );
		
		JButton btnRentas= new JButton("");
		btnRentas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		btnRentas.setBorderPainted(false);
		btnRentas.setContentAreaFilled(false);
		btnRentas.setBounds(700, 36, 93, 30);
		disneyFondo.add(btnRentas);
		
		JButton btnTarifas= new JButton("");
		btnTarifas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tarifas");
				frame.dispose();
				tarifa = new TarifasController();
				tarifa.crear();
			}
		});
		btnTarifas.setBorderPainted(false);
		btnTarifas.setContentAreaFilled(false);
		btnTarifas.setBounds(840, 36, 95, 30);
		disneyFondo.add(btnTarifas);
		
		
		JButton btnTipos= new JButton("");
		btnTipos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tipos");
				frame.dispose();
				tipo = new TiposController();
				tipo.crear();
			}
		});
		btnTipos.setBorderPainted(false);
		btnTipos.setContentAreaFilled(false);
		btnTipos.setBounds(985, 36, 295, 30);
		disneyFondo.add(btnTipos);
		
		//Panel vertical (Consultar)
		JPanel panelVertical1=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical1.setBounds(10,110,130, 277);
		panelVertical1.setBackground(new Color(0,73,102));
		panelVertical1.setLayout(null);
		panelCrear.add(panelVertical1);
		
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(252,210,87));
		panelVertical1.add(crear);
		
		
		JButton crearBtn = new JButton();
		crearBtn .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		crearBtn.setBounds(0, 0, 130, 277);
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		panelVertical1.add(crearBtn);
		
		//Panel vertical (Crear)
		
		JPanel panelVertical2=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical2.setBounds(10,393,130, 277);
		panelVertical2.setBackground(new Color(0,73,102));
		panelVertical2.setLayout(null);
		panelCrear.add(panelVertical2);
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(Color.white);
		panelVertical2.add(consultar);
		
		
		
		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.consultar();
			}
		});
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		consultarBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(consultarBtn);
		
		//panel central
		JPanel panelCentral=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/centralDegradado.jpg"));
					g2d.drawImage(image, 0,0, 1174, 560, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelCentral.setBounds(150,110,1175, 560);
		panelCentral.setLayout(null);
		panelCrear.add(panelCentral);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(0, 73, 102));
		panelAzul.setBounds(24, 11, 1115, 539);
		panelCentral.add(panelAzul);
		panelAzul.setLayout(null);
		
		JLabel rentarTitulo = new JLabel("Rentar");
		rentarTitulo.setForeground(new Color(0, 0, 0));
		rentarTitulo.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentarTitulo.setBounds(503, 30, 266, 46);
		panelAzul.add(rentarTitulo);
	
		JLabel fondoRentar = new JLabel("");
		fondoRentar.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoRentar.setBounds(315, 21, 489, 45);
		panelAzul.add(fondoRentar);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(BorderFactory.createLineBorder(new Color(252,210,87), 3));
		panelInfo.setBounds(42, 87, 1022, 384);
		panelAzul.add(panelInfo);
		panelInfo.setLayout(null);
		
		JLabel idCliente = new JLabel("ID del cliente");
		idCliente.setForeground(Color.BLACK);
		idCliente.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		idCliente.setBounds(35, 21, 327, 46);
		panelInfo.add(idCliente);
	
		JTextField idClienteResp = new JTextField();
		idClienteResp.setBorder(BorderFactory.createCompoundBorder(
				idClienteResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		idClienteResp.setBackground(new Color(217, 217, 217));
		idClienteResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		idClienteResp.setBounds(35, 70, 420, 25);
		panelInfo.add(idClienteResp);
		idClienteResp.setColumns(10);
		
		JLabel fechaInicial = new JLabel("Fecha inicial");
		fechaInicial.setForeground(Color.BLACK);
		fechaInicial.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaInicial.setBounds(35, 107, 160, 46);
		panelInfo.add(fechaInicial);
		
		JLabel fechaFinal = new JLabel("Fecha final");
		fechaFinal.setForeground(Color.BLACK);
		fechaFinal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaFinal.setBounds(35, 199, 160, 46);
		panelInfo.add(fechaFinal);
		
		fechaInicialResp = new JTextField();	////////////////////////////////////////////////////////
		fechaInicialResp.setBorder(BorderFactory.createCompoundBorder(
				fechaInicialResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		fechaInicialResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		fechaInicialResp.setColumns(10);
		fechaInicialResp.setBackground(new Color(217, 217, 217));
		fechaInicialResp.setBounds(35, 159, 360, 25);
		panelInfo.add(fechaInicialResp);
		
		
		JLabel tarifaTitulo = new JLabel("Tarifas");
		tarifaTitulo.setForeground(Color.BLACK);
		tarifaTitulo.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tarifaTitulo.setBounds(35, 282, 160, 46);
		panelInfo.add(tarifaTitulo);
		
		fechaFinalResp = new JTextField(); ///////////////////////////////////////////////////
		fechaFinalResp.setBorder(BorderFactory.createCompoundBorder(
				fechaFinalResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		fechaFinalResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		fechaFinalResp.setColumns(10);
		fechaFinalResp.setBackground(new Color(217, 217, 217));
		fechaFinalResp.setBounds(35, 246, 360, 25);
		panelInfo.add(fechaFinalResp);
		
		//Botones de fecha
		JButton fechaInicialBtn = new JButton();
		fechaInicialBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/fecha.png")));
		fechaInicialBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Fecha incial");
				fechas(1);
			}
		});
		fechaInicialBtn.setBounds(395, 159, 60, 25);
		fechaInicialBtn.setBorderPainted(false);
		fechaInicialBtn.setContentAreaFilled(false);
		panelInfo.add(fechaInicialBtn);
		
		
		JButton fechaFinalBtn = new JButton();
		fechaFinalBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Fecha final");
				fechas(2);
			}
		});
		fechaFinalBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/fecha.png")));
		fechaInicialBtn.setBorderPainted(false);
		fechaInicialBtn.setContentAreaFilled(false);
		fechaFinalBtn.setBounds(395, 246, 60, 25);
		panelInfo.add(fechaFinalBtn);
		
		
		
		JButton botonVacio = new JButton();
		botonVacio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleccion(1);
			}
		});
		botonVacio.setBorderPainted(false);
		botonVacio.setContentAreaFilled(false);
		botonVacio.setIcon(new ImageIcon(getClass().getResource("/contenido/vaciar.png")));
		botonVacio.setBounds(85, 482, 380, 50);
		panelAzul.add(botonVacio);
		
		JButton botonCrear = new JButton(); /////////////////////////////////////////////////////////////////////////////////////////////////////////
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new RentasModel();
				
				String idC = idClienteResp.getText();
				String fechaInicial =  fechaInicialResp.getText();
				String fechaFinal = fechaFinalResp.getText();
				String tarifaSeleccionada = (String) tarifasResp.getSelectedItem();
				
				StringBuilder seleccion = new StringBuilder();
				 if (wifi.isSelected()) {
	                    seleccion.append(wifi.getText()).append("\n");
	                }
	                if (restaurante.isSelected()) {
	                    seleccion.append(restaurante.getText()).append("\n");
	                }
	                if (recreativos.isSelected()) {
	                    seleccion.append(recreativos.getText()).append("\n");
	                }
	                if (lavanderia.isSelected()) {
	                    seleccion.append(lavanderia.getText()).append("\n");
	                }
	                String amenidades = seleccion.toString();
	                
	            String costo = costoFinalResp.getText();
	            int costoDef = 0;
	            
	            try {
	            	costoDef = Integer.parseInt(costo);
	            } catch (NumberFormatException e1) {
	                System.out.println("Error: El valor ingresado no es un número entero válido");
	                e1.printStackTrace();
	            }
	                
	            model.crear(idC, fechaInicial, fechaFinal, tarifaSeleccionada, amenidades, costoDef);

				pagoInicial();
			}
		});
		botonCrear.setBorderPainted(false);
		botonCrear.setContentAreaFilled(false);
		botonCrear.setIcon(new ImageIcon(getClass().getResource("/contenido/crearRenta.png")));
		botonCrear.setBounds(628, 482, 387, 50);
		panelAzul.add(botonCrear);
		
		
		JLabel costoFinal = new JLabel("Costo final");
		costoFinal.setBounds(563, 292, 364, 46);
		panelInfo.add(costoFinal);
		costoFinal.setForeground(Color.BLACK);
		costoFinal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JLabel capacidad = new JLabel("Condiciones");
		capacidad.setBounds(563, 180, 196, 46);
		panelInfo.add(capacidad);
		capacidad.setForeground(Color.BLACK);
		capacidad.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JPanel panelito = new JPanel();
		panelito.setBackground(new Color(217, 217, 217));
		panelito.setBounds(563, 220, 420, 60);
		panelInfo.add(panelito);
		panelito.setLayout(null);
		
		//JCheckBox wifi = new JCheckBox("Wi-Fi");
		wifi.setOpaque(false);
		wifi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		wifi.setBounds(0, 0, 200, 31);
		panelito.add(wifi);
		
		//JCheckBox restaurante = new JCheckBox("Restaurante");
		restaurante.setOpaque(false);
		restaurante.setFont(new Font("Tahoma", Font.PLAIN, 18));
		restaurante.setBounds(0, 30, 200, 31);
		panelito.add(restaurante);
		
		//JCheckBox recreativos = new JCheckBox("Espacios recreativos");
		recreativos.setOpaque(false);
		recreativos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		recreativos.setBounds(220, 0, 200, 31);
		panelito.add(recreativos);
		
		//JCheckBox lavanderia = new JCheckBox("Lavandería");
		lavanderia.setOpaque(false);
		lavanderia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lavanderia.setBounds(220, 30, 200, 31);
		panelito.add(lavanderia);
		
		//JTextField costoFinalResp = new JTextField();
		costoFinalResp.setBorder(BorderFactory.createCompoundBorder(
				costoFinalResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		costoFinalResp.setBounds(563, 326, 420, 25);
		panelInfo.add(costoFinalResp);
		costoFinalResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		costoFinalResp.setColumns(10);
		costoFinalResp.setBackground(new Color(217, 217, 217));
		
		model = new RentasModel();
		List<String> tarifas1 = model.obtenerNombresDeTarifas();
		
		
		for(String tarifa : tarifas1)
		{
			tarifasResp.addItem(tarifa);
		}
		
		//JComboBox tarifasResp = new JComboBox();
		tarifasResp.setBackground(new Color(217, 217, 217));
		tarifasResp.setBounds(35, 329, 420, 25);
		panelInfo.add(tarifasResp);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 73, 102));
		panel.setBounds(563, 21, 420, 152);
		panelInfo.add(panel);
		panel.setLayout(null);
		
		JLabel tituloNomHabi = new JLabel("Nombre de la habitación");
		tituloNomHabi.setOpaque(true);
		tituloNomHabi.setBackground(Color.white);
		tituloNomHabi.setBorder(BorderFactory.createCompoundBorder(
				tituloNomHabi.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		tituloNomHabi.setHorizontalAlignment(SwingConstants.CENTER);
		tituloNomHabi.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		tituloNomHabi.setBounds(10, 11, 400, 25);
		panel.add(tituloNomHabi);
		
		JLabel tipo = new JLabel("Tipo");
		tipo.setForeground(Color.white);
		tipo.setOpaque(false);
		tipo.setHorizontalAlignment(SwingConstants.CENTER);
		tipo.setFont(new Font("Palatino Linotype", Font.BOLD, 18));;
		tipo.setBounds(10, 47, 46, 25);
		panel.add(tipo);
		
		JLabel descripcion = new JLabel("Descripción ");
		descripcion.setOpaque(false);
		descripcion.setHorizontalAlignment(SwingConstants.CENTER);
		descripcion.setForeground(Color.WHITE);
		descripcion.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		descripcion.setBounds(10, 88, 115, 25);
		panel.add(descripcion);
		
		JLabel infoTipo = new JLabel("");
		infoTipo.setOpaque(true);
		infoTipo.setHorizontalAlignment(SwingConstants.CENTER);
		infoTipo.setForeground(new Color(255, 255, 255));
		infoTipo.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		infoTipo.setBounds(135, 47, 275, 25);
		panel.add(infoTipo);
		
		JLabel infoDesc = new JLabel("");
		infoDesc.setOpaque(true);
		infoDesc.setHorizontalAlignment(SwingConstants.CENTER);
		infoDesc.setForeground(Color.WHITE);
		infoDesc.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		infoDesc.setBounds(135, 90, 275, 51);
		panel.add(infoDesc);

		frame.getContentPane().add(panelCrear);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
			
	}	
	public void detalles()
	{
		frame.getContentPane().removeAll();
		frame.repaint();
		//Panel principal
		JPanel panelDetalles=new JPanel();
		panelDetalles.setBackground(Color.white);
		panelDetalles.setBounds(0, 0, 1200, 700);
		panelDetalles.setLayout(null);

		//Panel de la cabecera
		JLabel disneyFondo = new JLabel();
		disneyFondo.setBounds(10, 10, 1313, 90);
		disneyFondo.setOpaque(true);
		disneyFondo.setBackground(new Color(0,73,102));
		disneyFondo.setLayout(null);
		panelDetalles.add(disneyFondo);
		
	
		JButton btnDisney = new JButton();
		btnDisney.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				inicio = new InicioController();
				inicio.inicio();
			}
		});
		btnDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/imgCabecera.png")));
		btnDisney.setBorderPainted(false);
		btnDisney.setContentAreaFilled(false);
		btnDisney.setBounds(25, 16, 250, 56);
		disneyFondo.add(btnDisney);
		
		//Labels
		
		JLabel habitaciones = new JLabel("Habitaciones");
		habitaciones.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		habitaciones.setForeground(Color.white);
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(new Color(255, 255, 255));
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(new Color(252, 210, 87));
		rentas.setBounds(700, 36, 120, 40);
		disneyFondo.add(rentas);
		
		JLabel tarifas = new JLabel("Tarifas");
		tarifas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tarifas.setForeground(Color.white);
		tarifas.setBounds(840, 36, 130, 40);
		disneyFondo.add(tarifas);
		
		JLabel tipos = new JLabel("Tipos de habitaciones");
		tipos.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tipos.setForeground(Color.white);
		tipos.setBounds(985, 36, 310, 40);
		disneyFondo.add(tipos);
		
		
		JButton btnHabitaciones = new JButton();
		btnHabitaciones.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Habitaciones");
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		btnHabitaciones.setBorderPainted(false);
		btnHabitaciones.setContentAreaFilled(false);
		btnHabitaciones.setBounds(315, 36, 180, 30);
		disneyFondo.add(btnHabitaciones);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Clientes");
				frame.dispose();
				cliente = new ClientesController();
				cliente.crear();
			}
		});
		btnClientes.setBorderPainted(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBounds(540, 36, 110, 30);
		disneyFondo.add(btnClientes );
		
		JButton btnRentas= new JButton("");
		btnRentas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		btnRentas.setBorderPainted(false);
		btnRentas.setContentAreaFilled(false);
		btnRentas.setBounds(700, 36, 93, 30);
		disneyFondo.add(btnRentas);
		
		JButton btnTarifas= new JButton("");
		btnTarifas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tarifas");
				frame.dispose();
				tarifa = new TarifasController();
				tarifa.crear();
			}
		});
		btnTarifas.setBorderPainted(false);
		btnTarifas.setContentAreaFilled(false);
		btnTarifas.setBounds(840, 36, 95, 30);
		disneyFondo.add(btnTarifas);
		
		
		JButton btnTipos= new JButton("");
		btnTipos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tipos");
				frame.dispose();
				tipo = new TiposController();
				tipo.crear();
			}
		});
		btnTipos.setBorderPainted(false);
		btnTipos.setContentAreaFilled(false);
		btnTipos.setBounds(985, 36, 295, 30);
		disneyFondo.add(btnTipos);
		
		//Panel vertical (Consultar)
		JPanel panelVertical1=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical1.setBounds(10,110,130, 277);
		panelVertical1.setBackground(new Color(0,73,102));
		panelVertical1.setLayout(null);
		panelDetalles.add(panelVertical1);
		
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(255, 255, 255));
		panelVertical1.add(crear);
		
		
		JButton crearBtn = new JButton();
		crearBtn .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		crearBtn.setBounds(0, 0, 130, 277);
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		panelVertical1.add(crearBtn);
		
		//Panel vertical (Crear)
		
		JPanel panelVertical2=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical2.setBounds(10,393,130, 277);
		panelVertical2.setBackground(new Color(0,73,102));
		panelVertical2.setLayout(null);
		panelDetalles.add(panelVertical2);
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(new Color(252,210,87));
		panelVertical2.add(consultar);
		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.consultar();
			}
		});
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		consultarBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(consultarBtn);		
		
		//panel central
		JPanel panelCentral=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/centralDegradado.jpg"));
					g2d.drawImage(image, 0,0, 1174, 560, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelCentral.setBounds(150,111,1175, 560);
		panelCentral.setLayout(null);
		panelDetalles.add(panelCentral);
		
		JButton checkOutBtn= new JButton();
		checkOutBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/checkOut.png")));
		checkOutBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("check out");
				checkOut();
			}
		});
		checkOutBtn.setBorderPainted(false);
		checkOutBtn.setContentAreaFilled(false);
		checkOutBtn.setBounds(770, 472, 200, 49);
		panelCentral.add(checkOutBtn);
		
		
		//JLabel nombreHabitacion = new JLabel("Nombre de la habitación");
		nombreRenta.setHorizontalAlignment(SwingConstants.CENTER);
		nombreRenta.setForeground(new Color(0, 0, 0));
		nombreRenta.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		nombreRenta.setBounds(330, 28, 489, 46);
		panelCentral.add(nombreRenta);
	
		JLabel fondoCliente = new JLabel("");
		fondoCliente.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoCliente.setBounds(330, 21, 489, 45);
		panelCentral.add(fondoCliente);
		
		JButton atrasBtn = new JButton();
		atrasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("atras");
			}
		});
		atrasBtn.setBorderPainted(false);
		atrasBtn.setContentAreaFilled(false);
		atrasBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/anterior.png")));
		atrasBtn.setBounds(50, 11, 75, 61);
		panelCentral.add(atrasBtn);
		
		
		JButton sigBtn = new JButton();
		sigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Siguiente");
			}
		});
		sigBtn.setBorderPainted(false);
		sigBtn.setContentAreaFilled(false);
		sigBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/siguiente.png")));
		sigBtn.setBounds(1050, 15,75, 61);
		panelCentral.add(sigBtn);
		
		JPanel paneAzulFondo = new JPanel();
		paneAzulFondo.setBackground(new Color(0, 73, 102));
		paneAzulFondo.setBounds(50, 109, 517, 412);
		panelCentral.add(paneAzulFondo);
		paneAzulFondo.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 73, 102));
		panel.setBounds(610, 109, 515, 333);
		panelCentral.add(panel);
		panel.setLayout(null);
		
		JPanel panelImagen = new JPanel();
		panelImagen.setBounds(20, 20, 472, 291);
		panel.add(panelImagen);
		panelImagen.setLayout(null);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(null);
		panelInfo.setPreferredSize(new Dimension(400, 690));
		
		
		//Datos
		JLabel idRentaLbl = new JLabel("ID de la renta");
		idRentaLbl.setForeground(Color.BLACK);
		idRentaLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		idRentaLbl.setBounds(20, 11, 354, 29);
		panelInfo.add(idRentaLbl);
		
		//JTextField infoIdRenta = new JTextField("271873");
		infoIdRenta.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoIdRenta.setBorder(BorderFactory.createCompoundBorder(
				infoIdRenta.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoIdRenta.setEditable(false);
		infoIdRenta.setBackground(new Color(217, 217, 217));
		infoIdRenta.setBounds(20, 40, 420, 25);
		panelInfo.add(infoIdRenta);
		
		JLabel nomCompleto = new JLabel("Nombre del cliente");
		nomCompleto.setForeground(Color.BLACK);
		nomCompleto.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nomCompleto.setBounds(20, 78, 354, 29);
		panelInfo.add(nomCompleto);
		
		//JTextField infoNombre = new JTextField("Juan Dominguez");
		infoNombre.setBorder(BorderFactory.createCompoundBorder(
				infoNombre.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoNombre.setEditable(false);
		infoNombre.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoNombre.setColumns(10);
		infoNombre.setBackground(new Color(217, 217, 217));
		infoNombre.setBounds(20, 107, 420, 25);
		panelInfo.add(infoNombre);
		
		JLabel correoElect = new JLabel("Correo electrónico");
		correoElect.setForeground(Color.BLACK);
		correoElect.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		correoElect.setBounds(20, 143, 354, 29);
		panelInfo.add(correoElect);
		
		//JTextField infoCorreo = new JTextField("pepe@gmail.com");
		infoCorreo.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoCorreo.setBorder(BorderFactory.createCompoundBorder(
				infoCorreo.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoCorreo.setEditable(false);
		infoCorreo.setColumns(10);
		infoCorreo.setBackground(new Color(217, 217, 217));
		infoCorreo.setBounds(20, 171, 420, 25);
		panelInfo.add(infoCorreo);
		
		
		JLabel fechaInicial = new JLabel("Fecha inicial");
		fechaInicial.setForeground(Color.BLACK);
		fechaInicial.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaInicial.setBounds(20, 207, 354, 29);
		panelInfo.add(fechaInicial);
		
		//JTextField infoFechaInicial = new JTextField("2/02/23");
		infoFechaInicial.setBorder(BorderFactory.createCompoundBorder(
				infoFechaInicial.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoFechaInicial.setEditable(false);
		infoFechaInicial.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoFechaInicial.setColumns(10);
		infoFechaInicial.setBackground(new Color(217, 217, 217));
		infoFechaInicial.setBounds(20, 238, 420, 25);
		panelInfo.add(infoFechaInicial);
		
		JLabel fechaFinal = new JLabel("Fecha final");
		fechaFinal.setForeground(Color.BLACK);
		fechaFinal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaFinal.setBounds(20, 274, 354, 29);
		panelInfo.add(fechaFinal);
		
		//JTextField infoFechaFinal = new JTextField("12/02/23");
		infoFechaFinal.setBorder(BorderFactory.createCompoundBorder(
				infoFechaFinal.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoFechaFinal.setEditable(false);
		infoFechaFinal.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoFechaFinal.setColumns(10);
		infoFechaFinal.setBackground(new Color(217, 217, 217));
		infoFechaFinal.setBounds(20, 303, 420, 25);
		panelInfo.add(infoFechaFinal);
		
		
		JLabel tarifa = new JLabel("Tarifa");
		tarifa.setForeground(Color.BLACK);
		tarifa.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tarifa.setBounds(20, 335, 354, 29);
		panelInfo.add(tarifa);
		
		
		//JTextField infoNomEmerg = new JTextField("blabla");
		infoNomEmerg.setBorder(BorderFactory.createCompoundBorder(
				infoNomEmerg.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoNomEmerg.setEditable(false);
		infoNomEmerg.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoNomEmerg.setColumns(10);
		infoNomEmerg.setBackground(new Color(217, 217, 217));
		infoNomEmerg.setBounds(20,360, 420, 25);
		panelInfo.add(infoNomEmerg);
		
		JLabel amenidades = new JLabel("Amenidades");
		amenidades.setForeground(Color.BLACK);
		amenidades.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		amenidades.setBounds(20, 395, 354, 29);
		panelInfo.add(amenidades);
		
		
		//JTextArea infoAmenidades = new JTextArea("laslas");
		infoAmenidades.setBorder(BorderFactory.createCompoundBorder(
				infoAmenidades.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoAmenidades.setEditable(false);
		infoAmenidades.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoAmenidades.setColumns(10);
		infoAmenidades.setBackground(new Color(217, 217, 217));
		infoAmenidades.setBounds(20,425, 420, 50);
		panelInfo.add(infoAmenidades);
		

		JLabel subtotal = new JLabel("Subtotal");
		subtotal.setForeground(Color.BLACK);
		subtotal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		subtotal.setBounds(20, 485, 354, 29);
		panelInfo.add(subtotal);
		
		
		//JTextField infoSubtotal = new JTextField("2133");
		infoSubtotal.setBorder(BorderFactory.createCompoundBorder(
				infoSubtotal.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoSubtotal.setEditable(false);
		infoSubtotal.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoSubtotal.setColumns(10);
		infoSubtotal.setBackground(new Color(217, 217, 217));
		infoSubtotal.setBounds(20,515, 420, 25);
		panelInfo.add(infoSubtotal);
		
		JLabel total = new JLabel("Total");
		total.setForeground(Color.BLACK);
		total.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		total.setBounds(20, 555, 354, 29);
		panelInfo.add(total);
		
		
		//JTextField infoTotal = new JTextField("2671");
		infoTotal.setBorder(BorderFactory.createCompoundBorder(
				infoTotal.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoTotal.setEditable(false);
		infoTotal.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoTotal.setColumns(10);
		infoTotal.setBackground(new Color(217, 217, 217));
		infoTotal.setBounds(20,580, 420, 25);
		panelInfo.add(infoTotal);
		
		JLabel estatus = new JLabel("Estatus");
		estatus.setForeground(Color.BLACK);
		estatus.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		estatus.setBounds(20, 615, 354, 29);
		panelInfo.add(estatus);
		
		
		//JTextField infoEstatus = new JTextField("En hospedaje");
		infoEstatus.setBorder(BorderFactory.createCompoundBorder(
				infoEstatus.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoEstatus.setEditable(false);
		infoEstatus.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoEstatus.setColumns(10);
		infoEstatus.setBackground(new Color(217, 217, 217));
		infoEstatus.setBounds(20,640, 420, 25);
		panelInfo.add(infoEstatus);
		
		JScrollPane scrollPane = new JScrollPane(panelInfo);
		scrollPane.setBounds(20, 20, 477, 370);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		SwingUtilities.invokeLater(()->scrollPane.getViewport().setViewPosition(new Point(0, 0)));
		paneAzulFondo.add(scrollPane);
		

	
		frame.getContentPane().add(panelDetalles);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();	
	}
	
	public void editar()
	{
		frame.getContentPane().removeAll();
		frame.repaint();
		//Panel principal
		JPanel panelEditar=new JPanel();
		panelEditar.setBackground(Color.white);
		panelEditar.setBounds(0, 0, 1200, 700);
		panelEditar.setLayout(null);

		//Panel de la cabecera
		JLabel disneyFondo = new JLabel();
		disneyFondo.setBounds(10, 10, 1313, 90);
		disneyFondo.setOpaque(true);
		disneyFondo.setBackground(new Color(0,73,102));
		disneyFondo.setLayout(null);
		panelEditar.add(disneyFondo);
		
	
		JButton btnDisney = new JButton();
		btnDisney.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				inicio = new InicioController();
				inicio.inicio();
			}
		});
		btnDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/imgCabecera.png")));
		btnDisney.setBorderPainted(false);
		btnDisney.setContentAreaFilled(false);
		btnDisney.setBounds(25, 16, 250, 56);
		disneyFondo.add(btnDisney);
		
		//Labels
		
		JLabel habitaciones = new JLabel("Habitaciones");
		habitaciones.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		habitaciones.setForeground(Color.white);
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(new Color(255, 255, 255));
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(new Color(252, 210, 87));
		rentas.setBounds(700, 36, 120, 40);
		disneyFondo.add(rentas);
		
		JLabel tarifas = new JLabel("Tarifas");
		tarifas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tarifas.setForeground(Color.white);
		tarifas.setBounds(840, 36, 130, 40);
		disneyFondo.add(tarifas);
		
		JLabel tipos = new JLabel("Tipos de habitaciones");
		tipos.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tipos.setForeground(Color.white);
		tipos.setBounds(985, 36, 310, 40);
		disneyFondo.add(tipos);
		
		
		JButton btnHabitaciones = new JButton();
		btnHabitaciones.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Habitaciones");
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		btnHabitaciones.setBorderPainted(false);
		btnHabitaciones.setContentAreaFilled(false);
		btnHabitaciones.setBounds(315, 36, 180, 30);
		disneyFondo.add(btnHabitaciones);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Clientes");
				frame.dispose();
				cliente = new ClientesController();
				cliente.crear();
			}
		});
		btnClientes.setBorderPainted(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBounds(540, 36, 110, 30);
		disneyFondo.add(btnClientes );
		
		JButton btnRentas= new JButton("");
		btnRentas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		btnRentas.setBorderPainted(false);
		btnRentas.setContentAreaFilled(false);
		btnRentas.setBounds(700, 36, 93, 30);
		disneyFondo.add(btnRentas);
		
		JButton btnTarifas= new JButton("");
		btnTarifas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tarifas");
				frame.dispose();
				tarifa = new TarifasController();
				tarifa.crear();
			}
		});
		btnTarifas.setBorderPainted(false);
		btnTarifas.setContentAreaFilled(false);
		btnTarifas.setBounds(840, 36, 95, 30);
		disneyFondo.add(btnTarifas);
		
		
		JButton btnTipos= new JButton("");
		btnTipos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tarifas");
				frame.dispose();
				tipo = new TiposController();
				tipo.crear();
			}
		});
		btnTipos.setBorderPainted(false);
		btnTipos.setContentAreaFilled(false);
		btnTipos.setBounds(985, 36, 295, 30);
		disneyFondo.add(btnTipos);
		
		//Panel vertical (Consultar)
		JPanel panelVertical1=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical1.setBounds(10,110,130, 277);
		panelVertical1.setBackground(new Color(0,73,102));
		panelVertical1.setLayout(null);
		panelEditar.add(panelVertical1);
		
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(255, 255, 255));
		panelVertical1.add(crear);
		
		
		JButton crearBtn = new JButton();
		crearBtn .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		crearBtn.setBounds(0, 0, 130, 277);
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		panelVertical1.add(crearBtn);
		
		//Panel vertical (Crear)
		
		JPanel panelVertical2=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical2.setBounds(10,393,130, 277);
		panelVertical2.setBackground(new Color(0,73,102));
		panelVertical2.setLayout(null);
		panelEditar.add(panelVertical2);
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(new Color(252,210,87));
		panelVertical2.add(consultar);
		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.consultar();
			}
		});
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		consultarBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(consultarBtn);	
		
		//panel central
		JPanel panelCentral=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/centralDegradado.jpg"));
					g2d.drawImage(image, 0,0, 1174, 560, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelCentral.setBounds(150,110,1175, 560);
		panelCentral.setLayout(null);
		panelEditar.add(panelCentral);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(0, 73, 102));
		panelAzul.setBounds(24, 11, 1115, 539);
		panelCentral.add(panelAzul);
		panelAzul.setLayout(null);
		
		JLabel editarRentaTitulo = new JLabel("Editar renta");
		editarRentaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		editarRentaTitulo.setForeground(new Color(0, 0, 0));
		editarRentaTitulo.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		editarRentaTitulo.setBounds(315, 30, 489, 46);
		panelAzul.add(editarRentaTitulo);
	
		JLabel fondoRentar = new JLabel("");
		fondoRentar.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoRentar.setBounds(315, 21, 489, 45);
		panelAzul.add(fondoRentar);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(BorderFactory.createLineBorder(new Color(252,210,87), 3));
		panelInfo.setBounds(42, 87, 1022, 384);
		panelAzul.add(panelInfo);
		panelInfo.setLayout(null);
		
		JLabel idCliente = new JLabel("ID del cliente");
		idCliente.setForeground(Color.BLACK);
		idCliente.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		idCliente.setBounds(35, 11, 327, 46);
		panelInfo.add(idCliente);
		
	
		//JTextField idClienteResp = new JTextField();
		idClienteResp.setBorder(BorderFactory.createCompoundBorder(
				idClienteResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		idClienteResp.setBackground(new Color(217, 217, 217));
		idClienteResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		idClienteResp.setBounds(35, 44, 420, 25);
		panelInfo.add(idClienteResp);
		idClienteResp.setColumns(10);
		
		JLabel fechaInicial = new JLabel("Fecha inicial");
		fechaInicial.setForeground(Color.BLACK);
		fechaInicial.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaInicial.setBounds(35, 68, 160, 46);
		panelInfo.add(fechaInicial);
		
		JLabel fechaFinal = new JLabel("Fecha final");
		fechaFinal.setForeground(Color.BLACK);
		fechaFinal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaFinal.setBounds(35, 137, 160, 46);
		panelInfo.add(fechaFinal);
		
		//fechaInicialResp = new JTextField();
		fechaInicialResp.setBorder(BorderFactory.createCompoundBorder(
				fechaInicialResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		fechaInicialResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		fechaInicialResp.setColumns(10);
		fechaInicialResp.setBackground(new Color(217, 217, 217));
		fechaInicialResp.setBounds(35, 109, 360, 25);
		panelInfo.add(fechaInicialResp);
		
		
		JLabel tarifaTitulo = new JLabel("Tarifas");
		tarifaTitulo.setForeground(Color.BLACK);
		tarifaTitulo.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tarifaTitulo.setBounds(35, 206, 160, 46);
		panelInfo.add(tarifaTitulo);
		
		//fechaFinalResp = new JTextField();
		fechaFinalResp.setBorder(BorderFactory.createCompoundBorder(
				fechaFinalResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		fechaFinalResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		fechaFinalResp.setColumns(10);
		fechaFinalResp.setBackground(new Color(217, 217, 217));
		fechaFinalResp.setBounds(35, 180, 360, 25);
		panelInfo.add(fechaFinalResp);
		
		//Botones de fecha
		JButton fechaInicialBtn = new JButton();
		fechaInicialBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/fecha.png")));
		fechaInicialBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Fecha incial");
				fechas(1);
			}
		});
		fechaInicialBtn.setBounds(395, 109, 60, 25);
		fechaInicialBtn.setBorderPainted(false);
		fechaInicialBtn.setContentAreaFilled(false);
		panelInfo.add(fechaInicialBtn);
		
		
		JButton fechaFinalBtn = new JButton();
		fechaFinalBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Fecha final");
				fechas(2);
			}
		});
		fechaFinalBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/fecha.png")));
		fechaInicialBtn.setBorderPainted(false);
		fechaInicialBtn.setContentAreaFilled(false);
		fechaFinalBtn.setBounds(395, 180, 60, 25);
		panelInfo.add(fechaFinalBtn);
		
		
		
		JButton botonVacio = new JButton();
		botonVacio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//						nombreHabiResp.setText("");
//						tipoResp.setText("");
//						telResp.setText("");
//						direccionResp.setText("");
//						contactoResp.setText("");
//						relacionResp.setText("");
//						noContactoResp.setText("");
//						infoAdResp.setText("");
			}
		});
		botonVacio.setBorderPainted(false);
		botonVacio.setContentAreaFilled(false);
		botonVacio.setIcon(new ImageIcon(getClass().getResource("/contenido/eliminarRenta.png")));
		botonVacio.setBounds(85, 482, 387, 50);
		panelAzul.add(botonVacio);
		
		JButton botonCrear = new JButton();
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cambios");
				edicionPago();
			}
		});
		botonCrear.setBorderPainted(false);
		botonCrear.setContentAreaFilled(false);
		botonCrear.setIcon(new ImageIcon(getClass().getResource("/contenido/guardarCambios.png")));
		botonCrear.setBounds(628, 482, 387, 50);
		panelAzul.add(botonCrear);

		
		JLabel total = new JLabel("Total");
		total.setBounds(563, 305, 364, 46);
		panelInfo.add(total);
		total.setForeground(Color.BLACK);
		total.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JLabel capacidad = new JLabel("Condiciones");
		capacidad.setBounds(35, 267, 196, 46);
		panelInfo.add(capacidad);
		capacidad.setForeground(Color.BLACK);
		capacidad.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JPanel panelito = new JPanel();
		panelito.setBackground(new Color(217, 217, 217));
		panelito.setBounds(35, 305, 420, 60);
		panelInfo.add(panelito);
		panelito.setLayout(null);
		
		//JCheckBox wifi = new JCheckBox("Wi-Fi");
		wifi.setOpaque(false);
		wifi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		wifi.setBounds(0, 0, 200, 31);
		panelito.add(wifi);
		
		//JCheckBox restaurante = new JCheckBox("Restaurante");
		restaurante.setOpaque(false);
		restaurante.setFont(new Font("Tahoma", Font.PLAIN, 18));
		restaurante.setBounds(0, 30, 200, 31);
		panelito.add(restaurante);
		
		//JCheckBox recreativos = new JCheckBox("Espacios recreativos");
		recreativos.setOpaque(false);
		recreativos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		recreativos.setBounds(220, 0, 200, 31);
		panelito.add(recreativos);
		
		//JCheckBox lavanderia = new JCheckBox("Lavandería");
		lavanderia.setOpaque(false);
		lavanderia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lavanderia.setBounds(220, 30, 200, 31);
		panelito.add(lavanderia);
		
		//JTextField costoFinalResp = new JTextField();
		costoFinalResp.setBorder(BorderFactory.createCompoundBorder(
				costoFinalResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		costoFinalResp.setBounds(563, 340, 420, 25);
		panelInfo.add(costoFinalResp);
		costoFinalResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		costoFinalResp.setColumns(10);
		costoFinalResp.setBackground(new Color(217, 217, 217));
		
//		JComboBox tarifasResp = new JComboBox();
//		tarifasResp.setBackground(new Color(217, 217, 217));
//		tarifasResp.setBounds(35, 244, 420, 25);
//		panelInfo.add(tarifasResp);
		
		model = new RentasModel();
		List<String> tariNombres = model.getTar();

        for (String nombre : tariNombres) 
        {
        	tarifasResp.addItem(nombre);
        }

        tarifasResp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new RentasModel();
				String tipo = (String) tarifasResp.getSelectedItem();
//				String tarif = model.mostrarTarifas(tipo);
//				tarifasText1.setText(tarif);	
			}
	
		});
        tarifasResp.setBackground(new Color(217, 217, 217));
        tarifasResp.setBounds(35, 244, 420, 25);
		panelInfo.add(tarifasResp);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 73, 102));
		panel.setBounds(563, 21, 420, 152);
		panelInfo.add(panel);
		panel.setLayout(null);
		
		JLabel tituloNomHabi = new JLabel("Nombre de la habitación");
		tituloNomHabi.setOpaque(true);
		tituloNomHabi.setBackground(Color.white);
		tituloNomHabi.setBorder(BorderFactory.createCompoundBorder(
				tituloNomHabi.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		tituloNomHabi.setHorizontalAlignment(SwingConstants.CENTER);
		tituloNomHabi.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		tituloNomHabi.setBounds(10, 11, 400, 25);
		panel.add(tituloNomHabi);
		
		JLabel tipo = new JLabel("Tipo");
		tipo.setForeground(Color.white);
		tipo.setOpaque(false);
		tipo.setHorizontalAlignment(SwingConstants.CENTER);
		tipo.setFont(new Font("Palatino Linotype", Font.BOLD, 18));;
		tipo.setBounds(10, 47, 46, 25);
		panel.add(tipo);
		
		JLabel descripcion = new JLabel("Descripción ");
		descripcion.setOpaque(false);
		descripcion.setHorizontalAlignment(SwingConstants.CENTER);
		descripcion.setForeground(Color.WHITE);
		descripcion.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		descripcion.setBounds(10, 88, 115, 25);
		panel.add(descripcion);
		
		JLabel infoTipo = new JLabel("");
		infoTipo.setBorder(BorderFactory.createCompoundBorder(
				infoTipo.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoTipo.setOpaque(true);
		infoTipo.setHorizontalAlignment(SwingConstants.CENTER);
		infoTipo.setForeground(new Color(255, 255, 255));
		infoTipo.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		infoTipo.setBounds(135, 47, 275, 25);
		panel.add(infoTipo);
		
		JLabel infoDesc = new JLabel("");
		infoDesc.setOpaque(true);
		infoDesc.setHorizontalAlignment(SwingConstants.CENTER);
		infoDesc.setForeground(Color.WHITE);
		infoDesc.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		infoDesc.setBounds(135, 90, 275, 51);
		panel.add(infoDesc);
		
		JLabel estatus = new JLabel("Estatus");
		estatus.setForeground(Color.BLACK);
		estatus.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		estatus.setBounds(563, 180, 364, 46);
		panelInfo.add(estatus);
		
		//JTextField subtotalResp = new JTextField();
		subtotalResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		subtotalResp.setColumns(10);
		subtotalResp.setBackground(new Color(217, 217, 217));
		subtotalResp.setBounds(563, 278, 420, 25);
		panelInfo.add(subtotalResp);
		
		JLabel subtotal = new JLabel("Subtotal");
		subtotal.setForeground(Color.BLACK);
		subtotal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		subtotal.setBounds(563, 248, 364, 46);
		panelInfo.add(subtotal);
		
		//String[] posibles= {"Check-In","Cancelada", "Check-Out"};
		//JComboBox estatusResp = new JComboBox(posibles);
		estatusResp.setBorder(BorderFactory.createCompoundBorder(
				estatusResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		estatusResp.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		estatusResp.setBackground(new Color(217, 217, 217));
		estatusResp.setBounds(563, 216, 420, 30);
		panelInfo.add(estatusResp);
		
		JButton regresarBtn = new JButton();
		regresarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/regresar.png")));
		regresarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.consultar();
			}
		});
		regresarBtn.setBorderPainted(false);
		regresarBtn.setContentAreaFilled(false);
		regresarBtn.setBounds(33, 8, 80, 80);
		panelAzul.add(regresarBtn);
		
//		JPanel panelCentral=new JPanel()
//		{
//			@Override
//			public void paintComponent(Graphics create) {
//				super.paintComponent(create);
//				Graphics2D g2d = (Graphics2D) create;
//				try {
//					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/centralDegradado.jpg"));
//					g2d.drawImage(image, 0,0, 1174, 560, null);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		panelCentral.setBounds(150,111,1175, 560);
//		panelCentral.setLayout(null);
//		panelEditar.add(panelCentral);
//	
//		//Titulo del panel central
//		JLabel editarRenta = new JLabel("Editar renta");
//		editarRenta.setForeground(new Color(0, 0, 0));
//		editarRenta.setFont(new Font("Palatino Linotype", Font.BOLD, 35));
//		editarRenta.setBounds(439, 33, 465, 65);
//		panelCentral.add(editarRenta);
//		
//		//boton de regreso
//		JButton regresarBtn = new JButton();
//		regresarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/regresar.png")));
//		regresarBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
//				renta = new RentasController();
//				renta.consultar();
//			}
//		});
//		regresarBtn.setBorderPainted(false);
//		regresarBtn.setContentAreaFilled(false);
//		regresarBtn.setBounds(33, 11, 80, 80);
//		panelCentral.add(regresarBtn);
//	
//
//		JButton guardarCambiosBtn= new JButton();
//		guardarCambiosBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/guardarCambios.png")));
//		guardarCambiosBtn.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("Cambios");
//				edicionPago();
//			}
//		});
//		guardarCambiosBtn.setBorderPainted(false);
//		guardarCambiosBtn.setContentAreaFilled(false);
//		guardarCambiosBtn.setBounds(690, 480, 450, 54);
//		panelCentral.add(guardarCambiosBtn);
		
		frame.getContentPane().add(panelEditar);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
	}

	public void rentasPrincipal()
	{
		frame.getContentPane().removeAll();
		frame.repaint();
		//Panel principal
		JPanel panelPrincipal=new JPanel();
		panelPrincipal.setBackground(Color.white);
		panelPrincipal.setBounds(0, 0, 1200, 700);
		panelPrincipal.setLayout(null);
	
		
		
		//Panel de la cabecera
		JLabel disneyFondo = new JLabel();
		disneyFondo.setBounds(10, 10, 1313, 90);
		disneyFondo.setOpaque(true);
		disneyFondo.setBackground(new Color(0,73,102));
		disneyFondo.setLayout(null);
		panelPrincipal.add(disneyFondo);
		
	
		JButton btnDisney = new JButton();
		btnDisney.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				inicio = new InicioController();
				inicio.inicio();
			}
		});
		btnDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/imgCabecera.png")));
		btnDisney.setBorderPainted(false);
		btnDisney.setContentAreaFilled(false);
		btnDisney.setBounds(25, 16, 250, 56);
		disneyFondo.add(btnDisney);
		
		//Labels
		
		JLabel habitaciones = new JLabel("Habitaciones");
		habitaciones.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		habitaciones.setForeground(Color.white);
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(new Color(255, 255, 255));
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(new Color(252, 210, 87));
		rentas.setBounds(700, 36, 120, 40);
		disneyFondo.add(rentas);
		
		JLabel tarifas = new JLabel("Tarifas");
		tarifas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tarifas.setForeground(Color.white);
		tarifas.setBounds(840, 36, 130, 40);
		disneyFondo.add(tarifas);
		
		JLabel tipos = new JLabel("Tipos de habitaciones");
		tipos.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tipos.setForeground(Color.white);
		tipos.setBounds(985, 36, 310, 40);
		disneyFondo.add(tipos);
		
		
		JButton btnHabitaciones = new JButton();
		btnHabitaciones.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Habitaciones");
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		btnHabitaciones.setBorderPainted(false);
		btnHabitaciones.setContentAreaFilled(false);
		btnHabitaciones.setBounds(315, 36, 180, 30);
		disneyFondo.add(btnHabitaciones);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Clientes");
				frame.dispose();
				cliente = new ClientesController();
				cliente.crear();
			}
		});
		btnClientes.setBorderPainted(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBounds(540, 36, 110, 30);
		disneyFondo.add(btnClientes );
		
		JButton btnRentas= new JButton("");
		btnRentas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		btnRentas.setBorderPainted(false);
		btnRentas.setContentAreaFilled(false);
		btnRentas.setBounds(700, 36, 93, 30);
		disneyFondo.add(btnRentas);
		
		JButton btnTarifas= new JButton("");
		btnTarifas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tarifas");
				frame.dispose();
				tarifa = new TarifasController();
				tarifa.crear();
			}
		});
		btnTarifas.setBorderPainted(false);
		btnTarifas.setContentAreaFilled(false);
		btnTarifas.setBounds(840, 36, 95, 30);
		disneyFondo.add(btnTarifas);
		
		
		JButton btnTipos= new JButton("");
		btnTipos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tipos");
				frame.dispose();
				tipo = new TiposController();
				tipo.crear();
			}
		});
		btnTipos.setBorderPainted(false);
		btnTipos.setContentAreaFilled(false);
		btnTipos.setBounds(985, 36, 295, 30);
		disneyFondo.add(btnTipos);
		
		//Panel vertical (Consultar)
		JPanel panelVertical1=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical1.setBounds(10,110,130, 277);
		panelVertical1.setBackground(new Color(0,73,102));
		panelVertical1.setLayout(null);
		panelPrincipal.add(panelVertical1);
		
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(252,210,87));
		panelVertical1.add(crear);
		
		
		JButton crearBtn = new JButton();
		crearBtn .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		crearBtn.setBounds(0, 0, 130, 277);
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		panelVertical1.add(crearBtn);
		
		//Panel vertical (Crear)
		
		JPanel panelVertical2=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/mickeyMouse.png"));
					g2d.drawImage(image, 5, 80, 120,120, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelVertical2.setBounds(10,393,130, 277);
		panelVertical2.setBackground(new Color(0,73,102));
		panelVertical2.setLayout(null);
		panelPrincipal.add(panelVertical2);
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(Color.white);
		panelVertical2.add(consultar);

		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				renta = new RentasController();
				renta.consultar();
			}
		});
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		consultarBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(consultarBtn);
		
		//panel central
		JPanel panelCentral=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/centralDegradado.jpg"));
					g2d.drawImage(image, 0,0, 1174, 560, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelCentral.setBounds(150,110,1175, 560);
		panelCentral.setLayout(null);
		panelPrincipal.add(panelCentral);

		panelMovil = new JPanel();
		panelMovil.setOpaque(false);
		panelMovil.setLayout(null);
		
		createCenterPanel(); 
		panelMovil.repaint();
		panelMovil.revalidate();
		
		JScrollPane scrollPane = new JScrollPane(panelMovil);
		scrollPane.setBounds(0, 0, 1175, 575);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		//scrollPane.setVerticalScrollBar(null);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelCentral.add(scrollPane);
		
		frame.getContentPane().add(panelPrincipal);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
	}
	
	public void pagoInicial()
	{
		
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.setSize(650, 450);
		
		JPanel pagoInicialPanel= new JPanel();
		pagoInicialPanel.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		pagoInicialPanel.setBackground(new Color(220,220,220));
		pagoInicialPanel.setLayout(null);
		
		
		JLabel text = new JLabel("Pago en efectivo");
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.white);
		text.setBounds(195,15,250,40);
		pagoInicialPanel.add(text);
		
		JLabel fondoTexto = new JLabel();
		fondoTexto.setOpaque(true);
		fondoTexto.setBackground(new Color(0,73,102));
		fondoTexto.setBounds(0,0,emergente.getWidth(),50);
		pagoInicialPanel.add(fondoTexto);
		
		JButton botonAceptar = new JButton();
		botonAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/continuarCheck.png")));
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Continuar");
				emergente.dispose();
				renta = new RentasController();
				renta.crear();
				reservaExitosa();
				
			}
		});
		botonAceptar.setVerticalAlignment(SwingConstants.CENTER);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setBounds(440, 350, 165, 50);
		pagoInicialPanel.add(botonAceptar);
		
		
		JButton botonCancelar = new JButton();
		botonCancelar .setIcon(new ImageIcon(getClass().getResource("/contenido/cancelarCheck.png")));
		botonCancelar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Cancelar");
				emergente.dispose();
				
			}
		});
		botonCancelar.setBorderPainted(false);
		botonCancelar.setContentAreaFilled(false);
		botonCancelar.setBounds(30, 350, 166, 50);
		pagoInicialPanel.add(botonCancelar);
	
		
		JPanel panelAzulImagen=new JPanel();
		panelAzulImagen.setBackground(new Color(0,72,103));
		panelAzulImagen.setBounds(40, 65, 550, 270);
		pagoInicialPanel.add(panelAzulImagen);
		panelAzulImagen.setLayout(null);
		
		JLabel panelDinero=new JLabel();
		panelDinero.setOpaque(true);
		panelDinero.setLayout(null);
		panelDinero.setBackground(Color.white);
		panelDinero.setBounds(20, 20, 510, 233);
		panelAzulImagen.add(panelDinero);
		
		JLabel imgDinero = new JLabel("");
		imgDinero.setIcon(new ImageIcon(getClass().getResource("/contenido/dinero.png")));
		imgDinero.setHorizontalAlignment(SwingConstants.CENTER);
		imgDinero.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		imgDinero.setBounds(160, 18, 47, 40);
		panelDinero.add(imgDinero);
		
		
		JLabel tituloEfectivo_1 = new JLabel("Efectivo");
		tituloEfectivo_1.setHorizontalAlignment(SwingConstants.CENTER);
		tituloEfectivo_1.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tituloEfectivo_1.setBounds(195, 25, 163, 40);
		panelDinero.add(tituloEfectivo_1);
		
		JLabel monto = new JLabel("Monto");
		monto.setHorizontalAlignment(SwingConstants.LEFT);
		monto.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		monto.setBounds(20, 75, 100, 35);
		panelDinero.add(monto);
		
		JLabel recibido = new JLabel("Recibido");
		recibido.setHorizontalAlignment(SwingConstants.LEFT);
		recibido.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		recibido.setBounds(20, 130, 100, 35);
		panelDinero.add(recibido);
		
		JLabel cambio = new JLabel("Cambio");
		cambio.setHorizontalAlignment(SwingConstants.LEFT);
		cambio.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		cambio.setBounds(20, 185, 100, 35);
		panelDinero.add(cambio);
		
		String monto1 = costoFinalResp.getText(); /////////////////////////////
		//Salida de datos sobre el monto
		JTextField infoMonto = new JTextField(monto1);
		infoMonto.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoMonto.setBorder(BorderFactory.createCompoundBorder(
				infoMonto.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoMonto.setEditable(false);
		infoMonto.setBackground(new Color(217, 217, 217));
		infoMonto.setBounds(140, 75, 350, 25);
		panelDinero.add(infoMonto);
		
		JTextField infoRecibido = new JTextField();
		infoRecibido.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoRecibido.setBorder(BorderFactory.createCompoundBorder(
				infoRecibido.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoRecibido.setEditable(true);
		infoRecibido.setBackground(new Color(217, 217, 217));
		infoRecibido.setBounds(140, 130, 350, 25);
		panelDinero.add(infoRecibido);
		
		//Aparece en automatico con una operacion
		JTextField infoCambio = new JTextField();
		infoCambio.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoCambio.setBorder(BorderFactory.createCompoundBorder(
				infoCambio.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoCambio.setEditable(false);
		infoCambio.setBackground(new Color(217, 217, 217));
		infoCambio.setBounds(140, 185, 350, 25);
		panelDinero.add(infoCambio);

		emergente.getContentPane().add(pagoInicialPanel);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);
	    
		
	}
	
	public void edicionPago() {
	    emergente.getContentPane().removeAll();
	    emergente.repaint();
	    emergente.setSize(650, 450);

	    JPanel edicionPagoPanel = new JPanel();
	    edicionPagoPanel.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
	    edicionPagoPanel.setBackground(new Color(220, 220, 220));
	    edicionPagoPanel.setLayout(null);

	    JLabel text = new JLabel("Pago en efectivo");
	    text.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
	    text.setHorizontalAlignment(SwingConstants.CENTER);
	    text.setForeground(Color.white);
	    text.setBounds(195, 15, 250, 40);
	    edicionPagoPanel.add(text);

	    JLabel fondoTexto = new JLabel();
	    fondoTexto.setOpaque(true);
	    fondoTexto.setBackground(new Color(0, 73, 102));
	    fondoTexto.setBounds(0, 0, emergente.getWidth(), 50);
	    edicionPagoPanel.add(fondoTexto);

	    JButton botonAceptar = new JButton();
	    botonAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/continuarCheck.png")));
	    botonAceptar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            model = new RentasModel();
	            String tipo2 = (String) tarifasResp.getSelectedItem();
	            String estatusC = (String) estatusResp.getSelectedItem();

	            StringBuilder seleccion = new StringBuilder();
	            if (wifi.isSelected()) {
	                seleccion.append(wifi.getText()).append("\n");
	            }
	            if (restaurante.isSelected()) {
	                seleccion.append(restaurante.getText()).append("\n");
	            }
	            if (recreativos.isSelected()) {
	                seleccion.append(recreativos.getText()).append("\n");
	            }
	            if (lavanderia.isSelected()) {
	                seleccion.append(lavanderia.getText()).append("\n");
	            }
	            String amenidades1 = seleccion.toString();

	            model.textField3(idClienteResp, fechaInicialResp, fechaFinalResp, tipo2, amenidades1, estatusC, subtotalResp, costoFinalResp);
	            model.actualizarRentas(idRenta);

	            reservaExitosa();
	        }
	    });
	    botonAceptar.setVerticalAlignment(SwingConstants.CENTER);
	    botonAceptar.setBorderPainted(false);
	    botonAceptar.setContentAreaFilled(false);
	    botonAceptar.setBounds(440, 350, 165, 50);
	    edicionPagoPanel.add(botonAceptar);

	    JButton botonCancelar = new JButton();
	    botonCancelar.setIcon(new ImageIcon(getClass().getResource("/contenido/cancelarCheck.png")));
	    botonCancelar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("Cancelar");
	            emergente.dispose();
	        }
	    });
	    botonCancelar.setBorderPainted(false);
	    botonCancelar.setContentAreaFilled(false);
	    botonCancelar.setBounds(30, 350, 166, 50);
	    edicionPagoPanel.add(botonCancelar);

	    JPanel panelAzulImagen = new JPanel();
	    panelAzulImagen.setBackground(new Color(0, 72, 103));
	    panelAzulImagen.setBounds(40, 65, 550, 270);
	    edicionPagoPanel.add(panelAzulImagen);
	    panelAzulImagen.setLayout(null);

	    JLabel panelDinero = new JLabel();
	    panelDinero.setOpaque(true);
	    panelDinero.setLayout(null);
	    panelDinero.setBackground(Color.white);
	    panelDinero.setBounds(20, 20, 510, 233);
	    panelAzulImagen.add(panelDinero);

	    JLabel imgDinero = new JLabel("");
	    imgDinero.setIcon(new ImageIcon(getClass().getResource("/contenido/dinero.png")));
	    imgDinero.setHorizontalAlignment(SwingConstants.CENTER);
	    imgDinero.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
	    imgDinero.setBounds(160, 18, 47, 40);
	    panelDinero.add(imgDinero);

	    JLabel tituloEfectivo_1 = new JLabel("Efectivo");
	    tituloEfectivo_1.setHorizontalAlignment(SwingConstants.CENTER);
	    tituloEfectivo_1.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
	    tituloEfectivo_1.setBounds(195, 25, 163, 40);
	    panelDinero.add(tituloEfectivo_1);

	    JLabel subtotal = new JLabel("Subtotal");
	    subtotal.setHorizontalAlignment(SwingConstants.LEFT);
	    subtotal.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
	    subtotal.setBounds(20, 75, 120, 35);
	    panelDinero.add(subtotal);

	    JLabel total = new JLabel("Total");
	    total.setHorizontalAlignment(SwingConstants.LEFT);
	    total.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
	    total.setBounds(20, 115, 100, 35);
	    panelDinero.add(total);

	    JLabel recibido = new JLabel("Recibido");
	    recibido.setHorizontalAlignment(SwingConstants.LEFT);
	    recibido.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
	    recibido.setBounds(20, 155, 100, 35);
	    panelDinero.add(recibido);

	    JLabel cambio = new JLabel("Cambio");
	    cambio.setHorizontalAlignment(SwingConstants.LEFT);
	    cambio.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
	    cambio.setBounds(20, 195, 100, 35);
	    panelDinero.add(cambio);

	    // Salida de datos sobre el monto
	    JTextField infoSubtotal = new JTextField();
	    infoSubtotal.setText(model.obtenerRenta(idRenta));
	    infoSubtotal.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
	    infoSubtotal.setBorder(BorderFactory.createCompoundBorder(
	            infoSubtotal.getBorder(),
	            BorderFactory.createEmptyBorder(3, 1, -5, 0)
	    ));
	    infoSubtotal.setEditable(false);
	    infoSubtotal.setBackground(new Color(217, 217, 217));
	    infoSubtotal.setBounds(140, 75, 350, 25);
	    panelDinero.add(infoSubtotal);

	    JTextField infoTotal = new JTextField();
	    infoTotal.setText(model.obtenerRenta(idRenta));
	    infoTotal.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
	    infoTotal.setBorder(BorderFactory.createCompoundBorder(
	            infoTotal.getBorder(),
	            BorderFactory.createEmptyBorder(3, 1, -5, 0)
	    ));
	    infoTotal.setEditable(false);
	    infoTotal.setBackground(new Color(217, 217, 217));
	    infoTotal.setBounds(140, 115, 350, 25);
	    panelDinero.add(infoTotal);

	    JTextField infoRecibido = new JTextField();
	    infoRecibido.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
	    infoRecibido.setBorder(BorderFactory.createCompoundBorder(
	            infoRecibido.getBorder(),
	            BorderFactory.createEmptyBorder(3, 1, -5, 0)
	    ));
	    infoRecibido.setEditable(true);
	    infoRecibido.setBackground(new Color(217, 217, 217));
	    infoRecibido.setBounds(140, 155, 350, 25);
	    panelDinero.add(infoRecibido);

	    JTextField infoCambio = new JTextField();
	    infoCambio.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
	    infoCambio.setBorder(BorderFactory.createCompoundBorder(
	            infoCambio.getBorder(),
	            BorderFactory.createEmptyBorder(3, 1, -5, 0)
	    ));
	    infoCambio.setEditable(false);
	    infoCambio.setBackground(new Color(217, 217, 217));
	    infoCambio.setBounds(140, 195, 350, 25);
	    panelDinero.add(infoCambio);

	    // Agregar el ActionListener para calcular el cambio
	    infoRecibido.addActionListener(new ActionListener() 
	    {
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	            try 
	            {
	                double totalAmount = Double.parseDouble(infoTotal.getText());
	                double receivedAmount = Double.parseDouble(infoRecibido.getText());
	                double changeAmount = totalAmount - receivedAmount;
	                infoCambio.setText(String.format("%.2f", changeAmount));
	            } catch (NumberFormatException ex) {
	                datosNoValidos();
	            }
	        }
	    });

	    emergente.getContentPane().add(edicionPagoPanel);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);
	    emergente.repaint();
	    emergente.revalidate();
	}
	
	
	public void checkOut()
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.setSize(650, 450);
		
		JPanel checkOutPanel= new JPanel();
		checkOutPanel.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		checkOutPanel.setBackground(new Color(220,220,220));
		checkOutPanel.setLayout(null);

		String textoCheck=("<html><div style='text-align: center;'>"
				+" ¿Está seguro de realizar el check-out?<br>"
				+ " Esta acción finalizará su estadía."
				+"</div></html>");
				
		JLabel text = new JLabel(textoCheck);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.white);
		text.setBounds(155,10,350,60);
		checkOutPanel.add(text);
		
		JLabel fondoTexto = new JLabel();
		fondoTexto.setOpaque(true);
		fondoTexto.setBackground(new Color(0,73,102));
		fondoTexto.setBounds(0,0,emergente.getWidth(),80);
		checkOutPanel.add(fondoTexto);
		
		JButton botonAceptar = new JButton();
		botonAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/continuarCheck.png")));
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Continuar");
				emergente.dispose();
				renta = new RentasController();
				renta.detalles();
				checkOutExitoso();
			
			}
		});
		botonAceptar.setVerticalAlignment(SwingConstants.CENTER);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setBounds(440, 350, 165, 50);
		checkOutPanel.add(botonAceptar);
		
		
		JButton botonCancelar = new JButton();
		botonCancelar .setIcon(new ImageIcon(getClass().getResource("/contenido/cancelarCheck.png")));
		botonCancelar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Cancelar");
				emergente.dispose();
				renta = new RentasController();
				renta.detalles();
				
			}
		});
		botonCancelar.setBorderPainted(false);
		botonCancelar.setContentAreaFilled(false);
		botonCancelar.setBounds(20, 350, 166, 50);
		checkOutPanel.add(botonCancelar);
	
	
		JPanel panelAzulImagen=new JPanel();
		panelAzulImagen.setBackground(new Color(0,72,103));
		panelAzulImagen.setBounds(40, 90, 550, 250);
		panelAzulImagen.setLayout(null);
		checkOutPanel.add(panelAzulImagen);
		
		JLabel imagenCheck=new JLabel();
		imagenCheck.setOpaque(true);
		imagenCheck.setIcon(new ImageIcon(getClass().getResource("/contenido/castle.jpg")));
		imagenCheck.setBounds(20, 20, 510, 210);
		panelAzulImagen.add(imagenCheck);


		emergente.getContentPane().add(checkOutPanel);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);
	    emergente.repaint();
		emergente.revalidate();
		
	}
	public void fechas(int opcion)
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.revalidate();
		emergente.setSize( 560, 290);
		String[] dias = new String[31];
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] años = new String[80]; 
        
        for (int i = 0; i < 31; i++) {
            dias[i] = Integer.toString(i + 1);
        }
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 80; i++) {
            años[i] = Integer.toString(currentYear+ i);
        }
        JPanel fechasPanel= new JPanel();
        fechasPanel.setBackground(new Color(220,220,220));
        fechasPanel.setLayout(null);
        
        JComboBox<String> comboDias = new JComboBox<>(dias);
        comboDias.setBackground(Color.white);
        comboDias.setBounds(200,65,250,25);
        fechasPanel.add(comboDias);
        
        JComboBox<String> comboMes = new JComboBox<>(meses);
        comboMes.setBackground(Color.white);
        comboMes.setBounds(200,105,250,25);
        fechasPanel.add(comboMes);

        JComboBox<String> comboAño = new JComboBox<>(años);
        comboAño.setBackground(Color.white);
        comboAño.setBounds(200,145,250,25);
        fechasPanel.add(comboAño);
        String text="";
        if(opcion==1)
        {
        	text="Seleccione la fecha inicial";
        }
        else
        {
        	if(opcion==2)
        	{
        		text="Seleccione la fecha final";
        	}
        	else
        	{
        		System.out.println("Error");
        	}
        }
        JLabel tituloFechas=new JLabel(text);
		tituloFechas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tituloFechas.setForeground(Color.black);
		tituloFechas.setBounds(100, 15, 350, 45);
		fechasPanel.add(tituloFechas);
       
        JLabel dia=new JLabel("Día: ");
		dia.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		dia.setForeground(Color.black);
		dia.setBounds(120, 65, 70, 40);
		fechasPanel.add(dia);
        
        JLabel mes=new JLabel("Mes: ");
        mes.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		mes.setForeground(Color.black);
		mes.setBounds(120, 105, 70, 40);
        fechasPanel.add(mes);
        
        JLabel año=new JLabel("Año:");
        año.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		año.setForeground(Color.black);
		año.setBounds(120, 145, 70, 40);
        fechasPanel.add(año);
       
        JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Cancelar");
				emergente.getContentPane().removeAll();
				emergente.dispose();
			}
		});
		botonCancelar.setForeground(new Color(255, 255, 255));
		botonCancelar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonCancelar.setBorderPainted(false);
		botonCancelar.setContentAreaFilled(false);
		botonCancelar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonCancelar.setBounds(40, 190, 181, 51);
		fechasPanel.add(botonCancelar);
		
		JLabel imgCancelar= new JLabel();
		imgCancelar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgCancelar.setBounds(40, 190, 181, 51);
		fechasPanel.add(imgCancelar);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Aceptar");
				String fechaNacimiento = (String) comboAño.getSelectedItem() + "-" + (String) comboMes.getSelectedItem() + "-" + (String) comboDias.getSelectedItem();
				 if(opcion==1)
			        {
					 fechaInicialResp.setText(fechaNacimiento);
			        }
			        else
			        {
			        	if(opcion==2)
			        	{
			        		fechaFinalResp.setText(fechaNacimiento);
			        	}
			        	else
			        	{
			        		System.out.println("Error");
			        	}
			        }
				emergente.getContentPane().removeAll();
				emergente.dispose();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(325, 190, 181, 51);
		fechasPanel.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(325, 190, 181, 51);
		fechasPanel.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/tiempo.png")));
		iconPosion.setBounds(15, 74, 90, 94);
		fechasPanel.add(iconPosion);

		emergente.getContentPane().add(fechasPanel);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);
	    emergente.repaint();
	    emergente.revalidate();
		
	}
	
	public void checkOutExitoso()
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.revalidate();
		emergente.setSize( 560, 290);
		
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		String info=("<html><div style='text-align: center;'>"
		+"El check-out se ha completado<br>"+
		"exitosamente<br>"+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(40,20,487,168);
		datos.add(text);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Acceso");
				emergente.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(182, 190, 181, 51);
		datos.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(183, 190, 181, 51);
		datos.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/exito.png")));
		iconPosion.setBounds(10, 55, 80, 87);
		datos.add(iconPosion);
		
		emergente.getContentPane().add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	public void errorFechas()
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.revalidate();
		emergente.setSize( 560, 290);
		
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		String info=("<html><div style='text-align: center;'>"
		+"Las fechas seleccionadas ya<br>"+
		"tienen una reserva. Por favor,<br>"+
		"elige otras fechas para continuar.<br>"+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(40,20,487,168);
		datos.add(text);
		
		JButton botonAceptar = new JButton("Continuar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Continuar");
				emergente.dispose();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(182, 190, 181, 51);
		datos.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(183, 190, 181, 51);
		datos.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/error.png")));
		iconPosion.setBounds(15, 55, 80, 87);
		datos.add(iconPosion);
		
		emergente.getContentPane().add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	
	public void reservaExitosa()
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.revalidate();
		emergente.setSize( 560, 290);
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		
		String info=("<html><div style='text-align: center;'>"
		+"El pago se ha realizado de<br>"+
		"manera exitosa. La reservación se<br>"+
		"ha completado."+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(40,10,487,168);
		datos.add(text);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Acceso");
				emergente.dispose();
				renta = new RentasController();
				renta.rentasPrincipal();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(182, 190, 181, 51);
		datos.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(183, 190, 181, 51);
		datos.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/cofrecito.png")));
		iconPosion.setBounds(10, 48, 80, 87);
		datos.add(iconPosion);
		
		emergente.getContentPane().add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	public void campoVacio()
	{
		
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		//String info="Los datos que ha ingresado son "+"\n"+ "incorrectos, favor de ingresarlos"+ "\n"+ "correctamente";
		String info=("<html><div style='text-align: center;'>"
		+"Hay campos vacíos, por favor <br>"+
		"rellene los campos faltantes. <br>"+
		"</div></html>");
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(34,23,487,168);
		datos.add(text);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Acceso");
				emergente.dispose();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(182, 190, 181, 51);
		datos.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(183, 190, 181, 51);
		datos.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/camposVacios.png")));
		iconPosion.setBounds(15, 57, 80, 80);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	
	public void datosNoValidos()
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.revalidate();
		emergente.setSize( 560, 290);
		
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		//String info="Los datos que ha ingresado son "+"\n"+ "incorrectos, favor de ingresarlos"+ "\n"+ "correctamente";
		String info=("<html><div style='text-align: center;'>"
		+"El tipo de dato que estás  <br>"+
		"intentando ingresar no es válido <br>"+
		"para este campo. Por favor, <br>"+
		"inténtalo de nuevo con un dato <br>"+
		"diferente.<br>"+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(34,12,487,168);
		datos.add(text);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Acceso");
				emergente.dispose();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(182, 190, 181, 51);
		datos.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(183, 190, 181, 51);
		datos.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/camposVacios.png")));
		iconPosion.setBounds(15, 57, 80, 80);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	
	
	public void eleccion(int eleccion)
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.revalidate();
		emergente.setSize( 560, 290);
		
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		//String info="Los datos que ha ingresado son "+"\n"+ "incorrectos, favor de ingresarlos"+ "\n"+ "correctamente";
		String info=("<html><div style='text-align: center;'>"
		+"¿Estás seguro de que deseas  <br>"+
		"continuar? <br>"+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(34,17,487,168);
		datos.add(text);
		
		JButton cancelarBtn = new JButton("Cancelar");
		cancelarBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("cancelar");
				emergente.dispose();
			}
		});
		cancelarBtn.setForeground(new Color(255, 255, 255));
		cancelarBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		cancelarBtn.setBorderPainted(false);
		cancelarBtn.setContentAreaFilled(false);
		cancelarBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		cancelarBtn.setBounds(50, 190, 181, 51);
		datos.add(cancelarBtn);
		
		JLabel imgCancelar= new JLabel();
		imgCancelar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgCancelar.setBounds(50, 190, 181, 51);
		datos.add(imgCancelar);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Aceptar");
				if(eleccion==1)
				{
					idClienteResp.setText("");
					fechaInicialResp.setText("");
					fechaFinalResp.setText("");
					costoFinalResp.setText("");

					emergente.dispose();
					emergente.dispose();
					
				}
				else
				{
					if(eleccion==2)
					{
						System.out.println("Pa eliminar el cliente");
						emergente.dispose();
					}
				}
				
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(313, 190, 181, 51);
		datos.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(313, 190, 181, 51);
		datos.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/veneno.png")));
		iconPosion.setBounds(20, 50, 80, 91);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	
	
	public void seleccion() 
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.revalidate();
		emergente.setSize( 560, 290);
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		//String info="Los datos que ha ingresado son "+"\n"+ "incorrectos, favor de ingresarlos"+ "\n"+ "correctamente";
		String info=("<html><div style='text-align: center;'>"
		+"No has seleccionado ninguna <br>"+
		"renta, favor de seleccionarla.<br>"+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(34,12,487,168);
		datos.add(text);
		
		JButton botonAceptar = new JButton("Continuar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Acceso");
				emergente.dispose();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(182, 190, 181, 51);
		datos.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(183, 190, 181, 51);
		datos.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/camposVacios.png")));
		iconPosion.setBounds(15, 57, 80, 80);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	
	public void createCenterPanel() {
        ArrayList<Habitacion> rooms = HabitacionesModel.getHabitaciones();
        System.out.println("Número de habitaciones: " + rooms.size());
        int y = 26; 
        int yCuarto =170;
        for (Habitacion roomcito : rooms) 
        {
        	HabitacionView habiView = new HabitacionView(roomcito,y,frame);
            panelMovil.add(habiView);
            panelMovil.repaint();
    		panelMovil.revalidate();
    		y+=yCuarto;
    		
        }
        int nuevaY=rooms.size()*yCuarto + 200; 
        panelMovil.setPreferredSize(new Dimension(1175, nuevaY));
        //panelMovil.setPreferredSize(new Dimension(1175, yPanel));
    }
	
	public JTextField getId() {	
        return infoIdRenta;		
    }

	
	public JTextField getNombre() {
        return infoNombre;
    }
	
	public JTextField getCorreo() {
        return infoCorreo;
    }
	
	public JTextField getFecha1() {
        return infoFechaInicial;
    }
	
	public JTextField getFecha2()
	{
		return infoFechaFinal;
	}
	
	public JTextField getTarifasD()
	{
		return infoNomEmerg;
	}
	
	public JTextArea getAmenidades()
	{
		return infoAmenidades;
	}
	
	public JTextField getSubtotal() {
        return infoSubtotal;
    }
 
	 public JTextField getTotal()
	 {
		 return infoTotal;
	 }
	 
	 public JTextField getEstatus() {
		    return infoEstatus;
		}
	
	
}
