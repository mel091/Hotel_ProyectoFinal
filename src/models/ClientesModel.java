package models;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
	private JPanel panelImg;
	private String idRecuperado;
	
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
				pst.setBlob(10, img);
				//pst.setBinaryStream(10, img);
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
	
public void descargar(String id) {
		
		this.idRecuperado = id;
		System.out.println(idRecuperado);
	    Document documento = new Document();
	    try {
	        String ruta = System.getProperty("user.home") + File.separator + "InformacionCliente";
	        File directoEscritorio = new File(ruta);

	        if (!directoEscritorio.exists()) {
	            System.out.println("Directorio no existente, creando");
	            boolean dirCreated = directoEscritorio.mkdirs();
	            if (!dirCreated) {
	                throw new IOException("No se pudo crear el directorio del escritorio.");
	            }
	        } else {
	            System.out.println("La carpeta Desktop existe.");
	        }

	        String rutaArc = ruta + File.separator + "Cliente.pdf";
	        System.out.println(rutaArc);

	        PdfWriter.getInstance(documento, new FileOutputStream(rutaArc));
	        documento.open();

	        // Configurar tabla con 10 columnas
	        PdfPTable tabla = new PdfPTable(10);
	        tabla.setWidthPercentage(100); // Ancho de la tabla
	        tabla.setSpacingBefore(10f); // Espaciado antes de la tabla
	        tabla.setSpacingAfter(10f); // Espaciado después de la tabla

	        // Configurar anchos de columnas
	        float[] columnWidths = {1f, 2f, 2f, 1.5f, 2.5f, 2.5f, 2f, 1.5f, 2.5f, 1.5f};
	        tabla.setWidths(columnWidths);

	        // Añadir encabezados de columnas
	        String[] columnHeaders = {
	            "ID", "Nombre", "Correo", "Teléfono", "Dirección",
	            "Contacto de emergencia", "Relación con el cliente", "Teléfono de emergencia",
	            "Información adicional", "Estatus"
	        };

	        for (String header : columnHeaders) {
	            PdfPCell cell = new PdfPCell(new Paragraph(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
	            cell.setBackgroundColor(BaseColor.PINK);
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            cell.setPadding(5f);
	            tabla.addCell(cell);
	        }

	        try {
	            Connection cn = Conexion.conectar();
	            PreparedStatement pst = cn.prepareStatement("SELECT * FROM clientes WHERE idCliente = ?");
	            pst.setString(1, id);

	            ResultSet rs = pst.executeQuery();

	            if (rs.next()) {
	                for (int i = 1; i <= 10; i++) {
	                    PdfPCell cell = new PdfPCell(new Paragraph(rs.getString(i), FontFactory.getFont(FontFactory.HELVETICA, 10)));
	                    cell.setBackgroundColor(BaseColor.ORANGE);
	                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                    cell.setPadding(5f);
	                    tabla.addCell(cell);
	                }
	            }
	            documento.add(tabla);

	        } catch (DocumentException | SQLException e2) {
	            e2.printStackTrace();
	        }

	        documento.close();
	        JOptionPane.showMessageDialog(null, "Documento creado");

	    } catch (DocumentException | HeadlessException | IOException e2) {
	        e2.printStackTrace();
	    }
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
	
	public DefaultTableModel tablaHistorialClientes() //tabla de sql
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("ID de la renta");
		tabla.addColumn("Fecha inicial");
		tabla.addColumn("Fecha final");
		tabla.addColumn("Costo final");
		tabla.addColumn("Estatus de la reserva");
		tabla.addColumn("Estado de la reserva");
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select rentas.idRenta, rentas.fecha_inicial, rentas.fecha_final, rentas.costo_final, "
					+ "rentas.estatus AS estatus_reserva, clientes.estatus AS estatus_cliente from rentas join clientes on rentas.idCliente = clientes.idCliente");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Object[] fila = new Object[6]; //ajusta tam x el num de columnas
				 fila[0] = rs.getString("idRenta");
		         fila[1] = rs.getString("fecha_inicial");
		         fila[2] = rs.getString("fecha_final");
		         fila[3] = rs.getString("costo_final");
		         fila[4] = rs.getString("estatus_reserva");
		         fila[5] = rs.getString("estatus_cliente");
				
				tabla.addRow(fila);
			}
			
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tabla;
	}
	
	public void textField(JTextField idC, JTextField nombre, JTextField correo, JTextField telefono, JTextField direccion, JTextField nombreEmergencia, JTextField relacion,
			JTextField telefonoEmergencia, JTextArea infoAdicional, JTextField estatus)
	{
		this.idC = idC;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.nombreEmergencia = nombreEmergencia;
		this.relacion = relacion;
		this.telefonoEmergencia = telefonoEmergencia;
		this.infoAdicional = infoAdicional;
		this.estatus = estatus;
	}
	
	public void panel(JPanel panelImg)
	{
		this.panelImg = panelImg;
	}
	
	public BufferedImage byteToImage(byte[] imgByte) // saca la img de la base de datos y convierte 
	{
		if(imgByte != null && imgByte.length > 0)
		{
			try {
				return ImageIO.read(new ByteArrayInputStream(imgByte));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Clientes mostrarDetalles(String id)
	{
		Clientes cliente = null;
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from clientes where idCliente = ?");
			pst.setString(1, id);
		
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				byte[] imagen = rs.getBytes("img");
				cliente = new Clientes(
				rs.getString("idCliente"),
				rs.getString("nombreCompleto"),
				rs.getString("correo"),
				rs.getString("telefono"),
				rs.getString("direccion"),
				rs.getString("contactoEmergencia"),
				rs.getString("relacionCliente"),
				rs.getString("telefonoEmergencia"),
				rs.getString("infAdicional"),
				rs.getString("estatus"),
				imagen
				);
				
				System.out.println("encontrado: " + cliente.toString());
				 if (id != null && nombre != null) 
				 {
                    idC.setText(rs.getString("idCliente"));  // Actualiza el JTextField
                    nombre.setText(rs.getString("nombreCompleto"));
                    correo.setText(rs.getString("correo"));
                    telefono.setText(rs.getString("telefono"));
                    direccion.setText(rs.getString("direccion"));
                    nombreEmergencia.setText(rs.getString("contactoEmergencia"));
                    relacion.setText(rs.getString("relacionCliente"));
                    telefonoEmergencia.setText(rs.getString("telefonoEmergencia"));
                    infoAdicional.setText(rs.getString("infAdicional"));
                    estatus.setText(rs.getString("estatus"));
                    
                    if(panelImg != null)
                    {
                    	panelImg.removeAll(); // quita todo para colocar img
                    	BufferedImage img = byteToImage(imagen);
                    	
                    	if(img != null)
                    	{
                    		JLabel label = new JLabel(new ImageIcon(img));
                    		panelImg.add(label);
                    	}
                    	
                    	panelImg.revalidate();
                    	panelImg.repaint();
                    }
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
		return cliente;
	}
	
	public InputStream getImagen()
	{
		return imagenSeleccionada;
	}

}
