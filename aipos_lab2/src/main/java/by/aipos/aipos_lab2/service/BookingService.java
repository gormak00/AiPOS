package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Booking;

import java.util.List;

public interface BookingService {
    Booking addBooking(Booking booking);

    Booking getBookingById(int id);

    List<Booking> getAllBookings();

    void dropById(int id);
}
