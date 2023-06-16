package br.com.project.customer.controller;

import br.com.project.customer.BaseMocks;
import br.com.project.customer.dto.CustomerDTO;
import br.com.project.customer.handler.ResourceNotFoundException;
import br.com.project.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.com.project.customer.constant.CommonConstant.baseMocksConstant.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  CustomerService customerService;

  @BeforeEach
  public void setUp() {
    Mockito.reset(customerService);
  }

  @Test
  public void should_ReturnCustomerById_isOk() throws Exception {
    CustomerDTO customer = BaseMocks.getCustomerDTO(CUSTOMER_NAME_01, CUSTOMER_EMAIL_01, 1l);

    when(customerService.getCustomerById(1L)).thenReturn(customer);

    mockMvc.perform(get("/customers/1").accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.name", is(CUSTOMER_NAME_01)))
        .andExpect(jsonPath("$.email", is(CUSTOMER_EMAIL_01)));
  }

  @Test
  public void should_ReturnCustomerById_isNotFound() throws Exception {
    when(customerService.getCustomerById(1L)).thenThrow(ResourceNotFoundException.class);

    mockMvc.perform(get("/customers/1").accept(APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void should_ReturnGetAllCustomers_isOk() throws Exception {
    List<CustomerDTO> customers = BaseMocks.getAllCustomersDTO();

    when(customerService.getAllCustomers()).thenReturn(customers);

    mockMvc.perform(get("/customers/").accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("length()").value(customers.size()))
        .andExpect(jsonPath("$[0].name", is(CUSTOMER_NAME_01)))
        .andExpect(jsonPath("$[0].email", is(CUSTOMER_EMAIL_01)))
        .andExpect(jsonPath("$[1].name", is(CUSTOMER_NAME_02)))
        .andExpect(jsonPath("$[1].email", is(CUSTOMER_EMAIL_02)))
        .andExpect(jsonPath("$[2].name", is(CUSTOMER_NAME_03)))
        .andExpect(jsonPath("$[2].email", is(CUSTOMER_EMAIL_03)));
  }

  @Test
  public void should_ReturnGetAllCustomers_isNotFound() throws Exception {
    when(customerService.getAllCustomers()).thenThrow(ResourceNotFoundException.class);

    mockMvc.perform(get("/customers/").accept(APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}