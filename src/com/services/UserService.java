package com.services;

import java.util.Scanner;

import com.service.db.connection.DBConnection;
import com.user.dao.impl.UserImpl;

public class UserService
{

	DBConnection db = new DBConnection();
	Scanner sc = new Scanner(System.in);
	
	public void getUserLogin()
	{
		UserImpl userImpl= new UserImpl();
		System.out.println("\n----------------------- \n      USER LOGIN \n-----------------------");
		System.out.print("Enter Username : ");
		String username=sc.next();
		System.out.print("Enter Password : ");
		String password=sc.next();
		System.out.println("-----------------------");
		int userId = userImpl.userLogin(username,password);
		if (userId==-1) 
		{
			System.err.println("Check Username or Password");
			getUserLogin();
					
		}
		else 
		{
			System.out.println("LOGGED IN SUCCESS.\n");
			ProductService.productService(userId);
		}
	}
	
}
