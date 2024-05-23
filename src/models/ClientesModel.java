package models;

import controllers.ClientesController;
import controllers.HabitacionesController;

public class ClientesModel 
{
	public ClientesController cliente;
	
	public ClientesModel()
	{
		
	}
	
	public void descargar()
	{
		cliente = new ClientesController();
		cliente.descarga();
	}
	
	public void historial()
	{
		cliente = new ClientesController();
		cliente.historial();
	}
	
	public void subirImg() //preguntar
	{
		//proceso
		System.out.println("sube imagen");
	}

}
