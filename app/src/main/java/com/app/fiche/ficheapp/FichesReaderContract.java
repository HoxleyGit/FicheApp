package com.app.fiche.ficheapp;

import android.provider.BaseColumns;

/**
 * Created by kamil on 24.03.18.
 */

public final class FichesReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FichesReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FichesEntry implements BaseColumns {
        public static final String TABLE_NAME = "fiches";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_QUESTION = "question";
        public static final String COLUMN_NAME_ANSWER = "answer";
        public static final String COLUMN_NAME_LESSONS = "lessons";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + FichesEntry.TABLE_NAME + " (" +
                        FichesEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        FichesEntry.COLUMN_NAME_QUESTION + " VARCHAR(200)," +
                        FichesEntry.COLUMN_NAME_ANSWER + " VARCHAR(200), " +
                        FichesEntry.COLUMN_NAME_LESSONS +
                        " REFERENCES " + FichesEntry.COLUMN_NAME_LESSONS +
                        "("+ LessonsReaderContract.LessonsEntry.COLUMN_NAME_ID +"))";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FichesEntry.TABLE_NAME;
    }
}
