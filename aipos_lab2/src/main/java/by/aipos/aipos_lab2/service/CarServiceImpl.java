package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Car;
import by.aipos.aipos_lab2.repository.CarRepository;
import by.aipos.aipos_lab2.repository.RentCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentCompanyRepository rentCompanyRepository;

    @Override
    public Car addCar(Car car) {
        if (car.getRentCompany() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RentCompany not found, FUCK YOU");
        }
        List<Car> carsFromRepository = carRepository.findAll();
        if (carsFromRepository.size() == 0) car.setId(1);
        else car.setId(carsFromRepository.get(carsFromRepository.size() - 1).getId() + 1);

        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id).get();
    }

    @Override
    public void dropCarById(int id) {
        carRepository.deleteById(id);
    }
}
