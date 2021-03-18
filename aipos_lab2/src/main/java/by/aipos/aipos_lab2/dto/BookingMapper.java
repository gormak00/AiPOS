package by.aipos.aipos_lab2.dto;

import by.aipos.aipos_lab2.model.Booking;

public interface BookingMapper {
    Booking toBooking(BookingDto bookingDto);
}
