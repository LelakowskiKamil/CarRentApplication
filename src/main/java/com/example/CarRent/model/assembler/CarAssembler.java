package com.example.CarRent.model.assembler;

import com.example.CarRent.controller.CustomerController;
import com.example.CarRent.model.Car;
import com.example.CarRent.model.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CarAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {
    @Override
    public EntityModel<Car> toModel(Car car) {
        return EntityModel.of(car,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).one(car.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).all()).withRel("car"));
    }


}