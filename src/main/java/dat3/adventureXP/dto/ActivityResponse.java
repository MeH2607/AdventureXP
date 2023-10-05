package dat3.adventureXP.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.adventureXP.entity.Activity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityResponse {
    int id;
    String name;
    String description;
    int ageLimit;
    String status;
    String employee;

    public ActivityResponse(Activity activity) {
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.ageLimit = activity.getAgeLimit();
        this.status = activity.getStatus();
        this.employee = activity.getEmployee().getUsername();
    }
}
