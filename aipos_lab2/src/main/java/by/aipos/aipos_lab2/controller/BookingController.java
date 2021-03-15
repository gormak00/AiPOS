package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.model.Booking;
import by.aipos.aipos_lab2.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

    @GetMapping(value = "/bookings")
    public ResponseEntity<List<Booking>> allBookings(){
        return new ResponseEntity(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @PostMapping(value = "/booking")
    public ResponseEntity<?> addBooking(@RequestBody Booking booking){
        bookingService.addBooking(booking);
        return new ResponseEntity(bookingService.getAllBookings(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/booking/{id}")
    public ResponseEntity<?> dropBookingById(@PathVariable(name = "id") int id ){
        bookingService.dropById(id);
        return new ResponseEntity(bookingService.getAllBookings(), HttpStatus.OK);
    }

}
