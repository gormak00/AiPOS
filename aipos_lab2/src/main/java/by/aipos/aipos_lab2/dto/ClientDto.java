package by.aipos.aipos_lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class ClientDto {
    @NotNull(message = "Empty name field")
    @Size(message = "Name is too long (max = 25)", max = 25)
    private String name;
    @NotNull(message = "Empty phoneNumber field")
    @Size(message = "PhoneNumber is too long or short (size = 9)", min = 9, max = 9)
    private String phoneNumber;
}
