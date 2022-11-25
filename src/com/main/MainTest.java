package com.main;

import java.util.List;
import java.util.Scanner;

import com.user.dao.UserDao;
import com.user.dao.impl.UserImpl;
import com.user.model.User;

public class MainTest 
{
	static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) 
	{
		//while(true) {
			
			System.out.println("1 for User Registeration");
			System.out.println("2 for User Login");
			System.out.println("3 for Admin Login");
			//System.out.println("4 for AllData");
			System.out.print("Please enter your choice: ");
			int choice=sc.nextInt();
			if(choice==1) 
			{
				System.out.println("\nUser Registration");
				System.out.println("******************");
				getUserDetails();
			}
			if(choice==2) 
			{
				UserImpl userImpl= new UserImpl();
				System.out.println("\nUSER LOGIN");
				System.out.println("******************");
				System.out.print("Enter Username : ");
				String username=sc.next();
				System.out.print("Enter Password : ");
				String password=sc.next();
				userImpl.userLogin(username,password);
			}
			if(choice==3) 
			{
//				System.out.println("AllData");
//				UserImpl userImpl= new UserImpl();
//				List<User>list=userImpl.getAllUsers();
//				list.forEach(System.out::println);
			}
			if(choice==4) 
			{
				System.out.println("Login");
				UserImpl userImpl= new UserImpl();
				//List<User>list=userImpl.getAllUsers();
				//list.forEach(System.out::println);
				
			}
			
		//}
		
	}

	private static void getUserDetails() 
	{
		UserDao userdao=new UserImpl();
		
		User user= new User();
		
		
		System.out.print("Enter your User Id : ");
		int userid=sc.nextInt();
		user.setUserId(userid);
		
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
		
		userdao.insertUser(user);
		
	}
}
