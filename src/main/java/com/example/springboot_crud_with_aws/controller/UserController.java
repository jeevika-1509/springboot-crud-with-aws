package com.example.springboot_crud_with_aws.controller;

import com.example.springboot_crud_with_aws.entity.User;
import com.example.springboot_crud_with_aws.exception.ResourceNotFoundException;
import com.example.springboot_crud_with_aws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value="id") long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not fond with id:"+userId));
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user,@PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not fond with id:"+userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not fond with id:"+userId));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

}
