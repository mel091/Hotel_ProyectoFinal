package controllers;

import views.TarifasView;

public class TarifasController {
	public TarifasView vista;
	
	public TarifasController()
	{
		vista = new TarifasView();
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
