package dat3.adventureXP.service;

import dat3.adventureXP.dto.ReservationResponse;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Reservation;
import dat3.adventureXP.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(res -> new ReservationResponse(res)).collect(Collectors.toList());
    }

    public List<ReservationResponse> getReservationsForActivity(String name) {
        try {
            List<Reservation> reservations = reservationRepository.findByActivityName(name);

            List<ReservationResponse> reservationResponses = reservations.stream()
                    .map(ReservationResponse::new)
                    .collect(Collectors.toList());

            return reservationResponses;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving reservations for the activity: " + e.getMessage(), e);
        }
    }
    public List<ReservationResponse> getReservationsMadeByUser(String username) {
        try {
            List<Reservation> reservations = reservationRepository.findByUserUsername(username);

            List<ReservationResponse> reservationResponses = reservations.stream()
                    .map(ReservationResponse::new)
                    .collect(Collectors.toList());

            return reservationResponses;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving reservations for this user: " + e.getMessage(), e);
        }
    }
}
