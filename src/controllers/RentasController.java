package controllers;

import views.RentasView;

public class RentasController 
{
	public RentasView vista;
	
	public RentasController()
	{
		vista = new RentasView();
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
	
	public void rentasPrincipal()
	{
		vista.rentasPrincipal();
	}
	
	public void pagoInicial()
	{
		vista.pagoInicial();
	}
	
	public void edicionPago()
	{
		vista.edicionPago();
	}
	
	public void checkOut()
	{
		vista.checkOut();
	}

}
