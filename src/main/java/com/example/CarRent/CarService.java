package com.example.CarRent;

import com.example.CarRent.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<Car> findByEnginePower(double from, double to);
}
