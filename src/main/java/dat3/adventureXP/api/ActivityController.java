package dat3.adventureXP.api;

import dat3.adventureXP.dto.ActivityResponse;
import dat3.adventureXP.service.ActivityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/activities")
public class ActivityController {
    ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
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

}
