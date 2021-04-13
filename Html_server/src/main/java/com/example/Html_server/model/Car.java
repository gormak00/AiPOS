package com.example.Html_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private int id;

    private String number;

    private String model;

    private RentCompany rentCompany;

    private boolean rented;
}
