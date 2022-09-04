package com.example.springrest;

import com.example.springrest.exceptions.UserNotFoundException;


//1
public class UserNotFoundAdvice {

    //2
    String employeeNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }
}

