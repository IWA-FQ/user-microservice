package com.polylink.usermicroservice.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "\"user_entity\"")
public class User {
    @Id
    private String id;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "realm_id")
    private String realm;

    @ManyToMany
    @JoinTable(name = "user_role_mapping")
    @JoinColumn(name = "user_id")
    List<Role> role;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Attribute> attribute;

    public List<Attribute> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<Attribute> attribute) {
        this.attribute = attribute;
    }
}
