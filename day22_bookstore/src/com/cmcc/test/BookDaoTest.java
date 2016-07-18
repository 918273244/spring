package com.cmcc.test;

import org.junit.Test;

import com.cmcc.dao.BookDao;
import com.cmcc.dao.impl.BookDaoImpl;
import com.cmcc.domain.Book;
import com.cmcc.domain.Category;
import com.cmcc.utils.JdbcUtils;

public class BookDaoTest {
	
	@Test
	public void testQuery(){
		
		BookDao dao = new BookDaoImpl();
		dao.pageQuery(0, 2, "", 1);
	}
	
	@Test
	public void addBook(){
		
		Book book = new Book();
		book.setAuthor("aaa");
		book.setDescription("fff");
		book.setId("4");
		book.setImage("4");
		book.setName("4444");
		book.setPrice(90);
		book.setCategory(new Category());
		BookDao dao = new BookDaoImpl();
		dao.add(book);
		
		
		JdbcUtils.commitTransaction();
		
	}
	
}
