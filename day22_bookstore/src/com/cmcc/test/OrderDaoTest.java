package com.cmcc.test;

import org.junit.Test;

import com.cmcc.dao.OrderDao;
import com.cmcc.dao.impl.OrderDaoImpl;
import com.cmcc.domain.Order;

public class OrderDaoTest {
	
	@Test
	public void findTest(){
		
		OrderDao dao = new OrderDaoImpl();
		Order o = dao.find("1");
		System.out.println(o);
	}
	
}
