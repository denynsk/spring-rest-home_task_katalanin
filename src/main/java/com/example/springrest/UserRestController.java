package com.example.springrest;

import com.example.springrest.entity.User;
import com.example.springrest.exceptions.UserNotFoundException;
import com.example.springrest.service.RepositoryStubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {


    @Autowired
    private RepositoryStubService repositoryService;

   @GetMapping("AllUser")
    public List<User> listAllUsers() {
        return repositoryService.findAll();
    }

    @GetMapping("UserById/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        User user = repositoryService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @PostMapping("createUser")
    public User createUser(@RequestBody() User newUser) {
        return repositoryService.saveUser(newUser);
    }

    @PutMapping("updateUser")
    public User updateUser(@RequestParam("id") Long id, @RequestBody User updatedUser) {
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

   @DeleteMapping("DelUser")
    public void deleteUser(@RequestParam("id") Long id) {
        repositoryService.deleteById(id);
    }


}
