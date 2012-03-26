package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Usuario {
	final String url = "jdbc:mysql://localhost/";
	final String dbName = "lel";
	final String driver = "com.mysql.jdbc.Driver";	
	final String userName = "root";	
	final String password = "yanet";

	private String Nombre_usuario;
	private String Password;
	
	public Usuario(String newname, String newpass){
		Nombre_usuario = newname;
		Password= newpass;
	}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------	
	
	public boolean Find_User(String name){
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement test = conn.createStatement();
			ResultSet rs= test.executeQuery("select name from user where name = '"+name+"' ");
			String a= "";
			if(rs.next()){
			  a= rs.getString(1);	
		    }
			conn.close();
			if (a.compareTo(name.toString())==0)
				return true;
			else return false;
		  }
	    
     	catch (Exception e){
	    	e.printStackTrace();
	    	return false;
	   }
		
	}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------	
	public String Get_User(){return Nombre_usuario;}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------	
	public String Get_Pass(){return Password;}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------	
	
	public String Insertar_User(Usuario user )
	{
		if (Find_User(user.Get_User())== true)
				{
			      return "Usuario duplicado, por favor escoja otro nombre"; 
				}
		else{
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement test = conn.createStatement();
			String a= user.Get_User();
			String query= "INSERT INTO User( name,password) VALUES ('"+a+"', '"+user.Get_Pass()+"')";
			test.executeUpdate(query);
            conn.close();
            return "Usuario insertado con éxito";
            }
		catch (Exception e) {
			e.printStackTrace();
			return "Error";
		   }}
	}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------	
	public void Delete_User(String name )
	{
		Connection conn = null;
		int Id= Find_ID_User(name);
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement test = conn.createStatement();
			
			String query= "DELETE FROM user WHERE id='"+Id+"';";
			test.executeUpdate(query);
            conn.close();
            }
		catch (Exception e) {
			e.printStackTrace();
		   }
	}
	
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
	
	public int Find_ID_User(String name){
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement test = conn.createStatement();
			ResultSet rs= test.executeQuery("select id from user where name = '"+name+"' ");
			int a= 0;
			if(rs.next()){
			  a= rs.getInt(1);	
		    }
			conn.close();
			return a;
		  }
	    
     	catch (Exception e){
	    	e.printStackTrace();
	    	return -2;
	   }
		
	}
	
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
	
	public void Change_Pass(String nameuser, String newpass){
		Connection conn = null;
		try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url + dbName, userName, password);
				Statement test = conn.createStatement();
				ResultSet rs= test.executeQuery("select id from user where name = '"+nameuser+"' ");
				int a= 0;
				if(rs.next()){
				  a= rs.getInt(1);	
		    }
			
				String query= "UPDATE user SET `password`='"+newpass+"' WHERE id='"+a+"' ";
				test.executeUpdate(query);
				conn.close();
			}
	    
     	catch (Exception e){
	    	e.printStackTrace();}
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
