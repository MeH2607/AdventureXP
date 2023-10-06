package dat3.adventureXP.api;

import dat3.adventureXP.dto.ReservationRequest;
import dat3.adventureXP.dto.ReservationResponse;
import dat3.adventureXP.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> getReservations() {
        List<ReservationResponse> res = reservationService.getReservations();
        return res;
    }

    @PostMapping
    public ReservationResponse(@RequestBody ReservationRequest reservationRequest){
        return reservationService.makeReservations(reservationRequest);
    }

}
