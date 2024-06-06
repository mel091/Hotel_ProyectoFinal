package models;

public class Clientes 
{
	private String idCliente;
	private String nombreCompleto;
	private String correo;
	private String telefono;
	private String direccion;
	private String contactoEmergencia;
	private String relacionCliente;
	private String telefonoEmergencia;
	private String infAdicional;
	private String estatus;
	private java.sql.Blob imagen;
	
	public Clientes(String idCliente, String nombreCompleto, String correo, String telefono, String direccion,
			String contactoEmergencia, String relacionCliente, String telefonoEmergencia, String infAdicional, String estatus,  java.sql.Blob imagen)
	{
		this.idCliente = idCliente;
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.contactoEmergencia = contactoEmergencia;
		this.relacionCliente = relacionCliente;
		this.telefonoEmergencia = telefonoEmergencia;
		this.infAdicional = infAdicional;
		this.estatus = estatus;
		this.imagen = imagen;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getContactoEmergencia() {
		return contactoEmergencia;
	}

	public String getRelacionCliente() {
		return relacionCliente;
	}

	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}

	public String getInfAdicional() {
		return infAdicional;
	}

	public String getEstatus() {
		return estatus;
	}

	public java.sql.Blob getImagen() {
		return imagen;
	}

}
