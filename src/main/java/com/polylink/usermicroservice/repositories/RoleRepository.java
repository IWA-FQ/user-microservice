package com.polylink.usermicroservice.repositories;

import com.polylink.usermicroservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);

}
