package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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

import controllers.HabitacionesController;
import views.HabitacionesView;

public class HabitacionesModel {

	public HabitacionesView habitacion;
	
	JFileChooser fc;
	private InputStream imagenSeleccionada;
	private String pathImg;
	public JComboBox<String> tipoResp ;
	
	private JCheckBox mainCheckWifi = new JCheckBox();
	private JCheckBox mainCheckRest = new JCheckBox();
	private JCheckBox mainCheckRecrea = new JCheckBox();;
	private JCheckBox mainCheckLava = new JCheckBox();;
	
	JTextField idH;
	JTextField nombre;
	JTextField tipo;
	JTextField tamaño;
	JTextArea desc;
	JTextArea solicitudes;
	JTextArea tarifas;
	JLabel nombreHab;
	
	String obtenido;
	 String amenidades;
	
	JTextField nombreHabiResp = new JTextField();
	JTextField tamResp = new JTextField();
   
	JTextArea descResp = new JTextArea();
	JCheckBox wifi = new JCheckBox("Wi-Fi");
	JCheckBox restaurante = new JCheckBox("Restaurante");
	JCheckBox recreativos = new JCheckBox("Espacios recreativos");
	JCheckBox lavanderia = new JCheckBox("Lavandería");
	JTextArea tarifasText = new JTextArea();
	
	private JPanel panelImg;
	
	public void textField(JTextField idH, JTextField nombre, JTextField tipo, JTextField tamaño, JTextArea desc, JTextArea solicitudes,
			JTextArea tarifas, JLabel nombreHab, JPanel panel)
	{
		this.idH = idH;
		this.nombre = nombre;
		this.tipo = tipo;
		this.tamaño = tamaño;
		this.desc = desc;
		this.solicitudes = solicitudes;
		this.tarifas = tarifas;
		this.nombreHab = nombreHab;
		this.panelImg = panel;
	}
	
	public void textField2(JTextField nombreHabiResp, JTextField tamResp, JComboBox tipoResp, JTextArea descResp, JCheckBox wifi, JCheckBox restaurante,
			JCheckBox recreativos, JCheckBox lavanderia, JTextArea tarifasText)
	{
		this.nombreHabiResp = nombreHabiResp;
		this.tamResp = tamResp;
		this.tipoResp = tipoResp;
		this.descResp = descResp;
		this.wifi = wifi;
		this.restaurante = restaurante;
		this.recreativos = recreativos;
		this.lavanderia = lavanderia;
		this.tarifasText = tarifasText;
	}
	
	public void textField3(JTextField nombreHabiResp, JTextField tamResp, JComboBox tipoResp, JTextArea descResp, String amenidades,
			 JTextArea tarifasText)
	{
		this.nombreHabiResp = nombreHabiResp;
		this.tamResp = tamResp;
		this.tipoResp = tipoResp;
		this.descResp = descResp;
		this.amenidades = amenidades;
		this.tarifasText = tarifasText;
	}
	
	public void crear(String nombre, String tipo, String tam, String desc, String amenidades, Blob imgB)
	{
		try 
		{
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("insert into habitaciones (idHabitacion, nombre, tipo, tamaño, descripcion, solicitudes,"
					+ " img) values(?, ?, ?, ?, ?, ?, ?)"); //signos = num columnas
	
			pst.setString(1, "0");
			pst.setString(2, nombre.trim()); //trim quita espacios del inicio y final del input
			pst.setString(3, tipo);
			pst.setString(4, tam);
			pst.setString(5, desc);
			pst.setString(6, amenidades);
			
			if(imgB != null)
			{
				pst.setBlob(7, imgB);
			}
			else
			{
				pst.setNull(7, java.sql.Types.BLOB);
			}
			
			pst.executeUpdate(); // EJECUTAR INSTRUCCIONES ENVIADAS A LA BASE DE DATOS, PONER PARA QUE FUNCIONE.
			
			System.out.println("registro exitoso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel tablaHabitaciones() //tabla de sql
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("ID Habitación");
		tabla.addColumn("Nombre");
		tabla.addColumn("Tipo de habitación");
		tabla.addColumn("Tamaño");
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select idHabitacion, nombre, tipo, tamaño from habitaciones");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Object[] fila = new Object[4]; //ajusta tam x el num de columnas
				fila[0] = rs.getString("idHabitacion");
				fila[1] = rs.getString("nombre");
				fila[2] = rs.getString("tipo");
				fila[3] = rs.getString("tamaño");
				tabla.addRow(fila);
			}
			
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tabla;
	}
	

	public DefaultTableModel tablaHabHistorial(String id)
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("ID del Cliente");
		tabla.addColumn("Fecha inicial");
		tabla.addColumn("Fecha final");
		tabla.addColumn("Costo final");
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("SELECT clientes.idCliente, rentas.fecha_inicial, rentas.fecha_final, rentas.total "
	                + "FROM rentas JOIN clientes ON rentas.idCliente = clientes.idCliente WHERE rentas.idHabitacion = ?");
	        pst.setString(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Object[] fila = new Object[4]; //ajusta tam x el num de columnas
				 fila[0] = rs.getString("idCliente");
		         fila[1] = rs.getString("fecha_inicial");
		         fila[2] = rs.getString("fecha_final");
		         fila[3] = rs.getString("total");
		         
		         tabla.addRow(fila);
			}
			
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tabla;
	}
	
