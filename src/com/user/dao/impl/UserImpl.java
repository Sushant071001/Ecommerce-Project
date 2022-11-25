package com.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.products.model.Product;
import com.service.db.connection.DBConnection;
import com.user.dao.UserDao;
import com.user.model.User;

public class UserImpl implements UserDao 
{
	
	Scanner sc = new Scanner(System.in);
	DBConnection db = new DBConnection();	

	@Override
	public void insertUser() 
	{
		User user = new User();
		System.out.print("Enter Your Name : ");
		String uname= sc.next();
		user.setuName(uname);
		
		System.out.print("Enter your Email : ");
		String email= sc.next();
		user.setEmail(email);
		
		System.out.print("Enter your Username : ");
		String username= sc.next();
		user.setUserName(username);
		
		System.out.print("Enter your Password : ");
		String password=sc.next();
		user.setPassword(password);
		String query = "insert into user(uname,email,password,username) values(?,?,?,?)";
		try 
		{
			Connection con = db.getConnection();
	
			PreparedStatement ps = con.prepareStatement(query);
			
//			ps.setInt(1,user.getUserId());
			ps.setString(1,user.getuName());
			ps.setString(2,user.getEmail());
			ps.setString(3,user.getPassword());
			ps.setString(4,user.getUserName());
			int i=ps.executeUpdate();
			if(i==1) 
			{
				System.out.println("You have successfully registered");
			}
			else 
			{
				System.err.println("Something went wrong");
			}

		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}
	
	@Override
	public List<User> getAllUsers() 
	{
		User user = null;
		List<User> userList = new ArrayList<User>();
		String query = "select * from user";
		try 
		{
			Connection con = db.getConnection();
	
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				int id = rs.getInt("user_id");
				String name = rs.getString("uname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String username = rs.getString("username");
				user = new User();
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
	public boolean userLogin(String username, String password) 
	{
		UserImpl userImpl = new UserImpl();
		List<User>list = userImpl.getAllUsers();
		
        for(User user:list) 
        {
        	if(username.equals(user.getUserName())&&password.equals(user.getPassword())) 
        	{
        		return true;
        	}
        }
        return false;
	}

	@Override
	public void addToCart(int pro_id) 
	{
		Product product = null;
		List<Product> addedInCart = new ArrayList<Product>();
		String query = "select * from products where product_id = ?";
		try 
		{
			Connection con = db.getConnection();
	
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, pro_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				int id = rs.getInt("product_id");
				String desc = rs.getString("description");
				int price = rs.getInt("price");
				String name = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				product = new Product();
				product.setProductId(id);
				product.setProductDesc(desc);
				product.setPrice(price);
				product.setName(name);
				product.setQuantity(quantity);
				addedInCart.add(product);
			}
			System.out.println("ITEMS IN YOUR CART");
			for(Product p:addedInCart)
			{
				System.out.println("Product Name : "+p.getName()+" Price : "+p.getPrice());
			}
			System.out.println(addedInCart);
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
//		return proList;
		
		
	}
	
}