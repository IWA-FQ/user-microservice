package com.polylink.usermicroservice.repositories;


import com.polylink.usermicroservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {


     List<User> findAll() ;
     List<User> findAllByRealm(String realm) ;
     List<User> findByRole_Name(String name);




}
