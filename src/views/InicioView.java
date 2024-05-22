package views;

import javax.swing.JFrame;

import controllers.Auth;

public class InicioView 
{
	private JFrame frame;
	private InicioView controller;
	
	public InicioView()
	{
		frame=new JFrame();
		frame.setVisible(false);
		frame.setBounds(10, 5, 1350, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
