package by.aipos.aipos_lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RentCompanyDto {
    @NotNull(message = "Empty name field")
    @Size(message = "Name is too long (min = 1, max = 25)", min = 1, max = 25)
    private String name;
}
