package com.example.CarRent;

import com.example.CarRent.model.Car;
import com.example.CarRent.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findByEnginePower(double from, double to) {
      return  carRepository.findAll().stream()
                .filter(car -> car.getEnginePower()>from && car.getEnginePower()<to)
                .collect(Collectors.toList());
    }


}
