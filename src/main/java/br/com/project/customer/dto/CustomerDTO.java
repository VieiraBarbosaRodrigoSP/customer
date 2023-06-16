package br.com.project.customer.dto;

import br.com.project.customer.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opengamma.strata.collect.ArgChecker;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@JsonIgnoreProperties
public class CustomerDTO implements Serializable {
  private Long id;
  private String name;
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Customer toEntity() {
    Customer customer = new Customer();
    BeanUtils.copyProperties(this, customer);
    return customer;
  }

  public static CustomerDTO of(Customer customer) {
    ArgChecker.notNull(customer, "customer");
    CustomerDTO dto = new CustomerDTO();
    BeanUtils.copyProperties(customer, dto);
    return dto;
  }
}