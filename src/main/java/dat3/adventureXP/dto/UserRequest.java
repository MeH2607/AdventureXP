package dat3.adventureXP.dto;

import dat3.adventureXP.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    String userName;
    String name;
    String role;
    int age;
    String email;
    String password;

    public static User getUserEntity(UserRequest u) {
        return new User(u.userName, u.password, u.email, u.name, u.age);
    }

    public UserRequest(User u) {
        this.name = u.getName();
        this.age = u.getAge();
        this.email = u.getEmail();
        this.password = u.getPassword();
    }
}
