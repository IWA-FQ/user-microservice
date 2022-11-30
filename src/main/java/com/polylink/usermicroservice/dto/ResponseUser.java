package com.polylink.usermicroservice.dto;

import com.polylink.usermicroservice.models.Role;
import com.polylink.usermicroservice.models.User;

import java.util.List;
import java.util.Set;

public class ResponseUser {
    private Long id;

    private String email;

    private String firstname;
    private String lastname;

    private String city;

    private Integer city_code;

    private String work_field;
    private String cv_link;
    private Set<Role> roles;

    public ResponseUser(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.city = user.getCity();
        this.city_code = user.getCity_code();
        this.work_field = user.getWork_field();
        this.cv_link = user.getCv_link();
        this.roles = user.getRoles();
    }
    public static List<ResponseUser> toList(List<User> users){
        return users.stream().map(ResponseUser::new).toList();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCity_code() {
        return city_code;
    }

    public void setCity_code(Integer city_code) {
        this.city_code = city_code;
    }

    public String getWork_field() {
        return work_field;
    }

    public void setWork_field(String work_field) {
        this.work_field = work_field;
    }

    public String getCv_link() {
        return cv_link;
    }

    public void setCv_link(String cv_link) {
        this.cv_link = cv_link;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
