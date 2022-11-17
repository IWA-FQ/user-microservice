package com.example.microservicetemplate.controllers;

import com.example.microservicetemplate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;


@RestController

public class MyController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api")
    public String resource(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Resource accessed by: %s (with subjectId: %s)" ,
                jwt.getClaims().get("preferred_username"),
                jwt.getSubject());
    }
    @GetMapping("/api/test")
    public Principal test(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        return principal;
    }
    @GetMapping("/api/user")
    public String user() {
        return userRepository.findAll().toString();

    }

}
