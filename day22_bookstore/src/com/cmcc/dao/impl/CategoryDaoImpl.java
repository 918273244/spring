package com.cmcc.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmcc.dao.CategoryDao;
import com.cmcc.domain.Category;
import com.cmcc.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
    
    /* (non-Javadoc)
     * @see com.cmcc.dao.impl.CategoryDao#add(com.cmcc.domain.Category)
     */
    @Override
    public void add(Category c) {
        try {
            Connection conn = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "insert into category(id, name, description) values(?, ?, ?)";
            Object params[] = {c.getId(), c.getName(), c.getDescription()};
            runner.update(conn, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.cmcc.dao.impl.CategoryDao#find(java.lang.String)
     */
    @Override
    public Category find(String id) {
        try {
            Connection conn = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select * from category where id=?";
            return (Category) runner.query(conn, sql, id, new BeanListHandler(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.cmcc.dao.impl.CategoryDao#getAll()
     */
    @Override
    public List getAll() {
        try {
            Connection conn = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select * from category";
            return (List) runner.query(conn, sql, new BeanListHandler(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
