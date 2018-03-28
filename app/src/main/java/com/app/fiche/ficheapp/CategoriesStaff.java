package com.app.fiche.ficheapp;

import java.util.ArrayList;

/**
 * Created by Łukasz on 28.03.2018.
 */

public class CategoriesStaff {

    private ArrayList<CategoriesDataBase> categoriesDataset;

    public CategoriesStaff() {
        this.categoriesDataset = new ArrayList<>();
    }

    public boolean addCategory(CategoriesDataBase categoriesDataBase) {
        categoriesDataset.add(categoriesDataBase);
        return true;
    }

    public ArrayList<CategoriesDataBase> getCategoriesDataset() {
        return categoriesDataset;
    }

    public String getNameOfCateogry(int position) {
        return categoriesDataset.get(position).getName();
    }
}



