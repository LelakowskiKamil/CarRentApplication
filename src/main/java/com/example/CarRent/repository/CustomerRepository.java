package com.example.CarRent.repository;

import com.example.CarRent.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByFirstnameAndLastname(String firstname, String lastname);

    List<Customer> findAllByCity(String city);
}
