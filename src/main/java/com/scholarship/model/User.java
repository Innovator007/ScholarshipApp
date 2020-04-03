package com.scholarship.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String username;
    private String email;
    private String password;

    public User() { }

    public User(String name, String username, String email, String password) {
    	this.setName(name);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    public User(int id, String name, String username, String email, String password) {
    	this.setId(id);
    	this.setName(name);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
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

    @Override
    public String toString() {
        return "User {" +
                "Id=" + id +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Username='" + username + '\'' +
                '}';
    }

}
