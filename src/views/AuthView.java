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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.Auth;
import models.AuthModel;

public class AuthView 
{
	private JFrame frame;
	private Auth controller;
	private AuthModel auth;
	
	public AuthView()
	{
		frame=new JFrame();
		frame.setVisible(false);
		frame.setBounds(10, 5, 1350, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Login
	public void login()
	{
		//Panel principal del login
		JPanel panelLogin=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {
					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/login.jpg"));
					g2d.drawImage(image, 0, 0, 1350, 700, null);
					

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelLogin.setBackground(new Color(122,121,123));
		panelLogin.setBounds(0, 0, 1200, 700);
		panelLogin.setLayout(null);
		
		JLabel hadas= new JLabel();
		hadas.setIcon(new ImageIcon(getClass().getResource("/contenido/hadas.png")));
		hadas.setBounds(800, 140, 388, 495);
		panelLogin.add(hadas);
		
		
		//Panel de la cabecera
		JLabel disneyFondo = new JLabel();
		disneyFondo.setBounds(10, 10, 1313, 90);
		disneyFondo.setOpaque(true);
		disneyFondo.setBackground(new Color(0,73,102));
		disneyFondo.setLayout(null);
		panelLogin.add(disneyFondo);
		
		JLabel tituloDisney = new JLabel();
		tituloDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/waltDisney.png")));
		tituloDisney.setBounds(435, -5, 428, 95);
		disneyFondo.add(tituloDisney);
		
		
		//Panel con la informacion del login
		JPanel panelInicio = new JPanel();
		panelInicio.setOpaque(false);
		panelInicio.setBounds(130, 150,570,485);
		panelInicio.setLayout(null);
		panelLogin.add(panelInicio);
		
		JLabel inicioSesion = new JLabel("Inicio se sesion");
		inicioSesion.setFont(new Font("Palatino Linotype", Font.BOLD, 35));
		inicioSesion.setBounds(163, 45, 247, 48);
		panelInicio.add(inicioSesion);
		
		JLabel nombreUsuario = new JLabel("Nombre de usuario");
		nombreUsuario.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		nombreUsuario.setBounds(62, 105, 247, 50);
		panelInicio.add(nombreUsuario);
		
		JTextField textUsuario = new JTextField();
		textUsuario.setBorder(BorderFactory.createCompoundBorder(
				textUsuario.getBorder(),
		        BorderFactory.createEmptyBorder(4, 1, -3, 0)
		));
		textUsuario.setBackground(new Color(217, 217, 217));
		textUsuario.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));

		textUsuario.setBounds(62, 165, 460, 50);
		panelInicio.add(textUsuario);
		

		JLabel iconUsr = new JLabel();
		iconUsr.setIcon(new ImageIcon(getClass().getResource("/contenido/corona.png")));
		iconUsr.setBounds(30, 176, 24, 24);
		panelInicio.add(iconUsr);
		
		
		JLabel pws = new JLabel("Constraseña");
		pws.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		pws.setBounds(62, 240, 247, 48);
		panelInicio.add(pws);
		
		JPasswordField textPws = new JPasswordField();
		textPws.setBorder(BorderFactory.createCompoundBorder(
				textPws.getBorder(),
		        BorderFactory.createEmptyBorder(4, 1, -3, 0)
		));
		textPws.setBackground(new Color(217, 217, 217));
		textPws.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		textPws.setBounds(62, 300, 460, 50);
		panelInicio.add(textPws);
		
		JLabel iconPws = new JLabel();
		iconPws.setIcon(new ImageIcon(getClass().getResource("/contenido/llave.png")));
		iconPws.setBounds(30, 313, 24, 24);
		panelInicio.add(iconPws);
		
		
		JButton botonAcceso = new JButton("Acceso"); /////////////////////////////////////////////////////
		botonAcceso.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthModel funcion = new AuthModel();
				String user = textUsuario.getText().trim();
                String password = new String(textPws.getPassword()).trim();
                funcion.setLoginFields(textUsuario, textPws);
                if(funcion.acceder(user, password))
                {
                	controller = new Auth();
    				frame.removeAll();
    				frame.setVisible(false);
    				controller.inicio();	
                }
               // funcion.acceder(user, password);
				
			}
		});
		botonAcceso.setForeground(new Color(255, 255, 255));
		botonAcceso.setVerticalAlignment(SwingConstants.BOTTOM);
		botonAcceso.setBorderPainted(false);
		botonAcceso.setContentAreaFilled(false);
		botonAcceso.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonAcceso.setBounds(184, 411, 181, 51);
		panelInicio.add(botonAcceso);
		
		JLabel imgAcceso= new JLabel();
		imgAcceso.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAcceso.setBounds(184, 411, 181, 51);
		panelInicio.add(imgAcceso);
		
		JLabel mandarRegistro = new JLabel("¿No tienes una cuenta? Registrate aquí  ");
		mandarRegistro.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		mandarRegistro.setBounds(98, 370, 359, 37);
		panelInicio.add(mandarRegistro);
		
		JLabel mandarRegistro_1 = new JLabel("______________");
		mandarRegistro_1.setBounds(310, 380, 147, 16);
		mandarRegistro_1.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		panelInicio.add(mandarRegistro_1);
	
		
		JButton btnRegistro= new JButton(); /////////////////////////////////////////////7
		btnRegistro.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				controller = new Auth();
				frame.removeAll();
				frame.setVisible(false);
				controller.registro();	
			}
		});
		btnRegistro.setBorderPainted(false);
		btnRegistro.setContentAreaFilled(false);
		btnRegistro.setBounds(310, 370, 147, 28);
		panelInicio.add(btnRegistro);
	
		
		frame.add(panelLogin);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
			
	}
	
	//Registro
	public void registro()
	{
		//Panel principal del Registro
		JPanel panelRegistro=new JPanel()
		{
			@Override
			public void paintComponent(Graphics create) {
				super.paintComponent(create);
				Graphics2D g2d = (Graphics2D) create;
				try {

					BufferedImage image = ImageIO.read(getClass().getResource("/contenido/login.jpg"));
					g2d.drawImage(image, 0, 0, 1350, 700, null);
					

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		panelRegistro.setBackground(new Color(122,121,123));
		panelRegistro.setBounds(0, 0, 1200, 700);
		panelRegistro.setLayout(null);
		
		JLabel hadas= new JLabel();
		hadas.setIcon(new ImageIcon(getClass().getResource("/contenido/hadas.png")));
		hadas.setBounds(800, 140, 388, 495);
		panelRegistro.add(hadas);
		
		
		//Panel de la cabecera
		JLabel disneyFondo = new JLabel();
		disneyFondo.setBounds(10, 10, 1313, 90);
		disneyFondo.setOpaque(true);
		disneyFondo.setBackground(new Color(0,73,102));
		disneyFondo.setLayout(null);
		panelRegistro.add(disneyFondo);
		
		JLabel tituloDisney = new JLabel();
		tituloDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/waltDisney.png")));
		tituloDisney.setBounds(435, -5, 428, 95);
		disneyFondo.add(tituloDisney);
		
		
		//Panel con la informacion del registro
		JPanel panelInicio = new JPanel();
		panelInicio.setOpaque(false);
		panelInicio.setBounds(130, 150,570,485);
		panelInicio.setLayout(null);
		panelRegistro.add(panelInicio);
		
		JLabel inicioSesion = new JLabel("Registro");
		inicioSesion.setFont(new Font("Palatino Linotype", Font.BOLD, 35));
		inicioSesion.setBounds(197, 24, 145, 48);
		panelInicio.add(inicioSesion);
		
		JLabel nombreCompleto = new JLabel("Nombre completo");
		nombreCompleto.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreCompleto.setBounds(62, 70, 247, 50);
		panelInicio.add(nombreCompleto);
		
		JTextField textNombre = new JTextField();
		textNombre.setBorder(BorderFactory.createCompoundBorder(
				textNombre.getBorder(),
		        BorderFactory.createEmptyBorder(4, 1, -3, 0)
		));
		textNombre.setBackground(new Color(217, 217, 217));
		textNombre.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		textNombre.setBounds(62, 114, 460, 30);
		panelInicio.add(textNombre);
		
		JLabel iconNom= new JLabel();
		iconNom.setIcon(new ImageIcon(getClass().getResource("/contenido/nom.png")));
		iconNom.setBounds(30, 115, 24, 24);
		panelInicio.add(iconNom);

		
		JLabel nombreUsuario = new JLabel("Nombre de usuario");
		nombreUsuario.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		nombreUsuario.setBounds(62, 146, 247, 48);
		panelInicio.add(nombreUsuario);
		
		JTextField textUsr = new JTextField();
		textUsr.setBorder(BorderFactory.createCompoundBorder(
		        textUsr.getBorder(),
		        BorderFactory.createEmptyBorder(4, 1, -3, 0)
		));
		textUsr.setBackground(new Color(217, 217, 217));
		textUsr.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		textUsr.setBounds(62, 186, 460, 30);
		panelInicio.add(textUsr);
		
		JLabel iconUsr = new JLabel();
		iconUsr.setIcon(new ImageIcon(getClass().getResource("/contenido/corona.png")));
		iconUsr.setBounds(30, 186, 24, 24);
		panelInicio.add(iconUsr);
		
		
		JLabel psw = new JLabel("Contraseña");
		psw.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		psw.setBounds(62, 217, 247, 48);
		panelInicio.add(psw);
		
		JPasswordField textPws = new JPasswordField();
		textPws.setBorder(BorderFactory.createCompoundBorder(
		        textPws.getBorder(),
		        BorderFactory.createEmptyBorder(4, 1, -3, 0)
		));
		textPws.setBackground(new Color(217, 217, 217));
		textPws.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		textPws.setBounds(62, 257, 460, 30);
		panelInicio.add(textPws);
		
		JLabel iconPws = new JLabel();
		iconPws.setIcon(new ImageIcon(getClass().getResource("/contenido/llave.png")));
		iconPws.setBounds(30, 257, 24, 24);
		panelInicio.add(iconPws);
		
		JLabel confipsw = new JLabel("Confirmar contraseña");
		confipsw.setFont(new Font("Palatino Linotype", Font.BOLD, 21));
		confipsw.setBounds(62, 287, 247, 48);
		panelInicio.add(confipsw);
		
		JPasswordField textPwsConfi = new JPasswordField();
		textPwsConfi.setBorder(BorderFactory.createCompoundBorder(
		        textPwsConfi.getBorder(),
		        BorderFactory.createEmptyBorder(4, 1, -3, 0)
		));
		textPwsConfi.setBackground(new Color(217, 217, 217));
		textPwsConfi.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		textPwsConfi.setBounds(62, 327, 460, 30);
		panelInicio.add(textPwsConfi);
		
		JLabel iconPwsConf = new JLabel();
		iconPwsConf.setIcon(new ImageIcon(getClass().getResource("/contenido/candado.png")));
		iconPwsConf.setBounds(30, 328, 24, 24);
		panelInicio.add(iconPwsConf);
		
		JButton botonRegistro = new JButton("Registrarse");
		botonRegistro.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthModel funcion = new AuthModel();
				String nombreCompleto = textNombre.getText().trim();
				String user = textUsr.getText().trim();
				String psw = new String(textPws.getPassword()).trim();
				String psw1 = new String(textPwsConfi.getPassword()).trim();
                funcion.setRegistrationFields(textNombre, textUsr, textPws, textPwsConfi);
                funcion.registro(nombreCompleto, user, psw, psw1);
			}
		});
		botonRegistro.setForeground(new Color(255, 255, 255));
		botonRegistro.setVerticalAlignment(SwingConstants.BOTTOM);
		botonRegistro.setBorderPainted(false);
		botonRegistro.setContentAreaFilled(false);
		botonRegistro.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		botonRegistro.setBounds(184, 411, 181, 51);
		panelInicio.add(botonRegistro);
		
		JLabel imgAcceso= new JLabel();
		imgAcceso.setIcon(new ImageIcon(getClass().getResource("/contenido/accesoLogin.png")));
		imgAcceso.setBounds(184, 411, 181, 51);
		panelInicio.add(imgAcceso);
		
		JLabel mandarInicio = new JLabel("¿Ya tienes una cuenta? Inicia sesión aquí  ");
		mandarInicio.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		mandarInicio.setBounds(89, 372, 384, 37);
		panelInicio.add(mandarInicio);
		
		JLabel mandarInicio_1 = new JLabel("________________");
		mandarInicio_1.setBounds(299, 382, 162, 16);
		mandarInicio_1.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		panelInicio.add(mandarInicio_1);
	
		
		JButton btnLogin= new JButton();
		btnLogin.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				controller = new Auth();
				frame.removeAll();
				frame.setVisible(false);
				controller.login();	
			}
		});
		btnLogin.setBorderPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBounds(310, 370, 147, 28);
		panelInicio.add(btnLogin);
		
		
		
		frame.add(panelRegistro);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
		
				
	}
	//Inicio
	
	public void inicio()
	{
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
				// TODO Auto-generated method stub
				System.out.println("Inicio");
			}
		});
		btnDisney.setIcon(new ImageIcon(getClass().getResource("/contenido/imgWalt.png")));
		btnDisney.setBorderPainted(false);
		btnDisney.setContentAreaFilled(false);
		btnDisney.setBounds(25, 20, 250, 56);
		disneyFondo.add(btnDisney);
		
		//Labels
		
		JLabel habitaciones = new JLabel("Habitaciones");
		habitaciones.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		habitaciones.setForeground(Color.white);
		habitaciones.setBounds(315, 40, 180, 40);
		disneyFondo.add(habitaciones);
		
		JLabel clientes = new JLabel("Clientes");
		clientes.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		clientes.setForeground(Color.white);
		clientes.setBounds(540, 40, 120, 40);
		disneyFondo.add(clientes );
		
		JLabel rentas = new JLabel("Rentas");
		rentas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		rentas.setForeground(Color.white);
		rentas.setBounds(700, 40, 120, 40);
		disneyFondo.add(rentas);
		
		JLabel tarifas = new JLabel("Tarifas");
		tarifas.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tarifas.setForeground(Color.white);
		tarifas.setBounds(840, 40, 130, 40);
		disneyFondo.add(tarifas);
		
		JLabel tipos = new JLabel("Tipos de habitaciones");
		tipos.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		tipos.setForeground(Color.white);
		tipos.setBounds(985, 40, 310, 40);
		disneyFondo.add(tipos);
		
		
		JButton btnHabitaciones = new JButton();
		btnHabitaciones.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Habitaciones");
			}
		});
		btnHabitaciones.setBorderPainted(false);
		btnHabitaciones.setContentAreaFilled(false);
		btnHabitaciones.setBounds(315, 40, 180, 30);
		disneyFondo.add(btnHabitaciones);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Clientes");
			}
		});
		btnClientes.setBorderPainted(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBounds(540, 40, 110, 30);
		disneyFondo.add(btnClientes );
		
		JButton btnRentas= new JButton("");
		btnRentas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Rentas");
			}
		});
		btnRentas.setBorderPainted(false);
		btnRentas.setContentAreaFilled(false);
		btnRentas.setBounds(700, 40, 93, 30);
		disneyFondo.add(btnRentas);
		
		JButton btnTarifas= new JButton("");
		btnTarifas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Tarifas");
			}
		});
		btnTarifas.setBorderPainted(false);
		btnTarifas.setContentAreaFilled(false);
		btnTarifas.setBounds(840, 40, 95, 30);
		disneyFondo.add(btnTarifas);
		
		
		JButton btnTipos= new JButton("");
		btnTipos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Rentas");
			}
		});
		btnTipos.setBorderPainted(false);
		btnTipos.setContentAreaFilled(false);
		btnTipos.setBounds(985, 40, 295, 30);
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
				// TODO Auto-generated method stub
				System.out.println("Registro");
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
				// TODO Auto-generated method stub
				System.out.println("Logout");
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
		
		frame.add(panelInicio);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
		
				
	}

}
