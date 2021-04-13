package com.example.Html_server.controller;

import com.example.Html_server.dto.BookingDto;
import com.example.Html_server.model.Booking;
import com.example.Html_server.model.Car;
import com.example.Html_server.model.Client;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookingController {

    @GetMapping(value = "/bookings")
    public String allBookings(Model model) {
        model.addAttribute("bookingDto", new BookingDto());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Booking>> responseForBookings = restTemplate.exchange("http://localhost:8080/bookings",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
                });
        List<Booking> allBookings = responseForBookings.getBody();
        model.addAttribute("bookings", allBookings);

        ResponseEntity<List<Client>> responseForClients = restTemplate.exchange("http://localhost:8080/clients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Client>>() {
                });
        List<Client> allClients = responseForClients.getBody();
        model.addAttribute("clients", allClients);

        ResponseEntity<List<Car>> responseForCars = restTemplate.exchange("http://localhost:8080/cars",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });
        List<Car> allCars = responseForCars.getBody();
        model.addAttribute("cars", allCars);
        return "start/bookingListPage";
    }

    @GetMapping(value = "/booking")
    public String getBookingById(@RequestParam(value = "id", required = true) int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Booking> response = restTemplate.exchange("http://localhost:8080/booking?id=" + id,
                HttpMethod.GET, null, Booking.class);
        Booking booking = response.getBody();
        model.addAttribute("booking", booking);
        return "final/bookingGetPageFinal";
    }

    @GetMapping(value = "/addBooking")
    public String showAddPersonPage(Model model) {
        model.addAttribute("bookingDto", new BookingDto());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Client>> responseForClients = restTemplate.exchange("http://localhost:8080/clients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Client>>() {
                });
        List<Client> allClients = responseForClients.getBody();
        model.addAttribute("clients", allClients);

        ResponseEntity<List<Car>> responseForCars = restTemplate.exchange("http://localhost:8080/cars",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });
        List<Car> allCars = responseForCars.getBody();
        model.addAttribute("cars", allCars);

        return "start/bookingAddPage";
    }

    @PostMapping(value = "/booking")
    public String addBooking(@Valid @ModelAttribute("bookingDto") BookingDto bookingDto, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Booking> response = restTemplate.exchange("http://localhost:8080/booking",
                HttpMethod.POST, new HttpEntity<BookingDto>(bookingDto), Booking.class);
        Booking booking = response.getBody();
        model.addAttribute("booking", booking);

        return "final/bookingAddPageFinal";
    }

    @PostMapping(value = "/deleteBooking")
    public String dropBookingById(@ModelAttribute("id") @Valid int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Booking> response = restTemplate.exchange("http://localhost:8080/deleteBooking?id=" + id,
                HttpMethod.POST, null, Booking.class);
        Booking booking = response.getBody();
        model.addAttribute("booking", booking);
        return "final/bookingDropByIdPageFinal";
    }

    @PostMapping(value = "/updateBooking")
    public String updateBookingById(@Valid @ModelAttribute("id") int id, @Valid @ModelAttribute("bookingDto") BookingDto bookingDto, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Booking> response = restTemplate.exchange("http://localhost:8080/updateBooking?id=" + id,
                HttpMethod.POST, new HttpEntity<BookingDto>(bookingDto), Booking.class);
        Booking booking = response.getBody();
        model.addAttribute("booking", booking);
        return "final/bookingUpdatePageFinal";
    }

}
