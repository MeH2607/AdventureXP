package dat3.adventureXP.service;

import dat3.adventureXP.dto.UserResponse;
import dat3.adventureXP.entity.User;
import dat3.adventureXP.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
