package dat3.adventureXP.configuration;

import dat3.adventureXP.dto.ReservationResponse;
import dat3.adventureXP.entity.Activity;
import dat3.adventureXP.entity.Equipment;
import dat3.adventureXP.entity.Reservation;
import dat3.adventureXP.entity.User;
import dat3.adventureXP.repository.ActivityRepository;
import dat3.adventureXP.repository.EquipmentRepository;
import dat3.adventureXP.repository.ReservationRepository;
import dat3.adventureXP.repository.UserRepository;
import dat3.adventureXP.service.ReservationService;
import dat3.adventureXP.service.UserService;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    ActivityRepository activityRepository;
    UserRepository userRepository;
    ReservationRepository reservationRepository;
    UserService userService;
    ReservationService reservationService;
    PasswordEncoder passwordEncoder;
    String passwordUsedByAll;
    EquipmentRepository equipmentRepository;


    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder,
                         UserRepository userRepository, ActivityRepository activityRepository,
                         ReservationRepository reservationRepository, EquipmentRepository equipmentRepository, UserService userService, ReservationService reservationService) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.passwordEncoder = passwordEncoder;
        passwordUsedByAll = "test12";
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.reservationRepository = reservationRepository;
        this.equipmentRepository = equipmentRepository;
        this.userService = userService;
        this.reservationService = reservationService;
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
        user5.addRole(Role.USER);
        userWithRolesRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        User user11 = new User("test1", "test1", "test1@test1.dk", "nameTest", 23);
        User user22 = new User("user22", "pass1", "user1@dk.dk", "Osama", 15);
        user22.addRole(Role.ADMIN);
        user22.addRole(Role.EMPLOYEE);
        User user23 = new User("user23", "pass1", "user23@dk.dk", "Osama", 15);
        User user24 = new User("user24", "pass1", "user24@dk.dk", "Osama", 15);
        userRepository.saveAll(Arrays.asList(user11, user22, user23, user24));

        Activity activity1 = new Activity(16, "Early morning Tennis Practice", user22, "Open", "Tennis Practice");
        Activity activity2 = new Activity(18, "Scenic Hiking Adventure", user23, "Closed", "Guided Hike");
        Activity activity3 = new Activity(21, "Italian Cooking Class", user23, "Open", "Italian Cooking workshop");
        Activity activity4 = new Activity(18, "Friendly Golf Tournament", user23, "Open", "Golf Tournament");


        activityRepository.saveAll(Arrays.asList(activity1, activity2, activity3, activity4));

        Reservation reservation = new Reservation(LocalDate.now().plusDays(10), user22);
        reservation.addActivity(activity3);
        Reservation reservation2 = new Reservation(LocalDate.now().plusDays(20), user24);
        reservation2.addActivity(activity3);
        reservationRepository.save(reservation);
        reservationRepository.save(reservation2);

        Equipment e1 = new Equipment("Tennis racket", "Available", activity2);
        Equipment e2 = new Equipment("Tennis ball", "Available", activity2);
        Equipment e3 = new Equipment("Cones", "Unavailable", activity3);
        equipmentRepository.saveAll(Arrays.asList(e1, e2, e3));

        /* List<ReservationResponse> activityReservations = reservationService.getReservationsForActivity("Activity 3");
        System.out.println(activityReservations.size());

        List<ReservationResponse> reservationResponse = reservationService.getReservationsMadeByUser("user22");
        System.out.println(reservationResponse.size());
*/

    }
}
