package dat3.adventureXP.service;

import dat3.adventureXP.dto.ActivityResponse;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.repository.ActivityRepository;
import dat3.adventureXP.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    ActivityRepository activityRepository;
    EquipmentRepository equipmentRepository;

    public ActivityService(ActivityRepository activityRepository, EquipmentRepository equipmentRepository) {
        this.activityRepository = activityRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public List<ActivityResponse> getActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(activity -> new ActivityResponse(activity)).collect(Collectors.toList());
    }

}
