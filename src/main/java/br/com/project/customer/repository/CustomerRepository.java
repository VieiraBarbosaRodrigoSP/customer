package br.com.project.customer.repository;

import br.com.project.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  @Query(value = "SELECT u FROM Customer u")
  List<Customer> findAllCustomers();
}