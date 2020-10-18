package com.example.CarRent.controller;


import com.example.CarRent.CustomerService;
import com.example.CarRent.exception.CustomerNotFoundException;
import com.example.CarRent.exception.IncorrectPasswordException;
import com.example.CarRent.exception.IncorrectUsernameException;
import com.example.CarRent.model.Customer;
import com.example.CarRent.model.assembler.CustomerAssembler;
import com.example.CarRent.repository.CustomerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@IllegalExceptionProcessing
@RestController
public class CustomerController {
    private final CustomerRepository repository;
    private final CustomerAssembler assembler;
    private final CustomerService service;

    public CustomerController(CustomerRepository repository, CustomerAssembler assembler, CustomerService service) {
        this.repository = repository;
        this.assembler = assembler;
        this.service = service;
    }

    //Agregate root
    @GetMapping("/customers")
    public CollectionModel<EntityModel<Customer>> all() {
        List<EntityModel<Customer>> customers = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(customers,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).all()).withSelfRel());
    }

    @PostMapping("/customers")
    public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer) {
        if (service.validateUsername(newCustomer.getUsername()) && service.validatePassword(newCustomer.getPassword())) {

            EntityModel<Customer> entityModel = assembler.toModel(repository.save(newCustomer));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }else {
            throw new IllegalArgumentException("Password or username are incorrect");
        }
    }

    //Single item
    @GetMapping("/customers/{id}")
    public EntityModel<Customer> one(@PathVariable Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return assembler.toModel(customer);
    }

//    @GetMapping("/customers/{firstname}/{lastname}")
//    public EntityModel<Customer> byFirstnameAndLastname(@PathVariable String firstname, @PathVariable String lastname ) {
//        Customer customer = repository.findByFirstnameAndLastname(firstname,lastname)
//                .orElseThrow(() -> new CustomerNotFoundException(firstname,lastname));
//
//        return assembler.toModel(customer);
//    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Customer updatedCustomer = repository.findById(id)
                .map(customer -> {
                    customer.setUsername(newCustomer.getUsername());
                    customer.setPassword(newCustomer.getPassword());
                    customer.setFirstname(newCustomer.getFirstname());
                    customer.setLastname(newCustomer.getLastname());
                    customer.setCity(newCustomer.getCity());
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return repository.save(newCustomer);
                });

        EntityModel<Customer> entityModel = assembler.toModel(updatedCustomer);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new CustomerNotFoundException(id);
        }
        return ResponseEntity.noContent().build();
    }
}