package com.login.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.login.tmall.entity.Category;
import com.login.tmall.entity.Property;
import com.login.tmall.service.CategoryService;
import com.login.tmall.service.LastIDService;
import com.login.tmall.service.PropertyService;
import com.login.tmall.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.mysql.cj.conf.PropertyKey.logger;

@Slf4j
@Controller
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private LastIDService lastIDService;
    /*
    * 跳转到产品分类页面
    * */
    @RequestMapping("/admin/category")
    public String toAdminCategory(HttpServletRequest request, Map<String, Object> map){

        PageUtil pageUtil = new PageUtil(0, 10);
        List<Category> listPage = categoryService.getListPage(null, pageUtil);
        map.put("categoryList",listPage);
        List<Category> categoryList = categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);
        Integer categoryCount = categoryService.selectTotal(null);
        request.setAttribute("categoryCount",categoryCount);
        pageUtil.setTotal(categoryCount);
        map.put("pageUtil", pageUtil);
        return "admin/categoryManagePage";
    }
    @RequestMapping(value = "admin/category/{cid}", method = RequestMethod.GET)
    public String goToPage(HttpSession session,HttpServletRequest request,  @PathVariable Integer cid){


        Category category = categoryService.getCategoryById(cid);
        category.setPropertyList(propertyService.getList(new Property().setProperty_category(category), null));
        request.setAttribute("category", category);
        return "admin/include/categoryDetails";
    }



    @ResponseBody
    @RequestMapping(value = "admin/category/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getCategoryBySearch(@RequestParam(required = false) String category_name,
                                      @PathVariable Integer index/* 页数 */,
                                      @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {
        if (category_name != null) {
            category_name = "".equals(category_name) ? null : URLDecoder.decode(category_name, "UTF-8");
        }
        JSONObject object = new JSONObject();

        PageUtil pageUtil = new PageUtil(index, count);
        List<Category> categoryList = categoryService.getListPage(category_name, pageUtil);
        object.put("categoryList", JSONArray.parseArray(JSON.toJSONString(categoryList)));

        Integer categoryCount = categoryService.selectTotal(category_name);
        object.put("categoryCount", categoryCount);

        pageUtil.setTotal(categoryCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);
        return object.toJSONString();
    }

    /**
     * 跳转到添加产品分类页面
     *
     */
    @RequestMapping(value = "admin/category/new", method = RequestMethod.GET)
    public String goToAddPage() {
        return "admin/include/categoryDetails";
    }
/**
 * 添加产品分类类别
 */
@ResponseBody
@RequestMapping(value = "admin/category", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
public String addCategory(@RequestParam String category_name/* 分类名称 */,
                          @RequestParam String category_image_src/* 分类图片路径 */) {
    JSONObject jsonObject = new JSONObject();

    Category category = new Category()
            .setCategory_name(category_name)
            .setCategory_image_src(category_image_src.substring(category_image_src.lastIndexOf("/") + 1));
    log.info("添加分类信息");
    boolean yn = categoryService.add(category);
    if (yn) {
        int category_id = lastIDService.selectLastID();
        log.info("添加成功！,新增分类的ID值为：{}", category_id);
        jsonObject.put("success", true);
        jsonObject.put("category_id", category_id);
    } else {
        jsonObject.put("success", false);
        log.warn("添加失败！事务回滚");
        throw new RuntimeException();
    }

    return jsonObject.toJSONString();
}
/**
 * 图片上传
 */

@ResponseBody
@RequestMapping(value = "admin/uploadCategoryImage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
public String uploadCategoryImage(@RequestParam MultipartFile file, HttpSession session) {
    String originalFileName = file.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
    String fileName = UUID.randomUUID() + extension;
    String filePath = session.getServletContext().getRealPath("/") + "res/images/item/categoryPicture/" + fileName;
    JSONObject jsonObject = new JSONObject();
    try {
        file.transferTo(new File(filePath));
        jsonObject.put("success", true);
        jsonObject.put("fileName", fileName);
    } catch (IOException e) {
        e.printStackTrace();
        jsonObject.put("success", false);
    }

    return jsonObject.toJSONString();
}
    //更新产品类型信息-ajax
    @ResponseBody
    @RequestMapping(value = "admin/category/{category_id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateCategory(@RequestParam String category_name/* 分类名称 */,
                                 @RequestParam String category_image_src/* 分类图片路径 */,
                                 @PathVariable("category_id") Integer category_id/* 分类ID */) {
        JSONObject jsonObject = new JSONObject();

        Category category = new Category()
                .setCategory_id(category_id)
                .setCategory_name(category_name)
                .setCategory_image_src(category_image_src.substring(category_image_src.lastIndexOf("/") + 1));

        boolean yn = categoryService.update(category);
        if (yn) {

            jsonObject.put("success", true);
            jsonObject.put("category_id", category_id);
        } else {
            jsonObject.put("success", false);

            throw new RuntimeException();
        }

        return jsonObject.toJSONString();
    }


}

