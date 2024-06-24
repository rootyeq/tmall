package com.login.tmall.service;

import com.login.tmall.entity.Review;

import java.util.List;

public interface ReviewCountService {
    List<Review> getReviewCount(Integer product_id);
}
