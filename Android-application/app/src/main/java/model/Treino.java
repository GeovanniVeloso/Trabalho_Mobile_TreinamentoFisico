package model;

import java.time.LocalDate;
import java.util.List;

public abstract class Treino {

    private int id;
    private LocalDate date;
    private String muscularGroup;

    private String exercises;
    public Treino(){
        super();
    }

    public String toString() {
        return "Treino feito em "+date+" para "+muscularGroup;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(String muscularGroup) {
        this.muscularGroup = muscularGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }
}
