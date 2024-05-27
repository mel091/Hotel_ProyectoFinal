package models;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.Auth;
import controllers.InicioController;

public class AuthModel 
{
	public Auth registro;
	public InicioController cont;
	
	public AuthModel()
	{
		
	}
	
	
	public boolean acceder(String usr, String psw)
	{
			try {
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement(
						"select username, contraseña from usuarios where username = ? and contraseña = ?");
				pst.setString(1, usr);
				pst.setString(2, psw);
			
				ResultSet rs = pst.executeQuery();
				
				if(rs.next())
				{
					System.out.println("exito");
					cont = new InicioController();
					cont.inicio();
					return true;
				}
				else
				{
					
					return false;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				return false;
			}
		
	}
	
	public void registro(String nombreC, String username, String psw, String psw1)
	{		
			try {
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement("insert into usuarios (idAdmin, nombreCompleto, username, contraseña) values(?, ?, ?, ?)"); //signos = num columnas
				
				pst.setString(1, "0");
				pst.setString(2, nombreC); //trim quita espacios del inicio y final del input
				pst.setString(3, username);
				pst.setString(4, psw);
				
				pst.executeUpdate(); // EJECUTAR INSTRUCCIONES ENVIADAS A LA BASE DE DATOS, PONER PARA QUE FUNCIONE.
				
				

				
				System.out.println("registro exitoso");
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
	}	
		
}
