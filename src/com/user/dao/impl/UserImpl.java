package com.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.service.db.connection.DBConnection;
import com.user.dao.UserDao;
import com.user.model.User;

public class UserImpl implements UserDao 
{
	DBConnection db = new DBConnection();	

	@Override
	public void insertUser(User user) 
	{
		String query="insert into user(user_id,uname,email,password,username) values(?,?,?,?,?)";
		try 
	{
	Connection con=db.getConnection();
	
	PreparedStatement ps= con.prepareStatement(query);
	ps.setInt(1,user.getUserId());
	ps.setString(2,user.getuName());
	ps.setString(3,user.getEmail());
	ps.setString(4,user.getPassword());
	ps.setString(5,user.getUserName());
	int i=ps.executeUpdate();
	if(i==1) {
		System.out.println("You have successfully registered");
	}
	else {
		System.err.println("Something went wrong");
	}
	
	} catch (Exception e) 
	{
		System.out.println(e);
	}
	}

	@Override
	public List<User> getAllUsers() {
		User user=null;
		List<User> userList= new ArrayList<User>();
		String query="select * from user";
		try 
	{
	Connection con=db.getConnection();
	
	PreparedStatement ps= con.prepareStatement(query);
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		int id=rs.getInt("user_id");
		String name=rs.getString("uname");
		String email=rs.getString("email");
		String password=rs.getString("password");
		String username= rs.getString("username");
		 user= new User();
		 user.setUserId(id);
		 user.setuName(name);
		 user.setEmail(email);
		 user.setPassword(password);
		 user.setUserName(username);
		userList.add(user);
	}
	} 
		catch (Exception e) 
	{
		System.out.println(e);
	}
		return userList;
	}

	@Override
	public void userLogin(String username,String password) {
		UserImpl userImpl= new UserImpl();
		List<User>list=userImpl.getAllUsers();
		//list.forEach(System.out::println);
        for(User user:list) {
        	//System.out.println(user);
        	if(username.equals(user.getUserName())&&password.equals(user.getPassword())) 
        	{
        		System.out.println("Login Succesfully");
        		break;
        	}
        	else {
        		System.out.println("invalid");
        	 break;
        	}
        	
        }
		
	}
	
}