package models;

import controllers.HabitacionesController;

public class HabitacionesModel {
public HabitacionesController habitacion;
	
	public HabitacionesModel()
	{
		
	}
	
	public void detalles()
	{
		habitacion = new HabitacionesController();
		habitacion.detalles();
	}
	
	public void descarga()
	{
		habitacion = new HabitacionesController();
		habitacion.descarga();
	}
	
	public void historial()
	{
		habitacion = new HabitacionesController();
		habitacion.historial();
	}
	
	public void crear()
	{
		System.out.println("creado");
	}
	
	public void subirImg() //preguntar
	{
		//proceso
		System.out.println("sube imagen");
		habitacion = new HabitacionesController();
		habitacion.consultar();
	}
}
