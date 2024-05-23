package models;

import controllers.HabitacionesController;
import controllers.RentasController;

public class RentasModel 
{
	public RentasController renta;
	
	public RentasModel()
	{
		
	}
	
	public void detalles()
	{
		renta = new RentasController();
		renta.detalles();
	}
}
