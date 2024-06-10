package models;

public class Renta 
{
	private String idCliente;
	private String idRenta;
	private String idHabitacion;
	private String fechaInicial;
	private String fechaFinal;
	private String tarifas;
	private String solicitudes;
	private String estatus;
	private String subtotal;
	private String total;
	
	public Renta(String idCliente, String idRenta, String idHabitacion, String fechaInicial, String fechaFinal,
	 String tarifas,String solicitudes, String subtotal,String total,String estatus)
	{
		this.idRenta = idRenta;
		this.idCliente = idCliente;
		this.idHabitacion = idHabitacion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.tarifas = tarifas;
		this.solicitudes = solicitudes;
		this.estatus = estatus;
		this.subtotal = subtotal;
		this.total = total;
	}

	public String getIdHabitacion() {
		return idHabitacion;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public String getIdRenta() {
		return idRenta;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public String getTarifas() {
		return tarifas;
	}

	public String getSolicitudes() {
		return solicitudes;
	}

	public String getEstatus() {
		return estatus;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public String getTotal() {
		return total;
	}
	
	
}
