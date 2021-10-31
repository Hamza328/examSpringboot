package com.Exam.ExamPortal.service;

import com.Exam.ExamPortal.Model.User;
import com.Exam.ExamPortal.Model.UserRole;

import java.util.Set;

public interface Userservice {

    public User createUser(User user, Set<UserRole> userrole) throws Exception;

    public User getUser(String name);

    public void deleteUser(Long userId);
}
