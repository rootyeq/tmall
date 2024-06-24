package com.login.tmall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.login.tmall.entity.User;
import com.login.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
public class ForeLoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String toLogin() {
        return "fore/loginPage";
    }
    @RequestMapping("/login/logout")
    public String logout(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
            session.removeAttribute("userId");
        }
        return "redirect:/";
    }



    @ResponseBody
    @PostMapping(value = "login/doLogin", produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, String username, String password) {
        User login = userService.login(username, password);
        JSONObject jsonObject = new JSONObject();
        if (login != null) {
            session.setAttribute("user", login);
            session.setAttribute("userId", login.getUser_id());
            jsonObject.put("success", true);

        } else {
            jsonObject.put("success", false);
        }
        return jsonObject.toJSONString();
    }
}
