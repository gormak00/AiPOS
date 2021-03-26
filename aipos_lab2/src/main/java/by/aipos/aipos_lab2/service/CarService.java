package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Car;

import java.util.List;

public interface CarService {
    Car addCar(Car car);

    Car getCarById(int id);

    List<Car> getAllCars();

    void dropCarById(int id);

    Car updateCarById(Car car, int id);
}
