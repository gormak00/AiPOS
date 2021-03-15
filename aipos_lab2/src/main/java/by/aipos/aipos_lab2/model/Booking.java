package by.aipos.aipos_lab2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    private int id;
}
