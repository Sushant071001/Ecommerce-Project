package com.models;

import java.util.ArrayList;
import java.util.List;

public class UserOrders
{

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Product> getListOfProducts() {
		return listOfProducts;
	}
	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}
	int userId;
	List<Product> listOfProducts = new ArrayList<>();
	
}

