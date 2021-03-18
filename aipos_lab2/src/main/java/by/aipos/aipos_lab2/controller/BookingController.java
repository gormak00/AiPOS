package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.BookingDto;
import by.aipos.aipos_lab2.dto.BookingMapperImpl;
import by.aipos.aipos_lab2.model.Booking;
import by.aipos.aipos_lab2.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;
    @Autowired
    BookingMapperImpl bookingMapper;

    @GetMapping(value = "/bookings")
    public ResponseEntity<List<Booking>> allBookings() {
        return new ResponseEntity(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping(value = "/booking/{id}")
    public ResponseEntity<List<Booking>> getBookingById(@PathVariable(name = "id") int id) {
        return new ResponseEntity(bookingService.getBookingById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/booking")
    public ResponseEntity<?> addBooking(@Valid @RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        return bookingService.addBooking(booking) != null
                ? new ResponseEntity(bookingService.getAllBookings(), HttpStatus.CREATED)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/booking/{id}")
    public ResponseEntity<?> dropBookingById(@PathVariable(name = "id") int id) {
        bookingService.dropById(id);
        return new ResponseEntity(bookingService.getAllBookings(), HttpStatus.OK);
    }

}
