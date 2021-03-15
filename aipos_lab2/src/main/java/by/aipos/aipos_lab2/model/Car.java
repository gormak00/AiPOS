package by.aipos.aipos_lab2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    private int id;
    @Column(name = "number")
    private String number;
    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "id_rent_company")
    private RentCompany rentCompany;

    @Transient
    private String rentCompanyString;

    @Column(name = "rented")
    private boolean rented;

    public Car(String number, String model, String rentCompanyString, boolean rented) {
        this.number = number;
        this.model = model;
        this.rentCompanyString = rentCompanyString;
        this.rented = rented;
    }
}
