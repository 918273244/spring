package com.cmcc.dao;

import java.util.List;

import com.cmcc.domain.Order;

public interface OrderDao {

	void add(Order o);

	Order find(String id);

	/*
	 * state:true:已发货
	 * state:false;未发货
	 */
	List<Order> getAll(boolean state);
	
	public void update(String id,boolean state);

}