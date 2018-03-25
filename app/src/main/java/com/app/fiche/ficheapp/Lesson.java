package com.app.fiche.ficheapp;


import java.util.LinkedList;
import java.util.List;

public class Lesson {
    private String name;
    private String desc;
    private List<Fiche> fiches;
    private Category category;

    public Lesson(String name, String desc, Category category) {
        this.name = name;
        this.desc = desc;
        this.fiches = new LinkedList<>();
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

    public List<Fiche> getFiches() {
        return fiches;
    }
}
