package com.main;

import java.awt.Choice;
import java.util.Scanner;

import com.services.AdminService;
import com.services.UserService;
import com.user.dao.impl.UserImpl;

public class MainTest 
{
	
	static Scanner sc= new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		
		UserService userService = new UserService();
		UserImpl userImpl = new UserImpl();
		
		System.out.println("------------------------------");
		System.out.println("  WELCOME TO ONLINE SHOPPING ");
		System.out.println("------------------------------");
		System.out.println("  1 : User Registeration");
		System.out.println("  2 : User Login");
		System.out.println("  3 : Admin Login");
		System.out.println("------------------------------");
		System.out.print("Please enter your choice: ");
		int choice=sc.nextInt();
		
		switch (choice) 
		{
			// USER REGISTRATION
			case 1:
			{
				System.out.println("\n-----------------------------"
						+ " \n   USER REGISTRATION "
						+ "\n-----------------------------");
				userImpl.insertUser();
				userService.getUserLogin();
				break;
			}
		
			// USER LOGIN
			case 2:
			{
				userService.getUserLogin();
				break;
			}
			
			//ADMIN LOGIN
			case 3:
			{
				AdminService adminService = new AdminService();
				adminService.getAdminLogin();
				break;
			}
			
			default:
			{
				System.err.println("Please enter choice from 1 to 3");
				break;
			}
		}
	}

}