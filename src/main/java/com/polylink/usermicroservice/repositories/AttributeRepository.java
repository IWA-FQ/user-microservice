package com.polylink.usermicroservice.repositories;

import com.polylink.usermicroservice.entity.Attribute;

import org.springframework.data.jpa.repository.JpaRepository;



public interface AttributeRepository extends JpaRepository<Attribute, String> {

}
