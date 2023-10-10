package dat3.adventureXP.dto;

import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Reservation;
import dat3.security.entity.UserWithRoles;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    private LocalDate rentalDate;

    private List<String> activityNames = new ArrayList<>();

    private String username;



}
