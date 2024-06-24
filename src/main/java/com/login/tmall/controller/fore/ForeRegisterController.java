package com.login.tmall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.login.tmall.entity.Address;
import com.login.tmall.entity.User;
import com.login.tmall.service.AddressService;
import com.login.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;




@Controller
public class ForeRegisterController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService  userService;
    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String toRegister(HttpServletRequest request) {
        String address_regionId="110000";
        String address_areaId ="110100";//默认北京市;

        List<Address> addressList = addressService.selectRoot();
        request.setAttribute("addressList",addressList);
        List<Address> cityList = addressService.getAddressList(address_regionId);

        request.setAttribute("cityList",cityList);
        List<Address> districtList = addressService.getAddressList(address_areaId);
        request.setAttribute("districtList",districtList);
        return "fore/register";
    }

    @ResponseBody
    @PostMapping(value = "/register/doRegister", produces = "application/json;charset=UTF-8")
    public String register(
            @RequestParam(value = "user_name") String user_name  /*用户名 */,
            @RequestParam(value = "user_nickname") String user_nickname  /*用户昵称 */,
            @RequestParam(value = "user_password") String user_password  /*用户密码*/,
            @RequestParam(value = "user_gender") String user_gender  /*用户性别*/,
            @RequestParam(value = "user_birthday") String user_birthday /*用户生日*/,
            @RequestParam(value = "user_address") String user_address  /*用户所在地 */
    ){
        User user = new User();
        user.setUser_name(user_name);
        user.setUser_nickname(user_nickname);
        user.setUser_password(user_password);
        user.setUser_gender(Byte.parseByte(user_gender));
        user.setUser_birthday(new Date());
        user.setUser_address(new Address(user_address,  null, null));
        user.setUser_homeplace(new Address(user_address,  null, null));
        user.setUser_profile_picture_src("708e48cb-87ab-4d5a-987f-e16dfadla826.jpg");
        JSONObject jsonObject = new JSONObject();
        User ResultUser = userService.getUser(user.getUser_name(),null);
        if(ResultUser != null){
            jsonObject.put("success", false);
            jsonObject.put("mes", "用户名已存在");
            return jsonObject.toJSONString();
        }
        boolean register = userService.register(user);
        if (register){
            jsonObject.put("success", true);
            jsonObject.put("mes", "注册成功");
        }else{
            jsonObject.put("success", false);
            jsonObject.put("mes", "注册失败");
        }

        return jsonObject.toJSONString();



    }




}
