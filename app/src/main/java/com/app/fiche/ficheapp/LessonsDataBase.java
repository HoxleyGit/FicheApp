package com.app.fiche.ficheapp;

/**
 * Created by Łukasz on 29.03.2018.
 */

//klasa przechowuje lekcje w liście
public class LessonsDataBase extends LessonsStaff {

    String name;

    public LessonsDataBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static LessonsDataBase addLesson(String name) {
        return new LessonsDataBase(name);
    }
}
