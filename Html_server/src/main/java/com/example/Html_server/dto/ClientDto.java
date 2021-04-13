package com.example.Html_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ClientDto {
    @NotNull(message = "Empty name field")
    @Size(message = "Name is too long or short (min = 1, max = 25)", min = 1, max = 25)
    private String name;

    @NotNull(message = "Empty phoneNumber field")
    @Size(message = "PhoneNumber is too long or short (size = 9)", min = 9, max = 9)
    private String phoneNumber;
}
