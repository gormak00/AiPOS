/*
package com.example.Html_server.dto;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookingMapperImpl implements BookingMapper {
    @Autowired
    CarRepository carRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Booking toBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setCar(carRepository.findById(bookingDto.getCarId()).orElse(null));
        booking.setClient(clientRepository.findById(bookingDto.getClientId()).orElse(null));
        booking.setDateStart(LocalDate.parse(bookingDto.getDateStart()));
        booking.setDateEnd(LocalDate.parse(bookingDto.getDateEnd()));
        return booking;
    }
}
*/
