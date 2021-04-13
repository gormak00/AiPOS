package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.BookingDto;
import by.aipos.aipos_lab2.dto.BookingMapperImpl;
import by.aipos.aipos_lab2.model.Booking;
import by.aipos.aipos_lab2.service.BookingServiceImpl;
import by.aipos.aipos_lab2.service.CarServiceImpl;
import by.aipos.aipos_lab2.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;
    @Autowired
    CarServiceImpl carService;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    BookingMapperImpl bookingMapper;

    @GetMapping(value = "/bookings")
    public List<Booking> allBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping(value = "/booking")
    public Booking getBookingById(@RequestParam(value = "id", required = true) int id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping(value = "/booking")
    public Booking addBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        return bookingService.addBooking(booking);
    }

    @PostMapping(value = "/deleteBooking")
    public Booking dropBookingById(@RequestParam(value = "id", required = true) int id) {
        Booking booking = bookingService.getBookingById(id);
        bookingService.dropById(id);
        return booking;
    }

    @PostMapping(value = "/updateBooking")
    public Booking updateBookingById(@RequestParam(value = "id", required = true) int id, @RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        return bookingService.updateBookingById(booking, id);
    }
}
