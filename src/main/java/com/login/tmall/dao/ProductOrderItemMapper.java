package com.login.tmall.dao;

import com.login.tmall.entity.ProductOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOrderItemMapper {
    int insertOne(ProductOrderItem productOrderItem);
    List<ProductOrderItem> selectByUserId(@Param("user_id") Integer user_id);
    Integer selectCountByUserId(@Param("user_id") Integer user_id);


    /**
     * 根据订单项ID查询
     */
    ProductOrderItem selectByOrderItemId(@Param("product_order_item_id") Integer product_order_item_id);


    /**
     * 更新
     */
    int updateProductOrderItem(ProductOrderItem productOrderItem);


    Integer deleteOrderItem(Integer productOrderItem_id);

}
