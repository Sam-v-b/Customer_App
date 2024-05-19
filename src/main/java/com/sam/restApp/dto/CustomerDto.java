package com.sam.restApp.dto;

import com.sam.restApp.entity.Customer;
import lombok.Data;

import java.time.LocalDate;
@Data
public class CustomerDto {
    private Integer custId;
    private String custName;
    private String custEmail;
    private LocalDate dateOfBirth;
    public static Customer prepareEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setCustId(customerDto.getCustId());
        customer.setCustName(customerDto.getCustName());
        customer.setCustEmail(customerDto.getCustEmail());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        return customer;
    }
    public static CustomerDto prepareDto(Customer customer){
        CustomerDto cust = new CustomerDto();
        cust.setCustId(customer.getCustId());
        cust.setCustName(customer.getCustName());
        cust.setCustEmail(customer.getCustEmail());
        cust.setDateOfBirth(customer.getDateOfBirth());
        return cust;
    }
}
