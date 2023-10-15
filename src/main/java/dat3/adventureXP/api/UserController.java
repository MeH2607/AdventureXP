package dat3.adventureXP.api;

import dat3.adventureXP.dto.UserRequest;
import dat3.adventureXP.dto.UserResponse;
import dat3.adventureXP.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserResponse> getUsers() {
        return userService.getUsers(false);
    }
    @PostMapping("/signup")
    UserResponse addUser(@RequestBody UserRequest body) {
        return userService.addUser(body);
    }

    @GetMapping("/customers")
    List<UserResponse> getCustomers() {
        return userService.getUsers(false);
    }


}
