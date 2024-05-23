package controllers;

import views.TiposView;

public class TiposController {
public TiposView vista;
	
	public TiposController()
	{
		vista = new TiposView();
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

}
