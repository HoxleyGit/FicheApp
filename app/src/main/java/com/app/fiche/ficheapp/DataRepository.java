package com.app.fiche.ficheapp;


import java.util.List;

public class DataRepository {
    private List<Category> categories;
    private List<Lesson> lessons;
    private List<Fiche> fiches;

    public DataRepository(List<Category> categories, List<Lesson> lessons, List<Fiche> fiches) {
        this.categories = categories;
        this.lessons = lessons;
        this.fiches = fiches;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Fiche> getFiches() {
        return fiches;
    }
}
