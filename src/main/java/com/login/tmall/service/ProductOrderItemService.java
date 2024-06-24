package com.login.tmall.service;

import com.login.tmall.entity.ProductOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductOrderItemService {
    boolean addProductOrderItem(ProductOrderItem productOrderItem);
    List<ProductOrderItem> selectByUserId(@Param("user_id") Integer user_id);
    Integer selectCountByUserId(@Param("user_id") Integer user_id);
    ProductOrderItem selectByOrderItemId(@Param("product_order_item_id") Integer product_order_item_id);
    boolean updateProductOrderItem(ProductOrderItem productOrderItem);
    boolean deleteOrderItem(Integer productOrderItem_id);
}
