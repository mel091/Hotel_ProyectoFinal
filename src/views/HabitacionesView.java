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

import com.mysql.cj.jdbc.Blob;

import controllers.ClientesController;
import controllers.HabitacionesController;
import controllers.InicioController;
import controllers.RentasController;
import controllers.TarifasController;
import controllers.TiposController;
import models.ClientesModel;
import models.HabitacionesModel;

public class HabitacionesView {
	
	private JFrame frame;
	private JDialog emergente;
	private HabitacionesController room;
	private HabitacionesModel model;
	private InicioController inicio;
	public String idHabSeleccion;
	public JLabel nomHabitacion = new JLabel("");
	private String idHabitacion;
	private String path;
	private Blob imgB;
	
	private ArrayList<String> HabitacionIds;
	public JComboBox <String> tipoResp = new JComboBox <String>();
	String[] tiposHab = {"Estándar", "Habitación doble", "Suite"};
	private int indexActual;
	
	public TarifasController tarifa;
	public TiposController tipo;
	public RentasController renta;
	public ClientesController cliente;
	
	JCheckBox wifi = new JCheckBox("Wi-Fi");
	JCheckBox restaurante = new JCheckBox("Restaurante");
	JCheckBox recreativos = new JCheckBox("Espacios recreativos");
	JCheckBox lavanderia = new JCheckBox("Lavandería");
	
	JTextField infoIdHabitacion = new JTextField("");
	JTextField infoNombre = new JTextField("");
	JTextField infoTipo = new JTextField("");
	JTextField infoTam = new JTextField("");
	JTextArea infoDescrip = new JTextArea("");
	JTextArea amenidades2 = new JTextArea("");
	JTextArea tarifasText = new JTextArea("");
	JLabel nombreHabi = new JLabel("");
	
	JTextField nombreHabiResp = new JTextField("");;
	JTextField tamResp = new JTextField("");;
	JComboBox tipoResp1 = new JComboBox();
	JTextArea descResp = new JTextArea();
	JCheckBox wifi1 = new JCheckBox("Wi-Fi");
	JCheckBox restaurante1 = new JCheckBox("Restaurante");
	JCheckBox recreativos1 = new JCheckBox("Espacios recreativos");
	JCheckBox lavanderia1 = new JCheckBox("Lavandería");
	JTextArea tarifasText1 = new JTextArea("");
	
	
	public HabitacionesView() {
		frame = new JFrame();
		frame.setBounds(10, 5, 1350, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emergente=new JDialog(frame,"Emergente", true);
		emergente.setSize( 560, 290);
		emergente.setResizable(false);
		frame.setVisible(true);
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
		habitaciones.setForeground(new Color(252,210,87));
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(Color.white);
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(Color.white);
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
				// TODO Auto-generated method stub
				System.out.println("Rentas");
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
				System.out.println("Rentas");
				frame.dispose();
				tipo = new TiposController();
				tipo.crear();
			}
		});
		btnTipos.setBorderPainted(false);
		btnTipos.setContentAreaFilled(false);
		btnTipos.setBounds(985, 36, 295, 30);
		disneyFondo.add(btnTipos);
		
		//Panel vertical (Detalles)
		
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
		
		
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(new Color(252,210,87));
		panelVertical1.add(consultar);
		
		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		consultarBtn.setBounds(0, 0, 130, 277);
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		panelVertical1.add(consultarBtn);
		
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
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(255, 255, 255));
		panelVertical2.add(crear);
		
		JButton crearBtn = new JButton();
		crearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.crear();
			}
		});
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		crearBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(crearBtn);
		
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
		
		//botones del panel central
		JButton editarBtn= new JButton(); //MOVER DESPUES DE DETALLES
		editarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/editar.png")));
		editarBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idHabitacion != null && !idHabitacion.isEmpty()) {
					 
					String tipoSeleccionado = getTipoSeleccionado();
					System.out.println(tipoSeleccionado);
  		            model = new HabitacionesModel();
  		            model.textField2(nombreHabiResp, tamResp,  tipoResp, descResp, wifi, restaurante, recreativos, lavanderia, tarifasText1 );
  		            model.recuperaDatos(tipoSeleccionado, idHabitacion); // Solo recupera y establece datos
  		            editar();
  		        }
			}
		});
		editarBtn.setBorderPainted(false);
		editarBtn.setContentAreaFilled(false);
		editarBtn.setBounds(690, 480, 328, 45);
		panelCentral.add(editarBtn);
		
		JButton detallesBtn= new JButton(); //MOVER DESPUES DE LA TABLA
		detallesBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/detalles.png")));
		detallesBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idHabitacion != null && !idHabitacion.isEmpty()) {
		    	    System.out.println(idHabitacion);
		    	    
		    	    model = new HabitacionesModel();
		    	    HabitacionesView view = new HabitacionesView();
		    	    
		    	    model.textField(view.getId(), view.getNombre(), view.getTipo(), view.getTam(),
		    			   view.getDesc(), view.getSolicitudes(), view.getTarifas(), view.getNombreDetalles());

		    	    frame.dispose();
		    	    
		    	    model.mostrarDetalles(idHabitacion);
		    	   
		    	    view.detalles();
		    	   
		    	} else {
		    	    //seleccion();
		    		System.out.println("ningun coso selec");
		    	}
			}
		});
		detallesBtn.setBorderPainted(false);
		detallesBtn.setContentAreaFilled(false);
		detallesBtn.setBounds(145, 480, 328, 45);
		panelCentral.add(detallesBtn);
		
		//Panel azul que contiene el panel de la tabla
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(0, 73, 102));
		panelAzul.setBounds(50, 50, 1075, 366);
		panelCentral.add(panelAzul);
		panelAzul.setLayout(null);
		
		JPanel panelDeTabla = new JPanel();
		panelDeTabla.setBounds(25, 25, 1023, 316);
		panelAzul.add(panelDeTabla);
		
		model = new HabitacionesModel();
		DefaultTableModel datosHab = model.tablaHabitaciones();
		
