package org.example.crud.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min =2, max = 30,message = "Name should be between 2 and 30 char")
    @Column(name="NAME")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min =2, max = 30,message = "Surname should be between 2 and 30 char")
    @Column(name="SURNAME")
    private String surname;

    @Min(value = 0, message=" Age>0")
    @Column(name="AGE")
    private int age;

    @Column(name="EMAIL")
    @Email
    private String email;

    public User() {
    }



    public User(int id, String name, String surname, int age, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}














