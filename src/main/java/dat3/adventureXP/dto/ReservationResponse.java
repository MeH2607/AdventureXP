package dat3.adventureXP.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Reservation;
import dat3.adventureXP.entity.User;
import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {

    int id;

    LocalDate rentalDate;

    String username;


    List<String> activityNames;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.rentalDate = reservation.getRentalDate();
        this.username = reservation.getUser().getUsername();
      // this.activities = reservation.getActivities();
               //.stream().map(a ->(new ActivityResponse(a))).collect(Collectors.toList());
        this.activityNames = reservation.getActivities().stream().map(activity -> activity.getName()).collect(Collectors.toList());
    }
}
