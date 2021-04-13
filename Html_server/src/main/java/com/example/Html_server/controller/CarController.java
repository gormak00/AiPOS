/*
package com.example.Html_server.controller;

import by.aipos.aipos_lab2.dto.CarDto;
import by.aipos.aipos_lab2.dto.CarMapperImpl;
import by.aipos.aipos_lab2.model.Car;
import by.aipos.aipos_lab2.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String allCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        CarDto carDto = new CarDto();
        model.addAttribute("carDto", carDto);
        return "start/carListPage";
    }

    @GetMapping(value = "/car")
    public String getCarById(@RequestParam(value = "id", required = true) int id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "final/carGetAllPageFinal";
    }

    @GetMapping(value = "/addCar")
    public String showAddCarPage(Model model){
        CarDto carDto = new CarDto();
        model.addAttribute("carDto", carDto);
        return "start/carAddPage";
    }

    @PostMapping(value = "/car")
    public String addCar(@Valid @ModelAttribute("carDto") CarDto carDto, Model model) {
        Car car = carMapper.toCar(carDto);
        model.addAttribute("car", carService.addCar(car));
        return "final/carAddPageFinal";
    }

    @PostMapping(value = "/deleteCar")
    public String dropCarById(@RequestParam(value = "id", required = true) int id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        carService.dropCarById(id);
        return "final/carDropByIdPageFinal";
    }

    @PostMapping(value = "/updateCar")
    public String updateCarById(@RequestParam(value = "id", required = true) int id, @Valid @ModelAttribute CarDto carDto, Model model){
        Car car = carMapper.toCar(carDto);
        model.addAttribute("car", carService.updateCarById(car, id));
        return "final/carUpdatePageFinal";
    }
}
*/
