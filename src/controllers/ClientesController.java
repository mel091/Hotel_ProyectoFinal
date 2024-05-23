package controllers;

import views.ClientesView;

public class ClientesController 
{
public ClientesView vista;
	
	public ClientesController()
	{
		vista = new ClientesView();
	}
	
	public void consultar()
	{
		vista.consultar();
	}
	
	public void crear()
	{
		vista.crear();
	}
	
	public void detalles()
	{
		vista.detalles();
	}
	
	public void editar()
	{
		vista.editar();
	}
	
	public void historial()
	{
		vista.historial();
	}
	
	public void descarga()
	{
		vista.descarga();
	}

}
