package dat3.adventureXP.dto;

import dat3.adventureXP.entity.Activity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityRequest {
    String name;
    String description;
    int ageLimit;
    String status;
    String employee;

    public static Activity activityFromActivityRequest(ActivityRequest ar){
        return Activity.builder().name(ar.name).description(ar.description).ageLimit(ar.ageLimit).status(ar.status).build();
    }
}
