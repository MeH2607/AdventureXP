package dat3.adventureXP.entity;

import dat3.security.entity.UserWithRoles;
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
    private int id;

    @Column(name="Reservation_rentalDate", nullable = false)
    private LocalDate rentalDate;

    @ManyToOne
    @JoinColumn(name="reservation_user", nullable = false)
    private UserWithRoles user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "activity_reservations",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_name")
    )
    private List<Activity> activities;

    public void addActivity(Activity activity){
        if(this.activities == null){
            this.activities = new ArrayList<>();
        }
        this.activities.add(activity);
        activity.addReservation(this);
    }



    public Reservation(LocalDate rentalDate,User user) {
        this.rentalDate = rentalDate;
        this.user = user;
        user.addReservation(this);
    }

    public Reservation(int id, LocalDate rentalDate, UserWithRoles user) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.user = user;

    }
}

