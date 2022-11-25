package com.service.db.connection;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	Connection con=null;
	public Connection getConnection() 
	{
	final String DRIVER_CLASS= "com.mysql.cj.jdbc.Driver";
	final String URL= "jdbc:mysql://localhost:3306/ECommerce";
	final String USERNAME= "root";
	final String PASSWORD= "Sush@123";
	
	try {
		Class.forName(DRIVER_CLASS);
		con=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
	} catch (Exception e) {
		
		e.printStackTrace();
	   
	}
	
	 return con;
	
	
	}
	

}