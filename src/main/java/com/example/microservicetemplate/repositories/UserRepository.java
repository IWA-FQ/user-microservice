package com.example.microservicetemplate.repositories;

import com.example.microservicetemplate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


     List<User> findAll() ;
}
