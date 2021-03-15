package by.aipos.aipos_lab2.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_car")
    private Car car;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Transient
    private int clientId;
    @Transient
    private int carId;
    @Transient
    private String dateStartString;
    @Transient
    private String dateEndString;

    public Booking(int clientId, int carId, String dateStart, String dateEnd) {
        this.clientId = clientId;
        this.carId = carId;
        this.dateStartString = dateStart;
        this.dateEndString = dateEnd;
    }
}
