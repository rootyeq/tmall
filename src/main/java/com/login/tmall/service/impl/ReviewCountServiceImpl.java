package com.login.tmall.service.impl;

import com.login.tmall.dao.ReviewCountMapper;
import com.login.tmall.entity.Review;
import com.login.tmall.service.ReviewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewCountServiceImpl  implements ReviewCountService {
    @Autowired
    private ReviewCountMapper reviewCountMapper;
    @Override
    public List<Review> getReviewCount(Integer product_id) {
        return reviewCountMapper.getReviewCount(product_id);
    }
}
