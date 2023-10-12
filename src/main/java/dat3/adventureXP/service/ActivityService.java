package dat3.adventureXP.service;

import dat3.adventureXP.dto.ActivityResponse;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.User;
import dat3.adventureXP.repository.ActivityRepository;
import dat3.adventureXP.repository.UserRepository;
import dat3.security.entity.UserWithRoles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    ActivityRepository activityRepository;
    UserRepository userRepository;

    public ActivityService(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }
    public List<ActivityResponse> getActivities() {
         List<Activity> activities = activityRepository.findAll();
         return activities.stream().map(activity -> new ActivityResponse(activity)).collect(Collectors.toList());
    }
    public ActivityResponse updateActivity(ActivityResponse body) {
        UserWithRoles employee = userRepository.findByUsername(body.getEmployee());
        Activity activity = activityRepository.save(new Activity(body.getAgeLimit(),body.getDescription(), employee, body.getStatus(),  body.getName()));
        return new ActivityResponse(activity);
    }
}
