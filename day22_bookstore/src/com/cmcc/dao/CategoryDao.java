package com.cmcc.dao;

import java.util.List;

import com.cmcc.domain.Category;

public interface CategoryDao {

    void add(Category c);

    Category find(String id);

    List getAll();

}