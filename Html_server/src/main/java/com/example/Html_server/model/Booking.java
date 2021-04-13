package com.example.Html_server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class Booking {

    private int id;

    private Client client;

    private Car car;

    private LocalDate dateStart;

    private LocalDate dateEnd;
}
