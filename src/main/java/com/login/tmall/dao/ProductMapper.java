package com.login.tmall.dao;


import com.login.tmall.entity.Category;
import com.login.tmall.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProductMapper {
    List<Product> getProductList();
    List<Product> getSpecialProductList();

    List<Product> getProductSpecialList(Integer category_id);


    List<Product> getProductListByName(String product_name);
    List<Product> DtGetProductListByName(@Param("product_name") String product_name, @Param("orderBy") String orderBy, @Param("isDesc") Boolean isDesc);
    Product getProductById(Integer product_id);

}
