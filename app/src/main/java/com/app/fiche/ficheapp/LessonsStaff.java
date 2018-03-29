package com.app.fiche.ficheapp;

import java.util.ArrayList;

/**
 * Created by Łukasz on 29.03.2018.
 */

public class LessonsStaff {

    //klasa obsługująca listę, dodawanie, usuwanie itp

    private ArrayList<LessonsDataBase> lessonsDataSet;

    public LessonsStaff() {
        this.lessonsDataSet = new ArrayList<>();
    }

    public boolean addLesson(LessonsDataBase lessonsDataBase) {
        lessonsDataSet.add(lessonsDataBase);
        return true;
    }

    public ArrayList<LessonsDataBase> getLessonsDataSet() {
        return lessonsDataSet;
    }

    public String getNameOfCLesson(int position) {
        return lessonsDataSet.get(position).getName();
    }
}



