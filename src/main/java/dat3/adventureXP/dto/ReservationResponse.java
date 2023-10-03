package dat3.adventureXP.dto;

import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Reservation;
import dat3.adventureXP.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {

    Long id;

    LocalDate rentalDate;

    User user;

    List<Activity> activities;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.rentalDate = reservation.getRentalDate();
        this.user = reservation.getUser();
        this.activities = reservation.getActivities();
    }
}
