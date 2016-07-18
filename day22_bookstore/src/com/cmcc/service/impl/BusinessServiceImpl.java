package com.cmcc.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.cmcc.dao.BookDao;
import com.cmcc.dao.CategoryDao;
import com.cmcc.dao.DbBakDao;
import com.cmcc.dao.OrderDao;
import com.cmcc.dao.UserDao;
import com.cmcc.domain.Book;
import com.cmcc.domain.Cart;
import com.cmcc.domain.CartItem;
import com.cmcc.domain.Category;
import com.cmcc.domain.DbBak;
import com.cmcc.domain.Order;
import com.cmcc.domain.OrderItem;
import com.cmcc.domain.PageBean;
import com.cmcc.domain.QueryInfo;
import com.cmcc.domain.QueryResult;
import com.cmcc.domain.User;
import com.cmcc.factory.DaoFactory;
import com.cmcc.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
	
	private CategoryDao cdao = DaoFactory.getInstance().createDao(CategoryDao.class); 
	private BookDao bdao = DaoFactory.getInstance().createDao(BookDao.class); 
	private UserDao udao = DaoFactory.getInstance().createDao(UserDao.class); 
	private OrderDao odao = DaoFactory.getInstance().createDao(OrderDao.class); 
	private DbBakDao ddao = DaoFactory.getInstance().createDao(DbBakDao.class); 
	
	/****************************************
	 * 
	 * ������صķ���
	 * 
	 **************************************/
	public void addCategory(Category c){
		cdao.add(c);
	}
	
	public Category findCategory(String id){
		return cdao.find(id);
	}
	
	public List getAllCategory(){
		return cdao.getAll();
	}
	
	/****************************************
	 * 
	 * ͼ����صķ���
	 * 
	 **************************************/
	public void addBook(Book book){
		bdao.add(book);
	}
	
	public Book findBook(String id){
		return bdao.find(id);
	}
	
	public PageBean bookPageQuery(QueryInfo info){
		
		QueryResult result = bdao.pageQuery(info.getStartindex(), info.getPagesize(), info.getWhere(), info.getQueryvalue());
		
		PageBean bean = new PageBean();
		bean.setCurrentpage(info.getCurrentpage());
		bean.setList(result.getList());
		bean.setPagesize(info.getPagesize());
		bean.setTotalrecord(result.getTotalrecord());
		
		return bean;
	}
	
	public List getAllBook(){
		return bdao.getAll();
	}
	
	/****************************************
	 * 
	 * �û���صķ���
	 * 
	 **************************************/
	public void addUser(User user){
		udao.add(user);
	}
	
	public User findUser(String username,String password){
		return udao.find(username, password);
		
	}
	
	public User findUser(String id){
		return udao.find(id);
	}
	/****************************************
	 * 
	 * ������صķ���
	 * 
	 **************************************/
	
	//��   �û��Ĺ��ﳵ�����������󣬲����浽����
	public void saveOrder(Cart cart,User user){
		
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		
		
		//����һ�����ϣ����ڱ������ж�����
		Set oitems = new HashSet();
		
		//�ù��ﳵ�еĹ��������ɶ�����
		Set<Map.Entry<String, CartItem>> set = cart.getMap().entrySet();
		for(Map.Entry<String, CartItem> entry : set){
			CartItem citem = entry.getValue();   //�õ�ÿһ��������
			OrderItem oitem = new OrderItem();
			
			//�ù��ﳵ�еĹ��������ɶ�����
			oitem.setBook(citem.getBook());
			oitem.setId(UUID.randomUUID().toString());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			
			
			oitems.add(oitem);
		}
		
		order.setOrderitems(oitems);
		odao.add(order);
	}
	
	public Order findOrder(String id){
		return odao.find(id);
	}
	
	public List getOrderByState(boolean state){
		return odao.getAll(state);
	}
	
	public void updateOrder(String id,boolean state){
		odao.update(id, state);
	}
	
	/****************************************
     * 
     * ���ݿⱸ����صķ���
     * 
     **************************************/
    public void addDbBak(DbBak bak){
        ddao.add(bak);
    }
    
    public List getAllBak(){
        return ddao.getAll();
    }
    
    public DbBak findDbBak(String id){
        return ddao.find(id);
    }
	
}
