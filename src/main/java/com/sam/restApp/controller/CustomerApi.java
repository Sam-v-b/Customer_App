package com.sam.restApp.controller;

import com.sam.restApp.dto.CustomerDto;
import com.sam.restApp.exception.CustomerException;
import com.sam.restApp.service.CustomerService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/custApi")
@CrossOrigin
@Validated
@OpenAPIDefinition(info = @Info(title = "This API is developed for handling "
                    +"Customer related data,It has various endpoints to save/get"
                    +"/update/delete Customer Data",version = "1.0",summary = "This is my endpoint"))
public class CustomerApi {
    @Autowired
    private CustomerService service;
    @Autowired
    private Environment env;

    //http://localhost:8400/custApi/customer
    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDto customerDto) throws CustomerException {
        ResponseEntity<String> response = null;
        Integer custId = service.saveCustomer(customerDto);
        String successMessage = env.getProperty("API_INSERT_SUCCESS") + custId;
        response = new ResponseEntity<String>(successMessage, HttpStatus.CREATED);
        return response;
    }

    //http://localhost:8400/custApi/customers/1
    @GetMapping(value = "/customers/{custId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer custId) throws CustomerException {
        ResponseEntity<CustomerDto> res = null;
        CustomerDto cust = service.getSpecificCust(custId);
        res = new ResponseEntity<>(cust, HttpStatus.OK);
        return res;
    }

    //http://localhost:8400/custApi/customers
    @GetMapping(value = "/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomer() throws CustomerException {
        ResponseEntity<List<CustomerDto>> res = null;
        List<CustomerDto> cust = service.getAll();
        res = new ResponseEntity<>(cust, HttpStatus.OK);
        return res;
    }

    //http://localhost:8400/custApi/customers/1
    @PutMapping(value = "/customer/{custId}/update?custEmail")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer custId, @RequestParam String custEmail) throws CustomerException {
        ResponseEntity<String> res = null;
        service.updateCustomer(custId, custEmail);
        String msg = env.getProperty("API_UPDATE_SUCCESS");

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    //http://localhost:8400/custApi/customers/1
    @DeleteMapping(value = "/customer/{custId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer custId) throws CustomerException {
        service.deleteCustomer(custId);
        String msg = env.getProperty("API_DELETE_SUCCESS");
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
