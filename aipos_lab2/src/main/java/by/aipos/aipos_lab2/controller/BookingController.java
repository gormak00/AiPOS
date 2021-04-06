package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.*;
import by.aipos.aipos_lab2.model.Booking;
import by.aipos.aipos_lab2.service.BookingServiceImpl;
import by.aipos.aipos_lab2.service.CarServiceImpl;
import by.aipos.aipos_lab2.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
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
    public String allBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        BookingDto bookingDto = new BookingDto();
        model.addAttribute("bookingDto", bookingDto);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("cars", carService.getAllCars());
        return "start/bookingListPage";
    }

    @GetMapping(value = "/booking")
    public String getBookingById(@RequestParam(value = "id", required = true) int id, Model model) {
        model.addAttribute("booking", bookingService.getBookingById(id));
        return "final/bookingGetPageFinal";
    }

    @GetMapping(value = "/addBooking")
    public String showAddPersonPage(Model model) {
        BookingDto bookingDto = new BookingDto();
        model.addAttribute("bookingDto", bookingDto);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("cars", carService.getAllCars());
        return "start/bookingAddPage";
    }

    @PostMapping(value = "/booking")
    public String addBooking(@Valid @ModelAttribute("bookingDto") BookingDto bookingDto, Model model) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        model.addAttribute("booking", bookingService.addBooking(booking));

        return "final/bookingAddPageFinal";
    }

    @PostMapping(value = "/deleteBooking")
    public String dropBookingById(@ModelAttribute("id") @Valid int id, Model model) {
        model.addAttribute("booking", bookingService.getBookingById(id));
        bookingService.dropById(id);
        return "final/bookingDropByIdPageFinal";
    }

    @PostMapping(value = "/updateBooking")
    public String updateBookingById(@Valid @ModelAttribute("id") int id, @Valid @ModelAttribute("bookingDto") BookingDto bookingDto, Model model) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        model.addAttribute("booking", bookingService.updateBookingById(booking, id));
        return "final/bookingUpdatePageFinal";
    }

}