	public String mostrarTarifas(String tipo)
	{
		String tarifas = "";
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select tarifas from tipos where nombre=?");
			pst.setString(1, tipo);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				tarifas = rs.getString("tarifas");
			}
			else
			{
				System.out.println("tarifa no encontrada o no registrada");
			}
			
			rs.close();
	        pst.close();
	        cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tarifas;
	}
	
	public Habitacion mostrarDetalles(String id) //aparece en la ventana de detalles
	{
		Habitacion hab = null;
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from habitaciones where idHabitacion = ?");
			pst.setString(1, id);
		
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				
			{
				java.sql.Blob imagenBlob = rs.getBlob("img");
				hab = new Habitacion(
				rs.getString("idHabitacion"),
				rs.getString("nombre"),
				rs.getString("tipo"),
				rs.getString("tamaño"),
				rs.getString("descripcion"),
				rs.getString("solicitudes"),
				imagenBlob
				);
				
				System.out.println("encontrado: " + hab.toString());
				 if (id != null && nombre != null) {
					
	                    idH.setText(rs.getString("idHabitacion"));  // Actualiza el JTextField
	                    nombre.setText(rs.getString("nombre"));
	                    tipo.setText(rs.getString("tipo"));
	                    tamaño.setText(rs.getString("tamaño"));
	                    desc.setText(rs.getString("descripcion"));
	                    solicitudes.setText(rs.getString("solicitudes"));
	                    nombreHab.setText(rs.getString("nombre"));
	                  
	    				String tar = rs.getString("tipo");
	    				String tarif = mostrarTarifas(tar);
	    				tarifas.setText(tarif);          
	    				
	    				 if(imagenBlob != null)
		                    {
		                    	ImageIcon icon = blobToImageIcon(imagenBlob);
		                    	System.out.println("blob no null");
		                    
		                    if(icon != null)
		                    {
		                    	JPanel panel1 = new JPanel() {
		                            @Override
		                            protected void paintComponent(Graphics create) {
		                                super.paintComponent(create);
		                                Graphics2D g2d = (Graphics2D) create;
		                                g2d.drawImage(icon.getImage(), 0, 0, 475, 300, null);
		                            }
		                        };   	
		                    	panel1.setBackground(Color.red);
		                    	panel1.setBounds(0,0,475,300);
		                    	panel1.revalidate();
		                    	panel1.repaint();
		                    	
		                    	panelImg.add(panel1);
		                    	panelImg.revalidate();
		                    	panelImg.repaint();

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
	                System.out.println("no");
	            }
			
			rs.close();
			pst.close();
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hab;
	}
	
	public void descargar(String id) {
	    Document documento = new Document();
	    try {
	        String ruta = System.getProperty("user.home") + File.separator + "Información_Habitación";
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

	        String rutaArc = ruta + File.separator + "Habitación.pdf";
	        System.out.println(rutaArc);

	        PdfWriter.getInstance(documento, new FileOutputStream(rutaArc));
	        documento.open();

	        Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD, 20);
	        Paragraph title = new Paragraph("DATOS DE LA HABITACIÓN", fontTitle);
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
		            "ID habitación", "Nombre", "Tipo", "Tamaño", "Descripción",
		            "Solicitudes"
		        };

	        try {
	            Connection cn = Conexion.conectar();
	            PreparedStatement pst = cn.prepareStatement("SELECT * FROM habitaciones WHERE idHabitacion = ?");
	            pst.setString(1, id);

	            ResultSet rs = pst.executeQuery();

	            if (rs.next()) {
	                
	            	for (int i = 0; i < datos.length ; i++) {
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
	        habitacion = new HabitacionesView();
	        //habitacion.docExito();

	    } catch (DocumentException | HeadlessException | IOException e2) {
	        e2.printStackTrace();
	        habitacion = new HabitacionesView();
	        //view.docError();
	    }
	}

	
	public String subirImg() {
	    fc = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("archivo de imagen", "png", "jpg");
	    fc.setFileFilter(filter);
	    int r = fc.showOpenDialog(null);

	    if (r == JFileChooser.APPROVE_OPTION) {
	        try {
	            File selectFile = fc.getSelectedFile();
	            imagenSeleccionada = new FileInputStream(selectFile);
	            pathImg = selectFile.getAbsolutePath();
	            return pathImg; // ruta del archivo
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return null; // null si no se seleccionó ningún archivo
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
        	btn.setIcon(new ImageIcon(getClass().getResource("/contenido/subirHab.png")));
        }
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
	
	public ArrayList <String> cargarHab(int id)
	{
		ArrayList <String> IDs = new ArrayList<>();
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select idHabitacion from habitaciones order by idHabitacion");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				IDs.add(rs.getString("idHabitacion"));
			}
			
			cn.close();
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return IDs;
	}
	
	public void recuperaDatos(String id) {
		
		Habitacion hab = mostrarDetalles(id);
	   //habitacion = new HabitacionesView();

	    if(tipoResp != null)
	    {
	    	System.out.println("maincombo no null");
	    }else if(mainCheckWifi != null)
	    {
	    	System.out.println("check no null");
	    }
	    
	    if (hab != null) {
	    	nombreHabiResp.setText(hab.getNombre());
	    	tamResp.setText(hab.getTam());
	    	descResp.setText(hab.getDescripcion());    	
	    	
	    	if (wifi == null || mainCheckRest == null || mainCheckRecrea == null || mainCheckLava == null) {
	            System.out.println("Error: JCheckBox no inicializados correctamente");
	            return;
	        }
	    	
	    	 String amenidades = hab.getSolicitudes();
	         System.out.println("solicitudes rec: " + amenidades);

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
	        System.out.println("no hay clientes");
	    }
	}
	
	public void actualizarHabitaciones(String id) {
		String nuevoNombre = nombreHabiResp.getText();
		String nuevoTipo = (String) tipoResp.getSelectedItem();
		String nT = mostrarTarifas(nuevoTipo);
		tarifasText.setText(nT);
		String nuevaTarifa = tarifasText.getText();
	    String nuevoTam = tamResp.getText();
	    String nuevaDesc = descResp.getText();
	    String nuevoCheck = amenidades;
	    
	    Blob nuevaImagen = null;
	    if (pathImg != null && !pathImg.isEmpty()) {
	        nuevaImagen = imageToBlob(pathImg);
	    }
	  
	    editar(id, nuevoNombre, nuevoTipo, nuevaTarifa, nuevoTam, nuevaDesc, nuevoCheck, nuevaImagen);
	}
	
	public void editar(String id, String nuevoNombre, String nuevoTipo, String nuevaTarifa, String nuevoTam, String nuevaDesc, 
	        String nuevoCheck, Blob imagen) {
	    try {
	        Connection cn = Conexion.conectar();
	        PreparedStatement pst = cn.prepareStatement("UPDATE habitaciones SET nombre=?, tipo=?, tamaño=?, descripcion=?, solicitudes=?, img=? WHERE idHabitacion=?");
	        pst.setString(1, nuevoNombre);
	        pst.setString(2, nuevoTipo);
	        pst.setString(3, nuevoTam);
	        pst.setString(4, nuevaDesc);
	        pst.setString(5, nuevoCheck);

	        // Configurar la imagen solo si no es nula
	        if (imagen != null) {
	            pst.setBlob(6, imagen);
	        } else {
	            pst.setNull(6, Types.BLOB); // Configurar como NULL si la imagen es nula
	        }

	        pst.setString(7, id);

	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Habitación actualizada exitosamente.");
	        } else {
	            System.out.println("No se encontró ninguna habitación con el ID proporcionado.");
	        }

	        pst.close();
	        cn.close();
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
	
	public void eliminarHabitacion(String id)
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("delete from habitaciones where idHabitacion=?");
			pst.setString(1, id);
			
			int cambios = pst.executeUpdate();
			
			if(cambios > 0)
			{
				System.out.println("habitacion eliminada");
			}
			else
			{
				System.out.println("habitacion no encontrada");
			}
			
			pst.close();
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  public List<String> getTipos() 
	    {
	        List<String> tiposNombres = new ArrayList<>();

	        try {
	        	Connection cn = Conexion.conectar();
	        	PreparedStatement pst = cn.prepareStatement("select nombre from tipos");  	
	        	ResultSet rs = pst.executeQuery();
	        	
	            while (rs.next()) 
	            {
	                tiposNombres.add(rs.getString("nombre"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return tiposNombres;
	    }
	
	  public static ArrayList<Habitacion> getHabitaciones()//Para añadir el coso a rentas
	    {
	        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

	        try {
	            Connection cn = Conexion.conectar();
	            PreparedStatement pst = cn.prepareStatement("SELECT * FROM habitaciones");
	            ResultSet rs = pst.executeQuery();
	            while(rs.next())

	            {
	            	java.sql.Blob imagenBlob = rs.getBlob("img");
	                Habitacion hab = new Habitacion(
	                rs.getString("idHabitacion"),
	                rs.getString("nombre"),
	                rs.getString("tipo"),
	                rs.getString("tamaño"),
	                rs.getString("descripcion"),
	                rs.getString("solicitudes"),
	                imagenBlob
	                );
	                habitaciones.add(hab);
	                
	                
	            }

	            rs.close();
	            pst.close();
	            cn.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return habitaciones;
	    }
	  
	  public InputStream getImagen()
	{
		return imagenSeleccionada;
	}
}
