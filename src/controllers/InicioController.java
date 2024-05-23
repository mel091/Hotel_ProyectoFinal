package controllers;

import views.AuthView;
import views.InicioView;

public class InicioController 
{
	public InicioView vista;
	
	public InicioController()
	{
		vista = new InicioView();
	}
	
	public void inicio()
	{
		vista.inicio();
	}
		
	
}
