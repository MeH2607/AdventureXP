package dat3.adventureXP.api;

import dat3.adventureXP.dto.ActivityResponse;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.User;
import dat3.adventureXP.repository.ActivityRepository;
import dat3.adventureXP.repository.UserRepository;
import dat3.adventureXP.service.ActivityService;
import dat3.adventureXP.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/activities")
public class ActivityController {
    ActivityService activityService;
    UserService userService;
    UserRepository userRepository;
    ActivityRepository activityRepository;
    public ActivityController(ActivityService activityService, UserService userService, UserRepository userRepository, ActivityRepository activityRepository) {
        this.activityService = activityService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }


    @GetMapping
    List<ActivityResponse> getActivities() {
        return activityService.getActivities();
    }

    // ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    ActivityResponse updateActivity(@RequestBody ActivityResponse activityResponse) {
        return activityService.updateActivity(activityResponse);
    }

    @PostMapping("/assignActivity")
    ResponseEntity<String> assignActivityToEmployee(@RequestParam String userId, @RequestParam String activityName) {
        try {
            User user = (User) userRepository.findByUsername(userId);
            Activity activity = activityRepository.findByName(activityName);
            userService.assignActivityToEmployee(user, activity);
            return ResponseEntity.ok("Activity assigned to user");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to assign activity" + e.getMessage());
        }
    }

}
