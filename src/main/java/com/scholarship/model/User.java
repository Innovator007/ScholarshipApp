package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name="`user`")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 65)
    private String name;

    @NotNull
    @Size(max = 25)
    private String username;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 20)
    private String password;

    @NotNull
    private int role;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Philantropist philantropist;

    public User() { }

    public User(String name, String username, String email, String password, int role) {
    	this.setName(name);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }

    public User(int id, String name, String username, String email, String password) {
    	this.setId(id);
    	this.setName(name);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }

    public int getId() {
    	return id;
    }

    public String getName() {
    	return name;
    }

    public String getUsername() {
    	return username;
    }

    public String getEmail() {
    	return email;
    }

    public String getPassword() {
        return password;
    }

    public Philantropist getPhilantropist(){
        return philantropist;
    }

    public Student getStudent(){
        return student;
    }

    public int getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public void setEmail(String email) {
    	this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhilanthropist(Philantropist philantropist){
        this.philantropist = philantropist;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
