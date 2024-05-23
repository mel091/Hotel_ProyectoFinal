package controllers;

import views.HabitacionesView;

public class HabitacionesController 
{
public HabitacionesView vista;
	
	public HabitacionesController()
	{
		vista = new HabitacionesView();
	}
	
	public void consultar()
	{
		vista.consultar();
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
	
	public void crear()
	{
		vista.crear();
	}

}
