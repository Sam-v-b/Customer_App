package com.sam.restApp.service;

import com.sam.restApp.dto.CustomerDto;
import com.sam.restApp.exception.CustomerException;

import java.util.List;

public interface CustomerService {
    public Integer saveCustomer(CustomerDto customerDto) throws CustomerException;
    public CustomerDto getSpecificCust(Integer custId)throws CustomerException;

    public List<CustomerDto> getAll() throws CustomerException;
    public void updateCustomer(Integer custId, String custEmail)throws CustomerException;
    public  void  deleteCustomer(Integer custId) throws CustomerException;
}
