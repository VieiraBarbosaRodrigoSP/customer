package br.com.project.customer.service.impl;

import br.com.project.customer.BaseMocks;
import br.com.project.customer.handler.ResourceNotFoundException;
import br.com.project.customer.model.Customer;
import br.com.project.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static br.com.project.customer.constant.CommonConstant.baseMocksConstant.CUSTOMER_EMAIL_01;
import static br.com.project.customer.constant.CommonConstant.baseMocksConstant.CUSTOMER_NAME_01;
import static br.com.project.customer.constant.CommonConstant.logConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
  @InjectMocks
  private CustomerServiceImpl customerServiceImpl;
  @Mock
  private CustomerRepository customerRepository;

  @Test
  void should_getCustomer_ok() {
    var customer = BaseMocks.getCustomer(CUSTOMER_NAME_01, CUSTOMER_EMAIL_01, 1l);
    when(customerRepository.findById(any())).thenReturn(customer);
    var actual = customerServiceImpl.getCustomerById(1l);
    assertEquals(customer.get().getName(), actual.getName());
  }

  @Test
  void should_getCustomer_ResourceNotFoundException() {
    var customerId = 1l;
    when(customerRepository.findById(any())).thenReturn(Optional.empty());
    var thrown = assertThrows( ResourceNotFoundException.class, () -> customerServiceImpl.getCustomerById(customerId) );
    assertTrue(thrown.getLocalizedMessage().contentEquals(NOT_FOUND_BY_ID+ COMMA_WITH_ID + customerId));
  }

  @Test
  void should_getAllCustomers_isOK() {
    List<Customer> customers = BaseMocks.getAllCustomers();
    when(customerRepository.findAllCustomers()).thenReturn(customers);
    var actual = customerServiceImpl.getAllCustomers();
  }

  @Test
  void should_getAllCustomers_() {
    when(customerRepository.findAllCustomers()).thenReturn(List.of());
    var thrown = assertThrows( ResourceNotFoundException.class, () -> customerServiceImpl.getAllCustomers() );
    assertTrue(thrown.getLocalizedMessage().contentEquals(LIST_IS_EMPTY));
  }
}