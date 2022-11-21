package com.polylink.usermicroservice.controllers;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.polylink.usermicroservice.entity.Role;
import com.polylink.usermicroservice.entity.User;
import com.polylink.usermicroservice.repositories.RoleRepository;
import com.polylink.usermicroservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class MyController
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/testjwt")
    public Jwt resource(@AuthenticationPrincipal Jwt jwt) {
        return jwt;
    }
    @GetMapping("/testprincipal")
    public Principal test(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        return principal;
    }

    @GetMapping
    public List<User> getAllUser() {
        List<User> userlist = userRepository.findAllByRealm("polylink");
        return userlist;
    }
    @GetMapping("{id}")
    public Optional<User> getUser(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/role/{role}")
    public List<User> getUserByRole(@PathVariable String role) {
        List<User> user = userRepository.findByRole_Name(role);
        return user;
    }
    @PatchMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id,@RequestBody User user){
        return ResponseEntity.ok().build();
    }
    @GetMapping("role/change")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal Jwt jwt){
        jwt.getSubject();
        List<String> roles = (List<String>) jwt.<JSONObject>getClaim("realm_access").get("roles");
        Role role = roleRepository.findByName("recruiter");


        return ResponseEntity.ok().build();
    }


}
