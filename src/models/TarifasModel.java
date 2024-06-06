package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

public class TarifasModel {
	
	public DefaultTableModel tablaTarifas() //tabla de sql
	{
		DefaultTableModel tabla = new DefaultTableModel();
		tabla.addColumn("Nombre de la tarifa");
		tabla.addColumn("Fecha inicial");
		tabla.addColumn("Fecha final");
		tabla.addColumn("Precio base");
		
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

}
