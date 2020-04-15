package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name="`application`")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 100)
    private String answer;

    public Application() { }

    public Application(String answer) {
    	this.setAnswer(answer);
    }

    public Application(int id, String answer) {
    	this.setId(id);
    	this.setAnswer(answer);
    }

    public int getId() {
    	return id;
    }

    public String getAnswer() {
    	return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
