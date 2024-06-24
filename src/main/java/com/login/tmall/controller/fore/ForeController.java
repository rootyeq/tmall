package com.login.tmall.controller.fore;


import com.login.tmall.entity.Category;
import com.login.tmall.entity.Product;
import com.login.tmall.entity.ProductImage;
import com.login.tmall.service.CategoryService;


import com.login.tmall.service.ProductImageService;
import com.login.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ForeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;


    @RequestMapping("/")
    public String index(HttpServletRequest request){
        //获取分类列表
        List<Category> categoryList=categoryService.getCategoryList();
        //获取特价商品
        for(Category category : categoryList){
            List<Product> productSpecialList = productService.getProductSpecialList(category.getCategory_id());


//            for(Product product : productSpecialList){
//                List<ProductImage> singleProductImageList = productImageService.getSingleProductImageList(product.getProduct_id());
//                product.setSingleProductImageList(singleProductImageList);
//            }

            productSpecialList.forEach(product -> {
                List<ProductImage> productImageList =  productImageService.getSingleProductImageList(product.getProduct_id());
                product.setSingleProductImageList(productImageList);
            });

            category.setProductList(productSpecialList);
        }

        List<Product> productList=productService.getSpecialProductList();
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("specialProductList",productList);
        return "fore/homePage";
    }



}
