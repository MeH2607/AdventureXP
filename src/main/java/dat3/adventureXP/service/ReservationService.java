package dat3.adventureXP.service;

import dat3.adventureXP.dto.ReservationResponse;
import dat3.adventureXP.entity.Reservation;
import dat3.adventureXP.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }


    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return null;
    }
}
