package com.example.CarRent.controller;


import com.example.CarRent.CarService;
import com.example.CarRent.exception.CarNotFoundException;
import com.example.CarRent.model.Car;
import com.example.CarRent.model.assembler.CarAssembler;
import com.example.CarRent.repository.CarRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {
    private final CarRepository repository;
    private final CarAssembler assembler;
    private final CarService service;

    public CarController(CarRepository repository, CarAssembler assembler, CarService service) {
        this.repository = repository;
        this.assembler = assembler;
        this.service = service;
    }

    //Agregate root
    @GetMapping("/cars")
    public CollectionModel<EntityModel<Car>> all() {
        List<EntityModel<Car>> cars = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(cars,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).all()).withSelfRel());
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car newCar) {
        EntityModel<Car> entityModel = assembler.toModel(repository.save(newCar));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //Single item
    @GetMapping("/cars/{id}")
    public EntityModel<Car> one(@PathVariable Long id) {
        Car car = repository.findById(id)
              .orElseThrow(() -> new CarNotFoundException(id));

        return assembler.toModel(car);
    }



    @GetMapping("/cars/typecar")
    public CollectionModel<EntityModel<Car>> getByTypeCar(@RequestParam String typecar) {
        List<EntityModel<Car>> cars = repository.findByTypeCar(typecar)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(cars,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).getByTypeCar(typecar)).withSelfRel());
    }

    @GetMapping("/cars/brand/{brand}")
    public CollectionModel<EntityModel<Car>> getByBrand(@PathVariable String brand) {
        List<EntityModel<Car>> cars = repository.findByBrand(brand)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(cars,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).getByBrand(brand)).withSelfRel());
    }

    @GetMapping("/cars/enginepower")
    public CollectionModel<EntityModel<Car>> getByEnginePower(@RequestParam double from, @RequestParam double to) {
        List<EntityModel<Car>> cars = service.findByEnginePower(from,to)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cars,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).getByEnginePower(from,to)).withSelfRel());
    }




    @PutMapping("/cars/{id}")
    public ResponseEntity<?> replaceCar(@RequestBody Car newCar, @PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Car updatedCar = repository.findById(id)
                .map(car -> {
                    car.setTypeCar(newCar.getTypeCar());
                    car.setBrand(newCar.getBrand());
                    car.setModel(newCar.getModel());
                    car.setEngine(newCar.getEngine());
                    car.setGeneration(newCar.getGeneration());
                    car.setYearOfProduction(newCar.getYearOfProduction());
                    car.setOdometer(newCar.getOdometer());
                    car.setDisplacementVolume(newCar.getDisplacementVolume());
                    car.setEnginePower(newCar.getEnginePower());
                    car.setFuel(newCar.getFuel());
                    car.setSeats(newCar.getSeats());
                    car.setColor(newCar.getColor());
                    return repository.save(car);
                })
                .orElseGet(() -> {
                    newCar.setId(id);
                    return repository.save(newCar);
                });

        EntityModel<Car> entityModel = assembler.toModel(updatedCar);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new CarNotFoundException(id);
        }
        return ResponseEntity.noContent().build();
    }
}