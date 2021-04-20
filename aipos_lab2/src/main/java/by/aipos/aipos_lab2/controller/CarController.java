package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.CarDto;
import by.aipos.aipos_lab2.dto.CarMapperImpl;
import by.aipos.aipos_lab2.model.Car;
import by.aipos.aipos_lab2.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    CarServiceImpl carService;
    @Autowired
    CarMapperImpl carMapper;

    @GetMapping(value = "/cars")
    public List<Car> allCars() {
        return carService.getAllCars();
    }

    @GetMapping(value = "/car")
    public Car getCarById(@RequestParam(value = "id", required = true) int id) {
        return carService.getCarById(id);
    }

    @PostMapping(value = "/car")
    public Car addCar(@RequestBody CarDto carDto) {
        Car car = carMapper.toCar(carDto);
        Car car1 = carService.addCar(car);
        return carService.addCar(car);
    }

    @PostMapping(value = "/deleteCar")
    public Car dropCarById(@RequestParam(value = "id", required = true) int id) {
        Car car = carService.getCarById(id);
        carService.dropCarById(id);
        return car;
    }

    @PostMapping(value = "/updateCar")
    public Car updateCarById(@RequestParam(value = "id", required = true) int id, @RequestBody CarDto carDto){
        Car car = carMapper.toCar(carDto);
        return carService.updateCarById(car, id);
    }
}
