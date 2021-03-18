package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.CarDto;
import by.aipos.aipos_lab2.dto.CarMappingImpl;
import by.aipos.aipos_lab2.model.Car;
import by.aipos.aipos_lab2.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarServiceImpl carService;
    @Autowired
    CarMappingImpl carMapping;

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> allCars() {
        return new ResponseEntity(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping(value = "/car/{id}")
    public ResponseEntity<?> getCarById(@PathVariable(name = "id") int id) {
        return new ResponseEntity(carService.getCarById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/car")
    public String addCar(@Validated @RequestBody CarDto carDto, Model model) {
        Car car = carMapping.toCar(carDto);
        model.addAttribute("car", carService.addCar(car));
        return "carAddPage";
    }

    @DeleteMapping(value = "/car/{id}")
    public ResponseEntity<?> dropCarById(@PathVariable(name = "id") int id) {
        carService.dropCarById(id);
        return new ResponseEntity(carService.getAllCars(), HttpStatus.OK);
    }
}
