package com.login.tmall.controller.fore;

import com.login.tmall.entity.Category;
import com.login.tmall.entity.Product;
import com.login.tmall.entity.ProductImage;
import com.login.tmall.service.CategoryService;
import com.login.tmall.service.ProductImageService;
import com.login.tmall.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class ProductListController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private CategoryService categoryService;
//    处理首页搜索按钮
    @RequestMapping("/product")
    public String searchProducts(HttpServletRequest request,@RequestParam("product_name") String product_name){
        //设置searchValue
        String searchValue = product_name;
        request.setAttribute("searchValue",searchValue);
        if(product_name == null || product_name.trim().length() == 0){
            return "redirect:/"; //重定向到首页
        }

        List<Product> productList = productService.DtGetProductListByName(product_name,"product_name",null);
        for(Product product : productList){
                List<ProductImage> singleProductImageList = productImageService.getSingleProductImageList(product.getProduct_id());
                product.setSingleProductImageList(singleProductImageList);
            }

        request.setAttribute("productList",productList);
        List<Category> categoryList=categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);

        return "fore/productListPage";
    }

    @RequestMapping(value = "/product/{index}/{count}", method = RequestMethod.GET)
    public String searchProduct(HttpServletRequest request,
                                @PathVariable("index") Integer index/*页数 */,
                                @PathVariable("count") Integer count/*行数*/,
                                @RequestParam(value = "category id", required = false) Integer category_id/* 分类ID */,
                                @RequestParam(value = "product_name", required = false)  String product_name/* 产品名称 */,
                                @RequestParam(required=false) String orderBy/*排序字段*/,
                                @RequestParam(required =false, defaultValue = "true") Boolean isDesc/* 是否倒序 */) {
        String searchValue = product_name;
        request.setAttribute("searchValue",searchValue);
        if(product_name == null || product_name.trim().length() == 0){
            return "redirect:/"; //重定向到首页
        }

        List<Product> productList = productService.DtGetProductListByName(product_name,orderBy,isDesc);
        for(Product product : productList){
            List<ProductImage> singleProductImageList = productImageService.getSingleProductImageList(product.getProduct_id());
            product.setSingleProductImageList(singleProductImageList);
        }

        request.setAttribute("productList",productList);
        List<Category> categoryList=categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("orderBy",orderBy);
        return "fore/productListPage";

    }
}
