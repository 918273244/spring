package com.cmcc.test;

import org.junit.Test;

import com.cmcc.dao.OrderDao;
import com.cmcc.dao.UserDao;
import com.cmcc.dao.impl.OrderDaoImpl;
import com.cmcc.dao.impl.UserDaoImpl;
import com.cmcc.domain.Order;
import com.cmcc.domain.User;

public class UserDaoTest {
	
	@Test
	public void findTest(){
		UserDao userDao = new UserDaoImpl();
		User user = userDao.find("1");
		System.out.println("user = " + user);
		
	}
	
}
