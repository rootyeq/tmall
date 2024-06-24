package com.login.tmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.login.tmall.dao.ProductImageMapper;
import com.login.tmall.dao.ProductMapper;
import com.login.tmall.entity.Product;
import com.login.tmall.entity.ProductImage;
import com.login.tmall.service.ProductImageService;
import com.login.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageMapper productImageMapper;
    @Override
    public List<ProductImage> getSingleProductImageList(Integer product_id) {
        return productImageMapper.getSingleProductImageList(product_id);
    }

    @Override
    public List<ProductImage> getProductImageList(Integer product_id, Byte product_image_type) {
        return productImageMapper.getProductImageList(product_id, product_image_type);
    }
}



