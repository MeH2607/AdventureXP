package dat3.adventureXP.api;

import dat3.adventureXP.dto.ReservationRequest;
import dat3.adventureXP.dto.ReservationResponse;
import dat3.adventureXP.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @GetMapping("/{activityName}")
    public List<ReservationResponse> getReservationsForSpecificActivity(@PathVariable String activityName) {
        List<ReservationResponse> res = reservationService.getReservationsForActivity(activityName);
        return res;
    }
    @GetMapping("/reservations-for-authenticated")
    public List<ReservationResponse> getReservationsForUser(Principal principal){
        List<ReservationResponse> res = reservationService.getReservationsMadeByUser(principal.getName());
        return res;
    }
    @GetMapping("user/{userName}")
    public List<ReservationResponse> getReservationsForUser(@PathVariable String userName){
        List<ReservationResponse> res = reservationService.getReservationsMadeByUser(userName);
        return res;
    }

    @PostMapping
    public ReservationResponse(@RequestBody ReservationRequest reservationRequest){
        return reservationService.makeReservations(reservationRequest);
    }

}
