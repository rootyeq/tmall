package com.login.tmall.controller.admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class AdminHomeController {
    @RequestMapping("/admin")
    public String toAdmin(HttpSession session)
    {
        Object adminId = session.getAttribute("adminId");
        if(adminId == null){
            return "redirect:/admin/login";
        }
        return "admin/homePage";

    }

}
