package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Booking;
import by.aipos.aipos_lab2.repository.BookingRepository;
import by.aipos.aipos_lab2.repository.CarRepository;
import by.aipos.aipos_lab2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Booking addBooking(Booking booking) {
        if (booking.getCar() == null || booking.getClient() == null)
            throw new IllegalArgumentException("FUCK YOU SMTH IS NULL"); //todo response 400 Bad Request
        int count = bookingRepository.findAll().size();
        if (count == 0) booking.setId(1);
        else booking.setId(count + 1);
        if (booking.getCar().isRented())
            throw new IllegalArgumentException("FUCK YOU, CAR IS RENTED"); //todo response 400 Bad Request
        if (booking.getDateStart().isAfter(LocalDate.now()) || booking.getDateEnd().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("FUCK YOU, DATE IS UNCORRECTED"); //todo response 400 Bad Request

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public void dropById(int id) {
        bookingRepository.deleteById(id);
    }
}
