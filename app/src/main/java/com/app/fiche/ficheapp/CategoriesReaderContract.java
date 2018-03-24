package com.app.fiche.ficheapp;

import android.provider.BaseColumns;

/**
 * Created by kamil on 24.03.18.
 */

public final class CategoriesReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CategoriesReaderContract() {}

    /* Inner class that defines the table contents */
    public static class CategoriesEntry implements BaseColumns {
        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + CategoriesEntry.TABLE_NAME + " (" +
                        CategoriesEntry.COLUMN_NAME_NAME + " VARCHAR(200) PRIMARY KEY)";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CategoriesEntry.TABLE_NAME;
    }
}