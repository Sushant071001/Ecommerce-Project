package com.service.products.list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.models.Product;
import com.service.db.connection.DBConnection;

public class Products {

	DBConnection db = new DBConnection();

	public List<Product> listOfProduct() {
		Product product = null;
		List<Product> productList = new ArrayList<Product>();
		String query = "select * from products ORDER BY product_name";
		try {
			Connection con = db.getConnection();

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
				productList.add(product);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return productList;
	}

	public void showProducts(List<Product> productList) {
		System.out.println("LIST OF AVAILABLE PRODUCTS");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------");
		System.out.println("PRODUCT ID\tNAME\t\tDESCRIPTION\t\t\t\tPRICE");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------");
		for (Product product : productList) {
			System.out.println(product.getProductId() + "\t\t" + product.getName() + "\t\t"
					+ product.getProductDesc() + "\t\t\t\t" + product.getPrice());
		}

		System.out.println(
				"----------------------------------------------------------------------------------------------------------------");
	}

}