//		String tableTitle[]={"ID de la habitación", "Nombre", "Tipo de habitación", "Tamaño"}; //borrar de aqui
//		String tableData[][] = {
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""},
//							    {"", "", "", ""}
//		};
	
//		JTable productoTable= new JTable(tableData, tableTitle);
//		productoTable.setFont(new Font("Palatino Linotype", Font.PLAIN, 12));
//		productoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
//		{
//	            @Override
//	            public void valueChanged(ListSelectionEvent e) 
//	            {
//	                if (!e.getValueIsAdjusting()) 
//	                {
//	                    int selectedRow = productoTable.getSelectedRow();
//	                    if (selectedRow != -1) 
//	                    { 
//	                        System.out.println("Fila seleccionada: " + selectedRow);
//	                        
//	                    }
//	                }
//	            }
//		    }); //hasta aca
		

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
	                    	idHabitacion = (String) productoTable.getValueAt(selectedRow, 0);
	                    	System.out.println("ID seleccionado: " + idHabitacion);
	                    }
	                }
	            }
		    });
		
		panelDeTabla.setLayout(null);
		
		JScrollPane tableScroll=new JScrollPane(productoTable);
		tableScroll.setBounds(0, 0, 1023, 316);
		panelDeTabla.removeAll();
		panelDeTabla.add(tableScroll);
		

		frame.getContentPane().add(panelConsultar);
			
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
		
		idHabitacion = getIdHab();	///////////////////////////////////////////////////////////////////////////////////
	
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
		habitaciones.setForeground(new Color(252,210,87));
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(Color.white);
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(Color.white);
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
				// TODO Auto-generated method stub
				System.out.println("Rentas");
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
		
		//Panel vertical (Detalles)
		
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
		
		
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(new Color(252,210,87));
		panelVertical1.add(consultar);
		
		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		consultarBtn.setBounds(0, 0, 130, 277);
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		panelVertical1.add(consultarBtn);
		
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
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(255, 255, 255));
		panelVertical2.add(crear);
		
		JButton crearBtn = new JButton();
		crearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.crear();
			}
		});
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		crearBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(crearBtn);
		
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
		
		//botones del panel central
		JButton descargarBtn= new JButton();
		descargarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/descargar.png")));
		descargarBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Descargar ID seleccionado: " + idHabitacion);
				
				idHabSeleccion = infoIdHabitacion.getText();
                System.out.println("Descargar ID seleccionado: " + idHabSeleccion);
                descarga();
			}
		});
		descargarBtn.setBorderPainted(false);
		descargarBtn.setContentAreaFilled(false);
		descargarBtn.setBounds(925, 472, 200, 49);
		panelCentral.add(descargarBtn);
		
		JButton historialBtn= new JButton();
		historialBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/historial.png")));
		historialBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Historial");
				idHabSeleccion = infoIdHabitacion.getText();
				historial();
			}
		});
		historialBtn.setBorderPainted(false);
		historialBtn.setContentAreaFilled(false);
		historialBtn.setBounds(619, 472, 200, 49);
		panelCentral.add(historialBtn);
		
		
		//JLabel nombreHabi = new JLabel("Nombre de la habitación"); //comentar
		nombreHabi.setHorizontalAlignment(SwingConstants.CENTER);
		nombreHabi.setForeground(new Color(0, 0, 0));
		nombreHabi.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		nombreHabi.setBounds(330, 30, 489, 45);
		panelCentral.add(nombreHabi);
	
		JLabel fondoHabitacion = new JLabel("");
		fondoHabitacion.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoHabitacion.setBounds(330, 21, 489, 45);
		panelCentral.add(fondoHabitacion);
		
		idHabSeleccion = infoIdHabitacion.getText();
		indexActual = Integer.parseInt(idHabSeleccion);

		model = new HabitacionesModel();
		
		JButton atrasBtn = new JButton();
		atrasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("atras");
				
				 if (indexActual > 0) {
	                	System.out.println("viejo indexActual: " + indexActual);
	                    indexActual--;
	                    System.out.println("Nuevo indexActual: " + indexActual);
	 
	                    HabitacionIds = model.cargarHab(indexActual);
	                    String actual = HabitacionIds.get(indexActual-1);
	                    System.out.println("hab act: " + actual);

	                    HabitacionesView view = new HabitacionesView();
	                    model.textField(view.getId(), view.getNombre(), view.getTipo(), view.getTam(),
				    	 view.getDesc(), view.getSolicitudes(), view.getTarifas(), view.getNombreDetalles());

                   frame.dispose();

                   model.mostrarDetalles(actual);
                   view.detalles();
	                }
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

				HabitacionIds = model.cargarHab(indexActual);
				
				if (indexActual < HabitacionIds.size() - 1) 
				{
					System.out.println("viejo indexActual: " + indexActual); 
					indexActual++;
					System.out.println("Nuevo indexActual: " + indexActual);
					
                    String actual = HabitacionIds.get(indexActual-1);
                    System.out.println("cliente act: " + actual);
                    
                    HabitacionesView view = new HabitacionesView();
                    model.textField(view.getId(), view.getNombre(), view.getTipo(), view.getTam(),
			    			   view.getDesc(), view.getSolicitudes(), view.getTarifas(), view.getNombreDetalles());

		    	    frame.dispose();

		    	    model.mostrarDetalles(actual);
		    	    view.detalles();
                }
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
		
		JPanel panelInfo = new JPanel();
		 panelInfo.setPreferredSize(new Dimension(400, 640));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 73, 102));
		panel.setBounds(610, 109, 515, 333);
		panelCentral.add(panel);
		panel.setLayout(null);
		
		JPanel panelImagen = new JPanel();
		panelImagen.setBounds(20, 20, 472, 291);
		panel.add(panelImagen);
		panelImagen.setLayout(null);
		panelInfo.setLayout(null);
		
		JLabel idHabitacion = new JLabel("ID de la habitación"); //borra
		idHabitacion.setForeground(Color.BLACK);
		idHabitacion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		idHabitacion.setBounds(20, 11, 354, 29);
		panelInfo.add(idHabitacion);
		
		JTextField infoIdHabitacion = new JTextField("271873"); //borra
		infoIdHabitacion.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoIdHabitacion.setBorder(BorderFactory.createCompoundBorder(
				infoIdHabitacion.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoIdHabitacion.setEditable(false);
		infoIdHabitacion.setBackground(new Color(217, 217, 217));
		infoIdHabitacion.setBounds(20, 40, 420, 25);
		panelInfo.add(infoIdHabitacion);
		
		JLabel nombreHabitacion = new JLabel("Nombre");
		nombreHabitacion.setForeground(Color.BLACK);
		nombreHabitacion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreHabitacion.setBounds(20, 78, 354, 29);
		panelInfo.add(nombreHabitacion);
		
		JTextField infoNombre = new JTextField("Mickey"); //borra
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
		
		JLabel tipoHabitacion = new JLabel("Tipo de habitación");
		tipoHabitacion.setForeground(Color.BLACK);
		tipoHabitacion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tipoHabitacion.setBounds(20, 143, 354, 29);
		panelInfo.add(tipoHabitacion);
		
		JTextField infoTipo = new JTextField("Tipo"); //borra
		infoTipo.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoTipo.setBorder(BorderFactory.createCompoundBorder(
				infoTipo.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoTipo.setEditable(false);
		infoTipo.setColumns(10);
		infoTipo.setBackground(new Color(217, 217, 217));
		infoTipo.setBounds(20, 171, 420, 25);
		panelInfo.add(infoTipo);
		
		
		JLabel tamaño = new JLabel("Tamaño");
		tamaño.setForeground(Color.BLACK);
		tamaño.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tamaño.setBounds(20, 207, 354, 29);
		panelInfo.add(tamaño);
		
		JTextField infoTam = new JTextField("3m"); //borra
		infoTam.setBorder(BorderFactory.createCompoundBorder(
				infoTam.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoTam.setEditable(false);
		infoTam.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoTam.setColumns(10);
		infoTam.setBackground(new Color(217, 217, 217));
		infoTam.setBounds(20, 238, 420, 25);
		panelInfo.add(infoTam);
		
		JLabel descripcion = new JLabel("Descripción");
		descripcion.setForeground(Color.BLACK);
		descripcion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		descripcion.setBounds(20, 268, 194, 46);
		panelInfo.add(descripcion);
		
		JTextArea infoDescrip = new JTextArea("Blablabla"); //borra
		infoDescrip.setBackground(new Color(217, 217, 217));
		infoDescrip.setFont(new Font("Monospaced", Font.PLAIN, 18));
		infoDescrip.setBounds(20, 310, 420, 75);
		infoDescrip.setBorder(BorderFactory.createCompoundBorder(
				infoDescrip.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoDescrip.setEditable(false);
		panelInfo.add(infoDescrip);
		
		
		JLabel amenidades = new JLabel("Amenidades");
		amenidades.setForeground(Color.BLACK);
		amenidades.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		amenidades.setBounds(20, 390, 233, 46);
		panelInfo.add(amenidades);
		
		JPanel panelito = new JPanel();
		panelito.setBackground(new Color(217, 217, 217));
		panelito.setBounds(20, 430, 420, 60);
		panelInfo.add(panelito);
		panelito.setLayout(null);
		
		amenidades2.setBorder(BorderFactory.createCompoundBorder(
				amenidades2.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		amenidades2.setEditable(false);
		amenidades2.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		amenidades2.setColumns(10);
		amenidades2.setBackground(new Color(217, 217, 217));
		amenidades2.setBounds(0,0, 420, 60);
		panelito.add(amenidades2);
		
		
		wifi.setOpaque(false);
		wifi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		wifi.setBounds(0, 0, 200, 31);
		panelito.add(wifi);
		
		
		restaurante.setOpaque(false);
		restaurante.setFont(new Font("Tahoma", Font.PLAIN, 18));
		restaurante.setBounds(0, 30, 200, 31);
		panelito.add(restaurante);
		
		
		recreativos.setOpaque(false);
		recreativos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		recreativos.setBounds(220, 0, 200, 31);
		panelito.add(recreativos);
		
		
		lavanderia.setOpaque(false);
		lavanderia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lavanderia.setBounds(220, 30, 200, 31);
		panelito.add(lavanderia); /////////////////////////////// hasta aqui
		
		JLabel tarif = new JLabel("Tarifas");
		tarif.setForeground(Color.BLACK);
		tarif.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tarif.setBounds(20, 500, 194, 46);
		panelInfo.add(tarif);
		
		JPanel tarifaPanel = new JPanel();
		tarifaPanel.setBackground(new Color(217, 217, 217));
		tarifaPanel.setLayout(null);
		tarifaPanel.setSize(new Dimension(420,100));
		
		tarifasText.setBorder(BorderFactory.createCompoundBorder(
				amenidades2.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		tarifasText.setEditable(false);
		tarifasText.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		tarifasText.setColumns(10);
		tarifasText.setBackground(new Color(217, 217, 217));
		tarifasText.setBounds(0,0, 420, 100);
		tarifaPanel.add(tarifasText);
		
		JScrollPane scrollBar = new JScrollPane(tarifaPanel);
		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.setBounds(20, 540, 420, 75);
		panelInfo.add(scrollBar);
		
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
		//Panel principal
		frame.getContentPane().removeAll();
		frame.repaint();
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
		habitaciones.setForeground(new Color(252,210,87));
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(Color.white);
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(Color.white);
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
				// TODO Auto-generated method stub
				System.out.println("Rentas");
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
		
		//Panel vertical (Detalles)
		
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
		
		
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(new Color(252,210,87));
		panelVertical1.add(consultar);
		
		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		consultarBtn.setBounds(0, 0, 130, 277);
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		panelVertical1.add(consultarBtn);
		
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
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(255, 255, 255));
		panelVertical2.add(crear);
		
		JButton crearBtn = new JButton();
		crearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.crear();
			}
		});
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		crearBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(crearBtn);
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
		panelEditar.add(panelCentral);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(0, 73, 102));
		panelAzul.setBounds(24, 11, 1115, 539);
		panelCentral.add(panelAzul);
		panelAzul.setLayout(null);
		
		JLabel editarHabitacion = new JLabel("Editar habitación");
		editarHabitacion.setForeground(new Color(0, 0, 0));
		editarHabitacion.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		editarHabitacion.setBounds(443, 30, 254, 46);
		panelAzul.add(editarHabitacion);
	
		JLabel fondoHabita = new JLabel("");
		fondoHabita.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoHabita.setBounds(315, 21, 489, 45);
		panelAzul.add(fondoHabita);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(BorderFactory.createLineBorder(new Color(252,210,87), 3));
		panelInfo.setBounds(42, 87, 1038, 384);
		panelAzul.add(panelInfo);
		panelInfo.setLayout(null);
		
		JButton subirBtn= new JButton();
		subirBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		subirBtn.setForeground(new Color(255, 255, 255));
		subirBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/subirHab.png")));
		subirBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("Subir");
//				model = new ClientesModel();
				model.subirImg();
			}
		});
		subirBtn.setBorderPainted(false);
		subirBtn.setContentAreaFilled(false);
		subirBtn.setBounds(581, 24, 420, 116);
		panelInfo.add(subirBtn);
		
		JLabel nombreHabitacion = new JLabel("Nombre");
		nombreHabitacion.setForeground(Color.BLACK);
		nombreHabitacion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreHabitacion.setBounds(35, 21, 196, 46);
		panelInfo.add(nombreHabitacion);
		
	
		JTextField nombreHabiResp = new JTextField(); //borra
		nombreHabiResp.setBorder(BorderFactory.createCompoundBorder(
				nombreHabiResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		nombreHabiResp.setBackground(new Color(217, 217, 217));
		nombreHabiResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		nombreHabiResp.setBounds(35, 59, 420, 25);
		panelInfo.add(nombreHabiResp);
		nombreHabiResp.setColumns(10);
		
		JLabel tipoHabi = new JLabel("Tipo de habitación");
		tipoHabi.setForeground(Color.BLACK);
		tipoHabi.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tipoHabi.setBounds(35, 95, 196, 46);
		panelInfo.add(tipoHabi);
		
		JLabel tamaño = new JLabel("Tamaño");
		tamaño.setForeground(Color.BLACK);
		tamaño.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tamaño.setBounds(35, 170, 216, 46);
		panelInfo.add(tamaño);
		
		JTextField telResp = new JTextField(); //borra
		telResp.setBorder(BorderFactory.createCompoundBorder(
				telResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		telResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		telResp.setColumns(10);
		telResp.setBackground(new Color(217, 217, 217));
		telResp.setBounds(35, 214, 420, 25);
		panelInfo.add(telResp);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setForeground(Color.BLACK);
		descripcion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		descripcion.setBounds(35, 244, 109, 46);
		panelInfo.add(descripcion);
		
		JLabel amenidades = new JLabel("Amenidades");
		amenidades.setForeground(Color.BLACK);
		amenidades.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		amenidades.setBounds(581, 145, 233, 46);
		panelInfo.add(amenidades);
		
		JLabel tarifa = new JLabel("Tarifas");
		tarifa.setForeground(Color.BLACK);
		tarifa.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tarifa.setBounds(581, 250, 364, 46);
		panelInfo.add(tarifa);
		
		
		tipoResp.setBackground(new Color(217, 217, 217));
		tipoResp.setBounds(35, 138, 420, 25);
		panelInfo.add(tipoResp);
		
		
		descResp.setBackground(new Color(217, 217, 217));
		descResp.setBounds(35, 286, 420, 75);
		panelInfo.add(descResp);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(217, 217, 217));
		panel.setBounds(581, 185, 420, 60);
		panelInfo.add(panel);
		panel.setLayout(null);
		
		
		wifi.setOpaque(false);
		wifi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		wifi.setBounds(0, 0, 200, 31);
		panel.add(wifi);
		
		
		restaurante.setOpaque(false);
		restaurante.setFont(new Font("Tahoma", Font.PLAIN, 18));
		restaurante.setBounds(0, 30, 200, 31);
		panel.add(restaurante);
		
		
		recreativos.setOpaque(false);
		recreativos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		recreativos.setBounds(220, 0, 200, 31);
		panel.add(recreativos);
		
		
		lavanderia.setOpaque(false);
		lavanderia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lavanderia.setBounds(220, 30, 200, 31);
		panel.add(lavanderia);
		
		JPanel tarifaPanel = new JPanel();
		tarifaPanel.setBackground(new Color(217, 217, 217));
		tarifaPanel.setLayout(null);
		tarifaPanel.setSize(new Dimension(420,100));
		
		JScrollPane scrollBar = new JScrollPane(tarifaPanel);
		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.setBounds(581, 290, 420, 75);
		panelInfo.add(scrollBar);

		JButton eliminarHabi = new JButton();
		eliminarHabi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new HabitacionesModel();
				model.eliminarHabitacion(idHabitacion);
			}
		});
		eliminarHabi.setBorderPainted(false);
		eliminarHabi.setContentAreaFilled(false);
		eliminarHabi.setIcon(new ImageIcon(getClass().getResource("/contenido/eliminarHabi.png")));
		eliminarHabi.setBounds(85, 482, 380, 50);
		panelAzul.add(eliminarHabi);
		
		JButton cambiosBtn = new JButton();
		cambiosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cambiosBtn.setBorderPainted(false);
		cambiosBtn.setContentAreaFilled(false);
		cambiosBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/guardarCambios.png")));
		cambiosBtn.setBounds(642, 482, 387, 50);
		panelAzul.add(cambiosBtn);
	
//		//Titulo del panel central
//		JLabel editarHabi = new JLabel("Editar habitación");
//		editarHabi.setForeground(new Color(0, 0, 0));
//		editarHabi.setFont(new Font("Palatino Linotype", Font.BOLD, 35));
//		editarHabi.setBounds(413, 34, 465, 65);
//		panelCentral.add(editarHabi);
//		
		//boton de regreso
		JButton regresarBtn = new JButton();
		regresarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/regresar.png")));
		regresarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		regresarBtn.setBorderPainted(false);
		regresarBtn.setContentAreaFilled(false);
		regresarBtn.setBounds(34, 11, 80, 80);
		panelAzul.add(regresarBtn);
		
	
		frame.getContentPane().add(panelEditar);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
			
	}
	public void historial()
	{
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.setSize(650, 450);
		
		JPanel historialPanel= new JPanel();
		historialPanel.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		historialPanel.setBackground(new Color(220,220,220));
		historialPanel.setLayout(null);

		JLabel text = new JLabel("Historial");
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.white);
		text.setBounds(225,15,150,40);
		historialPanel.add(text);
		
		//checar eto
		idHabitacion = getIdHab();
		
		JLabel fondoTexto = new JLabel();
		fondoTexto.setOpaque(true);
		fondoTexto.setBackground(new Color(0,73,102));
		fondoTexto.setBounds(0,0,emergente.getWidth(),50);
		historialPanel.add(fondoTexto);
		
		JPanel paneltablitaAzul= new JPanel();
		paneltablitaAzul.setBounds(13, 60, 610, 270);
		paneltablitaAzul.setBackground(new Color(0,73,102));
		paneltablitaAzul.setLayout(null);
		historialPanel.add(paneltablitaAzul);
		
		JPanel paneltablita= new JPanel();
		paneltablita.setBounds(10, 10, 590, 250);
		paneltablita.setBackground(Color.white);
		paneltablita.setLayout(null);
		paneltablitaAzul.add(paneltablita);

		//Checar eto
//		model=new HabitacionesModel();
//		DefaultTableModel historialHabitaciones= model.tablaHistorialHabitaciones(idHabitacion);
//		JTable productoTable= new JTable(historialHabitaciones); //dentro de los parentesis mete "datosClientes" 
//		
		//productoTable.setFont(new Font("Palatino Linotype", Font.PLAIN, 12));
//		JScrollPane scrollPane = new JScrollPane(productoTable);
//		scrollPane.setBounds(0, 0, 600, 400);
//		paneltablita.add(scrollPane);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				cliente = new ClientesController();
				cliente.detalles();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(220, 345, 181, 51);
		historialPanel.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(220, 345, 181, 51);
		historialPanel.add(imgAceptar);
		
		emergente.getContentPane().add(historialPanel);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);
	    emergente.repaint();
	    emergente.revalidate();
	    
		
	}

	
	public void descarga()
	{
		
		emergente.getContentPane().removeAll();
		emergente.repaint();
		emergente.setSize(550, 680);
		JPanel descargaPanel= new JPanel();
		descargaPanel.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		descargaPanel.setBackground(new Color(220,220,220));
		descargaPanel.setLayout(null);
		
		JLabel text = new JLabel("Previsualización");
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.white);
		text.setBounds(140,15,250,40);
		descargaPanel.add(text);
		
		JLabel fondoTexto = new JLabel();
		fondoTexto.setOpaque(true);
		fondoTexto.setBackground(new Color(0,73,102));
		fondoTexto.setBounds(0,0,emergente.getWidth(),50);
		descargaPanel.add(fondoTexto);
		
		JButton botonAceptar = new JButton("Cancelar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.detalles();
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAceptar.setBorderPainted(false);
		botonAceptar.setContentAreaFilled(false);
		botonAceptar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAceptar.setBounds(180, 560, 181, 51);
		descargaPanel.add(botonAceptar);
		
		JLabel imgAceptar= new JLabel();
		imgAceptar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAceptar.setBounds(180, 560, 181, 51);
		descargaPanel.add(imgAceptar);
		
		
		emergente.getContentPane().add(descargaPanel);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);
	    emergente.repaint();
	    emergente.revalidate();

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
		habitaciones.setForeground(new Color(252,210,87));
		habitaciones.setBounds(315, 36, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(Color.white);
		clientes.setBounds(540, 36, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(Color.white);
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
				// TODO Auto-generated method stub
				System.out.println("Rentas");
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
		
		//Panel vertical (Detalles)
		
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
		
		
		
		JLabel consultar=new JLabel("Consultar");
		consultar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		consultar.setBounds(9,  133, 130, 35);
		consultar.setForeground(Color.white);
		panelVertical1.add(consultar);
		
		
		JButton consultarBtn = new JButton();
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.consultar();
			}
		});
		consultarBtn.setBounds(0, 0, 130, 277);
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		panelVertical1.add(consultarBtn);
		
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
		
		JLabel crear=new JLabel("Crear");
		crear.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		crear.setBounds(28, 135, 130, 35);
		crear.setForeground(new Color(252,210,87));
		panelVertical2.add(crear);
		
		JButton crearBtn = new JButton();
		crearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				room = new HabitacionesController();
				room.crear();
			}
		});
		crearBtn.setBorderPainted(false);
		crearBtn.setContentAreaFilled(false);
		crearBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(crearBtn);
		
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
		
		JLabel habitacion = new JLabel("Habitación");
		habitacion.setForeground(new Color(0, 0, 0));
		habitacion.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		habitacion.setBounds(480, 30, 179, 46);
		panelAzul.add(habitacion);
	
		JLabel fondoCliente = new JLabel("");
		fondoCliente.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoCliente.setBounds(315, 21, 489, 45);
		panelAzul.add(fondoCliente);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(BorderFactory.createLineBorder(new Color(252,210,87), 3));
		panelInfo.setBounds(42, 87, 1038, 384);
		panelAzul.add(panelInfo);
		panelInfo.setLayout(null);
		
		JButton subirBtn= new JButton();
		subirBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		subirBtn.setForeground(new Color(255, 255, 255));
		subirBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/subirHab.png")));
		subirBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new HabitacionesModel();
				path = model.subirImg();
				if(path != null)
				{
					imgB = model.imageToBlob(path);
					model.imagen(subirBtn);
				}
				else
				{
					System.out.println("ninguna img selec");
				}
				subirBtn.setEnabled(false); //el boton no sirve despues de que q se click una vez
			}
		});
		subirBtn.setBorderPainted(false);
		subirBtn.setContentAreaFilled(false);
		subirBtn.setBounds(581, 24, 420, 116);
		panelInfo.add(subirBtn);
		
		JLabel nombreHabitacion = new JLabel("Nombre");
		nombreHabitacion.setForeground(Color.BLACK);
		nombreHabitacion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreHabitacion.setBounds(35, 21, 196, 46);
		panelInfo.add(nombreHabitacion);
		
	
		JTextField nombreHabiResp = new JTextField();
		nombreHabiResp.setBorder(BorderFactory.createCompoundBorder(
				nombreHabiResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		nombreHabiResp.setBackground(new Color(217, 217, 217));
		nombreHabiResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		nombreHabiResp.setBounds(35, 59, 420, 25);
		panelInfo.add(nombreHabiResp);
		nombreHabiResp.setColumns(10);
		
		JLabel tipoHabi = new JLabel("Tipo de habitación");
		tipoHabi.setForeground(Color.BLACK);
		tipoHabi.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tipoHabi.setBounds(35, 95, 196, 46);
		panelInfo.add(tipoHabi);
		
		JLabel tamaño = new JLabel("Tamaño");
		tamaño.setForeground(Color.BLACK);
		tamaño.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tamaño.setBounds(35, 170, 216, 46);
		panelInfo.add(tamaño);
		
		JTextField telResp = new JTextField();
		telResp.setBorder(BorderFactory.createCompoundBorder(
				telResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		telResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		telResp.setColumns(10);
		telResp.setBackground(new Color(217, 217, 217));
		telResp.setBounds(35, 214, 420, 25);
		panelInfo.add(telResp);
		
		JLabel descripcion = new JLabel("Dirección");
		descripcion.setForeground(Color.BLACK);
		descripcion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		descripcion.setBounds(35, 244, 109, 46);
		panelInfo.add(descripcion);
		
		JLabel amenidades = new JLabel("Amenidades");
		amenidades.setForeground(Color.BLACK);
		amenidades.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		amenidades.setBounds(581, 145, 233, 46);
		panelInfo.add(amenidades);
		
		JLabel tarifa = new JLabel("Tarifas");
		tarifa.setForeground(Color.BLACK);
		tarifa.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		tarifa.setBounds(581, 250, 364, 46);
		panelInfo.add(tarifa);
		
//		JComboBox tipoResp = new JComboBox();
//		tipoResp.setBackground(new Color(217, 217, 217));
//		tipoResp.setBounds(35, 138, 420, 25);
//		panelInfo.add(tipoResp);
		
		JTextArea descResp = new JTextArea();
		descResp.setBackground(new Color(217, 217, 217));
		descResp.setBounds(35, 286, 420, 75);
		panelInfo.add(descResp);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(217, 217, 217));
		panel.setBounds(581, 185, 420, 60);
		panelInfo.add(panel);
		panel.setLayout(null);
		
		
		wifi.setOpaque(false);
		wifi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		wifi.setBounds(0, 0, 200, 31);
		panel.add(wifi);
		
		
		restaurante.setOpaque(false);
		restaurante.setFont(new Font("Tahoma", Font.PLAIN, 18));
		restaurante.setBounds(0, 30, 200, 31);
		panel.add(restaurante);
		
		
		recreativos.setOpaque(false);
		recreativos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		recreativos.setBounds(220, 0, 200, 31);
		panel.add(recreativos);
		
		
		lavanderia.setOpaque(false);
		lavanderia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lavanderia.setBounds(220, 30, 200, 31);
		panel.add(lavanderia);
		
		JPanel tarifaPanel = new JPanel();
		tarifaPanel.setBackground(new Color(217, 217, 217));
		tarifaPanel.setLayout(null);
		tarifaPanel.setSize(new Dimension(420,100));
		
		JTextArea tarifasP = new JTextArea();
		tarifasP.setBackground(new Color(217, 217, 217));
		tarifasP.setBounds(0, 0, 420, 100);
		tarifaPanel.add(tarifasP);
		
		JScrollPane scrollBar = new JScrollPane(tarifaPanel);
		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.setBounds(581, 290, 420, 75);
		panelInfo.add(scrollBar);
		
		
		tipoResp = new JComboBox<>(tiposHab); //////////////////////////////////////
		tipoResp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new HabitacionesModel();
				String tipo = (String) tipoResp.getSelectedItem();
				String tarif = model.mostrarTarifas(tipo);
				tarifasP.setText(tarif);	
			}
	
		});
		tipoResp.setBackground(new Color(217, 217, 217));
		tipoResp.setBounds(35, 138, 420, 25);
		panelInfo.add(tipoResp);
	
		JButton botonVacio = new JButton();
		botonVacio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				nombreHabiResp.setText("");
