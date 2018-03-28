package com.app.fiche.ficheapp;

/**
 * Created by Łukasz on 28.03.2018.
 */

public class CategoriesDataBase extends CategoriesStaff {

    private String name;

    public CategoriesDataBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CategoriesDataBase addCategory(String name) {
        return new CategoriesDataBase(name);
    }


}
