package com.polylink.usermicroservice.entity;


import javax.persistence.*;


@Entity
@Table(name = "user_attribute")
public class Attribute {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;


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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
