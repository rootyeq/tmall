package com.login.tmall.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.login.tmall.entity.Admin;
import com.login.tmall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/login")
    public String toAdminLogin(){
        return "admin/loginPage";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/login/doLogin",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String toLoginAdmin(HttpSession session, String username, String password)
    {
        JSONObject jsonObject = new JSONObject();
        Admin admin = adminService.login(username, password);
        if(admin != null){
            session.setAttribute("adminId",admin.getAdmin_id());
            jsonObject.put("success",true);
        }else {
            jsonObject.put("success",false);
        }
        return jsonObject.toJSONString();
    }





}
