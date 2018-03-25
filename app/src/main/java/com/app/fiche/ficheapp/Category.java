package com.app.fiche.ficheapp;


import java.util.LinkedList;
import java.util.List;

public class Category {
    private String name;
    private List<Lesson> lessons;

    public Category (String name) {
        this.name = name;
        this.lessons = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

}