//				tipoResp.setText("");
//				telResp.setText("");
//				direccionResp.setText("");
//				contactoResp.setText("");
//				relacionResp.setText("");
//				noContactoResp.setText("");
//				infoAdResp.setText("");
			}
		});
		botonVacio.setBorderPainted(false);
		botonVacio.setContentAreaFilled(false);
		botonVacio.setIcon(new ImageIcon(getClass().getResource("/contenido/vaciar.png")));
		botonVacio.setBounds(85, 482, 380, 50);
		panelAzul.add(botonVacio);
		
		JButton botonCrear = new JButton();
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new HabitacionesModel();
				
				String nombre = nombreHabiResp.getText();
				String tipo = (String) tipoResp.getSelectedItem();
				String tam = telResp.getText();
				String desc = descResp.getText();
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
				
				System.out.println("tipo de hab: " + tipo);
				System.out.println("amenidades selec: "+ amenidades);

				if(path != null)
				{
					imgB = model.imageToBlob(path);
				}
				else
				{
					System.out.println("Path no existe");
				}

				model.crear(nombre, tipo, tam, desc, amenidades,imgB);
			}
		});
		botonCrear.setBorderPainted(false);
		botonCrear.setContentAreaFilled(false);
		botonCrear.setIcon(new ImageIcon(getClass().getResource("/contenido/crearHab.png")));
		botonCrear.setBounds(642, 482, 387, 50);
		panelAzul.add(botonCrear);
		

		frame.getContentPane().add(panelCrear);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
			
	}
	
	public void campoVacio()
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
	
	
	public void eleccion()
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
				emergente.dispose();
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
	
	public void docExito()
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
		+"El documento se ha guardado  <br>"+
		"exitosamente.<br>"+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(30,10,487,168);
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
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/completado.png")));
		iconPosion.setBounds(15, 57, 80, 80);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	public void exito()
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
		+"Esta acción se ha completado  <br>"+
		"exitosamente. <br>"+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(34,20,487,168);
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
		imgAceptar.setBounds(181, 190, 181, 51);
		datos.add(imgAceptar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/exito.png")));
		iconPosion.setBounds(17, 50, 80, 80);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	public void docError()
	{
		
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		//String info="Los datos que ha ingresado son "+"\n"+ "incorrectos, favor de ingresarlos"+ "\n"+ "correctamente";
		String info=("<html><div style='text-align: center;'>"
		+"EL documento no se pudo  <br>"+
		"guardar.<br>"+
		"</div></html>");
		
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(30,13,487,168);
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
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/noCompletado.png")));
		iconPosion.setBounds(22, 57, 80, 80);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
	public void errorImagen()
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
		+"La imagen no se pudo cargar,<br>"+
		"por favor ingrese otra imagen.<br>"+
		"</div></html>");
		 
		
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(34,17,487,168);
		datos.add(text);
		
		JButton botonContinuar = new JButton("Continuar");
		botonContinuar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Acceso");
				emergente.dispose();
			}
		});
		botonContinuar.setForeground(new Color(255, 255, 255));
		botonContinuar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonContinuar.setBorderPainted(false);
		botonContinuar.setContentAreaFilled(false);
		botonContinuar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonContinuar.setBounds(182, 190, 181, 51);
		datos.add(botonContinuar);
		
		JLabel imgContinuar= new JLabel();
		imgContinuar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgContinuar.setBounds(183, 190, 181, 51);
		datos.add(imgContinuar);
		
		JLabel iconPosion= new JLabel();
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/error.png")));
		iconPosion.setBounds(20, 65, 80, 80);
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
		"habitacion, favor de seleccionarla.<br>"+
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
	public JTextField getId() {	
        return infoIdHabitacion;		
    }

	
	public JTextField getNombre() {
        return infoNombre;
    }
	
	public JTextField getTipo() {
        return infoTipo;
    }
	
	public JTextField getTam() {
        return infoTam;
    }
	
	public JTextArea getDesc()
	{
		return infoDescrip;
	}
	
	public JTextArea getSolicitudes()
	{
		return amenidades2;
	}
	
	public JTextArea getTarifas()
	{
		return tarifasText;
	}
	
	public JLabel getNombreDetalles() {
        return nomHabitacion;
    }

	 
	 public String getIdHab()
	 {
		 return idHabitacion;
	 }
	 
	 public String getTipoSeleccionado() {
		    return (String) tipoResp.getSelectedItem();
		}

	 
	 public JCheckBox getCheckWifi() {
	        return wifi;
	    }
	 
	 public JCheckBox getCheckRest() {
	        return restaurante;
	    }
	 
	 public JCheckBox getCheckRecrea() {
	        return recreativos;
	    }
	 
	 public JCheckBox getCheckLava() {
	        return lavanderia;
	    }
	 
}
