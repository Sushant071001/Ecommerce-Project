package com.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.models.Product;
import com.models.User;
import com.service.db.connection.DBConnection;
import com.service.products.list.Products;
import com.user.dao.UserDao;

public class UserImpl implements UserDao {

	Scanner sc = new Scanner(System.in);
	DBConnection db = new DBConnection();
	User user = new User();

	@Override
	public void insertUser() {
		User user = new User();
		System.out.print("Enter Your Name : ");
		String uname = sc.next();
		user.setuName(uname);

		System.out.print("Enter your Email : ");
		String email = sc.next();
		user.setEmail(email);

		System.out.print("Enter your Username : ");
		String username = sc.next();
		user.setUserName(username);

		System.out.print("Enter your Password : ");
		String password = sc.next();
		user.setPassword(password);
		String query = "insert into user(uname,email,password,username) values(?,?,?,?)";
		try {
			Connection con = db.getConnection();

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, user.getuName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getUserName());
			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println("You have successfully registered");
			} else {
				System.err.println("Something went wrong");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<User> getAllUsers() {
		User user = null;
		List<User> userList = new ArrayList<User>();
		String query = "select * from user";
		try {
			Connection con = db.getConnection();

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		} catch (Exception e) {
			System.out.println(e);
		}
		return userList;
	}

	@Override
	public int userLogin(String username, String password) {
		UserImpl userImpl = new UserImpl();
		List<User> list = userImpl.getAllUsers();

		for (User user : list) {
			if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
				return user.getUserId();
			}
		}
		return -1;
	}

	Product product = new Product();
	List<Product> addedInCart = new ArrayList<Product>();

	@Override
	public List<Product> addToCart(Product product) {
		Products prd = new Products();
		List<Product> productList = prd.listOfProduct();
		addedInCart.add(product);
		System.out.println("\n              ITEMS IN YOUR CART");
		System.out.println("--------------------------------------------------------");
		System.out.println("PRODUCT NAME\tPRICE\t\tQUANTITY\tTOTAL");
		System.out.println("--------------------------------------------------------");
		for (Product p : addedInCart) {
			int productTotalPrice =p.getPrice()*p.getQuantity();
			System.out.println(p.getName()+"\t\t"+p.getPrice()+"\t\t"+p.getQuantity()+"\t\t"+productTotalPrice);			
		}
		System.out.println("--------------------------------------------------------");
		return addedInCart;
	}


	private void reduceProductQuantity(List<Product> addedInCart) {
	
	}

	@Override
	public void getPayment() {
		// TODO Auto-generated method stub
		
	}

}