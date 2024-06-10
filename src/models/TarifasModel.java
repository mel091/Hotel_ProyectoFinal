package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TarifasModel {

	JTextArea descripcion;
	JTextArea servicios;
	JTextArea condiciones;
	JTextField precio;
	JLabel nombre;
	
	JTextField nombre1;
	JTextArea descripcion1;
	JTextArea servicios1;
	JTextArea condiciones1;
	JTextField precio1;
	
	public void crear(String nombre, String descripcion, String serviciosIncluidos, String condiciones, int precioBase)
	{
		try 
		{
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("insert into tarifas (idTarifa, nombre, descripcion, serviciosIncluidos, condiciones, precioBase)"
					+ "  values(?, ?, ?, ?, ?, ?)"); //signos = num columnas
	
			pst.setString(1, "0");
			pst.setString(2, nombre);
			pst.setString(3, descripcion); //trim quita espacios del inicio y final del input
			pst.setString(4, serviciosIncluidos);
			pst.setString(5, condiciones);
			pst.setInt(6, precioBase);
			
			pst.executeUpdate(); // EJECUTAR INSTRUCCIONES ENVIADAS A LA BASE DE DATOS, PONER PARA QUE FUNCIONE.
			
			System.out.println("registro exitoso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel tablaTarifas() //tabla de sql
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("ID Tarifa");
		tabla.addColumn("Nombre");
		tabla.addColumn("Servicios incluidos");
		tabla.addColumn("Precio base");
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select idTarifa, nombre, serviciosIncluidos, precioBase from tarifas");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Object[] fila = new Object[4]; //ajusta tam x el num de columnas
				fila[0] = rs.getString("idTarifa");
				fila[1] = rs.getString("nombre");
				fila[2] = rs.getString("serviciosIncluidos");
				fila[3] = rs.getString("precioBase");
				tabla.addRow(fila);
			}
			
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tabla;
	}
	
	public void textField(JTextArea descripcion, JTextArea servicios, JTextArea condiciones, JTextField precio, JLabel nombre)
	{
		this.descripcion = descripcion;
		this.servicios = servicios;
		this.condiciones = condiciones;
		this.precio = precio;
		this.nombre = nombre;
	}
	
	public void textField2(JTextField nombre, JTextArea descripcion, JTextArea condiciones, JTextArea servicios, JTextField precio)
	{
		this.nombre1 = nombre;
		this.descripcion1 = descripcion;
		this.servicios1 = servicios;
		this.condiciones1 = condiciones;
		this.precio1 = precio;	
	}
	
	public void editar(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from tarifas where idTarifa = ?");
			pst.setString(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				rs.getString("nombre");
				rs.getString("descripcion");
				rs.getString("condiciones");
				rs.getString("serviciosIncluidos");
				rs.getString("precioBase");
				
				if(nombre1 != null)
				{
					nombre1.setText(rs.getString("nombre"));
					descripcion1.setText(rs.getString("descripcion"));
					servicios1.setText(rs.getString("serviciosIncluidos"));
					condiciones1.setText(rs.getString("condiciones"));
					precio1.setText(rs.getString("precioBase"));		
				}
			}else {
				System.out.println("tipo no encontrado");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editar1(String id, String nombre, String desc, String condicion, String servicio, int precio)
	{
		try {
	        Connection cn = Conexion.conectar();
	        PreparedStatement pst = cn.prepareStatement("UPDATE tarifas SET nombre=?, descripcion=?, condiciones=?, serviciosIncluidos=?, precioBase=? WHERE idTarifa=?");
	       
	        pst.setString(1, nombre);
	        pst.setString(2, desc);
	        pst.setString(3, condicion);
	        pst.setString(4, servicio);
	        pst.setInt(5, precio);
	        
	        pst.setString(6, id);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("tarifa actualizado exitosamente.");
	        } else {
	            System.out.println("No se encontró ningún cliente con el ID proporcionado.");
	        }

	        pst.close();
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void mostrarDetalles(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from tarifas where idTarifa = ?");
			pst.setString(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				descripcion.setText(rs.getString("descripcion"));
				servicios.setText(rs.getString("serviciosIncluidos"));
				condiciones.setText(rs.getString("condiciones"));
				precio.setText(rs.getString("precioBase"));
				nombre.setText(rs.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarTarifa(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("delete from tarifas where idTarifa=?");
			pst.setString(1, id);
			
			int cambios = pst.executeUpdate();
			
			if(cambios > 0)
			{
				System.out.println("tarifa eliminado");
			}
			else
			{
				System.out.println("tarifa no encontrado");
			}
			
			pst.close();
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
