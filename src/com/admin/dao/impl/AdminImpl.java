package com.admin.dao.impl;


import com.admin.dao.AdminDao;

public class AdminImpl implements AdminDao
{

	@Override
	public boolean adminLogin(String username, String passsword) 
	{

		final String ADMIN_USERNAME = "groupy";
		final String ADMIN_PASSWORD = "12345";
		
		if (username.equals(ADMIN_USERNAME) && passsword.equals(ADMIN_PASSWORD)) 
		{
			return true;
		}
		return false;
		
	}
	
	

}
