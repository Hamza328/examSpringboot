package com.Exam.ExamPortal.helper;

public class UserFoundException extends Exception{


    public UserFoundException() {
        super("User with this username is already Exist in DataBase!! Try with Another One");
    }

    public UserFoundException(String message) {
        super(message);
    }
}
