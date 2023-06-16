package br.com.project.customer;

import br.com.project.customer.dto.CustomerDTO;
import br.com.project.customer.model.Customer;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Optional;

import static br.com.project.customer.constant.CommonConstant.baseMocksConstant.*;
import static br.com.project.customer.constant.CommonConstant.baseMocksConstant.CUSTOMER_EMAIL_03;

public final class BaseMocks {
  public static CustomerDTO getCustomerDTO(String name, String email, Long id) {
    CustomerDTO customer = new CustomerDTO();
    customer.setName(name);
    customer.setEmail(email);
    if(id==null) customer.setId(id);
    return customer;
  }
  public static List<CustomerDTO> getAllCustomersDTO(){
    CustomerDTO customer01 = BaseMocks.getCustomerDTO(CUSTOMER_NAME_01, CUSTOMER_EMAIL_01, 1l);
    CustomerDTO customer02 = BaseMocks.getCustomerDTO(CUSTOMER_NAME_02, CUSTOMER_EMAIL_02, 2l);
    CustomerDTO customer03 = BaseMocks.getCustomerDTO(CUSTOMER_NAME_03, CUSTOMER_EMAIL_03, 3l);

    List<CustomerDTO> customers = ImmutableList.<CustomerDTO>builder()
        .add(customer01)
        .add(customer02)
        .add(customer03)
        .build();

    return customers;
  }
  public static Optional<Customer> getCustomer(String name, String email, Long id){
    Customer customer = new Customer();
    customer.setId(id);
    customer.setName(name);
    customer.setEmail(email);
    return Optional.of(customer);
  }
  public static List<Customer> getAllCustomers(){
    Customer customer01 = BaseMocks.getCustomer(CUSTOMER_NAME_01, CUSTOMER_EMAIL_01, 1l).get();
    Customer customer02 = BaseMocks.getCustomer(CUSTOMER_NAME_02, CUSTOMER_EMAIL_02, 2l).get();
    Customer customer03 = BaseMocks.getCustomer(CUSTOMER_NAME_03, CUSTOMER_EMAIL_03, 3l).get();

    List<Customer> customers = ImmutableList.<Customer>builder()
        .add(customer01)
        .add(customer02)
        .add(customer03)
        .build();

    return customers;
  }
}