package dat3.adventureXP.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityRequest {

        String name;
        String description;
        String status;
        int ageLimit;
        int employeeId;

}
