package com.cmcc.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmcc.dao.OrderDao;
import com.cmcc.domain.Book;
import com.cmcc.domain.Order;
import com.cmcc.domain.OrderItem;
import com.cmcc.domain.User;
import com.cmcc.utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {
	
	public void add(Order o){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "insert into orders(id,ordertime,state,price,user_id) values(?,?,?,?,?)";
			Object params[] = {o.getId(),o.getOrdertime(),o.isState(),o.getPrice(),o.getUser().getId()};
			runner.update(conn,sql, params);
			
			Set<OrderItem> set = o.getOrderitems();
			for(OrderItem item : set){
				sql = "insert into orderitem(id,quantity,price,book_id,order_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(),item.getQuantity(),item.getPrice(),item.getBook().getId(),o.getId()};
				runner.update(conn,sql, params);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Order find(String id){
		
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			
			//找出订单的信息
			String sql = "select * from orders where id=?";
			Order order = (Order) runner.query(conn,sql, id, new BeanHandler(Order.class));
			
			//找出order中的每一个订单项
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = (List<OrderItem>) runner.query(conn,sql, id, new BeanListHandler(OrderItem.class));
			
			//找出每一个订单项代表的书
			for(OrderItem item : list){
				sql = "select b.* from orderitem oi,book b where oi.id=? and b.id=oi.book_id";
				Book book = (Book) runner.query(conn,sql, item.getId(), new BeanHandler(Book.class));
				item.setBook(book);
			}
			order.getOrderitems().addAll(list);
			
			//找出下订单的人
			sql= "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
			User user = (User) runner.query(conn,sql, id, new BeanHandler(User.class));
			
			order.setUser(user);
			
			return order;
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * state:true:已发货
	 * state:false;未发货
	 */
	public List<Order> getAll(boolean state){
		
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from orders where state=?";
			List<Order> list = (List<Order>) runner.query(conn,sql, state, new BeanListHandler(Order.class));
			
			for(Order o : list){
				sql= "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
				User user = (User) runner.query(conn,sql, o.getId(), new BeanHandler(User.class));
				o.setUser(user);
			}
			
			return list;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(String id,boolean state){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "update orders set state=? where id=?";
			Object params[] = {state,id};
			runner.update(conn,sql,params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
