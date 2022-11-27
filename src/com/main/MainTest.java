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
				System.out.println("\n----------------------- \n   USER REGISTRATION \n-----------------------");
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





















//if(choice==1) 
//{
//	System.out.println("\n----------------------- \n   USER REGISTRATION \n-----------------------");
//	getUserDetails();
//}
//if(choice==2) 
//{
//	UserImpl userImpl= new UserImpl();
//	System.out.println("\n----------------------- \n      USER LOGIN \n-----------------------");
//	System.out.print("Enter Username : ");
//	String username=sc.next();
//	System.out.print("Enter Password : ");
//	String password=sc.next();
//	if (userImpl.userLogin(username,password)) 
//	{
//		
//		System.out.println("Logged in success.");
//		Products products = new Products();
//		products.showProducts();
//
//			
//			System.out.print("Please select PRODUCT_ID : ");
//			int pro_id = sc.nextInt();
//			userImpl.addToCart(pro_id);
//			System.out.println("do u want to buy more products : ");
//			String isTrue = sc.next();
//			if (isTrue.equals("y")) 
//			{
//				pro_id = sc.nextInt();
//				userImpl.addToCart(pro_id);
//			}
//			else {
//
//			}
//		
//		
//	}
//	else {
//		System.err.println("Check Username or Password");
//	}
//}
//if(choice==3) 
//{
//	AdminImpl adminimpl = new AdminImpl();
//	
//	System.out.println("\n------------------ \n   ADMIN LOGIN \n------------------");
//	System.out.print("Enter username : ");
//	String adminUsername = sc.next();
//	
//	System.out.print("Enter password : ");
//	String adminPass = sc.next();
//	
//	if (adminimpl.adminLogin(adminUsername, adminPass)) 
//	{
//		System.out.println("Admin Logged in Successfully");
//		boolean isCheck = true;
//		while (true) 
//		{
//			System.out.println("\n1. ALL USER DATA");
//			System.out.println("2. All Product details");
//			System.out.println("3. Order History");
//			System.out.println("4. Log Out");
//			
//			System.out.print("Enter ur choice : ");
//			int ch = sc.nextInt();
//			
//			switch (ch)
//			{
//			case 1:
//			{		
//				System.out.println("\n-----------------------------------------------------------------");
//				System.out.println("                      All user data ");
//				System.out.println("-----------------------------------------------------------------");
//				UserImpl userImpl = new UserImpl();
//				List<User> list = userImpl.getAllUsers();
//				System.out.println("USER ID\t\tNAME\t\tEMAIL\t\tPASSWORD\t\tUSERNAME");
//				for (User user : list) {
//					System.out.println(user.getUserId()+"\t\t"+user.getuName()+"\t\t"+user.getEmail()+"\t\t"+user.getPassword()+"\t\t\t"+user.getUserName());
//				}
//				
////				list.forEach(System.out::println);
//				break;
//			}
//			
//			case 2:
//			{
//				System.out.println("All product data :");
//				Products products = new Products();
//				List<Product> list = products.displayAllProducts();
//				System.out.println("---------------------------------------------------------------------------------------------------------------------");
//				System.out.println("PRODUCT ID\t\tNAME\t\t\tDESCRIPTION\t\t\tPRICE\t\t\tQUANTITY");
//				System.out.println("---------------------------------------------------------------------------------------------------------------------");
//				for (Product product : list)
//				{
//					System.out.println(product.getProductId()+"\t\t\t"+product.getName()+"\t\t\t"+product.getProductDesc()+"\t\t\t"+product.getPrice()+"\t\t\t"+product.getQuantity());
//				}
//				System.out.println("---------------------------------------------------------------------------------------------------------------------");
//				break;
//			}
//			
//			case 3:
//			{
//				System.out.println("history");
//				break;
//			}
//			
//			case 4:
//			{
//				System.out.println("log out :");
//				isCheck = false;
//			}
//			
//			default:
//				break;
//			}
//		}
//	}
//	else {
//		System.out.println("y're not an admin");
//	}
//	
//	
//	
//}
//if(choice==4) 
//{
//	System.out.println("Login");
//	
//}