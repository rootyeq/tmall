package com.login.tmall.service.impl;

import com.login.tmall.dao.ProductOrderItemMapper;
import com.login.tmall.entity.ProductOrderItem;
import com.login.tmall.service.ProductOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
public class ProductOrderItemServiceImpl implements ProductOrderItemService {
    @Autowired
    private ProductOrderItemMapper productOrderItemMapper;
    @Override
    public boolean addProductOrderItem(ProductOrderItem productOrderItem) {
        int row = productOrderItemMapper.insertOne(productOrderItem);
        if(row >0){
            return true;
        }
        return false;
    }

    @Override
    public List<ProductOrderItem> selectByUserId(Integer user_id) {
        return productOrderItemMapper.selectByUserId(user_id);
    }

    @Override
    public Integer selectCountByUserId(Integer user_id) {
        return productOrderItemMapper.selectCountByUserId(user_id);
    }

    @Override
    public ProductOrderItem selectByOrderItemId(Integer product_order_item_id) {
        return productOrderItemMapper.selectByOrderItemId(product_order_item_id);
    }

    @Override
    public boolean updateProductOrderItem(ProductOrderItem productOrderItem) {
        int row = productOrderItemMapper.updateProductOrderItem(productOrderItem);
        if(row >0 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOrderItem(Integer productOrderItem_id) {
        int row = productOrderItemMapper.deleteOrderItem(productOrderItem_id);
        if (row >0){
            return true;
        }
        return false;
    }
}
