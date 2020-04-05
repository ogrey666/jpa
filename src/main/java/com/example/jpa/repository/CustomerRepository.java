package com.example.jpa.repository;

import com.example.jpa.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    // return Collection<Object> if expecting more than 1 row, else only Object
    Collection<Customer> findByLastName(String lastName);
}
