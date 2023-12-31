package dat3.adventureXP.dto;

import dat3.adventureXP.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    String username;
    String name;
    String role;
    int age;
    String email;
    String password;

    public static User getUserEntity(UserRequest u) {
        return new User(u.getUsername(), u.password, u.email, u.name, u.age);
    }

    public UserRequest(User u) {
        this.username = u.getUsername();
        this.name = u.getName();
        this.age = u.getAge();
        this.email = u.getEmail();
        this.password = u.getPassword();
    }
}
