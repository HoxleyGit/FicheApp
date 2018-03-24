package com.app.fiche.ficheapp;


import java.util.LinkedList;

public class Category {
    private String name;
    private LinkedList<Lesson> lessons;

    public Category(String name, LinkedList<Lesson> lessons) {
        this.name = name;
        this.lessons = lessons;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Lesson> getLessons() {
        return lessons;
    }

}
