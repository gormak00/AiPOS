package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.CarDto;
import by.aipos.aipos_lab2.dto.CarMapperImpl;
import by.aipos.aipos_lab2.model.Car;
import by.aipos.aipos_lab2.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CarController {

    @Autowired
    CarServiceImpl carService;
    @Autowired
    CarMapperImpl carMapper;

    @GetMapping(value = "/cars")
    public List<Car> allCars() {
        return carService.getAllCars();
    }

    @GetMapping(value = "/car/{id}")
    public Car getCarById(@PathVariable (value = "id") int id) {
        return carService.getCarById(id);
    }

    @PostMapping(value = "/car/{id}")
    public Car addCar(@RequestBody CarDto carDto) {
        Car car = carMapper.toCar(carDto);
        Car car1 = carService.addCar(car);
        return carService.addCar(car);
    }

    @DeleteMapping(value = "/deleteCar/{id}")
    public Car dropCarById(@PathVariable (value = "id") int id) {
        Car car = carService.getCarById(id);
        carService.dropCarById(id);
        return car;
    }

    @PutMapping(value = "/updateCar/{id}")
    public Car updateCarById(@PathVariable (value = "id") int id, @RequestBody CarDto carDto){
        Car car = carMapper.toCar(carDto);
        return carService.updateCarById(car, id);
    }
}
