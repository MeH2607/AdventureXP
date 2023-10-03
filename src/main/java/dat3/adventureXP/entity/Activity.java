package dat3.adventureXP.entity;

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
@Table(name="Activities")
public class Activity {

    @Id
    @Column(name="activity_name", length = 30, nullable = false, unique = true)
    private String name;

    @Column(name="activity_status", length = 10, nullable = false)
    private String status;

    @Column(name="activity_agelimit", nullable = false)
    private int ageLimit;

    @Column(name="activity_description", length = 100, nullable = false, unique = true)
    private String description;

    @ManyToMany //TODO skal der cascadetype på?
    @JoinTable(name="activity_reservations",
    joinColumns = @JoinColumn(name = "activity_name"),
    inverseJoinColumns = @JoinColumn(name="reservation_id"))
    private List<Reservation> reservations;

    public void addReservation(Reservation reservation){
        if (reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }

    @ManyToOne
    private User employee;

  @OneToMany(mappedBy="activity")
    List<Equipment> equipment;

    public Activity(String name, String status, int ageLimit, String description,  User employee) {
        this.name = name;
        this.status = status;
        this.ageLimit = ageLimit;
        this.description = description;
        this.reservations = new ArrayList<>();
        this.employee = employee;
        this.equipment = new ArrayList<>();
    }
}
