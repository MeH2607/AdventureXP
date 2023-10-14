package dat3.adventureXP.repository;

import dat3.adventureXP.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {

    @Query("SELECT a FROM Activity a WHERE a.name = :activityName")
    Activity findByName(@Param("activityName") String activityName);
    Activity findByEmployeeUsername(String username);
}