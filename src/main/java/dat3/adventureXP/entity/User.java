package dat3.adventureXP.entity;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
// ----Lombok anotations above --------- //
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR_TYPE")
public class User extends UserWithRoles {
    String name;
    int age;

   // Implement when reservations are implemented
    @OneToMany(mappedBy = "user")
    List<Reservation> reservations;

    @ManyToOne
    Activity activity;

    public void addReservation(Reservation reservation){
        if (reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }
    public void setActivityIfEmployee(Activity activity) {
        if(getRoles().contains(Role.EMPLOYEE)) {
            this.activity = activity;
        }
    }

    public User(String user, String password, String email, String name, int age) {
        super(user, password, email);
        this.name = name;
        this.age = age;

    }
}
