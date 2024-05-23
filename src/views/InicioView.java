package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.SwingConstants;

import controllers.Auth;
import controllers.ClientesController;
import controllers.HabitacionesController;
import controllers.InicioController;
import controllers.RentasController;
import controllers.TarifasController;
import controllers.TiposController;

public class InicioView 
{
	private JFrame frame;
	public Auth login;
	public InicioController inicio;
	public TarifasController tarifa;
	public TiposController tipo;
	public RentasController renta;
	public HabitacionesController room;
	public ClientesController cliente;
	
	public InicioView()
	{
		frame=new JFrame();
		frame.setVisible(true);
		frame.setBounds(10, 5, 1350, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void inicio()
	{
		frame.getContentPane().removeAll();
		frame.repaint();
		//Panel principal
		JPanel panelInicio=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {

					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/imagenInicio.jpg"));
					g2d.drawImage(image, 0, 0, 1350, 700, null);
					

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelInicio.setBackground(new Color(122,121,123));
		panelInicio.setBounds(0, 0, 1200, 700);
		panelInicio.setLayout(null);
	
		
		
		//Panel de la cabecera
		JLabel disneyFondo = new JLabel();
		disneyFondo.setBounds(10, 10, 1313, 90);
		disneyFondo.setOpaque(true);
		disneyFondo.setBackground(new Color(0,73,102));
		disneyFondo.setLayout(null);
		panelInicio.add(disneyFondo);
		
	
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
		btnDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/imgWalt.png")));
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
				frame.dispose();
				tipo = new TiposController();
				tipo.crear();
			}
		});
		btnTipos.setBorderPainted(false);
		btnTipos.setContentAreaFilled(false);
		btnTipos.setBounds(985, 36, 295, 30);
		disneyFondo.add(btnTipos);
		
		
		//Panel movil
		JPanel panelMovil = new JPanel();
		panelMovil.setPreferredSize(new Dimension(1304, 1300));
		panelMovil.setOpaque(false);
		panelMovil.setLayout(null);
		
		JLabel gifDisney = new JLabel();
		gifDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/disney2.gif")));
		gifDisney.setBounds(35, 4,1250,432);
		panelMovil.add(gifDisney);	
		
		JLabel img1 = new JLabel();
		img1.setIcon(new ImageIcon(getClass().getResource("/contenido/img1.jpg")));
		img1.setBounds(35, 475,600,400);
		panelMovil.add(img1);
		
		JLabel img2 = new JLabel();
		img2.setIcon(new ImageIcon(getClass().getResource("/contenido/img2.jpg")));
		img2.setBounds(685, 475,600,400);
		panelMovil.add(img2);
		
		
		//Panel de botones
		JPanel panelBotones=new JPanel();
		panelBotones.setBounds(35,915,1250,70);
		panelBotones.setBackground(Color.white);
		panelBotones.setLayout(null);
		panelMovil.add(panelBotones);
		

		JLabel registro= new JLabel("Registrarse");
		registro.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		registro.setForeground(Color.black);
		registro.setBounds(250, 25, 150, 40);
		panelBotones.add(registro);
		
		JLabel iconRegistro= new JLabel();
		iconRegistro.setIcon(new ImageIcon(getClass().getResource("/contenido/registro.png")));
		iconRegistro.setBounds(190, 10, 50, 50);
		panelBotones.add(iconRegistro);
		
		
		JLabel logout= new JLabel("Cerrar sesión");
		logout.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		logout.setForeground(Color.black);
		logout.setBounds(880, 25, 180, 40);
		panelBotones.add(logout);
		
		JLabel iconLogout= new JLabel();
		iconLogout.setIcon(new ImageIcon(getClass().getResource("/contenido/logout.png")));
		iconLogout.setBounds(820, 10, 55, 50);
		panelBotones.add(iconLogout);
		
		JButton btnRegistro= new JButton();
		btnRegistro.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				login = new Auth();
				login.registro();
			}
		});
		btnRegistro.setBorderPainted(false);
		btnRegistro.setContentAreaFilled(false);
		btnRegistro.setBounds(190, 10, 210, 50);
		panelBotones.add(btnRegistro);

		JButton btnLogout= new JButton();
		btnLogout.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				login = new Auth();
				login.login();
			}
		});
		btnLogout.setBorderPainted(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBounds(820, 10, 240, 50);
		panelBotones.add(btnLogout);
		
		//Panel info
		JPanel panelInfo=new JPanel();
		panelInfo.setBounds(35,1025,1250,210);
		panelInfo.setBackground(new Color(0,73,102));
		panelInfo.setLayout(null);
		panelMovil.add(panelInfo);
		
		String texto=("<html><div style='text-align: center;'>"
				+"© Disney, Todos los Derechos Reservados <br>"
				+ "Disney Vacations, LLC <br>"
				+ "PO Box 10250 <br>"
				+ "Lake Buena Vista, FL 32830-0250 | 81-2564985<br>"
				+ "<br>"
				+ "ContactUs@DisneyVacationsLLC.com<br>"
				+ "<br>"
				+ "+1 (407) 827-7144<br>"
				+ "<br>"
				+ "Se aplicarán cargos por llamadas internacionales cuando llames a Disney desde fuera de los Estados Unidos"
				+"</div></html>");
		JLabel info= new JLabel(texto);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		info.setForeground(Color.white);
		info.setBounds(290, 1, 600, 210);
		panelInfo.add(info);
		
		JScrollPane scrollPane = new JScrollPane(panelMovil);
		scrollPane.setBounds(0, 130, 1334, 575);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		panelInicio.add(scrollPane);
		
		frame.getContentPane().add(panelInicio);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
			
	}

}
