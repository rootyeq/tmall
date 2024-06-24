package com.login.tmall.dao;


import com.login.tmall.entity.Product;
import com.login.tmall.entity.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProductImageMapper {

    List<ProductImage> getSingleProductImageList(Integer product_id);
    List<ProductImage> getProductImageList(@Param("product_id") Integer product_id, @Param("product_image_type") Byte product_image_type);

}
