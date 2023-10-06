package dat3.adventureXP.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Equipment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentResponse {
    String name;
    String status;
    String activity;

    public EquipmentResponse(Equipment equipment) {
        this.name = equipment.getName();
        this.status = equipment.getStatus();
        this.activity = equipment.getActivity().getName();
    }
}
