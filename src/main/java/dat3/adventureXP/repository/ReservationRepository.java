package dat3.adventureXP.repository;

import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
        @Query("SELECT r FROM Reservation r JOIN r.activities a WHERE a.name = :activityName")
        List<Reservation> findByActivityName(String activityName);
        List<Reservation> findByUserUsername(String username);

}
