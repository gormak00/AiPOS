package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Car;

import java.util.List;

public interface CarService {
    void addCar(Car car);
    List<Car> getAllCars();
    void dropCarById(int id);
}
