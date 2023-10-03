package dat3.adventureXP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder //We will talk about this in the class
@NoArgsConstructor
// ----Lombok anotations above --------- //
@Entity
@Table(name="Reservations")
public class Reservation {

    /*Rental date [LocalDate]
User (costumer) [Class]
Activity [Class]
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id", unique = true, nullable = false)
    private long id;

    @Column(name="Reservation_rentalDate", nullable = false)
    private LocalDate rentalDate;

    @ManyToOne
    @JoinColumn(name="reservation_user", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "reservations")
    private List<Activity> activities;

    public Reservation(LocalDate rentalDate,User user) {
        this.rentalDate = rentalDate;
        this.user = user;
        user.addReservation(this);
    }

    public Reservation(long id, LocalDate rentalDate, User user) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.user = user;
        this.activities = activities = new ArrayList<>();
    }
}
