package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name="`scholarship`")
public class Scholarship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    private String description;

    @NotNull
    private int amount;

    public Scholarship() { }

    public Scholarship(String title, String description, int amount) {
    	this.setTitle(title);
    	this.setDescription(description);
    	this.setAmount(amount);
    }

    public Scholarship(int id, String title, String description, int amount) {
    	this.setId(id);
    	this.setTitle(title);
    	this.setDescription(description);
    	this.setAmount(amount);
    }

    public int getId() {
    	return id;
    }

    public String getTitle() {
    	return title;
    }

    public String getDescription() {
    	return description;
    }

    public int getAmount() {
    	return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
    	this.description = description;
    }

    public void setAmount(int amount) {
    	this.amount = amount;
    }

    @Override
    public String toString() {
        return "Scholarship {" +
                "Id=" + id +
                ", Title='" + title + '\'' +
                ", Description='" + description + '\'' +
                ", Amount='" + amount + '\'' +
                '}';
    }

}
