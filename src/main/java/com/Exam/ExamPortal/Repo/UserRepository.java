package com.Exam.ExamPortal.Repo;

import com.Exam.ExamPortal.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String name);

}
