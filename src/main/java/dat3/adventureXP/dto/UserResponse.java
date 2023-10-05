package dat3.adventureXP.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.adventureXP.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    String userName;
    String name;
    String role;
    int age;
    String email;
    String password;

    public UserResponse(User u, boolean isAdmin) {
        this.name = u.getName();
        this.userName = u.getUsername();
        this.age = u.getAge();
        this.email = u.getEmail();
        if(isAdmin) {
            this.password = u.getPassword();
    }
}
}
