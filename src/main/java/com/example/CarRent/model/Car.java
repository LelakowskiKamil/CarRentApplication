package com.example.CarRent.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {

    private @Id
    @GeneratedValue
    Long id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "engine", nullable = false)
    private String engine;
    @Column(name = "generation", nullable = false)
    private String generation;
    @Column(name = "yearOfProduction", nullable = false)
    private int yearOfProduction;
    @Column(name = "odometer", nullable = false)
    private int odometer;
    @Column(name = "displacementVolume", nullable = false)
    private int displacementVolume;
    @Column(name = "enginePower", nullable = false)
    private double enginePower;
    @Column(name = "fuel", nullable = false)
    private String fuel;
    @Column(name = "seats", nullable = false)
    private int seats;
    @Column(name = "color", nullable = false)
    private String color;

    public Car() {
    }

    public Car(String type,
               String brand,
               String model,
               String engine,
               String generation,
               int yearOfProduction,
               int odometer,
               int displacementVolume,
               double enginePower,
               String fuel,
               int seats,
               String color) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.generation = generation;
        this.yearOfProduction = yearOfProduction;
        this.odometer = odometer;
        this.displacementVolume = displacementVolume;
        this.enginePower = enginePower;
        this.fuel = fuel;
        this.seats = seats;
        this.color = color;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getDisplacementVolume() {
        return displacementVolume;
    }

    public void setDisplacementVolume(int displacementVolume) {
        this.displacementVolume = displacementVolume;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engine=" + engine +
                ", generation='" + generation + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", odometer=" + odometer +
                ", displacementVolume=" + displacementVolume +
                ", enginePower='" + enginePower + '\'' +
                ", fuel='" + fuel + '\'' +
                ", seats='" + seats + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(this.id, car.id) &&
                Objects.equals(this.type, car.type) &&
                Objects.equals(this.brand, car.brand) &&
                Objects.equals(this.model, car.model) &&
                Objects.equals(this.engine, car.engine) &&
                Objects.equals(this.generation, car.generation) &&
                Objects.equals(this.yearOfProduction, car.yearOfProduction) &&
                Objects.equals(this.odometer, car.odometer) &&
                Objects.equals(this.displacementVolume, car.displacementVolume) &&
                Objects.equals(this.enginePower, car.enginePower) &&
                Objects.equals(this.fuel, car.fuel) &&
                Objects.equals(this.seats, car.seats) &&
                Objects.equals(this.color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                type,
                brand,
                model,
                engine,
                generation,
                yearOfProduction,
                odometer,
                displacementVolume,
                enginePower,
                fuel,
                seats,
                color );
    }
}
