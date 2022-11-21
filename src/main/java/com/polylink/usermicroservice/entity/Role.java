package com.polylink.usermicroservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "keycloak_role")
public class Role {
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "user_role_mapping")
    @JoinColumn(name = "role_id")
    List<User> user;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
