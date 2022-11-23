package com.polylink.usermicroservice.controllers;

import com.polylink.usermicroservice.models.User;
import com.polylink.usermicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<User> getAllUser() {
        List<User> userlist = userRepository.findAll();
        return userlist;
    }
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id,@RequestBody User user){
        User user1 = new User(id,user.getEmail(),user.getPassword(), user.getFirstname(), user.getLastname(), user.getCity(), user.getCity_code(), user.getWork_field(),user.getCv_link());
        userRepository.save(user1);
        return ResponseEntity.ok().build();
    }

}
