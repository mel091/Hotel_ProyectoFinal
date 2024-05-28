package models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import controllers.ClientesController;
import controllers.HabitacionesController;
import views.ClientesView;

public class ClientesModel 
{
	JFileChooser fc;
	private InputStream imagenSeleccionada;
	ClientesView view;
	private JTextField idC;
	private JTextField nombre;
	private JTextField correo;
	private JTextField telefono;
	private JTextField direccion;
	private JTextField nombreEmergencia;
	private JTextField relacion;
	private JTextField telefonoEmergencia;
	private JTextArea infoAdicional;
	private JTextField estatus;
	
	
	public ClientesModel()
	{
		
	}
	
	public void crear(String nombreC, String correo, String tel, String dir, String contactoEmergencia, 
			String relacion, String telEmergencia, String inf, InputStream img)
	{
		try 
		{
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("insert into clientes (idCliente, nombreCompleto, correo, telefono, direccion, contactoEmergencia, relacionCliente,"
					+ "telefonoEmergencia, infAdicional, img) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); //signos = num columnas
			
			pst.setString(1, "0");
			pst.setString(2, nombreC.trim()); //trim quita espacios del inicio y final del input
			pst.setString(3, correo);
			pst.setString(4, tel);
			pst.setString(5, dir);
			pst.setString(6, contactoEmergencia);
			pst.setString(7, relacion);
			pst.setString(8, telEmergencia);
			pst.setString(9, inf);
			
			if(img != null)
			{
				//pst.setBlob(10, img);
				pst.setBinaryStream(10, img);
			}
			else
			{
				pst.setNull(10, java.sql.Types.BLOB);
			}
			
			pst.executeUpdate(); // EJECUTAR INSTRUCCIONES ENVIADAS A LA BASE DE DATOS, PONER PARA QUE FUNCIONE.
			
			System.out.println("registro exitoso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void descargar()
	{
		
	}
	
	public void historial()
	{
		
	}
	
	public void subirImg() //preguntar
	{
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png", "jpeg");
		fc.setFileFilter(filter);
		int r = fc.showOpenDialog(null);
		
		if(r == JFileChooser.APPROVE_OPTION)
		{
			try {
				File selectFile = fc.getSelectedFile();
				imagenSeleccionada = new FileInputStream(selectFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void imagen(JButton btn) //mostrar imagen encima del btn
	{ 
		if (imagenSeleccionada != null) {
            try {
                BufferedImage img = ImageIO.read(imagenSeleccionada);
                ImageIcon icon = new ImageIcon(img);
                btn.setIcon(icon);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
        	btn.setIcon(new ImageIcon(getClass().getResource("/contenido/subirCliente.png")));
        }
	}
	
	public DefaultTableModel tablaClientes() //tabla de sql
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("ID");
		tabla.addColumn("Nombre completo");
		tabla.addColumn("Correo electrónico");
		tabla.addColumn("Teléfono");
		tabla.addColumn("Estado de hospedaje");
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select idCliente, nombreCompleto, correo, telefono, estatus from clientes");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Object[] fila = new Object[5]; //ajusta tam x el num de columnas
				fila[0] = rs.getString("idCliente");
				fila[1] = rs.getString("nombreCompleto");
				fila[2] = rs.getString("correo");
				fila[3] = rs.getString("telefono");
				fila[4] = rs.getString("estatus");
				
				tabla.addRow(fila);
			}
			
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tabla;
	}
	
	public InputStream getImagen()
	{
		return imagenSeleccionada;
	}

}
