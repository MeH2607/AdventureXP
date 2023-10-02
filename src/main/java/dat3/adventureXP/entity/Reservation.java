package dat3.adventureXP.entity;

import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Reservation {

    /*Rental date [LocalDate]
User (costumer) [Class]
Activity [Class]
*/

    private LocalDate rentalDate;

    private User user;

    @ManyToOne
    private List<Activity> activities;
}
