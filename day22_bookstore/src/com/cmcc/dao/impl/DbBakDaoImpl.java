package com.cmcc.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cmcc.dao.DbBakDao;
import com.cmcc.domain.DbBak;
import com.cmcc.utils.JdbcUtils;


public class DbBakDaoImpl implements DbBakDao {
	
	public void add(DbBak bak){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource_bak());
			String sql = "insert into dbbak(id,filename,baktime,description) values(?,?,?,?)";
			Object params[] = {bak.getId(),bak.getFilename(),bak.getBaktime(),bak.getDescription()};
			runner.update(sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List getAll(){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource_bak());
			String sql = "select * from dbbak order by baktime desc";
			return (List) runner.query(sql, new BeanListHandler(DbBak.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public DbBak find(String id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource_bak());
			String sql = "select * from dbbak where id=?";
			return (DbBak) runner.query(sql,id, new BeanHandler(DbBak.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
