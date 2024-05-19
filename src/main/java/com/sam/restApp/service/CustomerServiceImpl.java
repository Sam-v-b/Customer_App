package com.sam.restApp.service;

import com.sam.restApp.dto.CustomerDto;
import com.sam.restApp.entity.Customer;
import com.sam.restApp.exception.CustomerException;
import com.sam.restApp.repo.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo repo;

    @Override
    public Integer saveCustomer(CustomerDto customerDto) throws CustomerException {
        Customer custEntity = CustomerDto.prepareEntity(customerDto);
        Customer saveEntity = repo.save(custEntity);
        return saveEntity.getCustId();
    }

    @Override
    public CustomerDto getSpecificCust(Integer custId) throws CustomerException {
        Optional<Customer> optCust = repo.findById(custId);
        Customer actualCustEntity = optCust.orElseThrow(() -> new CustomerException("CUSTOMER_NOT_FOUND"));
        CustomerDto customerDto = CustomerDto.prepareDto(actualCustEntity);
        return customerDto;
    }

    @Override
    public List<CustomerDto> getAll() throws CustomerException {
        Iterable<Customer> customer = repo.findAll();
        List<CustomerDto> cust = new ArrayList<>();
        customer.forEach(cu -> {
            CustomerDto c = new CustomerDto();
            c.prepareDto(cu);
            cust.add(c);
        });
        if (cust.isEmpty())
            throw new CustomerException("CUSTOMER_NOT_FOUND");
        return cust;
    }

    @Override
    public void updateCustomer(Integer custId, String custEmail) throws CustomerException {
        Optional<Customer> optCust = repo.findById(custId);
        Customer actualCustEntity = optCust.orElseThrow(() -> new CustomerException("CUSTOMER_NOT_FOUND"));
        actualCustEntity.setCustEmail(custEmail);
    }

    @Override
    public void deleteCustomer(Integer custId) throws CustomerException {
        Optional<Customer> optCust = repo.findById(custId);
        Customer actualCustEntity = optCust.orElseThrow(() -> new CustomerException("CUSTOMER_NOT_FOUND"));
        repo.deleteById(custId);
    }
}
