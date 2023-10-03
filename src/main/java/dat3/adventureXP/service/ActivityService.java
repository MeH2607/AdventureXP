package dat3.adventureXP.service;

import dat3.adventureXP.dto.ActivityResponse;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    public List<ActivityResponse> getActivities() {
         List<Activity> activities = activityRepository.findAll();
         return activities.stream().map(activity -> new ActivityResponse(activity)).collect(Collectors.toList());
    }
}
