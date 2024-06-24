package com.login.tmall.controller.admin;

import com.login.tmall.entity.User;
import com.login.tmall.service.UserService;
import com.login.tmall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
@Controller
public class AdminUserController {
    @Autowired
    private UserService userService;
    //转到后台管理-用户页-ajax
    @RequestMapping(value = "admin/user", method = RequestMethod.GET)
    public String goUserManagePage(HttpSession session, HttpServletRequest request, Map<String, Object> map){
        PageUtil pageUtil = new PageUtil(0,10);
        List<User> adminSelect = userService.getAdminSelect(null, null, pageUtil);

        request.setAttribute("userList", adminSelect);
        return "admin/userManagePage";
    }
}
