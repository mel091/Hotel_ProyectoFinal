package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.HabitacionesController;
import controllers.RentasController;

public class RentasModel 
{
	JTextField id1;
	JTextField nombre;
	JTextField correo;
	JTextField f1;
	JTextField f2;
	JTextField tarifasD;
	JTextArea amenidades;
	JTextField subtotal;
	JTextField total;
	JTextField estatusD;
	
	public void textField(JTextField id, JTextField nombre, JTextField correo, JTextField f1, JTextField f2, JTextField tarifasD,
			JTextArea amenidades, JTextField subtotal, JTextField total, JTextField estatusD)
	{
		this.id1 = id;
		this.nombre = nombre;
		this.correo = correo;
		this.f1 = f1;
		this.f2 = f2;
		this.tarifasD = tarifasD;
		this.amenidades = amenidades;
		this.subtotal = subtotal;
		this.total = total;
		this.estatusD = estatusD;
	}
	
	public void mostrarDetalles(String id) //aparece en la ventana de detalles
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select rentas.idRenta, clientes.nombreCompleto, clientes.correo, rentas.fecha_inicial, "
					+ "rentas.fecha_final, rentas.tarifas, rentas.solicitudes, rentas.subtotal, rentas.total, rentas.estatus AS estatus_renta from rentas join clientes on rentas.idCliente = clientes.idCliente where rentas.idRenta = ?");
			pst.setString(1, id);
		
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())	
			{
				rs.getString("idRenta");
				rs.getString("nombreCompleto");
				rs.getString("correo");
				rs.getString("fecha_inicial");
				rs.getString("fecha_final");
				rs.getString("tarifas");
				rs.getString("solicitudes");
				rs.getString("subtotal");
				rs.getString("total");
				rs.getString("estatus_renta");
				
				 if (id != null) {
					
	                    id1.setText(rs.getString("idRenta"));  // Actualiza el JTextField
	                    nombre.setText(rs.getString("nombreCompleto"));
	                    correo.setText(rs.getString("correo"));
	                    f1.setText(rs.getString("fecha_inicial"));
	                    f2.setText(rs.getString("fecha_final"));
	                    tarifasD.setText(rs.getString("tarifas"));
	                    amenidades.setText(rs.getString("solicitudes"));
	                    subtotal.setText(rs.getString("total"));
	                    total.setText(rs.getString("total"));
	                    estatusD.setText(rs.getString("estatus_renta"));
	
				 }
	            } else {
	                System.out.println("no");
	            }
			
			rs.close();
			pst.close();
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public DefaultTableModel tablaRentas() //tabla de sql
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("ID Renta");
		tabla.addColumn("ID Cliente");
		tabla.addColumn("Fecha inicial");
		tabla.addColumn("Fecha final");
		tabla.addColumn("Estatus");
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select idRenta, idCliente, fecha_inicial, fecha_final, estatus from rentas");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Object[] fila = new Object[5]; //ajusta tam x el num de columnas
				fila[0] = rs.getString("idRenta");
				fila[1] = rs.getString("idCliente");
				fila[2] = rs.getString("fecha_inicial");
				fila[3] = rs.getString("fecha_final");
				fila[4] = rs.getString("estatus");
				tabla.addRow(fila);
			}
			
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tabla;
	}
	
	public String buscarCliente(String id) {
	    String idCliente = null;
	    try {
	        Connection cn = Conexion.conectar();
	        PreparedStatement pst = cn.prepareStatement("SELECT idCliente, nombreCompleto FROM clientes WHERE idCliente=?");
	        pst.setString(1, id);
	        
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            idCliente = rs.getString("idCliente"); // Obtén el ID del cliente
	            System.out.println("Cliente encontrado: " + rs.getString("nombreCompleto"));
	        } else {
	            System.out.println("Cliente no encontrado");
	        }
	        
	        cn.close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return idCliente;
	}
	
	public void crear(String idCliente, String fecha1, String fecha2, String tarifas, String solicitudes, int costo)
	{
		String clienteEncontrado = buscarCliente(idCliente);
		
		if (clienteEncontrado == null) {
	        System.out.println("No se puede crear la renta. Cliente no encontrado.");
	        return;
	    }
		
		try 
		{
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("insert into rentas (idRenta, idCliente, fecha_inicial, fecha_final, tarifas, solicitudes, total, estatus)"
					+ "  values(?, ?, ?, ?, ?, ?, ?,?)"); //signos = num columnas
	
			pst.setString(1, "0");
			pst.setString(2, clienteEncontrado);
			pst.setString(3, fecha1); //trim quita espacios del inicio y final del input
			pst.setString(4, fecha2);
			pst.setString(5, tarifas);
			pst.setString(6, solicitudes);
			pst.setInt(7, costo);
			pst.setString(8, "Reservado");
			
			pst.executeUpdate(); // EJECUTAR INSTRUCCIONES ENVIADAS A LA BASE DE DATOS, PONER PARA QUE FUNCIONE.
			
			System.out.println("registro exitoso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> obtenerNombresDeTarifas() {
	    List<String> tarifas = new ArrayList<>();
	    try {
	        Connection cn = Conexion.conectar();
	        PreparedStatement pst = cn.prepareStatement("SELECT nombre FROM tarifas");
	        ResultSet rs = pst.executeQuery();
	        
	        while (rs.next()) {
	            tarifas.add(rs.getString("nombre"));
	        }
	        
	        cn.close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return tarifas;
	}
	
}
