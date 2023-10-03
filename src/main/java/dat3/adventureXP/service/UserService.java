package dat3.adventureXP.service;

import dat3.adventureXP.dto.UserRequest;
import dat3.adventureXP.dto.UserResponse;
import dat3.adventureXP.entity.User;
import dat3.adventureXP.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserResponse> getUsers(boolean includeAll) {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(user, includeAll)).toList();
    }
    public UserResponse addUser(UserRequest body) {
        if (userRepository.existsById(body.getUserName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user already exists");
        }

        User newUser = UserRequest.getUserEntity(body);
        newUser = userRepository.save(newUser);
        return new UserResponse(newUser, true);
    }
}
