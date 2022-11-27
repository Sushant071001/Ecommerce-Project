package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.models.Product;
import com.models.User;
import com.service.db.connection.DBConnection;
import com.service.products.list.Products;
import com.user.dao.impl.UserImpl;

public class ProductService {

	static UserImpl userImpl = new UserImpl();
	static Scanner sc = new Scanner(System.in);
	DBConnection db = new DBConnection();
	static int totalPrice=0;
	
	public static void productService(int userId) {
		Products products = new Products();
		List<Product> productList = products.listOfProduct();
		products.showProducts(productList);
		String isTrue = "n";
		List<Product> cart = new ArrayList<>();
		while (isTrue.equalsIgnoreCase("N")) {
			System.out.print("\nPlease select PRODUCT_ID : ");
			int pro_id = sc.nextInt();
			List<Integer> productIds = new ArrayList<Integer>();
			productList.stream().forEach(product -> productIds.add(product.getProductId()));
			if (!productIds.contains(pro_id)) {
				System.out.println("no product avaible " + pro_id);
				System.err.println("\nProduct id is invalid");
				productService(userId);
			}
			System.out.print("Enter Quantity : ");
			int productQuantity = sc.nextInt();
			Product product = new Product();
			productList.stream().filter(pr -> pr.getProductId() == pro_id)
			.forEach(pr->{
				product.setName(pr.getName());
				product.setPrice(pr.getPrice());
				product.setProductDesc(pr.getProductDesc());
				product.setProductId(pr.getProductId());
				product.setQuantity(productQuantity);
			});
			cart = userImpl.addToCart(product);
			System.out.print("\nDo you want to checkout Press 'Y' for YES & 'N' for NO : ");
			isTrue = sc.next();

		}
		for (Product product : cart) {
			totalPrice = totalPrice + product.getPrice()*product.getQuantity();
		}
		payment();
		
		OrderService orderService = new OrderService();
		orderService.addOrders(cart, userId);
		
	}

	private static void payment() {
		// TODO Auto-generated method stub
		System.out.println("--------------------------------------");
		System.out.println("      Total Amount:" + totalPrice);
		System.out.println("--------------------------------------");
		System.out.print("Press 1 for payment : ");
		int pay = sc.nextInt();
		if(pay==1)
		{
			System.out.println("** YOU HAVE SUCCESSFULLY PLACED YOUR ORDER **");
		}
		else {
			System.err.println("Please make a payment to place your order.\n");
			payment();
		}
		
	}



	public Product getProductById(int productId) {

		Product product = new Product();
		String query = "select * from products where product_id = ?";
		try {
			Connection con = db.getConnection();

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, productId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("product_id");
				String name = rs.getString("product_name");
				String desc = rs.getString("description");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				
				product.setName(name);
				product.setProductDesc(desc);
				product.setPrice(price);
				product.setQuantity(quantity);
				product.setProductId(id);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return product;

	}
}
