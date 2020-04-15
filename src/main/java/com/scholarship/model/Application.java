package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="`application`")
public class Application implements Serializable {

    public Application() { }

    public Application(String aboutyourself) {
        this.setAboutyourself(aboutyourself);
    }

    public Application(int id, String aboutyourself) {
        this.setId(id);
        this.setAboutyourself(aboutyourself);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    private String aboutyourself;

    public String getAboutyourself() {
        return aboutyourself;
    }

    public void setAboutyourself(String aboutyourself) {
        this.aboutyourself = aboutyourself;
    }

}
