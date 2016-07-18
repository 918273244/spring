package com.cmcc.dao;

import java.util.List;

import com.cmcc.domain.DbBak;


public interface DbBakDao {

	void add(DbBak bak);

	List getAll();

	DbBak find(String id);

}