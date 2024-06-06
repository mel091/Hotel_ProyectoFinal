package models;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.Blob;

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
	
	private JTextField nombreResp;
	private JTextField correoResp;
	private JTextField telefonoResp;
	private JTextField direccionResp;
	private JTextField nombreEmergenciaResp;
	private JTextField relacionResp;
	private JTextField telefonoEmergenciaResp;
	private JTextArea infoAdicionalResp;
	private JLabel nombreDetalles;
	
	private String pathImg;
	
	private JPanel panelImg;
	private String idRecuperado;
	
	public ClientesModel()
	{
		
	}
	
	public void crear(String nombreC, String correo, String tel, String dir, String contactoEmergencia, 
			String relacion, String telEmergencia, String inf, Blob img)
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
	    Document documento = new Document();
	    try {
	        String ruta = System.getProperty("user.home") + File.separator + "Información_Cliente";
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

	        Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD, 20);
	        Paragraph title = new Paragraph("DATOS DEL CLIENTE", fontTitle);
	        title.setAlignment(Element.ALIGN_CENTER);
	        documento.add(title);

	        String imagePath = "/contenido/imgWalt.png";
	        Image img = Image.getInstance(ClientesModel.class.getResource(imagePath));
	        title.setAlignment(Element.ALIGN_RIGHT);
	        img.scaleToFit(150, 150);
	        documento.add(img);
	        
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(100);
	        
	        String[] datos = {
		            "ID", "Nombre", "Correo", "Teléfono", "Dirección",
		            "Contacto de emergencia", "Relación con el cliente", "Teléfono de emergencia",
		            "Información adicional", "Estatus"
		        };

	        try {
	            Connection cn = Conexion.conectar();
	            PreparedStatement pst = cn.prepareStatement("SELECT * FROM clientes WHERE idCliente = ?");
	            pst.setString(1, id);

	            ResultSet rs = pst.executeQuery();

	            if (rs.next()) {
	                
	                for (int i = 0; i < datos.length; i++) {
	                    PdfPCell datosR = new PdfPCell(new Phrase(datos[i]));
	                    datosR.setBackgroundColor(new BaseColor(168, 203, 248));
	                    table.addCell(datosR);
	                    PdfPCell dataCell = new PdfPCell(new Phrase(rs.getString(i + 1)));
	                    table.addCell(dataCell);
	                }               
	            }

	            rs.close();
	            pst.close();
	            cn.close();

	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }

	        documento.add(table);

	        documento.close();
	        view = new ClientesView();
	        view.docExito();

	    } catch (DocumentException | HeadlessException | IOException e2) {
	        e2.printStackTrace();
	        view = new ClientesView();
	        view.docError();
	    }
	}

	public String subirImg() //especificaciones al seleccionar imagen en crear
	{
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("archivo de imagen", "png");
		fc.setFileFilter(filter);
		int r = fc.showOpenDialog(null);
		
		if(r == JFileChooser.APPROVE_OPTION)
		{
			try {
				File selectFile = fc.getSelectedFile();
				imagenSeleccionada = new FileInputStream(selectFile);
				pathImg = selectFile.getAbsolutePath();
	            return pathImg;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return null;
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
	
	public DefaultTableModel tablaHistorialClientes(String id) //tabla de sql
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("ID de la renta");
		tabla.addColumn("Fecha inicial");
		tabla.addColumn("Fecha final");
		tabla.addColumn("Costo final");
		tabla.addColumn("Estatus reserva");
		tabla.addColumn("Estado");
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select rentas.idRenta, rentas.fecha_inicial, rentas.fecha_final, rentas.costo_final, "
					+ "rentas.estatus AS estatus_reserva, clientes.estatus AS estatus_cliente from rentas join clientes on rentas.idCliente = clientes.idCliente where clientes.idCliente = ?");
			pst.setString(1, id);
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
			JTextField telefonoEmergencia, JTextArea infoAdicional, JTextField estatus, JLabel nombreCliente, JPanel panel)
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
		this.nombreDetalles = nombreCliente;
		this.panelImg = panel;
	}
	
	public void textField2(JTextField nombreResp, JTextField correoResp, JTextField telResp, JTextField direccionResp, JTextField contactoResp, JTextField relacionResp, JTextField noContactoResp, JTextArea infoAdResp) 
	{
	    this.nombreResp = nombreResp;
	    this.correoResp = correoResp;
	    this.telefonoResp = telResp;
	    this.direccionResp = direccionResp;
	    this.nombreEmergenciaResp = contactoResp;
	    this.relacionResp = relacionResp;
	    this.telefonoEmergenciaResp = noContactoResp;
	    this.infoAdicionalResp = infoAdResp;
	    
	}
	
	
	public Clientes mostrarDetalles(String id) //aparece en la ventana de detalles
	{
		Clientes cliente = null;
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from clientes where idCliente = ?");
			pst.setString(1, id);
		
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				
			{
				java.sql.Blob imagenBlob = rs.getBlob("img");
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
				imagenBlob
				);
				
				System.out.println("encontrado: " + cliente.toString());
				 if (id != null && nombre != null) {
					
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
	                    nombreDetalles.setText(rs.getString("nombreCompleto"));
	                   
	                    if(imagenBlob != null)
	                    {
	                    	ImageIcon icon = blobToImageIcon(imagenBlob);
	                    	System.out.println("blob no null");
	                    
	                    if(icon != null)
	                    {
	                    	JLabel label = new JLabel(icon);
	                    	label.setPreferredSize(new Dimension(472, 291));
	                    	label.setOpaque(true);
	                    	panelImg.removeAll();
	                    	panelImg.add(label);
	                    	panelImg.revalidate();
	                    	panelImg.repaint();
	                    	System.out.println("icon no null");
	                    	Component[] components = panelImg.getComponents();
	                    	for (Component component : components) {
	                    	    System.out.println("Componente en el panel: " + component.getClass().getName());
	                    	}

	                    }else
	                    {
	                    	System.out.println("no se puede cargar la imagen");
	                    }
				 } else
				 {
					 System.out.println("imagen null");
				 }
	                    
				 }
	            } else {
	                System.out.println("no se encontro cliente");
	            }
			
			rs.close();
			pst.close();
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public void recuperaDatos(String id) {
	    Clientes cliente = mostrarDetalles(id);

	    if (cliente != null) {
	        //idC.setText(cliente.getIdCliente());
	    	nombreResp.setText(cliente.getNombreCompleto());
	    	correoResp.setText(cliente.getCorreo());
	        telefonoResp.setText(cliente.getTelefono());
	        direccionResp.setText(cliente.getDireccion());
	        nombreEmergenciaResp.setText(cliente.getContactoEmergencia());
	        relacionResp.setText(cliente.getRelacionCliente());
	        telefonoEmergenciaResp.setText(cliente.getTelefonoEmergencia());
	        infoAdicionalResp.setText(cliente.getInfAdicional());
	        //estatus.setText(cliente.getEstatus());
	    } else {
	        System.out.println("no hay clientes");
	    }
	}
	
	public void actualizarClientes(String id) {
		String nuevoNombre = nombreResp.getText();
	    String nuevoCorreo = correoResp.getText();
	    String nuevoTelefono = telefonoResp.getText();
	    String nuevaDireccion = direccionResp.getText();
	    String nuevoContactoEmergencia = nombreEmergenciaResp.getText();
	    String nuevaRelacionCliente = relacionResp.getText();
	    String nuevoTelefonoEmergencia = telefonoEmergenciaResp.getText();
	    String nuevaInfoAdicional = infoAdicionalResp.getText();
	   // String nuevoEstatus = estatus.getText();

	    // Llamar al método editar para actualizar los datos en la base de datos
	    editar(id, nuevoNombre, nuevoCorreo, nuevoTelefono, nuevaDireccion, nuevoContactoEmergencia, nuevaRelacionCliente, nuevoTelefonoEmergencia, nuevaInfoAdicional);
	}
	
	public void editar(String id, String nombreCompleto, String correo, String telefono, String direccion, String contactoEmergencia, String relacionCliente, String telefonoEmergencia, String infAdicional) {
	    try {
	        Connection cn = Conexion.conectar();
	        PreparedStatement pst = cn.prepareStatement("UPDATE clientes SET nombreCompleto=?, correo=?, telefono=?, direccion=?, contactoEmergencia=?, relacionCliente=?, telefonoEmergencia=?, infAdicional=? WHERE idCliente=?");
	        pst.setString(1, nombreCompleto);
	        pst.setString(2, correo);
	        pst.setString(3, telefono);
	        pst.setString(4, direccion);
	        pst.setString(5, contactoEmergencia);
	        pst.setString(6, relacionCliente);
	        pst.setString(7, telefonoEmergencia);
	        pst.setString(8, infAdicional);
	        pst.setString(9, id);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Cliente actualizado exitosamente.");
	        } else {
	            System.out.println("No se encontró ningún cliente con el ID proporcionado.");
	        }

	        pst.close();
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void eliminarCliente(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("delete from clientes where idCliente=?");
			pst.setString(1, id);
			
			int cambios = pst.executeUpdate();
			
			if(cambios > 0)
			{
				System.out.println("cliente eliminado");
			}
			else
			{
				System.out.println("cliente no encontrado");
			}
			
			pst.close();
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList <String> cargarCliente(int id)
	{
		ArrayList <String> IDs = new ArrayList<>();
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select idCliente from clientes order by idCliente");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				IDs.add(rs.getString("idCliente"));
			}
			
			cn.close();
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return IDs;
	}
	
	public Blob imageToBlob(String path) {
		try {
			BufferedImage buffImage = ImageIO.read(new File(path));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			if (path.endsWith("jpg")) {
				ImageIO.write(buffImage, "jpg", baos);
			} else if (path.endsWith("png")) {
				ImageIO.write(buffImage, "png", baos);
			}
			
			byte[] imageInBytes = baos.toByteArray();
			Blob imageBlob = new Blob(imageInBytes, null);
			return imageBlob;
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public static ImageIcon blobToImageIcon(java.sql.Blob imagenBlob) {
		try {
			byte[] imageBytes = imagenBlob.getBytes(1, (int) imagenBlob.length());
			ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
			BufferedImage buffImage = ImageIO.read(bais);
			 if (buffImage != null) {
	                return new ImageIcon(buffImage); // BufferedImage is a subclass of Image, so this works directly ahaja
	            } else {
	                System.err.println("BufferedImage is null");
	            }
		} catch (SQLException | IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public InputStream getImagen()
	{
		return imagenSeleccionada;
	}

}
