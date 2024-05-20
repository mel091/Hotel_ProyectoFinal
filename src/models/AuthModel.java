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
	
	public void setLoginFields(JTextField usrField, JPasswordField pswField)
	{
		this.usrField = usrField;
		this.pswField = pswField;
	}
	
	public void setRegistrationFields(JTextField nameField, JTextField usrField, JPasswordField pswField, JPasswordField psw1Field)
	{
		this.nameField = nameField;
		this.usrField = usrField;
		this.pswField = pswField;
		this.psw1Field = psw1Field;
	}
	
	public boolean acceder(String usr, String psw)
	{

		if(usr.length() <= 0 || psw.length() <= 0)
		{
			usrField.setBorder(BorderFactory.createLineBorder(Color.RED));
            pswField.setBorder(BorderFactory.createLineBorder(Color.RED));
			JOptionPane.showMessageDialog(null, "Campos vacíos");
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
					String username = rs.getString("username");
					String password = rs.getString("contraseña");
					JOptionPane.showMessageDialog(null, "Login exitoso! Administrador: " + username);
					return true;
				}
				else
				{
					usrField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					pswField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					JOptionPane.showMessageDialog(null, "Campos incorrectos o no registrados, intenta de nuevo."); 
					usrField.setText("");
					pswField.setText("");
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
			nameField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			usrField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			pswField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			psw1Field.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			JOptionPane.showMessageDialog(null, "Campos vacíos");
			return;
		}
		else if(!psw.equals(psw1))
		{
			pswField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			psw1Field.setBorder(BorderFactory.createLineBorder(Color.red, 2));
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
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
