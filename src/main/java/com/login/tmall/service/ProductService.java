package com.login.tmall.service;

import com.github.pagehelper.PageInfo;
import com.login.tmall.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
  PageInfo<Product> getProductList(int pageNum, int pageSize);
  List<Product> getSpecialProductList();
  List<Product> getProductSpecialList(Integer category_id);

  List<Product> getProductListByName(String product_name);
  List<Product> DtGetProductListByName(@Param("product_name") String product_name, @Param("orderBy") String orderBy, @Param("isDesc") Boolean isDesc);
  Product getProductById(Integer product_id);
}
