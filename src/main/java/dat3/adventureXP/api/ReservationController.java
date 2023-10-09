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
    public ReservationResponse makeReservations(@RequestBody ReservationRequest reservationRequest){
        return reservationService.makeReservations(reservationRequest);
    }

    @PutMapping("/{id}")
    public ReservationResponse updateReservation(@RequestBody ReservationRequest reservationRequest, @PathVariable int id){
        return reservationService.updateReservation(reservationRequest, id);
    }

}
