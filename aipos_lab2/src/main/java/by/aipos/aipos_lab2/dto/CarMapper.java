package by.aipos.aipos_lab2.dto;

import by.aipos.aipos_lab2.model.Car;

public interface CarMapper {
    Car toCar(CarDto carDto);
}
