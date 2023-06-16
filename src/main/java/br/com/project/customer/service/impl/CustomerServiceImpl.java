package br.com.project.customer.service.impl;

import br.com.project.customer.dto.CustomerDTO;
import br.com.project.customer.handler.ResourceNotFoundException;
import br.com.project.customer.model.Customer;
import br.com.project.customer.repository.CustomerRepository;
import br.com.project.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.project.customer.constant.CommonConstant.logConstant.COMMA_WITH_ID;
import static br.com.project.customer.constant.CommonConstant.logConstant.NOT_FOUND_BY_ID;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public CustomerDTO getCustomerById(Long customerId) {
    var customer = customerRepository.findById(customerId);
    if(customer.isEmpty()) throw new ResourceNotFoundException(NOT_FOUND_BY_ID + COMMA_WITH_ID + customerId);
    return CustomerDTO.of(customer.get());
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    List<Customer> customers = customerRepository.findAllCustomers();
    if(customers.isEmpty()) throw new ResourceNotFoundException("Customer list is empty");
    return customers.stream().map(CustomerDTO::of).collect(Collectors.toList());
  }
}