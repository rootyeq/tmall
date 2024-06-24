package com.login.tmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.login.tmall.dao.ProductMapper;
import com.login.tmall.entity.Product;
import com.login.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getSpecialProductList() {
        return productMapper.getSpecialProductList();
    }

    @Override
    public List<Product> getProductSpecialList(Integer category_id) {
        return productMapper.getProductSpecialList(category_id);
    }




    @Override
    public List<Product> getProductListByName(String product_name) {
        return productMapper.getProductListByName(product_name);
    }

    @Override
    public List<Product> DtGetProductListByName(String product_name, String orderBy, Boolean isDesc) {
        return productMapper.DtGetProductListByName(product_name,orderBy,isDesc);
    }

    @Override
    public Product getProductById(Integer product_id) {
        return productMapper.getProductById(product_id);
    }

    @Override
    public PageInfo<Product> getProductList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.getProductList(); // 假设你有一个通用的查询方法
        return new PageInfo<>(list);
    }

}

