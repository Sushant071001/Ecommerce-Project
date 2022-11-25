package com.user.dao;

import java.util.List;
import com.user.model.User;

public interface UserDao 
{

	void insertUser(User user);
	
    List<User> getAllUsers();
    void userLogin(String username,String password);
	
}
