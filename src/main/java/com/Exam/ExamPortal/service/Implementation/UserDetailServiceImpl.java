package com.Exam.ExamPortal.service.Implementation;

import com.Exam.ExamPortal.Model.User;
import com.Exam.ExamPortal.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("User Detail Service");
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("No User Found !!");
        }else{
            System.out.println("User Found !!");
        }

        return user;
    }
}
