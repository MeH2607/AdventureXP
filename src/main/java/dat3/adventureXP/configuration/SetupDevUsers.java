package dat3.adventureXP.configuration;

import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.User;
import dat3.adventureXP.repository.ActivityRepository;
import dat3.adventureXP.repository.UserRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    ActivityRepository activityRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    String passwordUsedByAll;


    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, ActivityRepository activityRepository){
        this.userWithRolesRepository = userWithRolesRepository;
        this.activityRepository = activityRepository;
        this.passwordEncoder = passwordEncoder;
        passwordUsedByAll = "test12";
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        setupUserWithRoleUsers();
    }

     /*****************************************************************************************
     IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO

     If you see the lines below in log-outputs on Azure, forget whatever had your attention on, AND FIX THIS PROBLEM

     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("********** IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ************");
        System.out.println();
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println();
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        UserWithRoles user5 = new UserWithRoles("user5", passwordUsedByAll, "user5@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
        userWithRolesRepository.save(user5);


        Activity activity1 = new Activity(10, "Tennis", user1, "Closed", "Tennis") ;
        activityRepository.save(activity1);
        System.out.println("Activity 1: " + activity1.getName());

/*
        List<User> users = new ArrayList<>();
        User userr1 = new User("test1", "test1", "test1@test1.dk", "nameTest", 23);
        User userr2 = new User("test2", "test2", "test2@test2.dk", "nameTest2", 23);
        users.add(userr1);
        users.add(userr2);

        userRepository.saveAll(users);

 */





    }
}
