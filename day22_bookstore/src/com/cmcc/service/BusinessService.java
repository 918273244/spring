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
	 * ������صķ���
	 * 
	 **************************************/
	void addCategory(Category c);

	Category findCategory(String id);

	List getAllCategory();

	/****************************************
	 * 
	 * ͼ����صķ���
	 * 
	 **************************************/
	void addBook(Book book);

	Book findBook(String id);

	PageBean bookPageQuery(QueryInfo info);
	public List getAllBook();

	/****************************************
	 * 
	 * �û���صķ���
	 * 
	 **************************************/
	void addUser(User user);

	User findUser(String username, String password);

	User findUser(String id);

	/****************************************
	 * 
	 * ������صķ���
	 * 
	 **************************************/

	void saveOrder(Cart cart, User user);

	Order findOrder(String id);

	List getOrderByState(boolean state);
	
	public void updateOrder(String id,boolean state);

	
	/****************************************
     * 
     * ���ݿⱸ����صķ���
     * 
     **************************************/
	
	public void addDbBak(DbBak bak);
	
    public List getAllBak();
    
    public DbBak findDbBak(String id);
}