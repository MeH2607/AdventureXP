package dat3.adventureXP.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder //We will talk about this in the class
@NoArgsConstructor
// ----Lombok anotations above --------- //
@Entity
@Table(name="Equipment")
public class Equipment {

    @Id
    @Column(name="equipment_name")
    String name;

    @Column(name="status")
    String status;

    @ManyToOne
    Activity activity;
}
