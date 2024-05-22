package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
	public static Connection conectar()
	{
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_testeoDB", "freedb_mel2891", "&e$2*JamK*VtMV8");
			return cn;
		} catch (SQLException e) {
			System.out.println("Error en conexión local: " + e);
		}
		return (null);
	}

}
