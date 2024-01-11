package com.springsecurity7.repository;

import com.springsecurity7.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    List<Customer> findByEmail(String email);
}
