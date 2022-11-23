package com.polylink.usermicroservice.repository;

import com.polylink.usermicroservice.models.ERole;
import com.polylink.usermicroservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
