package com.cmcc.service;

import java.util.List;

import com.cmcc.domain.Book;
import com.cmcc.domain.Cart;
import com.cmcc.domain.Category;
import com.cmcc.domain.DbBak;
import com.cmcc.domain.Order;
import com.cmcc.domain.PageBean;
import com.cmcc.domain.QueryInfo;
import com.cmcc.domain.User;

public interface BusinessService {

	/****************************************
	 * 
	 * 分类相关的服务
	 * 
	 **************************************/
	void addCategory(Category c);

	Category findCategory(String id);

	List getAllCategory();

	/****************************************
	 * 
	 * 图书相关的服务
	 * 
	 **************************************/
	void addBook(Book book);

	Book findBook(String id);

	PageBean bookPageQuery(QueryInfo info);
	public List getAllBook();

	/****************************************
	 * 
	 * 用户相关的服务
	 * 
	 **************************************/
	void addUser(User user);

	User findUser(String username, String password);

	User findUser(String id);

	/****************************************
	 * 
	 * 订单相关的服务
	 * 
	 **************************************/

	void saveOrder(Cart cart, User user);

	Order findOrder(String id);

	List getOrderByState(boolean state);
	
	public void updateOrder(String id,boolean state);

	
	/****************************************
     * 
     * 数据库备份相关的服务
     * 
     **************************************/
	
	public void addDbBak(DbBak bak);
	
    public List getAllBak();
    
    public DbBak findDbBak(String id);
}