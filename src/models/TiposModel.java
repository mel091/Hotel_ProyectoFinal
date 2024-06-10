package models;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.Blob;

public class TiposModel 
{
	private JTextField idT;
	private JTextField nombre;
	private JTextArea descripcion;
	private JTextField capacidad;
	private JTextField servicios;
	//private JTextArea tarifas;
	
	List<String> tarifas = new ArrayList<>();
	
	private JLabel nombreDetalles;
	
	private JTextArea listado;

	public List <String> getTarifasName()
	{
		
		String query = "select nombre from tarifas";
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				tarifas.add(rs.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tarifas;
	}

	
	public void crear(String nombre, String descripcion, String capacidad, String servicios, String tarifas)
	{
		try 
		{
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("insert into tipos (idTipo, nombre, descripcion, capacidad, serviciosIncluidos, tarifas)"
					+ " values(?, ?, ?, ?, ?, ?)"); //signos = num columnas
			
			pst.setString(1, "0");
			pst.setString(2, nombre.trim()); //trim quita espacios del inicio y final del input
			pst.setString(3, descripcion);
			pst.setString(4, capacidad);
			pst.setString(5, servicios);
			pst.setString(6, tarifas.trim());
		
			pst.executeUpdate(); // EJECUTAR INSTRUCCIONES ENVIADAS A LA BASE DE DATOS, PONER PARA QUE FUNCIONE.
			
			System.out.println("registro exitoso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editar(String nombre1)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from tipos where nombre = ?");
			pst.setString(1, nombre1);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				rs.getString("nombre");
				rs.getString("descripcion");
				rs.getString("capacidad");
				rs.getString("serviciosIncluidos");
				rs.getString("tarifas");
				
				if(nombre != null)
				{
					nombre.setText(rs.getString("nombre"));
					descripcion.setText(rs.getString("descripcion"));
					capacidad.setText(rs.getString("capacidad"));
					servicios.setText(rs.getString("serviciosIncluidos"));
					
					String tarifasSeleccionadas = obtenerCheckBoxSeleccionados();
					System.out.println("SELECCION: " + tarifasSeleccionadas);
				}
			}else {
				System.out.println("tipo no encontrado");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editar1(String id, String nombre, String desc, String capacidad, String servicios, String tarifas) 
	{
	    try {
	        Connection cn = Conexion.conectar();
	        PreparedStatement pst = cn.prepareStatement("UPDATE tipos SET nombre=?, descripcion=?, capacidad=?, serviciosIncluidos=?, tarifas=? WHERE idTipo=?");
	        pst.setString(1, nombre);
	        pst.setString(2, desc);
	        pst.setString(3, capacidad);
	        pst.setString(4, servicios);
	        pst.setString(5, tarifas);
	        pst.setString(6, id);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("tipo actualizado exitosamente.");
	        } else {
	            System.out.println("No se encontró ningún cliente con el ID proporcionado.");
	        }

	        pst.close();
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public String buscarId(String nom)
	{
		String idEncontrado = null;
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select idTipo from tipos where nombre=?");
	        pst.setString(1, nom);
	        
	        ResultSet rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	        	idEncontrado =rs.getString("idTipo");
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return idEncontrado;
	}
	
	public String obtenerCheckBoxSeleccionados() {
	    StringBuilder seleccion = new StringBuilder();
	    
	    List<String> tarifas = getTarifasName(); // Obtener la lista de nombres de tarifas
	    for (String tarifa : tarifas) {
	        JCheckBox checkBox = new JCheckBox(tarifa);
	        if (checkBox.isSelected()) {
	            seleccion.append(checkBox.getText()).append("\n");
	        }
	    }
	    
	    return seleccion.toString();
	}

	
	public DefaultTableModel tablaTipos() //tabla de sql
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("Tipo de habitación");
		tabla.addColumn("Capacidad");
		tabla.addColumn("Servicios");
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select nombre, capacidad, serviciosIncluidos from tipos");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Object[] fila = new Object[3]; //ajusta tam x el num de columnas
				fila[0] = rs.getString("nombre");
				fila[1] = rs.getString("capacidad");
				fila[2] = rs.getString("serviciosIncluidos");
				
				tabla.addRow(fila);
			}
			
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tabla;
	}
	
	public void textField(JTextField nombre, JTextArea descripcion, JTextField capacidad, JTextField servicios
			)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.servicios = servicios;
		//this.tarifas = tarifas;	
	}
	
	public void listado(JTextArea listado, JLabel nombreDetalles)
	{
		this.listado = listado;
		this.nombreDetalles = nombreDetalles;
	}
	
	public void detalles(String nombre)
	{
		try {
			Connection cn = Conexion.conectar();	
			PreparedStatement pst = cn.prepareStatement("select nombre, descripcion from tipos where nombre =?");
			
			pst.setString(1, nombre);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				rs.getString("nombre");
				rs.getString("descripcion");
				
				if(nombre != null)
				{
					listado.setText(rs.getString("nombre") + " - " + rs.getString("descripcion"));
					nombreDetalles.setText(rs.getString("nombre"));
				}
			}
			else
			{
				System.out.println("nombre no encontrado");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarTipo(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("delete from tipos where nombre=?");
			pst.setString(1, id);
			
			int cambios = pst.executeUpdate();
			
			if(cambios > 0)
			{
				System.out.println("tipo eliminada");
			}
			else
			{
				System.out.println("tipo no encontrada");
			}
			
			pst.close();
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
