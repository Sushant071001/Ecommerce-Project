package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.models.Orders;
import com.models.Product;
import com.models.User;
import com.models.UserOrders;
import com.service.db.connection.DBConnection;

public class OrderService {

	DBConnection db = new DBConnection();

	public void addOrders(List<Product> addedCartList, int userId) {
		String query = "insert into orders(user_id,product_id,quantity) values(?,?,?)";
		try {
			Connection con = db.getConnection();

			PreparedStatement ps = con.prepareStatement(query);
			for (Product product : addedCartList) {
				ps.setInt(1, userId);
				ps.setInt(2, product.getProductId());
				ps.setInt(3, product.getQuantity());
				ps.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<UserOrders> fetchOrders() {
		List<Orders> orders = new ArrayList<Orders>();
		String query = "select * from orders";
		try {
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int orderId = rs.getInt("order_id");
				int userId = rs.getInt("user_id");
				int productId = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				Orders order = new Orders(orderId, userId, productId, quantity);
				orders.add(order);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		List<UserOrders> userOrderList = new ArrayList<>();
		List<Integer> userIdList = new ArrayList<>();
		ProductService productService = new ProductService();
		for (Orders o : orders) {
			UserOrders uo = new UserOrders();
			List<Product> productList = new ArrayList<>();
			Product product = productService.getProductById(o.getProductId());
			product.setQuantity(o.getQuantity());

			if (userOrderList.isEmpty()) {
				uo.setUserId(o.getUserId());
				productList.add(product);
				uo.setListOfProducts(productList);
				userOrderList.add(uo);
				userIdList.add(uo.getUserId());
			} else {
				
				for (int i = 0; i < userOrderList.size(); i++) {
					if (userOrderList.get(i).getUserId() == o.getUserId()) {
						userOrderList.get(i).getListOfProducts().add(product);
					}
					if(!userIdList.contains(o.getUserId())) {
						uo.setUserId(o.getUserId());
						productList.add(product);
						uo.setListOfProducts(productList);
						userOrderList.add(uo);
						userIdList.add(uo.getUserId());
						break;
					}
				}
				
			}
		}
		return userOrderList;
	}

}
