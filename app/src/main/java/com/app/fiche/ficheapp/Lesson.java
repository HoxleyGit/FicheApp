package com.app.fiche.ficheapp;


import java.util.LinkedList;
import java.util.List;

public class Lesson {
    private int id;
    private String name;
    private String desc;
    private List<Fiche> fiches;
    private Category category;

    public Lesson(int id, String name, String desc, Category category) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.fiches = new LinkedList<>();
        this.category = category;
    }

    public Lesson(String name, String desc, Category category) {
        this(DataRepository.getNextLessonId(), name, desc, category);
    }

    public Lesson(int id, String name, String desc) {
        this(id, name, desc, null);
    }

    public Lesson(String name, String desc) {
        this(name, desc, null);
    }

    public Lesson(String name) {
        this(name, "");
    }

    public String getName() {

        return name;
    }

    public int getId() {
        return id;
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
