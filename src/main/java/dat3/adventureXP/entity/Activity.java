package dat3.adventureXP.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder //We will talk about this in the class
@NoArgsConstructor
// ----Lombok anotations above --------- //
@Entity
@Table(name = "Activities")
public class Activity {

    @OneToMany(mappedBy = "activity")
    List<Equipment> equipment;
    @Id
    private int id;
    @Column(name = "activity_name", length = 30, nullable = false, unique = true)
    private String name;
    @Column(name = "activity_status", length = 10, nullable = false)
    private String status;
    @Column(name = "activity_agelimit", nullable = false)
    private int ageLimit;
    @Column(name = "activity_description", length = 100, nullable = false, unique = true)
    private String description;
    @ManyToMany //TODO skal der cascadetype på?
    @JoinTable(name = "activity_reservations",
            joinColumns = @JoinColumn(name = "activity_name"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id"))
    private List<Reservation> reservations;
    @ManyToOne
    private UserWithRoles employee;

    public Activity(String name, String description, int ageLimit, String status, User employee) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.ageLimit = ageLimit;
        this.employee = employee;
    }

    public void addReservation(Reservation reservation) {
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }



}
