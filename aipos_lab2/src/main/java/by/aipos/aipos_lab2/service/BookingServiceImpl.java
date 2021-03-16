package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Booking;
import by.aipos.aipos_lab2.model.Car;
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
        Booking booking1 = new Booking(booking.getClientId(), booking.getCarId(), booking.getDateStartString(), booking.getDateEndString());
        int count = bookingRepository.findAll().size();
        if (count == 0) booking1.setId(1);
            else booking1.setId(count + 1);

        booking1.setClient(clientRepository.findById(booking1.getClientId()).get());

        Car car1 = carRepository.findById(booking1.getCarId()).get();
        if(!car1.isRented()){
            booking1.setCar(carRepository.findById(booking1.getCarId()).get());
            car1.setRented(true);
        }  else return null;//+ todo какая-то логика если выбрана машина у которой rented=true

        booking1.setDateStart(LocalDate.parse(booking1.getDateStartString()));
        booking1.setDateEnd(LocalDate.parse(booking1.getDateEndString()));
        return bookingRepository.save(booking1);
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
