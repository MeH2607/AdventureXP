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
    public List<UserResponse> getUsers(boolean isAdmin) {
        List<User> users = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        for(User user: users){
            UserResponse ur = new UserResponse(user, isAdmin);
            response.add(ur);
        }
        return response;
    }
}
