package dat3.adventureXP.service;

import dat3.adventureXP.dto.ReservationRequest;
import dat3.adventureXP.dto.ReservationResponse;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Reservation;
import dat3.adventureXP.repository.ActivityRepository;
import dat3.adventureXP.repository.ReservationRepository;
import dat3.adventureXP.repository.UserRepository;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {


    ReservationRepository reservationRepository;
    UserWithRolesRepository userWithRolesRepository;
    ActivityRepository activityRepository;

    public ReservationService(ReservationRepository reservationRepository, UserWithRolesRepository userWithRolesRepository, ActivityRepository activityRepository) {
        this.reservationRepository = reservationRepository;
        this.activityRepository = activityRepository;
        this.userWithRolesRepository = userWithRolesRepository;
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


    public ReservationResponse makeReservations(ReservationRequest body) {
        Reservation reservation = new Reservation();

        //Sets user who made the reservation
        reservation.setUser(userWithRolesRepository.findByUsername(body.getUsername()));
        //Sets rental date
        reservation.setRentalDate(body.getRentalDate());
        //Sets activities
        for(String activityName : body.getActivities()){
            System.out.println("Before repository" + activityName);
            Activity newActivity = activityRepository.findByName(activityName);
            System.out.println("After repository" + newActivity.getName());
            reservation.addActivity(newActivity);
        }
        //Saves reservation in database
        reservationRepository.save(reservation);

        //Creates and returns new ReservationResponse
        return new ReservationResponse(reservation);
    }
}
