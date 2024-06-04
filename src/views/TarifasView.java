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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.ClientesController;
import controllers.HabitacionesController;
import controllers.InicioController;
import controllers.RentasController;
import controllers.TarifasController;
import controllers.TiposController;
import models.TarifasModel;

public class TarifasView {
	private JFrame frame;
	private InicioController inicio;
	private TarifasController tarifa;
	private TarifasModel model;
	


	public TiposController tipo;
	public RentasController renta;
	public HabitacionesController room;
	public ClientesController cliente;
	
	public TarifasView(){
		frame = new JFrame();
		frame.setBounds(10, 5, 1350, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		habitaciones.setForeground(Color.white);
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
		tarifas.setForeground(new Color(252,210,87));
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
				tarifa = new TarifasController();
				tarifa.crear();
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
				tarifa = new TarifasController();
				tarifa.consultar();
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
		
		//botones del panel central
		JButton editarBtn= new JButton();
		editarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/editar.png")));
		editarBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("editar");
				frame.dispose();
				tarifa = new TarifasController();
				tarifa.editar();
				
			}
		});
		editarBtn.setBorderPainted(false);
		editarBtn.setContentAreaFilled(false);
		editarBtn.setBounds(690, 480, 328, 45);
		panelCentral.add(editarBtn);
		
		JButton detallesBtn= new JButton();
		detallesBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/detalles.png")));
		detallesBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Detalles");
				frame.dispose();
				tarifa = new TarifasController();
				tarifa.detalles();
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
		
		String tableTitle[]={"Nombre de la tarifa", "Fecha inicial", "Fecha final", "Precio base"};
		String tableData[][] = {
							    {"", "", "", "" },
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""},
							    {"", "", "", ""}
		};
	
		JTable productoTable= new JTable(tableData, tableTitle);
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
	                        System.out.println("Fila seleccionada: " + selectedRow);
	                        
	                    }
	                }
	            }
		    });
		panelDeTabla.setLayout(null);
		
		JScrollPane tableScroll=new JScrollPane(productoTable);
		tableScroll.setBounds(0, 0, 1023, 316);
		panelDeTabla.add(tableScroll);
	
		
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
		tarifas.setForeground(new Color(252,210,87));
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
				tarifa = new TarifasController();
				tarifa.crear();
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
				tarifa = new TarifasController();
				tarifa.consultar();
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
		
		JLabel tarifasTitulo = new JLabel("Tarifas");
		tarifasTitulo.setForeground(new Color(0, 0, 0));
		tarifasTitulo.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tarifasTitulo.setBounds(503, 30, 266, 46);
		panelAzul.add(tarifasTitulo);
	
		JLabel fondoCliente = new JLabel("");
		fondoCliente.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoCliente.setBounds(315, 21, 489, 45);
		panelAzul.add(fondoCliente);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(BorderFactory.createLineBorder(new Color(252,210,87), 3));
		panelInfo.setBounds(42, 87, 1022, 384);
		panelAzul.add(panelInfo);
		panelInfo.setLayout(null);
		
		JLabel nombreTarifa = new JLabel("Nombre de la tarifa");
		nombreTarifa.setForeground(Color.BLACK);
		nombreTarifa.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreTarifa.setBounds(35, 21, 327, 46);
		panelInfo.add(nombreTarifa);
		
	
		JTextField nombreTarifaResp = new JTextField();
		nombreTarifaResp.setBorder(BorderFactory.createCompoundBorder(
				nombreTarifaResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		nombreTarifaResp.setBackground(new Color(217, 217, 217));
		nombreTarifaResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		nombreTarifaResp.setBounds(35, 59, 420, 25);
		panelInfo.add(nombreTarifaResp);
		nombreTarifaResp.setColumns(10);
		
		JLabel fechaInicial = new JLabel("Fecha inicial");
		fechaInicial.setForeground(Color.BLACK);
		fechaInicial.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaInicial.setBounds(35, 95, 160, 46);
		panelInfo.add(fechaInicial);
		
		JLabel fechaFinal = new JLabel("Fecha final");
		fechaFinal.setForeground(Color.BLACK);
		fechaFinal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaFinal.setBounds(35, 168, 160, 46);
		panelInfo.add(fechaFinal);
		
		JTextField fechaInicialResp = new JTextField();
		fechaInicialResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		fechaInicialResp.setColumns(10);
		fechaInicialResp.setBackground(new Color(217, 217, 217));
		fechaInicialResp.setBounds(35, 132, 360, 25);
		panelInfo.add(fechaInicialResp);
		
		
		
		JLabel descripcion = new JLabel("Descripción");
		descripcion.setForeground(Color.BLACK);
		descripcion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		descripcion.setBounds(35, 246, 160, 46);
		panelInfo.add(descripcion);
		
		JTextField fechaFinalResp = new JTextField();
		fechaFinalResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		fechaFinalResp.setColumns(10);
		fechaFinalResp.setBackground(new Color(217, 217, 217));
		fechaFinalResp.setBounds(35, 210, 360, 25);
		panelInfo.add(fechaFinalResp);
		
		//Botones de fecha
		JButton fechaInicialBtn = new JButton();
		fechaInicialBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/fecha.png")));
		fechaInicialBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Fecha inicial");
			}
		});
		fechaInicialBtn.setBounds(395, 132, 60, 25);
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
			}
		});
		fechaFinalBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/fecha.png")));
		fechaInicialBtn.setBorderPainted(false);
		fechaInicialBtn.setContentAreaFilled(false);
		fechaFinalBtn.setBounds(395, 210, 60, 25);
		panelInfo.add(fechaFinalBtn);
		
		JTextArea descResp = new JTextArea();
		descResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		descResp.setBackground(new Color(217, 217, 217));
		descResp.setBounds(35, 292, 420, 60);
		panelInfo.add(descResp);
		
		
		
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
			}
		});
		botonCrear.setBorderPainted(false);
		botonCrear.setContentAreaFilled(false);
		botonCrear.setIcon(new ImageIcon(getClass().getResource("/contenido/crearTarifa.png")));
		botonCrear.setBounds(628, 482, 387, 50);
		panelAzul.add(botonCrear);

		
		JLabel precioBase = new JLabel("Precio base");
		precioBase.setBounds(563, 282, 364, 46);
		panelInfo.add(precioBase);
		precioBase.setForeground(Color.BLACK);
		precioBase.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JLabel capacidad = new JLabel("Condiciones");
		capacidad.setBounds(563, 152, 196, 46);
		panelInfo.add(capacidad);
		capacidad.setForeground(Color.BLACK);
		capacidad.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JLabel serviciosInclu = new JLabel("Servicios incluidos");
		serviciosInclu.setBounds(533, 22, 216, 46);
		panelInfo.add(serviciosInclu);
		serviciosInclu.setForeground(Color.BLACK);
		serviciosInclu.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JTextArea serviciosResp = new JTextArea();
		serviciosResp.setBounds(563, 69, 420, 60);
		panelInfo.add(serviciosResp);
		serviciosResp.setBorder(BorderFactory.createCompoundBorder(
				serviciosResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		serviciosResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		serviciosResp.setColumns(10);
		serviciosResp.setBackground(new Color(217, 217, 217));
		
		JTextArea condicionesResp = new JTextArea();
		condicionesResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		condicionesResp.setBorder(BorderFactory.createCompoundBorder(
				condicionesResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		condicionesResp.setColumns(10);
		condicionesResp.setBackground(new Color(217, 217, 217));
		condicionesResp.setBounds(563, 199, 420, 60);
		panelInfo.add(condicionesResp);
		
		JTextField precioBaseResp = new JTextField();
		precioBaseResp.setBorder(BorderFactory.createCompoundBorder(
				precioBaseResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		precioBaseResp.setBounds(563, 326, 420, 25);
		panelInfo.add(precioBaseResp);
		precioBaseResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		precioBaseResp.setColumns(10);
		precioBaseResp.setBackground(new Color(217, 217, 217));
		
		
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
		tarifas.setForeground(new Color(252,210,87));
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
				tarifa = new TarifasController();
				tarifa.crear();
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
				tarifa = new TarifasController();
				tarifa.consultar();
			}
		});
		consultarBtn.setBorderPainted(false);
		consultarBtn.setContentAreaFilled(false);
		consultarBtn.setBounds(0, 0, 130, 277);
		panelVertical2.add(consultarBtn);		
		
		//Panel central
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
		
		
		JLabel nomTarifa = new JLabel("Nombre de la tarifa");
		nomTarifa.setForeground(new Color(0, 0, 0));
		nomTarifa.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		nomTarifa.setBounds(413, 28, 283, 46);
		panelCentral.add(nomTarifa);
	
		JLabel fondoCliente = new JLabel("");
		fondoCliente.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoCliente.setBounds(315, 21, 489, 45);
		panelCentral.add(fondoCliente);
		
		JPanel panelAzulFondo = new JPanel();
		panelAzulFondo.setBackground(new Color(0, 73, 102));
		panelAzulFondo.setBorder(null);
		panelAzulFondo.setBounds(42, 95, 500, 410);
		panelCentral.add(panelAzulFondo);
		panelAzulFondo.setLayout(null);
	
		JPanel panelInfo = new JPanel();
		panelInfo.setPreferredSize(new Dimension(439, 535));
		panelInfo.setLayout(null);
		//paneAzulFondo.add(panelInfo);
		
		JLabel fechaInicial = new JLabel("Fecha inicial");
		fechaInicial.setForeground(Color.BLACK);
		fechaInicial.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaInicial.setBounds(10, 11, 354, 29);
		panelInfo.add(fechaInicial);
		
		JTextField infoFechaInicial = new JTextField("//");
		infoFechaInicial.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoFechaInicial.setBorder(BorderFactory.createCompoundBorder(
				infoFechaInicial.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoFechaInicial.setEditable(false);
		infoFechaInicial.setBackground(new Color(217, 217, 217));
		infoFechaInicial.setBounds(10, 40, 420, 25);
		panelInfo.add(infoFechaInicial);
		
		JLabel fechaFinal = new JLabel("Fecha final");
		fechaFinal.setForeground(Color.BLACK);
		fechaFinal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaFinal.setBounds(10, 78, 354, 29);
		panelInfo.add(fechaFinal);
		
		JTextField infoFechaFinal = new JTextField("//");
		infoFechaFinal.setBorder(BorderFactory.createCompoundBorder(
				infoFechaFinal.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoFechaFinal.setEditable(false);
		infoFechaFinal.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoFechaFinal.setColumns(10);
		infoFechaFinal.setBackground(new Color(217, 217, 217));
		infoFechaFinal.setBounds(10, 107, 420, 25);
		panelInfo.add(infoFechaFinal);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setForeground(Color.BLACK);
		descripcion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		descripcion.setBounds(10, 143, 354, 29);
		panelInfo.add(descripcion);
		
		JTextArea infoDesc = new JTextArea("Ninguna");
		infoDesc.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoDesc.setBorder(BorderFactory.createCompoundBorder(
				infoDesc.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoDesc.setEditable(false);
		infoDesc.setColumns(10);
		infoDesc.setBackground(new Color(217, 217, 217));
		infoDesc.setBounds(10, 171, 420, 60);
		panelInfo.add(infoDesc);
		
		
		JLabel servInclu = new JLabel("Servicios incluidos");
		servInclu.setForeground(Color.BLACK);
		servInclu.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		servInclu.setBounds(10, 243, 354, 29);
		panelInfo.add(servInclu);
		
		JTextArea infoServInclu = new JTextArea("Ninguno");
		infoServInclu.setBorder(BorderFactory.createCompoundBorder(
				infoServInclu.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoServInclu.setEditable(false);
		infoServInclu.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoServInclu.setColumns(10);
		infoServInclu.setBackground(new Color(217, 217, 217));
		infoServInclu.setBounds(10, 273, 420, 60);
		panelInfo.add(infoServInclu);
		
		JLabel condiciones = new JLabel("Condiciones");
		condiciones.setForeground(Color.BLACK);
		condiciones.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		condiciones.setBounds(10, 348, 354, 29);
		panelInfo.add(condiciones);
		
		JTextArea infoCondiciones = new JTextArea("Ninguna");
		infoCondiciones.setBorder(BorderFactory.createCompoundBorder(
				infoCondiciones.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoCondiciones.setEditable(false);
		infoCondiciones.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoCondiciones.setColumns(10);
		infoCondiciones.setBackground(new Color(217, 217, 217));
		infoCondiciones.setBounds(10, 380, 420, 60);
		panelInfo.add(infoCondiciones);
		
		
		JLabel precioBase = new JLabel("Precio base");
		precioBase.setForeground(Color.BLACK);
		precioBase.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		precioBase.setBounds(10, 455, 354, 29);
		panelInfo.add(precioBase);
		
		
		JTextField infoPrecioBase = new JTextField("$");
		infoPrecioBase.setBorder(BorderFactory.createCompoundBorder(
				infoPrecioBase.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoPrecioBase.setEditable(false);
		infoPrecioBase.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoPrecioBase.setColumns(10);
		infoPrecioBase.setBackground(new Color(217, 217, 217));
		infoPrecioBase.setBounds(10,490, 420, 25);
		panelInfo.add(infoPrecioBase);

		//Scroll
		JScrollPane scrollPane = new JScrollPane(panelInfo);
		scrollPane.setBounds(20, 20, 460, 370);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		SwingUtilities.invokeLater(()->scrollPane.getViewport().setViewPosition(new Point(0, 0)));
		panelAzulFondo.add(scrollPane);
		
		JPanel panelInfo2 = new JPanel();
		panelInfo2.setBackground(new Color(0, 73, 102));
		panelInfo2.setLayout(null);
		panelInfo2.setBorder(null);
		panelInfo2.setBounds(580, 95, 550, 410);
		panelCentral.add(panelInfo2);
		
		JLabel imgDetalles = new JLabel();
		imgDetalles .setIcon(new ImageIcon(getClass().getResource("/contenido/imgTarifa.jpg")));
		imgDetalles .setBounds(20, 20, 510, 370);
		panelInfo2.add(imgDetalles );
		
		JButton sigHabitacion = new JButton();
		sigHabitacion.setIcon(new ImageIcon(getClass().getResource("/contenido/siguiente.png")));
		sigHabitacion .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Siguiente");
			}
		});
		sigHabitacion.setBounds(1060, 15, 75, 61);
		sigHabitacion.setBorderPainted(false);
		sigHabitacion.setContentAreaFilled(false);
		panelCentral.add(sigHabitacion);
		
		JButton antHabitacion = new JButton();
		antHabitacion.setBounds(42, 15, 75, 61);
		panelCentral.add(antHabitacion);
		antHabitacion.setIcon(new ImageIcon(getClass().getResource("/contenido/anterior.png")));
		antHabitacion .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Siguiente");
			}
		});
		antHabitacion.setBorderPainted(false);
		antHabitacion.setContentAreaFilled(false);
	
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
		tarifas.setForeground(new Color(252, 210, 87));
		tarifas.setBounds(840, 36, 130, 40);
		disneyFondo.add(tarifas);
		
		JLabel tipos = new JLabel("Tipos de habitaciones");
		tipos.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tipos.setForeground(new Color(255, 255, 255));
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
				tarifa = new TarifasController();
				tarifa.crear();
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
				tarifa = new TarifasController();
				tarifa.consultar();
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
		
		JLabel editarTarifas = new JLabel("Editar tarifa");
		editarTarifas.setForeground(new Color(0, 0, 0));
		editarTarifas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		editarTarifas.setBounds(463, 30, 266, 46);
		panelAzul.add(editarTarifas);
	
		JLabel fondoCliente = new JLabel("");
		fondoCliente.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoCliente.setBounds(315, 21, 489, 45);
		panelAzul.add(fondoCliente);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(BorderFactory.createLineBorder(new Color(252,210,87), 3));
		panelInfo.setBounds(42, 87, 1022, 384);
		panelAzul.add(panelInfo);
		panelInfo.setLayout(null);
		
		JLabel nombreTarifa = new JLabel("Nombre de la tarifa");
		nombreTarifa.setForeground(Color.BLACK);
		nombreTarifa.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreTarifa.setBounds(35, 21, 327, 46);
		panelInfo.add(nombreTarifa);
		
		JTextField nombreTarifaResp = new JTextField();
		nombreTarifaResp.setBorder(BorderFactory.createCompoundBorder(
				nombreTarifaResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		nombreTarifaResp.setBackground(new Color(217, 217, 217));
		nombreTarifaResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		nombreTarifaResp.setBounds(35, 59, 420, 25);
		panelInfo.add(nombreTarifaResp);
		nombreTarifaResp.setColumns(10);
		
		JLabel fechaInicial = new JLabel("Fecha inicial");
		fechaInicial.setForeground(Color.BLACK);
		fechaInicial.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaInicial.setBounds(35, 95, 160, 46);
		panelInfo.add(fechaInicial);
		
		JLabel fechaFinal = new JLabel("Fecha final");
		fechaFinal.setForeground(Color.BLACK);
		fechaFinal.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		fechaFinal.setBounds(35, 168, 160, 46);
		panelInfo.add(fechaFinal);
		
		JTextField fechaInicialResp = new JTextField();
		fechaInicialResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		fechaInicialResp.setColumns(10);
		fechaInicialResp.setBackground(new Color(217, 217, 217));
		fechaInicialResp.setBounds(35, 132, 360, 25);
		panelInfo.add(fechaInicialResp);
	
		JLabel descripcion = new JLabel("Descripción");
		descripcion.setForeground(Color.BLACK);
		descripcion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		descripcion.setBounds(35, 246, 160, 46);
		panelInfo.add(descripcion);
		
		JTextField fechaFinalResp = new JTextField();
		fechaFinalResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		fechaFinalResp.setColumns(10);
		fechaFinalResp.setBackground(new Color(217, 217, 217));
		fechaFinalResp.setBounds(35, 210, 360, 25);
		panelInfo.add(fechaFinalResp);
		
		//Botones de fecha
		JButton fechaInicialBtn = new JButton();
		fechaInicialBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/fecha.png")));
		fechaInicialBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Fecha inicial");
			}
		});
		fechaInicialBtn.setBounds(395, 132, 60, 25);
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
			}
		});
		fechaFinalBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/fecha.png")));
		fechaInicialBtn.setBorderPainted(false);
		fechaInicialBtn.setContentAreaFilled(false);
		fechaFinalBtn.setBounds(395, 210, 60, 25);
		panelInfo.add(fechaFinalBtn);
		
		JTextArea descResp = new JTextArea();
		descResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		descResp.setBackground(new Color(217, 217, 217));
		descResp.setBounds(35, 292, 420, 60);
		panelInfo.add(descResp);
	
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
		
		JButton guardarCambios = new JButton();
		guardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		guardarCambios.setBorderPainted(false);
		guardarCambios.setContentAreaFilled(false);
		guardarCambios.setIcon(new ImageIcon(getClass().getResource("/contenido/guardarCambios.png")));
		guardarCambios.setBounds(628, 482, 387, 50);
		panelAzul.add(guardarCambios);
		
		JLabel precioBase = new JLabel("Precio base");
		precioBase.setBounds(563, 282, 364, 46);
		panelInfo.add(precioBase);
		precioBase.setForeground(Color.BLACK);
		precioBase.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JLabel capacidad = new JLabel("Condiciones");
		capacidad.setBounds(563, 152, 196, 46);
		panelInfo.add(capacidad);
		capacidad.setForeground(Color.BLACK);
		capacidad.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JLabel serviciosInclu = new JLabel("Servicios incluidos");
		serviciosInclu.setBounds(533, 22, 216, 46);
		panelInfo.add(serviciosInclu);
		serviciosInclu.setForeground(Color.BLACK);
		serviciosInclu.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		
		JTextArea serviciosResp = new JTextArea();
		serviciosResp.setBounds(563, 69, 420, 60);
		panelInfo.add(serviciosResp);
		serviciosResp.setBorder(BorderFactory.createCompoundBorder(
				serviciosResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		serviciosResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		serviciosResp.setColumns(10);
		serviciosResp.setBackground(new Color(217, 217, 217));
		
		JTextArea condicionesResp = new JTextArea();
		condicionesResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		condicionesResp.setBorder(BorderFactory.createCompoundBorder(
				condicionesResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		condicionesResp.setColumns(10);
		condicionesResp.setBackground(new Color(217, 217, 217));
		condicionesResp.setBounds(563, 199, 420, 60);
		panelInfo.add(condicionesResp);
		
		JTextField precioBaseResp = new JTextField();
		precioBaseResp.setBorder(BorderFactory.createCompoundBorder(
				precioBaseResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		precioBaseResp.setBounds(563, 326, 420, 25);
		panelInfo.add(precioBaseResp);
		precioBaseResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		precioBaseResp.setColumns(10);
		precioBaseResp.setBackground(new Color(217, 217, 217));
		
		JButton regresarBtn = new JButton();
		regresarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Regresar");
				frame.dispose();
				tarifa = new TarifasController();
				tarifa.consultar();
			}
		});
		regresarBtn.setBorderPainted(false);
		regresarBtn.setContentAreaFilled(false);
		regresarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/regresar.png")));
		regresarBtn.setBounds(42, 15, 60, 60);
		panelAzul.add(regresarBtn);

		frame.getContentPane().add(panelEditar);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();	
	}
}
