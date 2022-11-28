package com.services;

import java.util.List;
import java.util.Scanner;

import com.admin.dao.impl.AdminImpl;
import com.models.Product;
import com.models.User;
import com.models.UserOrders;
import com.service.products.list.Products;
import com.user.dao.impl.UserImpl;

public class AdminService 
{
	
	Scanner sc = new Scanner(System.in);
	
	UserImpl userImpl = new UserImpl();
	
	public void getAdminLogin()
	{
		AdminImpl adminimpl = new AdminImpl();
		
		System.out.println("\n------------------ \n   ADMIN LOGIN \n------------------");
		System.out.print("Enter username : ");
		String adminUsername = sc.next();
		
		System.out.print("Enter password : ");
		String adminPass = sc.next();
		
		if (adminimpl.adminLogin(adminUsername, adminPass)) 
		{
			System.out.println("Admin Logged in Successfully");
			while (true) 
			{
				System.out.println("\n1. ALL USER DATA");
				System.out.println("2. All Product details");
				System.out.println("3. Order History");
				System.out.println("4. Log Out");
				
				System.out.print("\nEnter ur choice : ");
				int ch = sc.nextInt();
				
				switch (ch)
				{
					case 1:
					{		
						System.out.println("                         ALL USER DATA ");
						System.out.println("-------------------------------------------------------------------------------------");
						List<User> list = userImpl.getAllUsers();
						System.out.println("USER ID\tNAME\t\tEMAIL\t\t\tPASSWORD\tUSERNAME");
						System.out.println("-------------------------------------------------------------------------------------");
						for (User user : list) {
							System.out.println(user.getUserId()+"\t"+user.getuName()+"\t\t"+user.getEmail()+"\t"+user.getPassword()+"\t"+user.getUserName());
						}
						System.out.println("-------------------------------------------------------------------------------------");
						break;
					}						
					case 2:
					{
						System.out.println("                            ALL PRODUCT DATA :");
						Products products = new Products();
						List<Product> list = products.listOfProduct();
						System.out.println("-------------------------------------------------------------------------------------------------------------");
						System.out.println("PRODUCT ID\t\tNAME\t\tDESCRIPTION\t\tPRICE\t\tQUANTITY");
						System.out.println("-------------------------------------------------------------------------------------------------------------");
						for (Product product : list)
						{
							System.out.println(product.getProductId()+"\t\t\t"+product.getName()+"\t\t"+product.getProductDesc()+"\t\t"+product.getPrice()+"\t\t"+product.getQuantity());
						}
						System.out.println("-------------------------------------------------------------------------------------------------------------");
						break;
					}
					case 3:
					{
						OrderService orderService = new OrderService();
						List<UserOrders> userOrderList=orderService.fetchOrders();
						for (UserOrders userOrders : userOrderList) {
							System.out.println("USER ID : " + userOrders.getUserId());
							System.out.println("--------------------------------------------------------------------------------------------------------");
							System.out.println("USER ID\t\t\tPRODUCT ID\t\tPRODUCT NAME\t\tPRICE\t\t\tQUANTITY");
							System.out.println("--------------------------------------------------------------------------------------------------------");
							for (Product product : userOrders.getListOfProducts()) {
								System.out.println(userOrders.getUserId()+"\t\t\t"+product.getProductId()+"\t\t\t"+product.getName()+"\t\t\t"+product.getPrice()+"\t\t\t"+product.getQuantity());
							}
							System.out.println("--------------------------------------------------------------------------------------------------------");
							System.out.println();
						}
						break;
					}
					
					case 4:
					{
						System.out.println("You have logged out successfully..");
						System.exit(0);
					}
					
					default:
						break;
				}
			}
		}
		else
		{
			System.out.println("y're not an admin");
		}
	}

}
