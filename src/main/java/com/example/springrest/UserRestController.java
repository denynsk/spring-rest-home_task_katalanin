package com.example.springrest;

import com.example.springrest.entity.User;
import com.example.springrest.exceptions.UserNotFoundException;
import com.example.springrest.service.RepositoryStubService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//1
public class UserRestController {


    @Autowired
    private RepositoryStubService repositoryService;

   // 2
    public List<User> listAllUsers() {
        return repositoryService.findAll();
    }

    //3
    public User getUserById(/*4*/ Long id) {
        User user = repositoryService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    //5
    public User createUser(/* 6 */ User newUser) {
        return repositoryService.saveUser(newUser);
    }

    //7
    public User updateUser(/* 8 */ Long id, /* 9 */ User updatedUser) {
        User userToUpdate = repositoryService.findUserById(id);
        if (userToUpdate != null) {
            userToUpdate.setFirstName(updatedUser.getFirstName());
            userToUpdate.setSecondName(updatedUser.getSecondName());
            userToUpdate.setPosition(updatedUser.getPosition());
            userToUpdate.setDepartment(updatedUser.getDepartment());
            return repositoryService.saveUser(id, userToUpdate);
        } else {
            updatedUser.setId(id);
            return repositoryService.saveUser(id, updatedUser);
        }
    }

   // 10
    public void deleteUser(/* 11 */ Long id) {
        repositoryService.deleteById(id);
    }


}
