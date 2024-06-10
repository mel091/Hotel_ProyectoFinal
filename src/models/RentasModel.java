package models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.Blob;

import controllers.HabitacionesController;
import controllers.RentasController;
import views.HabitacionesView;
import views.RentasView;

public class RentasModel 
{
	JTextField id1 = new JTextField();
	JTextField nombre;
	JTextField correo;
	JTextField f1;
	JTextField f2;
	JTextField tarifasD;
	JTextArea amenidades;
	JTextField subtotal;
	JTextField total;
	JTextField estatusD;
	JLabel nombreDetalles; 
	
	String estatus1;
	
	RentasView renta ;
	private JCheckBox mainCheckWifi = new JCheckBox();
	 String obtenido;
	 String amenidades1;
	 String tarifas1;
	
	JTextField idCliente;
	JTextField fecha1 = new JTextField();
	JTextField fecha2 = new JTextField();
	JComboBox tarifas;
	JCheckBox wifi = new JCheckBox("Wi-Fi");
	private JCheckBox mainCheckRest = new JCheckBox();
	private JCheckBox mainCheckRecrea = new JCheckBox();;
	private JCheckBox mainCheckLava = new JCheckBox();;
	JComboBox estatus;
	JTextField sub = new JTextField();
	JTextField total2 = new JTextField();

