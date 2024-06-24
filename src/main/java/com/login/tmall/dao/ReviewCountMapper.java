package com.login.tmall.dao;

import com.login.tmall.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewCountMapper {
    List<Review> getReviewCount(Integer product_id);
}
