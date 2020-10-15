package com.example.CarRent.repository;

import com.example.CarRent.model.Car;
import com.example.CarRent.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface CarRepository extends JpaRepository<Car, Long> {

}
