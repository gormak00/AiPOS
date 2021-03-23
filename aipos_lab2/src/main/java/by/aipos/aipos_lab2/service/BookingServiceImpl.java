package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Booking;
import by.aipos.aipos_lab2.repository.BookingRepository;
import by.aipos.aipos_lab2.repository.CarRepository;
import by.aipos.aipos_lab2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (booking.getCar() == null || booking.getClient() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car or client is null");
        }
        if (booking.getCar().isRented()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is rented");
        }
        if (booking.getDateStart().isAfter(LocalDate.now()) || booking.getDateEnd().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date");
        }

        List<Booking> bookingsFromRepository = bookingRepository.findAll();
        if (bookingsFromRepository.size() == 0) booking.setId(1);
        else booking.setId(bookingsFromRepository.get(bookingsFromRepository.size() - 1).getId() + 1);

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
