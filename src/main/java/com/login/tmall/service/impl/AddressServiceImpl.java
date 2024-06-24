package com.login.tmall.service.impl;

import com.login.tmall.dao.AddressMapper;
import com.login.tmall.entity.Address;
import com.login.tmall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<Address> getAddressList(String address_regionId) {
        return addressMapper.getAddressList(address_regionId);
    }

    @Override
    public List<Address> selectRoot() {
        return addressMapper.selectRoot();
    }
}
