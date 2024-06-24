package com.login.tmall.dao;

import com.login.tmall.entity.Category;
import com.login.tmall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //查询商品所有列表
    List<Category> getCategoryList();
    Category getProductListByCategory(Integer category_id);
    Category getCategoryById(Integer category_id);


    /**
     * 增加产品列表
     */
    Integer insertOne(@Param("category") Category category);

    /**
     *产品分页查询
     */
    List<Category> selectPage(@Param("category_name") String category_name, @Param("pageUtil") PageUtil pageUtil);

    /**
     *产品总数查询
     */
    Integer selectTotal(@Param("category_name") String category_name);
    /**
     *产品修改
     */
    Integer updateCategory(@Param("category") Category category);

    /**
     *产品删除
     */
    Integer deleteCategory(@Param("category_id") Integer category_id);
}
