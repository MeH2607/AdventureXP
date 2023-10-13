package dat3.adventureXP.api;

import dat3.adventureXP.dto.ReservationRequest;
import dat3.adventureXP.dto.ReservationResponse;
import dat3.adventureXP.service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // ADMIN
    @GetMapping
    public List<ReservationResponse> getReservations() {
        List<ReservationResponse> res = reservationService.getReservations();
        return res;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    @GetMapping("/{activityName}")
    public List<ReservationResponse> getReservationsForSpecificActivity(@PathVariable String activityName) {
        List<ReservationResponse> res = reservationService.getReservationsForActivity(activityName);
        return res;
    }

    @GetMapping("user/{username}")
    public List<ReservationResponse> getReservationsForUser(@PathVariable String username){
        List<ReservationResponse> res = reservationService.getReservationsMadeByUser(username);
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

    @GetMapping("/id/{id}")
    public ReservationResponse getSingleReservation(@PathVariable int id){
        return reservationService.getSingleReservation(id);
    }

    @DeleteMapping("{id}")
    public void deleteReservation(@PathVariable int id){
         reservationService.deleteReservation(id);
    }

}
