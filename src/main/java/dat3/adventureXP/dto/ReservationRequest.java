package dat3.adventureXP.dto;

import dat3.adventureXP.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    private LocalDate rentalDate;

    private String username;

    private List<String> activities;

    public static Reservation getReservationEntity(ReservationRequest body){
        return Reservation.builder()
                .rentalDate(body.rentalDate)
                .user(body.)
    }

}
