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
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import controllers.Auth;
import controllers.ClientesController;
import controllers.HabitacionesController;
import controllers.InicioController;
import controllers.RentasController;
import controllers.TarifasController;
import controllers.TiposController;
import models.Clientes;
import models.ClientesModel;

public class ClientesView {
	private JFrame frame;
	private JDialog emergente;
	private InicioController inicio;
	private ClientesController cliente;
	private ClientesModel model;
	
	public Auth login;
	
	public String idClienteSeleccionado;

	public TarifasController tarifa;
	public TiposController tipo;
	public RentasController renta;
	public HabitacionesController room;

	private Blob imgB;
	private String path;
	private String idCliente;
	Clientes clienteDetalles;
	
	JTextField infoIdCliente = new JTextField("");
	JTextField infoNombre = new JTextField("");
	JTextField infoCorreo = new JTextField("");
	JTextField infoTelefono = new JTextField("");
	JTextField infoDireccion = new JTextField("");
	JTextField infoNomEmerg = new JTextField("");
	JTextField infoNumeroEmerg = new JTextField("");
	JTextField infoRelacion = new JTextField("");
	JTextArea infoAdicional = new JTextArea("");
	JTextField infoEstado = new JTextField("");
	
	JTextField idResp = new JTextField("");
	JTextField nombreResp = new JTextField("");
	JTextField correoResp  = new JTextField("");
	JTextField telResp  = new JTextField(""); 
	JTextField direccionResp  = new JTextField("");
	JTextField contactoResp  = new JTextField("");
	JTextField relacionResp  = new JTextField("");
	JTextField noContactoResp  = new JTextField("");
	JTextArea infoAdResp  = new JTextArea("");
	JLabel nombreCliente = new JLabel("");
	
	JButton subirBtn= new JButton();
	private ArrayList<String> clienteIds;
	private int indexActual;
	
