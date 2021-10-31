package com.Exam.ExamPortal.Controller;

import com.Exam.ExamPortal.Config.jwtUtils;
import com.Exam.ExamPortal.Model.User;
import com.Exam.ExamPortal.Model.jwtRequest;
import com.Exam.ExamPortal.Model.jwtResponse;
import com.Exam.ExamPortal.service.Implementation.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailServiceImpl userDetail;
    @Autowired
    private jwtUtils jwtutils;


    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody jwtRequest jwtrequest) throws Exception {

        try {

            authenticate(jwtrequest.getUsername(),jwtrequest.getPassword());

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User Name Not Found!!");
        }

        UserDetails userdetail = userDetail.loadUserByUsername(jwtrequest.getUsername());
        String token = jwtutils.generateToken(userdetail);


        return ResponseEntity.ok(new jwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){

            throw new Exception("User Disabled"+ e.getMessage());

        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credential: "+ e.getMessage());
        }
    }

    @GetMapping("current-user")
    public User getUser(Principal principal){
        return ((User) userDetail.loadUserByUsername(principal.getName()));
    }

}
