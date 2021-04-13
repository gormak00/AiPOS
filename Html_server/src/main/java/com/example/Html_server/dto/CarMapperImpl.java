/*
package com.example.Html_server.dto;

import by.aipos.aipos_lab2.model.Car;
import by.aipos.aipos_lab2.repository.RentCompanyRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
@Component
public class CarMapperImpl implements CarMapper {
    @Autowired
    RentCompanyRepository rentCompanyRepository;

    @Override
    public Car toCar(CarDto carDto) {
        Car car = new Car();
        car.setNumber(carDto.getNumber());
        car.setModel(carDto.getModel());
        car.setRentCompany(rentCompanyRepository.findByName(carDto.getNameOfRentCompany()).orElse(null));
        car.setRented(carDto.isRented());
        return car;
    }
}
*/
