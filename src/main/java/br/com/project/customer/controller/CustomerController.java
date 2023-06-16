package br.com.project.customer.controller;

import br.com.project.customer.dto.CustomerDTO;
import br.com.project.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CustomerDTO getCustomerById(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CustomerDTO> getAllCustomers() {
    return customerService.getAllCustomers();
  }
}