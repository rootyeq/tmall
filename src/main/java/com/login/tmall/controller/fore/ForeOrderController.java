package com.login.tmall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.login.tmall.entity.*;
import com.login.tmall.service.AddressService;
import com.login.tmall.service.ProductImageService;
import com.login.tmall.service.ProductOrderItemService;
import com.login.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ForeOrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductOrderItemService productOrderItemService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private AddressService addressService;
    //创建订单项-购物车-ajax
    ;

    @ResponseBody
    @RequestMapping(value = "orderItem/create/{product_id}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String createOrderItem(@PathVariable("product_id") Integer product_id,
                                  @RequestParam(required = false, defaultValue = "1") Short product_number,
                                  HttpSession session,
                                  HttpServletRequest request) {
        JSONObject object = new JSONObject();
        User user = (User) session.getAttribute("user");
        if (user == null){
            object.put("success",false);
            object.put("url","/login");
            object.put("msg","请先登录");
            return object.toJSONString();
        }
        ProductOrderItem productOrderItem = new ProductOrderItem();
        productOrderItem.setProductOrderItem_number(product_number);
        Product productById = productService.getProductById(product_id);
        double price = productById.getProduct_sale_price() * product_number;
        productOrderItem.setProductOrderItem_price(price);
        productOrderItem.setProductOrderItem_userMessage("加入成功");
        productOrderItem.setProductOrderItem_product(productById);
        productOrderItem.setProductOrderItem_user(user);
        productOrderItem.setProductOrderItem_order(new ProductOrder());
        boolean result = productOrderItemService.addProductOrderItem(productOrderItem);
        if(result){
            object.put("success",true);
        }else{
            object.put("success",false);
        }
        return object.toJSONString();
    }

    /**
     * 购物车页面
     */
    @GetMapping("/cart")
    public String toCart(HttpSession session ,HttpServletRequest request) {
        //1.去Session获取当前的登录用户
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        List<ProductOrderItem> orderItemList = productOrderItemService.selectByUserId(user.getUser_id());

        if(orderItemList != null && orderItemList.size() > 0){
            for(ProductOrderItem orderItem:orderItemList){
                Product product = productService.getProductById(orderItem.getProductOrderItem_product().getProduct_id());
                List<ProductImage> productImageList = productImageService.getProductImageList(product.getProduct_id(), (byte) 0);
                product.setSingleProductImageList(productImageList);

                orderItem.setProductOrderItem_product(product);
            }
        }
        request.setAttribute("orderItemList",orderItemList);
        Integer orderItemTotal = productOrderItemService.selectCountByUserId(user.getUser_id());
        request.setAttribute("orderItemTotal",orderItemTotal);
        return "fore/productBuyCarPage";
    }
    /**
     * 更新购物车
     */
    @ResponseBody
    @RequestMapping(value = "orderItem", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateOrderItem(@RequestParam String orderItemMap){
        JSONObject jsonobject = new JSONObject();
        JSONObject orderItemString = JSONObject.parseObject(orderItemMap);
        Set<String> orderItemIDSet = orderItemString.keySet();
        for (String orderItemID : orderItemIDSet) {
            Integer orderItemId = Integer.parseInt(orderItemID);
            Short orderItem = orderItemString.getShort(orderItemID);
            ProductOrderItem productOrderItem = productOrderItemService.selectByOrderItemId(orderItemId);
            //基于数据库里面的数据计算商品的单价
            Double price = productOrderItem.getProductOrderItem_price()/productOrderItem.getProductOrderItem_number();
            //设置购物车商品数量
            productOrderItem.setProductOrderItem_number(orderItem);
            productOrderItem.setProductOrderItem_price(price * orderItem);
            boolean update = productOrderItemService.updateProductOrderItem(productOrderItem);
            if(update){
                Object[] orderItemArray = orderItemIDSet.toArray();
                jsonobject.put("success",true);
                jsonobject.put("orderItemIDArray",orderItemArray);
            }else {
                jsonobject.put("success",false);
            }
        }
        return jsonobject.toJSONString();
    }
    //删除订单项-购物车-ajax
    @ResponseBody
    @RequestMapping(value = "orderItem/{orderItem_id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public String deleteOrderItem(@PathVariable("orderItem_id") Integer orderItem_id,
                                  HttpSession session,
                                  HttpServletRequest request) {
        JSONObject jsonobject = new JSONObject();
        boolean productOrderItem = productOrderItemService.deleteOrderItem(orderItem_id);
        if(productOrderItem){
            jsonobject.put("success",true);
        } else {
            jsonobject.put("success",false);
        }



        return jsonobject.toString();
    }
    @RequestMapping(value = "order/create/byCart", method = RequestMethod.GET)
    public String goToOrderConfirmPageByCart(Map<String, Object> map,
                                             HttpSession session, HttpServletRequest request,
                                             @RequestParam(required = false) Integer[] order_item_list) throws UnsupportedEncodingException {

        User user = (User) session.getAttribute("user");
        session.setAttribute("userId",user);
        String address_regionId="110000";
        String address_areaId ="110100";//默认北京市;

        List<Address> addressList = addressService.selectRoot();
        request.setAttribute("addressList",addressList);
        List<Address> cityList = addressService.getAddressList(address_regionId);

        request.setAttribute("cityList",cityList);
        List<Address> districtList = addressService.getAddressList(address_areaId);
        request.setAttribute("districtList",districtList);
        return "fore/productBuyPage";
    }


}
