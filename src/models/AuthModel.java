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

public class AuthModel 
{
	private JTextField nameField;
	private JTextField usrField;
	private JPasswordField pswField;
	private JPasswordField psw1Field;
	
	public AuthModel()
	{
		
	}
	
	
	public boolean acceder(String usr, String psw)
	{

		if(usr.length() <= 0 || psw.length() <= 0)
		{
			
			return false;
		}
		
		else
		{
			try {
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement(
						"select username, contraseña from administrador where username = ? and contraseña = ?");
				pst.setString(1, usr);
				pst.setString(2, psw);
			
				ResultSet rs = pst.executeQuery();
				
				if(rs.next())
				{
					
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
	}
	
	public void registro(String nombreC, String username, String psw, String psw1)
	{	
		if(nombreC.length() <= 0 || username.length() <= 0 || psw.length() <= 0 || psw1.length() <= 0)
		{
			
			return;
		}
		else if(!psw.equals(psw1))
		{
			
            return; 
		}
		
		else
		{
			try {
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement("insert into administrador (idAdmin, nombreCompleto, username, contraseña) values(?, ?, ?, ?)"); //signos = num columnas
				String password = new String(psw.toString());
				pst.setString(1, "0");
				pst.setString(2, nameField.getText().trim()); //trim quita espacios del inicio y final del input
				pst.setString(3, usrField.getText().trim());
				pst.setString(4, psw.trim());
				
				pst.executeUpdate(); // EJECUTAR INSTRUCCIONES ENVIADAS A LA BASE DE DATOS, PONER PARA QUE FUNCIONE.
				
				
				nameField.setText("");
				usrField.setText("");
				pswField.setText("");
				psw1Field.setText("");
				
				System.out.println("registro exitoso");
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
		

}
