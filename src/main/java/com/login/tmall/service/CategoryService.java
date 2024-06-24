package com.login.tmall.service;

import com.login.tmall.entity.Category;
import com.login.tmall.util.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService{
    //查询列表
    List<Category> getCategoryList();
    Category getCategoryById(Integer category_id);
    Integer selectTotal(@Param("category_name") String category_name);

    List<Category> getListPage(String category_name, PageUtil pageUtil);

    boolean add(Category category);
    boolean delete(Integer category_id);
    boolean update(Category category);
}