	public List<String> getTar() 
    {
        List<String> tarNombres = new ArrayList<>();

        try {
        	Connection cn = Conexion.conectar();
        	PreparedStatement pst = cn.prepareStatement("select nombre from tarifas");  	
        	ResultSet rs = pst.executeQuery();
        	
            while (rs.next()) 
            {
            	tarNombres.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarNombres;
    }
	
	public void textField(JTextField id, JTextField nombre, JTextField correo, JTextField f1, JTextField f2, JTextField tarifasD,
			JTextArea amenidades, JTextField subtotal, JTextField total, JTextField estatusD, JLabel nombreRenta)
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
		this.nombreDetalles = nombreRenta;
	}
	
	public void textField2(JTextField idCliente, JTextField fecha1, JTextField fecha2, JComboBox tarifas, JCheckBox wifi, JCheckBox restaurante,
			JCheckBox recreativos, JCheckBox lavanderia, JComboBox estatus, JTextField sub, JTextField total)
	{
		this.idCliente = idCliente;
		this.fecha1 = fecha1;
		this.fecha2 = fecha2;
		this.tarifas = tarifas;
		this.wifi = wifi;
		this.mainCheckRest = restaurante;
		this.mainCheckRecrea = recreativos;
		this.mainCheckLava = lavanderia;
		this.estatus = estatus;
		this.sub = sub;
		this.total2 = total;
	}
	
	public void textField3(JTextField idCliente, JTextField fecha1, JTextField fecha2, String tarifas, 
			String amenidades, String estatus, JTextField sub, JTextField total)
	{
		this.idCliente = idCliente;
		this.fecha1 = fecha1;
		this.fecha2 = fecha2;
		this.tarifas1 = tarifas;
		this.amenidades1 = amenidades;
		this.estatus1 = estatus;
		this.sub = sub;
		this.total2 = total;
	}
	
	public void recuperaDatos(String id) {
		
		Renta ren = mostrarDetalles(id);
	    
		if(tarifas != null)
	    {
	    	System.out.println("maincombo no null");
	    }else if(mainCheckWifi != null)
	    {
	    	System.out.println("check no null");
	    }
	    
	    if (ren != null) {
	    	idCliente.setText(ren.getIdRenta()); //	VOLTEADO 
	    	System.out.println("CLIENTE: " + ren.getIdCliente());
	    	System.out.println("RENTA: " + ren.getIdRenta());
	    	fecha1.setText(ren.getFechaInicial());
	    	fecha2.setText(ren.getFechaFinal());	
	    	sub.setText(ren.getTotal());
	    	total2.setText(ren.getTotal());
	    	
	    	if (wifi == null || mainCheckRest == null || mainCheckRecrea == null || mainCheckLava == null) {
	            System.out.println("Error: JCheckBox no inicializados correctamente");
	            return;
	        }
	    	
	    	 String amenidades = ren.getSolicitudes();
	         System.out.println("solicitudes rentas: " + amenidades);

	         String[] amenidadesArray = amenidades.split("\n");

	         for (String amenidad : amenidadesArray) 
	         {
	             System.out.println("Procesando amenidad: " + amenidad.trim());
	             switch (amenidad.trim()) 
	             {
	                 case "Wi-Fi":
	                     wifi.setSelected(true);
	                     System.out.println("Wi-Fi seleccionado");
	                     break;
	                 case "Restaurante":
	                     mainCheckRest.setSelected(true);
	                     System.out.println("Restaurante seleccionado");
	                     break;
	                 case "Espacios recreativos":
	                     mainCheckRecrea.setSelected(true);
	                     System.out.println("Espacios recreativos seleccionados");
	                     break;
	                 case "Lavandería":
	                     mainCheckLava.setSelected(true);
	                     System.out.println("Lavandería seleccionada");
	                     break;
	                 default:
	                     System.out.println("Amenidad desconocida: " + amenidad);
	                     break;
	             }
	         }
	         obtenido =  obtenerCheckBox();

	    } else {
	        System.out.println("no rentas");
	    }
	}
	
	public void actualizarRentas(String id) 
	{
		String nuevoIdCliente = idCliente.getText();
		String nuevaF1 = fecha1.getText();
		String nuevaF2 = fecha2.getText();
		String nuevaTarifa = tarifas1;
		String nuevaAmenidad = amenidades1;
		String nuevoEstatus = estatus1;
		
		String subtotal = sub.getText();
		int subTotalNum = Integer.parseInt(subtotal);
		
	    String nuevoTotal = total2.getText();
	    int totalNum = Integer.parseInt(nuevoTotal);

	    editar(id, nuevoIdCliente, nuevaF1, nuevaF2, nuevaTarifa, nuevaAmenidad, nuevoEstatus, subTotalNum, totalNum);
	}
	
	//FALTA IMAGEN
	public void editar(String id, String idCliente, String fecha1, String fecha2, String tarifas, 
			String amenidades, String estatus, int sub, int total) 
	{
	    try {
	        Connection cn = Conexion.conectar();
	        PreparedStatement pst = cn.prepareStatement("UPDATE rentas SET idCliente=?, fecha_inicial=?, fecha_final=?, tarifas=?, solicitudes=?,  estatus=?, subtotal =?, total=? WHERE idRenta=?");
	        pst.setString(1, idCliente);
	        pst.setString(2, fecha1);
	        pst.setString(3, fecha2);
	        pst.setString(4, tarifas);
	        pst.setString(5, amenidades);
	        pst.setString(6, estatus);
	        pst.setInt(7, sub);
	        pst.setInt(8, total);
	        
	        pst.setString(9, id);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("rentas actualizado exitosamente.");
	        } else {
	            System.out.println("No se encontró ningún rentas con el ID proporcionado.");
	        }

	        pst.close();
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void obtenerSubtotal(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select total from rentas where idRenta=?");
			pst.setString(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				//rs.getString("total");
				rs.getInt(9);
			}
			
			cn.close();
			pst.close();
			rs.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String obtenerCheckBox()
	{
		StringBuilder seleccion = new StringBuilder();
        if (wifi.isSelected()) {
            seleccion.append(wifi.getText()).append("\n");
        }
        if (mainCheckRest.isSelected()) {
            seleccion.append(mainCheckRest.getText()).append("\n");
        }
        if (mainCheckRecrea.isSelected()) {
            seleccion.append(mainCheckRecrea.getText()).append("\n");
        }
        if (mainCheckLava.isSelected()) {
            seleccion.append(mainCheckLava.getText()).append("\n");
        }
       return seleccion.toString();
	}
	
	public void checkOut(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("UPDATE rentas SET estatus = 'Check out' WHERE idRenta = ?");
			pst.setString(1, id);

			 int rowsAffected = pst.executeUpdate();
		        if (rowsAffected > 0) 
		        {
		            System.out.println("Estatus actualizado correctamente.");
		        } else {
		            System.out.println("No se encontró la renta con el ID proporcionado.");
		        }
		        
		        cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Renta mostrarDetalles(String id) //aparece en la ventana de detalles
	{
		Renta ren = null;
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from rentas where idRenta =?");
			pst.setString(1, id);
		
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())	
			{
				ren = new Renta(
				rs.getString("idRenta"),
				rs.getString("idCliente"),
				rs.getString("idHabitacion"),
				rs.getString("fecha_inicial"),
				rs.getString("fecha_final"),
				rs.getString("tarifas"),
				rs.getString("solicitudes"),
				rs.getString("subtotal"),
				rs.getString("total"),
				rs.getString("estatus")
				);
		
	          } else {
	                System.out.println("no");
	            }
			
			rs.close();
			pst.close();
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ren;
	}
	
	
	
	public void mostrarDetalles2(String id) //aparece en la ventana de detalles
	{
		//Renta ren = null;
		
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
	                    nombreDetalles.setText("Renta " + rs.getString("idRenta"));
	
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
		//return ren;
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
			PreparedStatement pst = cn.prepareStatement("insert into rentas (idRenta, idCliente, fecha_inicial, fecha_final, tarifas, solicitudes, costo_final, estatus)"
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
	
	public void eliminarRenta(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("delete from rentas where idRenta=?");
			pst.setString(1, id);
			
			int cambios = pst.executeUpdate();
			
			if(cambios > 0)
			{
				System.out.println("renta eliminada");
			}
			else
			{
				System.out.println("renta no encontrada");
			}
			
			pst.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String obtenerRenta(String id)
	{
		String total = null;
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select total from rentas where idRenta=?");
			pst.setString(1, id);

			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				total = rs.getString("total");
			}
			
			pst.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	public String obtenerTarifa(String id)
	{
		String total = null;
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select precioBase from tarifas where idTarifa=?");
			pst.setString(1, id);

			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				total = rs.getString("precioBase");
			}
			
			pst.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}

}
