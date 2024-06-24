package com.login.tmall.dao;

import com.login.tmall.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<Address> getAddressList(String address_regionId);
    List<Address> selectRoot();
}
