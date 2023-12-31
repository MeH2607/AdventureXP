package dat3.adventureXP.service;

import dat3.adventureXP.dto.UserRequest;
import dat3.adventureXP.dto.UserResponse;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.User;
import dat3.adventureXP.repository.ActivityRepository;
import dat3.adventureXP.repository.UserRepository;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    ActivityRepository activityRepository;


    public UserService(UserRepository userRepository, ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }
    public List<UserResponse> getUsers(boolean includeAll) {
        List<User> users = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        for(User user: users){
            UserResponse ur = new UserResponse(user,includeAll);
            response.add(ur);
        }
        return response;
    }
    public UserResponse addUser(UserRequest body) {


        User newUser = UserRequest.getUserEntity(body);
        newUser = userRepository.save(newUser);
        return new UserResponse(newUser, true);
    }
    public void assignActivityToEmployee(User user, Activity activity) {
        try {
            //TODO: Fetch data for every reservation in that activity. This method is created in ReservaationService, remember to use it in controller
            user.setActivityIfEmployee(activity);
            userRepository.save(user); // Save the updated user with the assigned activity
            activity.setEmployee(user);
            activityRepository.save(activity);

        } catch (Exception e) {
            throw new RuntimeException("User is not an employee" + e.getMessage(), e);
        }

    }
}
