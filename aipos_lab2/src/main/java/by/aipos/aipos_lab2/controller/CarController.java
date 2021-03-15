package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.model.Car;
import by.aipos.aipos_lab2.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarServiceImpl carService;

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> allCars(){
        return new ResponseEntity(carService.getAllCars(), HttpStatus.OK);
    }

    @PostMapping(value = "/car")
    public ResponseEntity<?> addCar(@RequestBody Car car){
        carService.addCar(car);
        return new ResponseEntity(carService.getAllCars(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/car/{id}")
    public ResponseEntity<?> dropCarById(@PathVariable(name = "id") int id){
        carService.dropCarById(id);
        return new ResponseEntity(carService.getAllCars(), HttpStatus.OK);
    }
}
