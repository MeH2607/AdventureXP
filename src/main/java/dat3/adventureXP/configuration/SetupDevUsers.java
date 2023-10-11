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
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
        userWithRolesRepository.save(user5);


        Activity activity1 = new Activity(10, "Tennis", user1, "Closed", "Tennis");
        activityRepository.save(activity1);
        System.out.println("Activity 1: " + activity1.getName());

        User user22 = new User("user22", "pass1", "user1@dk.dk", "Osama", 15);
        user22.addRole(Role.ADMIN);
        user22.addRole(Role.EMPLOYEE);
        userService.assignActivityToEmployee(user22, activity1);
        System.out.println();


        List<User> users = new ArrayList<>();
        User userr1 = new User("test1", "test1", "test1@test1.dk", "nameTest", 23);
        User userr2 = new User("test2", "test2", "test2@test2.dk", "nameTest2", 23);
        users.add(userr1);
        users.add(userr2);
        userRepository.saveAll(users);


        User user23 = new User("user23", "pass1", "user23@dk.dk", "Osama", 15);
        User user24 = new User("user24", "pass1", "user24@dk.dk", "Osama", 15);
        userRepository.save(user22);
        userRepository.save(user23);
        userRepository.save(user24);
        Activity activity2 = new Activity(10, "Really nice", user23, "Working", "Activity 2");
        Activity activity3 = new Activity(10, "Real", user23, "Working", "Activity 3");
        Activity golf = new Activity(10, "nice golf", user23, "Working", "Golf");


        activityRepository.save(activity2);
        activityRepository.save(activity3);
        activityRepository.save(golf);


        Reservation reservation = new Reservation(LocalDate.now().plusDays(10), user22);
        Reservation reservation2 = new Reservation(LocalDate.now().plusDays(20), user24);


        reservation.addActivity(activity3);

        reservation2.addActivity(activity3);



        reservationRepository.save(reservation);
        reservationRepository.save(reservation2);
       List<ReservationResponse> activityReservations = reservationService.getReservationsForActivity("Activity 3");
        System.out.println(activityReservations.size());

        List<ReservationResponse> reservationResponse = reservationService.getReservationsMadeByUser("user22");
        System.out.println(reservationResponse.size());


        Equipment e1 = new Equipment("Tennis racket", "Working", activity2);
        Equipment e2 = new Equipment("Tennis ball", "Working", activity2);
        Equipment e3 = new Equipment("Cones", "Working", activity3);

        equipmentRepository.save(e1);
        equipmentRepository.save(e2);
        equipmentRepository.save(e3);
    }
}
