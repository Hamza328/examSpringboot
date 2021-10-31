package com.Exam.ExamPortal.Controller;

import com.Exam.ExamPortal.Model.Role;
import com.Exam.ExamPortal.Model.User;
import com.Exam.ExamPortal.Model.UserRole;
import com.Exam.ExamPortal.helper.UserFoundException;
import com.Exam.ExamPortal.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody User user) throws Exception {
        try {

            Set<UserRole> roles = new HashSet<>();

            Role role = new Role();
            role.setRoleName("Normal");
//              role.setRoleName("Admin");

            user.setPassword(this.passwordEncoder.encode(user.getPassword()));

            UserRole userRole = new UserRole();

            userRole.setRole(role);
            userRole.setUser(user);

            roles.add(userRole);

            return ResponseEntity.ok(userService.createUser(user, roles));
        }catch (UserFoundException e) {
            e.printStackTrace();

            return exceptionHandler(e);
        }
    }

    @GetMapping("/{username}")
    public User getuser(@PathVariable("username") String username){
        return  userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void  Deleteuser(@PathVariable("userId") Long Id){
        userService.deleteUser(Id);
    }



    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex);
    }



}
