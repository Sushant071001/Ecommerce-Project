package com.user.dao;

import java.util.List;

import com.models.Product;
import com.models.User;

public interface UserDao 
{

	void insertUser();
	
    List<User> getAllUsers();
    
    int userLogin(String username,String password);
	
    List<Product> addToCart(Product product);
    
    
}
