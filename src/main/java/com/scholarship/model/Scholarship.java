package com.scholarship.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="`scholarship`")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
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