	JPanel panelImagen = new JPanel();
	

	
	public ClientesView () {
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
		//Panel principal
		frame.getContentPane().removeAll();
		frame.repaint();
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
		clientes.setForeground(new Color(252,210,87));
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
				cliente = new ClientesController();
				cliente.crear();
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
				cliente = new ClientesController();
				cliente.consultar();
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
		
		//inicializa el model
		model=new ClientesModel();
		DefaultTableModel datosClientes = model.tablaClientes();
		
	
		JTable productoTable= new JTable(datosClientes); 
																
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
	                        idCliente = (String) productoTable.getValueAt(selectedRow, 0);
	                    	System.out.println("ID seleccionado: " + idCliente);
	                    }
	                }
	            }
	    });
		panelDeTabla.setLayout(null);
		
		JScrollPane tableScroll=new JScrollPane(productoTable);
		tableScroll.setBounds(0, 0, 1023, 316);
		panelDeTabla.removeAll();
		panelDeTabla.add(tableScroll);
		panelDeTabla.revalidate();
	    panelDeTabla.repaint();

		JButton detallesBtn= new JButton();
		detallesBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/detalles.png")));
		detallesBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idCliente != null && !idCliente.isEmpty()) {
		    	    System.out.println(idCliente);
		    	    
		    	    model = new ClientesModel();
		    	    ClientesView view = new ClientesView();
		    	    model.textField(view.getId(), view.getNombre(), view.getCorreo(), view.getTelefono(), view.getDireccion(), view.getNombreEmergencia(), 
		    	    view.getRelacion(), view.getNumEmergencia(), view.getInfo(), view.getEstatus(), view.getNombreDetalles(), view.getPanelImg());
		    	    
		    	    frame.dispose();
		    	    
		    	    model.mostrarDetalles(idCliente);
//		    	    panelImagen.repaint();
//		    		panelImagen.revalidate();
		    	    view.detalles();
		    	   
		    	} else {
		    	    seleccion();
		    	}
			}
		});
		detallesBtn.setBorderPainted(false);
		detallesBtn.setContentAreaFilled(false);
		detallesBtn.setBounds(145, 470, 328, 45);
		panelCentral.add(detallesBtn);
		panelDeTabla.revalidate();
	    panelDeTabla.repaint();
	    
	    //botones del panel central
		JButton editarBtn= new JButton();//////////////////////////////////////////////////////////////////////mover debajo del boton de detalles
		editarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/editar.png")));
		editarBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idCliente != null && !idCliente.isEmpty()) {
		            ClientesModel model = new ClientesModel();
		            model.textField2(nombreResp, correoResp, telResp, direccionResp, contactoResp, relacionResp, noContactoResp, infoAdResp);
		            model.recuperaDatos(idCliente); // Solo recupera y establece datos
		            editar();
		        }
			}
		});
		editarBtn.setBorderPainted(false);
		editarBtn.setContentAreaFilled(false);
		editarBtn.setBounds(690, 470, 328, 45);
		panelCentral.add(editarBtn);
	    
		frame.getContentPane().add(panelConsultar); //que este quede despues del boton de detalles
			
	}

		
	public void crear()
	{
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
		clientes.setForeground(new Color(252,210,87));
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
				System.out.println("Crear");
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
				cliente = new ClientesController();
				cliente.consultar();
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
		
		JLabel editar = new JLabel("Cliente");
		editar.setForeground(new Color(0, 0, 0));
		editar.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		editar.setBounds(500, 30, 179, 46);
		panelAzul.add(editar);
	
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
		subirBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/subirCliente.png")));
		subirBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new ClientesModel();
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
		subirBtn.setBounds(581, 18, 420, 116);
		panelInfo.add(subirBtn);
		
		JLabel nombre = new JLabel("Nombre completo");
		nombre.setForeground(Color.BLACK);
		nombre.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombre.setBounds(35, 11, 196, 46);
		panelInfo.add(nombre);
		
	
		JTextField nombreResp = new JTextField();
		nombreResp.setBorder(BorderFactory.createCompoundBorder(
				nombreResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		nombreResp.setBackground(new Color(217, 217, 217));
		nombreResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		nombreResp.setBounds(35, 53, 420, 25);
		panelInfo.add(nombreResp);
		nombreResp.setColumns(10);
		
		JLabel correoElectronico = new JLabel("Correo electrónico");
		correoElectronico.setForeground(Color.BLACK);
		correoElectronico.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		correoElectronico.setBounds(35, 83, 196, 46);
		panelInfo.add(correoElectronico);
		
		JTextField correoResp = new JTextField();
		correoResp.setBorder(BorderFactory.createCompoundBorder(
				correoResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		correoResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		correoResp.setColumns(10);
		correoResp.setBackground(new Color(217, 217, 217));
		correoResp.setBounds(35, 125, 420, 25);
		panelInfo.add(correoResp);
		
		JLabel noTelefono = new JLabel("Número de teléfono");
		noTelefono.setForeground(Color.BLACK);
		noTelefono.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		noTelefono.setBounds(35, 157, 216, 46);
		panelInfo.add(noTelefono);
		
		JTextField telResp = new JTextField();
		telResp.setBorder(BorderFactory.createCompoundBorder(
				telResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		telResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		telResp.setColumns(10);
		telResp.setBackground(new Color(217, 217, 217));
		telResp.setBounds(35, 198, 420, 25);
		panelInfo.add(telResp);
		
		JLabel direccion = new JLabel("Dirección");
		direccion.setForeground(Color.BLACK);
		direccion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		direccion.setBounds(35, 227, 109, 46);
		panelInfo.add(direccion);
		
		JTextField direccionResp = new JTextField();
		direccionResp.setBorder(BorderFactory.createCompoundBorder(
				direccionResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		direccionResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		direccionResp.setColumns(10);
		direccionResp.setBackground(new Color(217, 217, 217));
		direccionResp.setBounds(35, 266, 420, 25);
		panelInfo.add(direccionResp);
		
		JLabel nombreContacto = new JLabel("Nombre del contacto de emergencia");
		nombreContacto.setForeground(Color.BLACK);
		nombreContacto.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreContacto.setBounds(35, 297, 364, 46);
		panelInfo.add(nombreContacto);
		
		JTextField contactoResp = new JTextField();
		contactoResp.setBorder(BorderFactory.createCompoundBorder(
				contactoResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		contactoResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		contactoResp.setColumns(10);
		contactoResp.setBackground(new Color(217, 217, 217));
		contactoResp.setBounds(35, 340, 420, 25);
		panelInfo.add(contactoResp);
		
		JLabel relacion = new JLabel("Relación con el cliente");
		relacion.setForeground(Color.BLACK);
		relacion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		relacion.setBounds(581, 142, 233, 46);
		panelInfo.add(relacion);
		

		JTextField relacionResp = new JTextField();
		relacionResp.setBorder(BorderFactory.createCompoundBorder(
				relacionResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		relacionResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		relacionResp.setColumns(10);
		relacionResp.setBackground(new Color(217, 217, 217));
		relacionResp.setBounds(581, 179, 420, 25);
		panelInfo.add(relacionResp);
		
		JLabel noContacto = new JLabel("Número del contacto de emergencia");
		noContacto.setForeground(Color.BLACK);
		noContacto.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		noContacto.setBounds(581, 211, 364, 46);
		panelInfo.add(noContacto);
		
		JTextField noContactoResp = new JTextField();
		noContactoResp.setBorder(BorderFactory.createCompoundBorder(
				noContactoResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		noContactoResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		noContactoResp.setColumns(10);
		noContactoResp.setBackground(new Color(217, 217, 217));
		noContactoResp.setBounds(581, 248, 420, 25);
		panelInfo.add(noContactoResp);
		
		JTextArea infoAdResp = new JTextArea();
		infoAdResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoAdResp.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		infoAdResp.setBackground(new Color(217, 217, 217));
		infoAdResp.setBounds(581, 315, 420, 50);
		panelInfo.add(infoAdResp);
		
		JLabel infoAdicional = new JLabel("Información adicional");
		infoAdicional.setForeground(Color.BLACK);
		infoAdicional.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		infoAdicional.setBounds(581, 278, 233, 46);
		panelInfo.add(infoAdicional);
		
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
		
		JButton botonCrear = new JButton();
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contadorErrores = 0;
				int contadorVacios = 0;
				String nombre = nombreResp.getText();
				String correo = correoResp.getText();
				String tel = telResp.getText();
				String dir = direccionResp.getText();
				String contactoEmergencia = contactoResp.getText();
				String relacion = relacionResp.getText();
				String telEmergencia = noContactoResp.getText();
				String info = infoAdResp.getText();
				String email = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
				String soloLetras = "^[a-zA-Z\\s]+$";
				
				InputStream img = model.getImagen();
				
				model = new ClientesModel();

				if(path != null)
				{
					imgB = model.imageToBlob(path);
				}
				else
				{
					System.out.println("Path no existe");
				}
				
				//validación
				
			    // Validar vacios
			    if (tel.equals("") || correo.equals("") || telEmergencia.equals("") || nombre.equals("") || dir.equals("") || contactoEmergencia.equals("") || relacion.equals("") || info.equals("")) {
			    	contadorVacios=1;
			        telResp.setBorder(BorderFactory.createLineBorder(tel.equals("") ? Color.red : Color.green, 2));
			        correoResp.setBorder(BorderFactory.createLineBorder(correo.equals("") ? Color.red : Color.green, 2));
			        noContactoResp.setBorder(BorderFactory.createLineBorder(telEmergencia.equals("") ? Color.red: Color.green, 2));
			        nombreResp.setBorder(BorderFactory.createLineBorder(nombre.equals("") ? Color.red : Color.green, 2));
			        direccionResp.setBorder(BorderFactory.createLineBorder(dir.equals("") ? Color.red : Color.green, 2));
			        contactoResp.setBorder(BorderFactory.createLineBorder(contactoEmergencia.equals("") ? Color.red : Color.green, 2));
			        relacionResp.setBorder(BorderFactory.createLineBorder(relacion.equals("") ? Color.red : Color.green, 2));
			        infoAdResp.setBorder(BorderFactory.createLineBorder(info.equals("") ? Color.red : Color.green, 2));
			       
			    }
			    if (tel.length() > 10 || !tel.matches("\\d+")) {
			        
			        telResp.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			        contadorErrores++;
			    
			    }
			    if (!correo.matches(email)) {	       
			       
			        correoResp.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			        contadorErrores++;
				    
			    }
			    if (telEmergencia.length() > 10 || !telEmergencia.matches("\\d+")) {
				    
			        
			        noContactoResp.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			        contadorErrores++;
				    
			    
			    }
			    if (!nombre.matches(soloLetras)) {
				    
			        
			        noContactoResp.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			        contadorErrores++;
			    }	

			    
			    if (contadorErrores > 0) {
			        datosNoValidos();
			    }

			    if (contadorVacios > 0) {
			        campoVacio();
			    }

			    
			    if (contadorErrores == 0 && contadorVacios == 0) {
			        model.crear(nombre, correo, tel, dir, contactoEmergencia, relacion, telEmergencia, info, imgB);
			        exito();
			    }	

				
			}
		});
		botonCrear.setBorderPainted(false);
		botonCrear.setContentAreaFilled(false);
		botonCrear.setIcon(new ImageIcon(getClass().getResource("/contenido/crear.png")));
		botonCrear.setBounds(642, 482, 387, 50);
		panelAzul.add(botonCrear);
		
		frame.getContentPane().add(panelCrear);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
			
	}	

	public void detalles()
	{
		//Panel principal
		frame.getContentPane().removeAll();
		frame.repaint();
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
		
		idCliente = getIdCliente();
	
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
		clientes.setForeground(new Color(252,210,87));
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
				cliente = new ClientesController();
				cliente.crear();
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
				cliente = new ClientesController();
				cliente.consultar();
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
		
		//botones del panel central
		JButton descargarBtn= new JButton();
		descargarBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/descargar.png")));
		descargarBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Descargar ID seleccionado: " + idCliente);
				
                idClienteSeleccionado = infoIdCliente.getText();
                System.out.println("Descargar ID seleccionado: " + idClienteSeleccionado);
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
				 //Asignar el ID del cliente al atributo idClienteSeleccionado
				
				idClienteSeleccionado = infoIdCliente.getText();
				historial();
			}
		});
		historialBtn.setBorderPainted(false);
		historialBtn.setContentAreaFilled(false);
		historialBtn.setBounds(619, 472, 200, 49);
		panelCentral.add(historialBtn);
		
		nombreCliente.setForeground(new Color(0, 0, 0));
		nombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		nombreCliente.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		nombreCliente.setBounds(330, 30, 489, 45);
		panelCentral.add(nombreCliente);
	
		JLabel fondoCliente = new JLabel("");
		fondoCliente.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoCliente.setBounds(330, 21, 489, 45);
		panelCentral.add(fondoCliente);
		
		idClienteSeleccionado = infoIdCliente.getText();
		indexActual = Integer.parseInt(idClienteSeleccionado);
		model = new ClientesModel();
		
		JButton atrasBtn = new JButton();
		atrasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("atras");
				 if (indexActual > 0) {
	                	System.out.println("viejo indexActual: " + indexActual);
	                    indexActual--;
	                    System.out.println("Nuevo indexActual: " + indexActual);
	 
	                    clienteIds = model.cargarCliente(indexActual);
	                    String actual = clienteIds.get(indexActual-1);
	                    System.out.println("cliente act: " + actual);

	                    ClientesView view = new ClientesView();
	                    model.textField(view.getId(), view.getNombre(), view.getCorreo(), view.getTelefono(), view.getDireccion(), view.getNombreEmergencia(),
	                             view.getRelacion(), view.getNumEmergencia(), view.getInfo(), view.getEstatus(), view.getNombreDetalles(), view.getPanelImg());

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
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("Siguiente");
				clienteIds = model.cargarCliente(indexActual);
				
				if (indexActual < clienteIds.size() - 1) 
				{
					System.out.println("viejo indexActual: " + indexActual); 
					indexActual++;
					System.out.println("Nuevo indexActual: " + indexActual);
					
                    String actual = clienteIds.get(indexActual-1);
                    System.out.println("cliente act: " + actual);
                    
                    ClientesView view = new ClientesView();
                    model.textField(view.getId(), view.getNombre(), view.getCorreo(), view.getTelefono(), view.getDireccion(), view.getNombreEmergencia(), 
		    	    		 view.getRelacion(), view.getNumEmergencia(), view.getInfo(), view.getEstatus(), view.getNombreDetalles(), view.getPanelImg());

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
		 panelInfo.setPreferredSize(new Dimension(400, 690));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 73, 102));
		panel.setBounds(610, 109, 515, 333);
		panelCentral.add(panel);
		panel.setLayout(null);
		
		
		panelImagen.setBounds(20, 20, 472, 291);
		panelImagen.setLayout(null);
		panelImagen.setOpaque(true); 
		panelImagen.setBackground(Color.white);
		panelImagen.repaint();
		panelImagen.revalidate();
		
		panel.add(panelImagen);
		panelInfo.setLayout(null);
		
		JLabel idCliente = new JLabel("ID del cliente");
		idCliente.setForeground(Color.BLACK);
		idCliente.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		idCliente.setBounds(20, 11, 354, 29);
		panelInfo.add(idCliente);
		
		
		infoIdCliente.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoIdCliente.setBorder(BorderFactory.createCompoundBorder(
				infoIdCliente.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoIdCliente.setEditable(false);
		infoIdCliente.setBackground(new Color(217, 217, 217));
		infoIdCliente.setBounds(20, 40, 420, 25);
		panelInfo.add(infoIdCliente);
		
		JLabel nomCompleto = new JLabel("Nombre completo");
		nomCompleto.setForeground(Color.BLACK);
		nomCompleto.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nomCompleto.setBounds(20, 78, 354, 29);
		panelInfo.add(nomCompleto);
		
		
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
		
		
		JLabel noTelefono = new JLabel("Número de teléfono");
		noTelefono.setForeground(Color.BLACK);
		noTelefono.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		noTelefono.setBounds(20, 207, 354, 29);
		panelInfo.add(noTelefono);
		
		
		infoTelefono.setBorder(BorderFactory.createCompoundBorder(
				infoTelefono.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoTelefono.setEditable(false);
		infoTelefono.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoTelefono.setColumns(10);
		infoTelefono.setBackground(new Color(217, 217, 217));
		infoTelefono.setBounds(20, 238, 420, 25);
		panelInfo.add(infoTelefono);
		
		JLabel direccion = new JLabel("Dirección");
		direccion.setForeground(Color.BLACK);
		direccion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		direccion.setBounds(20, 274, 354, 29);
		panelInfo.add(direccion);
		
		
		infoDireccion.setBorder(BorderFactory.createCompoundBorder(
				infoDireccion.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoDireccion.setEditable(false);
		infoDireccion.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoDireccion.setColumns(10);
		infoDireccion.setBackground(new Color(217, 217, 217));
		infoDireccion.setBounds(20, 303, 420, 25);
		panelInfo.add(infoDireccion);
		
		JLabel nombreContEmerg = new JLabel("Nombre del contacto de emergencia");
		nombreContEmerg.setForeground(Color.BLACK);
		nombreContEmerg.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreContEmerg.setBounds(20, 335, 354, 29);
		panelInfo.add(nombreContEmerg);
		
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
		
		JLabel numeroContEmerg = new JLabel("Número del contacto de emergencia");
		numeroContEmerg.setForeground(Color.BLACK);
		numeroContEmerg.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		numeroContEmerg.setBounds(20, 395, 354, 29);
		panelInfo.add(numeroContEmerg);
		
		infoNumeroEmerg.setBorder(BorderFactory.createCompoundBorder(
				infoNumeroEmerg.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoNumeroEmerg.setEditable(false);
		infoNumeroEmerg.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoNumeroEmerg.setColumns(10);
		infoNumeroEmerg.setBackground(new Color(217, 217, 217));
		infoNumeroEmerg.setBounds(20,425, 420, 25);
		panelInfo.add(infoNumeroEmerg);
		
		
		JLabel relacionCliente = new JLabel("Relación del contacto con el cliente");
		relacionCliente.setForeground(Color.BLACK);
		relacionCliente.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		relacionCliente.setBounds(20, 460, 354, 29);
		panelInfo.add(relacionCliente);
		
		
		
		infoRelacion.setBorder(BorderFactory.createCompoundBorder(
				infoRelacion.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoRelacion.setEditable(false);
		infoRelacion.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoRelacion.setColumns(10);
		infoRelacion.setBackground(new Color(217, 217, 217));
		infoRelacion.setBounds(20,490, 420, 25);
		panelInfo.add(infoRelacion);
		
		JLabel informacionAdicional = new JLabel("Informacion Adicional");
		informacionAdicional.setForeground(Color.BLACK);
		informacionAdicional.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		informacionAdicional.setBounds(20, 525, 354, 29);
		panelInfo.add(informacionAdicional);
		
		infoAdicional.setBorder(BorderFactory.createCompoundBorder(
				infoAdicional.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoAdicional.setEditable(false);
		infoAdicional.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoAdicional.setColumns(10);
		infoAdicional.setBackground(new Color(217, 217, 217));
		infoAdicional.setBounds(20,555, 420, 50);
		panelInfo.add(infoAdicional);
		
		JLabel estadoHospedaje = new JLabel("Estado de hospedaje");
		estadoHospedaje.setForeground(Color.BLACK);
		estadoHospedaje.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		estadoHospedaje.setBounds(20, 620, 354, 29);
		panelInfo.add(estadoHospedaje);
		
		infoEstado.setBorder(BorderFactory.createCompoundBorder(
				infoEstado.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		infoEstado.setEditable(false);
		infoEstado.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoEstado.setColumns(10);
		infoEstado.setBackground(new Color(217, 217, 217));
		infoEstado.setBounds(20,650, 420, 25);
		panelInfo.add(infoEstado);
		
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
		
		//revalida y repinta el panelInfo x si acaso
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
				
				System.out.println("Inicio");
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
		clientes.setForeground(new Color(252,210,87));
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
				cliente = new ClientesController();
				cliente.crear();
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
				cliente = new ClientesController();
				cliente.consultar();
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
		panelEditar.add(panelCentral);

		
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(0, 73, 102));
		panelAzul.setBounds(24, 11, 1115, 539);
		panelCentral.add(panelAzul);
		panelAzul.setLayout(null);
		
		JLabel editarCliente = new JLabel("Editar cliente");
		editarCliente.setForeground(new Color(0, 0, 0));
		editarCliente.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		editarCliente.setBounds(463, 30, 185, 46);
		panelAzul.add(editarCliente);
	
		JLabel fondoCliente = new JLabel("");
		fondoCliente.setIcon(new ImageIcon(getClass().getResource("/contenido/tituloCliente.png")));
		fondoCliente.setBounds(300, 21, 489, 45);
		panelAzul.add(fondoCliente);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(BorderFactory.createLineBorder(new Color(252,210,87), 3));
		panelInfo.setBounds(42, 87, 1038, 384);
		panelAzul.add(panelInfo);
		panelInfo.setLayout(null);
		
		JButton subirBtn= new JButton();
		subirBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		subirBtn.setForeground(new Color(255, 255, 255));
		subirBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/subirCliente.png")));
		subirBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new ClientesModel();
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
				subirBtn.setEnabled(false); 
			}
		});
		subirBtn.setBorderPainted(false);
		subirBtn.setContentAreaFilled(false);
		subirBtn.setBounds(581, 18, 420, 116);
		panelInfo.add(subirBtn);
		
		JLabel nombre = new JLabel("Nombre completo");
		nombre.setForeground(Color.BLACK);
		nombre.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombre.setBounds(35, 11, 196, 46);
		panelInfo.add(nombre);
		
		nombreResp.setBorder(BorderFactory.createCompoundBorder(
				nombreResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		nombreResp.setBackground(new Color(217, 217, 217));
		nombreResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		nombreResp.setBounds(35, 53, 420, 25);
		panelInfo.add(nombreResp);
		nombreResp.setColumns(10);
		
		JLabel correoElectronico = new JLabel("Correo electrónico");
		correoElectronico.setForeground(Color.BLACK);
		correoElectronico.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		correoElectronico.setBounds(35, 83, 196, 46);
		panelInfo.add(correoElectronico);
		
		
		correoResp.setBorder(BorderFactory.createCompoundBorder(
				correoResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		correoResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		correoResp.setColumns(10);
		correoResp.setBackground(new Color(217, 217, 217));
		correoResp.setBounds(35, 125, 420, 25);
		panelInfo.add(correoResp);
		
		JLabel noTelefono = new JLabel("Número de teléfono");
		noTelefono.setForeground(Color.BLACK);
		noTelefono.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		noTelefono.setBounds(35, 157, 216, 46);
		panelInfo.add(noTelefono);
		
		
		telResp.setBorder(BorderFactory.createCompoundBorder(
				telResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		telResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		telResp.setColumns(10);
		telResp.setBackground(new Color(217, 217, 217));
		telResp.setBounds(35, 198, 420, 25);
		panelInfo.add(telResp);
		
		JLabel direccion = new JLabel("Dirección");
		direccion.setForeground(Color.BLACK);
		direccion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		direccion.setBounds(35, 227, 109, 46);
		panelInfo.add(direccion);
		
		direccionResp.setBorder(BorderFactory.createCompoundBorder(
				direccionResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		direccionResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		direccionResp.setColumns(10);
		direccionResp.setBackground(new Color(217, 217, 217));
		direccionResp.setBounds(35, 266, 420, 25);
		panelInfo.add(direccionResp);
		
		JLabel nombreContacto = new JLabel("Nombre del contacto de emergencia");
		nombreContacto.setForeground(Color.BLACK);
		nombreContacto.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreContacto.setBounds(35, 297, 364, 46);
		panelInfo.add(nombreContacto);
		
		contactoResp.setBorder(BorderFactory.createCompoundBorder(
				contactoResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		contactoResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		contactoResp.setColumns(10);
		contactoResp.setBackground(new Color(217, 217, 217));
		contactoResp.setBounds(35, 340, 420, 25);
		panelInfo.add(contactoResp);
		
		JLabel relacion = new JLabel("Relación con el cliente");
		relacion.setForeground(Color.BLACK);
		relacion.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		relacion.setBounds(581, 142, 233, 46);
		panelInfo.add(relacion);
		
		relacionResp.setBorder(BorderFactory.createCompoundBorder(
				relacionResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		relacionResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		relacionResp.setColumns(10);
		relacionResp.setBackground(new Color(217, 217, 217));
		relacionResp.setBounds(581, 179, 420, 25);
		panelInfo.add(relacionResp);
		
		JLabel noContacto = new JLabel("Número del contacto de emergencia");
		noContacto.setForeground(Color.BLACK);
		noContacto.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		noContacto.setBounds(581, 211, 364, 46);
		panelInfo.add(noContacto);
		
		noContactoResp.setBorder(BorderFactory.createCompoundBorder(
				noContactoResp.getBorder(),
		        BorderFactory.createEmptyBorder(3, 1, -5, 0)
		));
		noContactoResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		noContactoResp.setColumns(10);
		noContactoResp.setBackground(new Color(217, 217, 217));
		noContactoResp.setBounds(581, 248, 420, 25);
		panelInfo.add(noContactoResp);
		
		infoAdResp.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		infoAdResp.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		infoAdResp.setBackground(new Color(217, 217, 217));
		infoAdResp.setBounds(581, 315, 420, 50);
		panelInfo.add(infoAdResp);
		
		JLabel infoAdicional = new JLabel("Información adicional");
		infoAdicional.setForeground(Color.BLACK);
		infoAdicional.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		infoAdicional.setBounds(581, 278, 233, 46);
		panelInfo.add(infoAdicional);
		
		JButton botonVacio = new JButton();
		botonVacio.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new ClientesModel();
				model.eliminarCliente(idCliente);
			}
	
		});
		botonVacio.setBorderPainted(false);
		botonVacio.setContentAreaFilled(false);
		botonVacio.setIcon(new ImageIcon(getClass().getResource("/contenido/eliminarCliente.png")));
		botonVacio.setBounds(85, 482, 387, 50);
		panelAzul.add(botonVacio);
		
		JButton botonCrear = new JButton();
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idCliente != null && !idCliente.isEmpty())
				{
					int contadorErrores = 0;
					int contadorVacios = 0;
					String nombre = nombreResp.getText();
					String correo = correoResp.getText();
					String tel = telResp.getText();
					String dir = direccionResp.getText();
					String contactoEmergencia = contactoResp.getText();
					String relacion = relacionResp.getText();
					String telEmergencia = noContactoResp.getText();
					String info = infoAdResp.getText();
					String email = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
					String soloLetras = "^[a-zA-Z\\s]+$";
					InputStream img = model.getImagen();
					model = new ClientesModel();
					
					if(path != null)
					{
						imgB = model.imageToBlob(path);
					}
					else
					{
						System.out.println("Path no existe");
					}
					
					System.out.println("guardar: " + idCliente);
					
					model = new ClientesModel();
					 // Validar vacios
				    if (tel.equals("") || correo.equals("") || telEmergencia.equals("") || nombre.equals("") || dir.equals("") || contactoEmergencia.equals("") || relacion.equals("") || info.equals("")) {
				    	contadorVacios=1;
				        telResp.setBorder(BorderFactory.createLineBorder(tel.equals("") ? Color.red : Color.green, 2));
				        correoResp.setBorder(BorderFactory.createLineBorder(correo.equals("") ? Color.red : Color.green, 2));
				        noContactoResp.setBorder(BorderFactory.createLineBorder(telEmergencia.equals("") ? Color.red: Color.green, 2));
				        nombreResp.setBorder(BorderFactory.createLineBorder(nombre.equals("") ? Color.red : Color.green, 2));
				        direccionResp.setBorder(BorderFactory.createLineBorder(dir.equals("") ? Color.red : Color.green, 2));
				        contactoResp.setBorder(BorderFactory.createLineBorder(contactoEmergencia.equals("") ? Color.red : Color.green, 2));
				        relacionResp.setBorder(BorderFactory.createLineBorder(relacion.equals("") ? Color.red : Color.green, 2));
				        infoAdResp.setBorder(BorderFactory.createLineBorder(info.equals("") ? Color.red : Color.green, 2));
				       
				    }
				    if (tel.length() > 10 || !tel.matches("\\d+")) {
				        
				        telResp.setBorder(BorderFactory.createLineBorder(Color.red, 2));
				        contadorErrores++;
				    
				    }
				    if (!correo.matches(email)) {	       
				       
				        correoResp.setBorder(BorderFactory.createLineBorder(Color.red, 2));
				        contadorErrores++;
					    
				    }
				    if (telEmergencia.length() > 10 || !telEmergencia.matches("\\d+")) {
					    
				        
				        noContactoResp.setBorder(BorderFactory.createLineBorder(Color.red, 2));
				        contadorErrores++;
					    
				    
				    }
				    if (!nombre.matches(soloLetras)) {
					    
				        
				        noContactoResp.setBorder(BorderFactory.createLineBorder(Color.red, 2));
				        contadorErrores++;
				    }	

				    
				    if (contadorErrores > 0) {
				        datosNoValidos();
				        contadorErrores=0;
				    }

				    if (contadorVacios > 0) {
				        campoVacio();
				        contadorVacios=0;
				    }

				    
				    if (contadorErrores == 0 && contadorVacios == 0) {
//				        model.crear(nombre, correo, tel, dir, contactoEmergencia, relacion, telEmergencia, info, imgB);
//				        exito();
				    	model.editar(idCliente, nombre, correo, tel, dir, contactoEmergencia, relacion, telEmergencia, info, imgB);
						exito();
				    }		
					
				}
			}
		});
		botonCrear.setBorderPainted(false);
		botonCrear.setContentAreaFilled(false);
		botonCrear.setIcon(new ImageIcon(getClass().getResource("/contenido/guardarCambios.png")));
		botonCrear.setBounds(642, 482, 387, 50);
		panelAzul.add(botonCrear);
		
		JButton regresarBtn = new JButton();
		regresarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Regresar");
				frame.dispose();
				cliente = new ClientesController();
				cliente.consultar();
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
		
		idCliente = getIdCliente();
		
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

		model=new ClientesModel();
		DefaultTableModel historialClientes= model.tablaHistorialClientes(idClienteSeleccionado);
		JTable productoTable= new JTable(historialClientes); //dentro de los parentesis mete "datosClientes" 
		
		productoTable.setFont(new Font("Palatino Linotype", Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane(productoTable);
		scrollPane.setBounds(0, 0, 600, 400);
		paneltablita.add(scrollPane);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				emergente.dispose();
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
		
		idCliente = getIdCliente();
		
		JLabel fondoTexto = new JLabel();
		fondoTexto.setOpaque(true);
		fondoTexto.setBackground(new Color(0,73,102));
		fondoTexto.setBounds(0,0,emergente.getWidth(),50);
		descargaPanel.add(fondoTexto);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new ClientesModel();
				System.out.println("id seleccionado: " + idClienteSeleccionado);
				model.descargar(idClienteSeleccionado);
			}
		});
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnGuardar.setBorderPainted(false);
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		btnGuardar.setBounds(50, 570, 181, 51);
		descargaPanel.add(btnGuardar);
		
		JLabel imgGuardar= new JLabel();
		imgGuardar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgGuardar.setBounds(50, 570, 181, 51);
		descargaPanel.add(imgGuardar);
		
		JButton botonCancelar = new JButton("Cancelar");  ///////////////////////////////////////////////////////////////////BORRAR
		botonCancelar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				emergente.dispose();
			}
		});
		botonCancelar.setForeground(new Color(255, 255, 255));
		botonCancelar.setVerticalAlignment(SwingConstants.BOTTOM);
		botonCancelar.setBorderPainted(false);
		botonCancelar.setContentAreaFilled(false);
		botonCancelar.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonCancelar.setBounds(300, 570, 181, 51);
		descargaPanel.add(botonCancelar);
		
		JLabel imgCancelar= new JLabel();
		imgCancelar.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgCancelar.setBounds(300, 570, 181, 51);
		descargaPanel.add(imgCancelar);
		

		//Panel para el documento
		JPanel docPanel= new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/imagenDesenfocada.jpg"));
					g2d.drawImage(image, 0, 0, 400,300, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		docPanel.setBounds(65,70,400,475);
		docPanel.setBackground(Color.white);
		docPanel.setLayout(null);
		descargaPanel.add(docPanel);
		
		emergente.getContentPane().add(descargaPanel);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);
	    emergente.repaint();
	    emergente.revalidate();

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
	public void exito()
	{
		
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
				frame.dispose();
				cliente=new ClientesController();
				cliente.consultar();
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
					nombreResp.setText("");
					correoResp.setText("");
					telResp.setText("");
					direccionResp.setText("");
					contactoResp.setText("");
					relacionResp.setText("");
					noContactoResp.setText("");
					infoAdResp.setText("");
					emergente.dispose();
					emergente.dispose();
					subirBtn.setIcon(new ImageIcon(getClass().getResource("/contenido/subirHab.png")));
			        subirBtn.setEnabled(true);
			        subirBtn.repaint();
			        subirBtn.revalidate();
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
	public void errorImagen()
	{
		
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
		+"No has seleccionado ningún  <br>"+
		"cliente, favor de seleccionarlo.<br>"+
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
	public void docExito()
	{
		
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
	
	public void docError()
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

	public JTextField getId() {	
        return infoIdCliente;		
    }

	
	public JTextField getNombre() {
        return infoNombre;
    }
	
	public JTextField getCorreo() {
        return infoCorreo;
    }
	
	public JTextField getTelefono() {
        return infoTelefono;
    }
	
	public JTextField getDireccion() {
        return infoDireccion;
    }
	
	public JTextField getNombreEmergencia() {
        return infoNomEmerg;
    }
	
	public JTextField getNumEmergencia() {
        return infoNumeroEmerg;
    }
	
	public JTextField getRelacion() {
        return infoRelacion;
    }
	
	public JTextField getEstatus() {
        return infoEstado;
    }
	
	public JTextArea getInfo()
	{
		return infoAdicional;
	}
	
	 public JPanel getPanelImg() {
	     return panelImagen;
	   }
	 
	 public String getIdCliente()
	 {
		 return idCliente;
	 }
	 public JLabel getNombreDetalles() {
	        return nombreCliente;
	 }
	//solo falta la imagen
}
