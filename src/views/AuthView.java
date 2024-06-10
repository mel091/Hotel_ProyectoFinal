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
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
	private JDialog emergente;
	public Auth login;
	public AuthModel model;
	public ClientesView cliente;
	
	public AuthView()
	{
		frame=new JFrame();
		frame.setVisible(true);
		frame.setBounds(10, 5, 1350, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icono = new ImageIcon(getClass().getResource("/contenido/castleIcon2.png"));
		frame.setIconImage(icono.getImage());
		emergente=new JDialog(frame,"ventana", true);
		emergente.setSize( 560, 290);
		emergente.setResizable(false);
	}
	
	//Login
	public void login()
	{
		frame.getContentPane().removeAll();
		frame.repaint();
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
		
		
		JButton botonAcceso = new JButton("Acceso"); 
		botonAcceso.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new AuthModel();
				String usr=textUsuario.getText();
				String psw=new String(textPws.getPassword());
				boolean resultado=model.acceder(textUsuario.getText(), psw);
				if(usr.length()<=0||psw.length()<=0||resultado!=true)
				{
					textUsuario.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					textPws.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					vDatosIncorrectos();
					textUsuario.setText("");
					textPws.setText("");
				}
				else
				{
					textUsuario.setBorder(BorderFactory.createLineBorder(Color.green, 2));
					textPws.setBorder(BorderFactory.createLineBorder(Color.green, 2));
					frame.dispose();
				}

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
		btnRegistro.setBounds(310, 370, 147, 28);
		panelInicio.add(btnRegistro);
	
		
		frame.getContentPane().add(panelLogin);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
			
	}
	
	//Registro
	public void registro()
	{
		frame.getContentPane().removeAll();
		frame.repaint();
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
				//mandar inputs a model.registro
				model = new AuthModel();
				String nombreCompleto = textNombre.getText();
				String user = textUsr.getText();
				String contraseña = new String(textPws.getPassword());
				String contraseña1 = new String(textPwsConfi.getPassword());
				//model.registro(nombreCompleto, user, contraseña, contraseña1);
				
				if(nombreCompleto.length() > 0 || user.length() > 0 || contraseña.length()>0 || contraseña1.length()>0 )
				{
					
					if(contraseña.equals(contraseña1))
					{
						model.registro(nombreCompleto, user, contraseña, contraseña1);
						frame.dispose();
						login = new Auth();
						login.login();
						
						
					}
					else
					{
						textPws.setBorder(BorderFactory.createLineBorder(Color.red, 2));
						textPwsConfi.setBorder(BorderFactory.createLineBorder(Color.red, 2));
						noCohincide();
					}
				}
				else
				{
					textNombre.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					textUsr.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					textPws.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					textPwsConfi.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					
					cliente = new ClientesView();
					cliente.campoVacio();
				}
				
				
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
				frame.dispose();
				login = new Auth();
				login.login();
			}
		});
		btnLogin.setBorderPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBounds(310, 370, 147, 28);
		panelInicio.add(btnLogin);

		frame.getContentPane().add(panelRegistro);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
		
				
	}
	public void vDatosIncorrectos()
	{
		
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		//String info="Los datos que ha ingresado son "+"\n"+ "incorrectos, favor de ingresarlos"+ "\n"+ "correctamente";
		String info=("<html><div style='text-align: center;'>"
		+"Los datos que ha ingresado son <br>"+
		"incorrectos, favor de ingresarlos <br>"+
		"correctamente"+"</div></html>");
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
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/posion.png")));
		iconPosion.setBounds(10, 55, 80, 80);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}

	public void noCohincide()
	{
		
		JPanel datos= new JPanel();
		datos.setBounds(0, 0, emergente.getWidth(), emergente.getHeight());
		datos.setBackground(new Color(220,220,220));
		datos.setLayout(null);
		
		//String info="Los datos que ha ingresado son "+"\n"+ "incorrectos, favor de ingresarlos"+ "\n"+ "correctamente";
		String info=("<html><div style='text-align: center;'>"
		+"Las contraseñas no coinciden, favor <br>"+
		"de ingresarlos correctamente. <br>"
		+"</div></html>");
		JLabel text = new JLabel(info);
		text.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.black);
		text.setBounds(42,20,487,168);
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
		iconPosion.setIcon(new ImageIcon(getClass().getResource("/contenido/posion.png")));
		iconPosion.setBounds(5, 63, 80, 80);
		datos.add(iconPosion);
		
		emergente.add(datos);
	    emergente.setLocationRelativeTo(frame);
	    emergente.setVisible(true);;
		
	}
}
