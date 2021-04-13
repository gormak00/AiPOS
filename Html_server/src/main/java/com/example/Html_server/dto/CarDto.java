package com.example.Html_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CarDto {
    @NotNull(message = "Empty number field")
    @Size(message = "Number is too long (max = 8)", max = 8) //format: "1234AA-7"
    private String number;

    @NotNull(message = "Empty model field")
    @Size(message = "Model is too long (min = 1, max = 25)", min = 1, max = 25)
    private String model;

    @NotNull(message = "Empty nameOfRentCompany field")
    @Size(message = "NameOfRentCompany is too long (min = 1, max = 25)", min = 1, max = 25)
    private String nameOfRentCompany;

    @NotNull(message = "Empty rented field")
    boolean rented;
}
