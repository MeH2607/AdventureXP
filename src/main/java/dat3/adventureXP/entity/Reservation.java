package dat3.adventureXP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder //We will talk about this in the class
@NoArgsConstructor
// ----Lombok anotations above --------- //
@Entity
public class Reservation {

    /*Rental date [LocalDate]
User (costumer) [Class]
Activity [Class]
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id", unique = true, nullable = false)
    private String id;

    @Column(name="Reservation_rentalDate", nullable = false)
    private LocalDate rentalDate;

    @ManyToOne
    @Column(name="reservation_user", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "activity_reservations",
            joinColumns = @JoinColumn(name = "activity_name"),
            inverseJoinColumns = @JoinColumn (name="reservation_id")
    )
    private List<Activity> activities;
}
