package com.login.tmall.controller.fore;

import com.login.tmall.entity.Category;
import com.login.tmall.entity.Product;
import com.login.tmall.entity.ProductImage;
import com.login.tmall.service.CategoryService;
import com.login.tmall.service.ProductImageService;
import com.login.tmall.service.ProductService;

import com.login.tmall.service.ReviewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


@Controller
public class ForeProductDetailsController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private ReviewCountService reviewCountService;

    @RequestMapping("/product/{pid}")
    public String productDetails(HttpServletRequest request, @PathVariable("pid") Integer pid) {
        //根据产品ID查询产品详情
        Product product = productService.getProductById(pid);
        Category category = categoryService.getCategoryById(product.getProduct_category_id());
        product.setProduct_category(category);
        List<ProductImage> singleProductlmageList = productImageService.getProductImageList(product.getProduct_id(),(byte)0);
        product.setSingleProductImageList(singleProductlmageList);

        List<ProductImage> detailProductImageList = productImageService.getProductImageList(product.getProduct_id(), (byte)1);




        List<Product> loveProductList = productService.getProductSpecialList(product.getProduct_category_id());
        for (Product productObj : loveProductList){
            List<ProductImage>loveProductlmageList= productImageService.getProductImageList(productObj.getProduct_id(),(byte)0);
            productObj.setSingleProductImageList(loveProductlmageList);
        }


        product.setProduct_review_count(reviewCountService.getReviewCount(product.getProduct_id()).size());
        product.setReviewList(reviewCountService.getReviewCount(product.getProduct_id()));
        product.setDetailProductImageList(detailProductImageList);

        request.setAttribute("product",product);
        request.setAttribute("loveProductList",loveProductList);
        return "fore/productDetailsPage";
    }
}
