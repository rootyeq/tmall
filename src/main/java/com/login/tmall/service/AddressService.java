package com.login.tmall.service;

import com.login.tmall.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddressList(String address_regionId);
    List<Address> selectRoot();
}
