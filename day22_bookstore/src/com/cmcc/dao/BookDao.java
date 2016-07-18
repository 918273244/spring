package com.cmcc.dao;

import java.util.List;

import com.cmcc.domain.Book;
import com.cmcc.domain.QueryResult;

public interface BookDao {
    
    public void add(Book b);
    public Book find(String id);
    public QueryResult pageQuery(int startindex,int pagesize,String where,Object param);
    public List getAll();

}
