package com.app.fiche.ficheapp;


import java.util.LinkedList;

public class Lesson {
    private String name;
    private String desc;
    private LinkedList<Fiche> fiches;
    private Category category;

    public Lesson(String name, String desc, LinkedList<Fiche> fiches, Category category) {
        this.name = name;
        this.desc = desc;
        this.fiches = fiches;
        this.category = category;
    }

    public String getName() {

        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Category getCategory() {
        return category;
    }

    public LinkedList<Fiche> getFiches() {
        return fiches;
    }
}
