package com.example.Html_server.controller;

import com.example.Html_server.dto.CarDto;
import com.example.Html_server.model.Car;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String allCars(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Car>> response = restTemplate.exchange("http://server:8080/cars",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});
        List<Car> allCars = response.getBody();
        model.addAttribute("cars", allCars);
        model.addAttribute("carDto", new CarDto());
        return "start/carListPage";
    }

    @GetMapping(value = "/car")
    public String getCarById(@RequestParam(value = "id", required = true) int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Car> response = restTemplate.exchange("http://server:8080/car?id=" + id,
                HttpMethod.GET, null, Car.class);
        Car car = response.getBody();
        model.addAttribute("car", car);
        return "final/carGetAllPageFinal";
    }

    @GetMapping(value = "/addCar")
    public String showAddCarPage(Model model){
        model.addAttribute("carDto", new CarDto());
        return "start/carAddPage";
    }

    @PostMapping(value = "/car")
    public String addCar(CarDto carDto, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Car> response = restTemplate.exchange("http://server:8080/car",
                HttpMethod.POST, new HttpEntity<CarDto>(carDto), Car.class);
        Car car = response.getBody();
        model.addAttribute("car", car);
        return "final/carAddPageFinal";
    }

    @PostMapping(value = "/deleteCar")
    public String dropCarById(@RequestParam(value = "id", required = true) int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Car> response = restTemplate.exchange("http://server:8080/deleteCar?id=" + id,
                HttpMethod.POST, null, Car.class);
        Car car = response.getBody();
        model.addAttribute("car", car);
        return "final/carDropByIdPageFinal";
    }

    @PostMapping(value = "/updateCar")
    public String updateCarById(@RequestParam(value = "id", required = true) int id, CarDto carDto, Model model){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Car> response = restTemplate.exchange("http://server:8080/updateCar?id=" + id,
                HttpMethod.POST, new HttpEntity<CarDto>(carDto), Car.class);
        Car car = response.getBody();
        model.addAttribute("car", car);
        return "final/carUpdatePageFinal";
    }
}
