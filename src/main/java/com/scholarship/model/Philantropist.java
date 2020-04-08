package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="`philantropist`")
public class Philantropist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @Size(max = 100)
    private String institute;    

    @Size(max = 100)
    private String philantropistId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Philantropist() { }

    public Philantropist(String phoneNumber, String institute, String philantropistId) {
    	this.setPhoneNumber(phoneNumber);
        this.setInstitute(institute);
        this.setPhilantropistId(philantropistId);
    }

    public Philantropist(int id, String phoneNumber, String institute, String philantropistId) {
    	this.setId(id);
    	this.setPhoneNumber(phoneNumber);
        this.setInstitute(institute);
        this.setPhilantropistId(philantropistId);
    }

    public int getId() {
    	return id;
    }

    public String getPhoneNumber() {
    	return phoneNumber;
    }

    public String getInstitute() {
    	return institute;
    }

    public String getPhilantropistId() {
    	return philantropistId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setInstitute(String institute) {
    	this.institute = institute;
    }

    public void setPhilantropistId(String philantropistId) {
    	this.philantropistId = philantropistId;
    }
    
}
