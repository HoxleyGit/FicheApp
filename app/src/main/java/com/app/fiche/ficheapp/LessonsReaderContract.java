package com.app.fiche.ficheapp;

import android.provider.BaseColumns;

/**
 * Created by kamil on 24.03.18.
 */

public final class LessonsReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LessonsReaderContract() {}

    /* Inner class that defines the table contents */
    public static class LessonsEntry implements BaseColumns {
        public static final String TABLE_NAME = "lessons";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_CATEGORY = "category";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_NAME_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_NAME_NAME +" VARCHAR(200), "
                        + COLUMN_NAME_DESCRIPTION +" VARCHAR(200), "
                        +  COLUMN_NAME_CATEGORY + " REFERENCES " + CategoriesReaderContract.CategoriesEntry.TABLE_NAME + "("
                        + CategoriesReaderContract.CategoriesEntry.COLUMN_NAME_NAME + "))";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + LessonsEntry.TABLE_NAME;
    }
}
