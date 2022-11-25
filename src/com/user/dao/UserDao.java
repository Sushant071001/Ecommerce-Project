package com.user.dao;

import java.util.List;
import com.user.model.User;

public interface UserDao 
{

	void insertUser();
	
    List<User> getAllUsers();
    
    boolean userLogin(String username,String password);
	
    void addToCart(int pro_id);
    
}
