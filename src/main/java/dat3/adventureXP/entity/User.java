package dat3.adventureXP.entity;

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
@DiscriminatorColumn(name = "USER_TYPE")
public class User extends UserWithRoles {
    String name;
    int age;


   // Implement when reservations are implemented
    @OneToMany(mappedBy = "user")
    List<Reservation> reservations;

    public void addReservation(Reservation reservation){
        if (reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }
    public User(String user, String password, String email, String name, int age) {
        super(user, password, email);
        this.name = name;
        this.age = age;
    }
}
