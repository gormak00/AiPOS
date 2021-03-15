package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Car;
import by.aipos.aipos_lab2.repository.CarRepository;
import by.aipos.aipos_lab2.repository.RentCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentCompanyRepository rentCompanyRepository;

    @Override
    public void addCar(Car car) {
        int count = carRepository.findAll().size();
        if (count == 0) car.setId(1);
            else car.setId(count + 1);

        car.setRentCompany(rentCompanyRepository.findByName(car.getRentCompanyString()));
        carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void dropCarById(int id) {
        carRepository.deleteById(id);
    }
}
