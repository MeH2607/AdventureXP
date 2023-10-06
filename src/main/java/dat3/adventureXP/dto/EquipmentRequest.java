package dat3.adventureXP.dto;

import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Equipment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentRequest {
    String name;
    String status;
    Activity activity;

    public static Equipment getEquipmentEntity(EquipmentRequest er){
        return new Equipment(er.getName(), er.getStatus(), er.getActivity());
    }

    public EquipmentRequest(Equipment eq){
        this.name = eq.getName();
        this.status = eq.getStatus();
        this.activity = eq.getActivity();
    }
}
