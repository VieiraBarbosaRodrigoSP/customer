package br.com.project.customer.service;

import br.com.project.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
  CustomerDTO getCustomerById(Long customerId);
  List<CustomerDTO> getAllCustomers();
}