package com.example.CarRent;

import com.example.CarRent.model.Car;
import com.example.CarRent.model.Customer;
import com.example.CarRent.repository.CarRepository;
import com.example.CarRent.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(CarRentApplication.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, CarRepository carRepository) {
        return args -> {
            Customer c1 = new Customer("kamil","kamil".toCharArray(),"Kamil","Lelakowski","Mielec");
            Customer c2 = new Customer("tomek","tomek".toCharArray(),"Tomek","Tomecki","Krakow");
            Customer c3 = new Customer("andrzej","andrzej".toCharArray(),"Andrzej","Zasadzinski","Warszawa");
            customerRepository.save(c1);
            customerRepository.save(c2);
            customerRepository.save(c3);
            Car car1 = new Car("Osobowe",
                    "Opel",
                    "Astra",
                    "1.4 TwinSpark",
                    "II",
                    2009,
                    140000,
                    1400,
                    90,"benzyna",
                    5,
                    "czerwony");
            Car car2 = new Car("Osobowe",
                    "Audi",
                    "A4",
                    "1.8 GTI",
                    "II",
                    2005,
                    170000,
                    1800,
                    125,"benzyna",
                    5,
                    "biały");
            carRepository.save(car1);
            carRepository.save(car2);
            customerRepository.findAll().forEach(customer -> log.info("New Customer: " + customer));
            carRepository.findAll().forEach(car -> log.info("New Car: " + car));


        };
    }

}

