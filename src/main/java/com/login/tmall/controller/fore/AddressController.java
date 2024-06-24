package com.login.tmall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.login.tmall.entity.Address;
import com.login.tmall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;
    @ResponseBody
    @RequestMapping("/address/{address_areaId}")
    public String getAddressList(@PathVariable("address_areaId") String address_areaId){
        JSONObject jsonObject = new JSONObject();
        List<Address> cityList = addressService.getAddressList( address_areaId);
        if (cityList != null && cityList.size()>0){
            jsonObject.put("success",true);
            jsonObject.put("addressList",cityList);
            Address address=cityList.get(0);
            List<Address> childAddressList = addressService.getAddressList( address.getAddress_areaId());
            jsonObject.put("childAddressList",childAddressList);
        }
        return jsonObject.toJSONString();

    }


}
