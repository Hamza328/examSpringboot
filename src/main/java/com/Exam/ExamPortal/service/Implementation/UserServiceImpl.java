package com.Exam.ExamPortal.service.Implementation;

import com.Exam.ExamPortal.Model.User;
import com.Exam.ExamPortal.Model.UserRole;
import com.Exam.ExamPortal.Repo.RoleRepository;
import com.Exam.ExamPortal.Repo.UserRepository;
import com.Exam.ExamPortal.helper.UserFoundException;
import com.Exam.ExamPortal.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements Userservice {

    @Autowired
    RoleRepository roleRepo;
    @Autowired
    UserRepository userRepo;


    @Override
    public User createUser(User user, Set<UserRole> userroles) throws Exception {
         User user1 = userRepo.findByUsername(user.getUsername());
         if(user1 != null){
             System.out.println("User is Already There");
             throw new UserFoundException();
         }else {
             System.out.println(userroles);
             for (UserRole ur:userroles){
                 System.out.println(ur.getRole());
                 roleRepo.save(ur.getRole());
             }
//             System.out.println(user);
            user.getUserRole().addAll(userroles);
//             System.out.println(user);

             User local= userRepo.save(user);
            return local;
         }

    }

    @Override
    public User getUser(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public void deleteUser(Long userId) {
         userRepo.deleteById(userId);
    }
}
