package models;

public class Habitacion 
{
	private String idHabitacion;
	private String nombre;
	private String tipo;
	private String tam;
	private String descripcion;
	private String solicitudes;
	//private String costo;
	private java.sql.Blob imagen;
	
	public Habitacion(String idHabitacion, String nombre, String tipo, String tam, String descripcion,
			String solicitudes, java.sql.Blob imagen)
	{
		this.idHabitacion = idHabitacion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.tam = tam;
		this.descripcion = descripcion;
		this.solicitudes = solicitudes;
		this.imagen = imagen;
	}

	public String getIdHabitacion() {
		return idHabitacion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public String getTam() {
		return tam;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getSolicitudes() {
		return solicitudes;
	}


	public java.sql.Blob getImagen() {
		return imagen;
	}
	
	
}
