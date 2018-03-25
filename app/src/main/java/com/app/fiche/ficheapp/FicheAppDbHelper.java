package com.app.fiche.ficheapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil on 24.03.18.
 */

public class FicheAppDbHelper extends SQLiteOpenHelper {
    //some finals for configuration and future use
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FicheApp.db";

    private final String ALL_CATEGORIES_QUERY = "SELECT * FROM "
            + CategoriesReaderContract.CategoriesEntry.TABLE_NAME;
    private final String ALL_LESSONS_QUERY = "SELECT * FROM "
            + LessonsReaderContract.LessonsEntry.TABLE_NAME;
    private final String ALL_FICHES_QUERY = "SELECT * FROM "
            + FichesReaderContract.FichesEntry.TABLE_NAME;


    /**
     * Simply constructor based on SQLiteOpenHelper constructor
     *
     * @param context
     */
    public FicheAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Method that creates entries in database
     *
     * @param db - database
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriesReaderContract.CategoriesEntry.SQL_CREATE_ENTRIES);
        db.execSQL(LessonsReaderContract.LessonsEntry.SQL_CREATE_ENTRIES);
        db.execSQL(FichesReaderContract.FichesEntry.SQL_CREATE_ENTRIES);
    }


    /**
     * Method used for upgrade database if needed (old and new version are not implemented)
     *
     * @param db         - database
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(CategoriesReaderContract.CategoriesEntry.SQL_DELETE_ENTRIES);
        db.execSQL(LessonsReaderContract.LessonsEntry.SQL_DELETE_ENTRIES);
        db.execSQL(FichesReaderContract.FichesEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    /**
     * Method that can decrease version of database, but it's not really implemented
     *
     * @param db         - database
     * @param oldVersion
     * @param newVersion
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    /**
     * Method used to add new Category to database
     *
     * @param db           - database
     * @param categoryName
     * @return true - category added, false - category cannot be added
     */
    public boolean addCategory(SQLiteDatabase db, String categoryName) {
        if (categoryName.equals("")) {
            //cannot be added
            return false;
        }
        //adding
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesReaderContract.CategoriesEntry.COLUMN_NAME_NAME, categoryName);
        db.insert(CategoriesReaderContract.CategoriesEntry.TABLE_NAME, null, contentValues);
        //added
        return true;
    }


    /**
     * Method used to add new lesson to database
     *
     * @param db           - database
     * @param lessonName
     * @param lessonDesc
     * @param categoryName
     * @return true - lesson added, false - lesson cannot be added
     */
    public boolean addLesson(SQLiteDatabase db, String lessonName, String lessonDesc, String categoryName) {
        if (lessonName.equals("")
                || categoryName.equals("")) {
            //cannot be added
            return false;
        }
        //adding
        ContentValues contentValues = new ContentValues();
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_NAME, lessonName);
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_DESCRIPTION, lessonDesc);
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_CATEGORY, categoryName);
        db.insert(LessonsReaderContract.LessonsEntry.TABLE_NAME, null, contentValues);
        //added
        return true;
    }


    /**
     * Overloaded method for addLesson (option without specify lesson description
     *
     * @param db           - database
     * @param lessonName
     * @param categoryName
     * @return true - lesson added, false - lesson cannot be added
     */
    public boolean addLesson(SQLiteDatabase db, String lessonName, String categoryName) {
        return addLesson(db, lessonName, "", categoryName);
    }


    /**
     * Method used to add new Fiche to database
     *
     * @param db            - database
     * @param ficheQuestion
     * @param ficheAnswer
     * @param lessonName
     * @return true - fiche added, false - fiche cannot be added
     */
    public boolean addFiche(SQLiteDatabase db, String ficheQuestion, String ficheAnswer, String lessonName) {
        if (ficheQuestion.equals("")
                || ficheAnswer.equals("")
                || lessonName.equals("")) {
            //cannot be added
            return false;
        }
        //adding
        ContentValues contentValues = new ContentValues();
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_QUESTION, ficheQuestion);
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_ANSWER, ficheAnswer);
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_LESSONS, lessonName);
        db.insert(FichesReaderContract.FichesEntry.TABLE_NAME, null, contentValues);
        //added
        return true;
    }


    /**
     * Method used to generate object of class DataRepository
     * which handles data downloaded from database and which is used to operate this data though
     * appliaction
     *
     * @param db - database
     * @return object of class DataRepository used to operate data downloaded from database
     */
    public DataRepository transportDbToRepo(SQLiteDatabase db) {
        List<Category> categories = getAllCategories(db);
        List<Lesson> lessons = getAllLessons(db, categories);
        List<Fiche> fiches = getAllFiches(db, lessons);
        return new DataRepository(categories, lessons, fiches);
    }


    /**
     * Method that is used to generate a list of categories downloaded from database,
     * but every single category doesn't have any lessons yet
     *
     * @param db - database
     * @return list of categories without lessons or null
     */
    private List<Category> getAllCategories(SQLiteDatabase db) {
        List<Category> categoryList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_CATEGORIES_QUERY, null);
        while (cursor.moveToNext()) {
            String currentName = cursor.getString(0);
            if (!currentName.equals("")) {
                categoryList.add(new Category(currentName));
            }
        }
        cursor.close();
        if (categoryList.isEmpty()) {
            return null;
        }
        return categoryList;
    }


    /**
     * Method that is used to generate a list of lessons downloaded from database,
     * but every single lesson doesn't have any fiche yet, this method also
     * fills every lesson to category in given categories list
     *
     * @param db         - database
     * @param categories - list of categories to fill
     * @return list of categories without lessons or null
     */
    private List<Lesson> getAllLessons(SQLiteDatabase db, List<Category> categories) {
        if (categories == null) {
            return null;
        }

        List<Lesson> lessonList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_LESSONS_QUERY, null);
        while (cursor.moveToNext()) {
            String currentName = cursor.getString(0);
            String currentDesc = cursor.getString(1);
            String currentCategoryName = cursor.getString(2);
            if (!currentName.equals("") && !currentCategoryName.equals("")) {
                Category currentCategory = null;
                for (Category category : categories) {
                    if (currentCategoryName.equals(category.getName())) ;
                    currentCategory = category;
                }
                if (currentCategory != null) {
                    Lesson currentLesson = new Lesson(currentName, currentDesc, currentCategory);
                    lessonList.add(currentLesson);
                    currentCategory.getLessons().add(currentLesson);
                }
            }
        }
        cursor.close();
        if (lessonList.isEmpty()) {
            return null;
        }
        return lessonList;
    }


    /**
     * /**
     * Method that is used to generate a list of fiches downloaded from database,
     * this method also fills every fiche to lesson in given lessons list
     *
     * @param db      - database
     * @param lessons - list of lessons to fill
     * @return list of fiches or null
     */
    private List<Fiche> getAllFiches(SQLiteDatabase db, List<Lesson> lessons) {
        if (lessons == null) {
            return null;
        }

        List<Fiche> ficheList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_FICHES_QUERY, null);
        while (cursor.moveToNext()) {
            String currentQuestion = cursor.getString(0);
            String currentAnswer = cursor.getString(1);
            String currentLessonName = cursor.getString(2);
            if (!currentQuestion.equals("") && !currentAnswer.equals("") && !currentLessonName.equals("")) {
                Lesson currentLesson = null;
                for (Lesson lesson : lessons) {
                    if (currentLessonName.equals(lesson.getName())) ;
                    currentLesson = lesson;
                }
                if (currentLesson != null) {
                    Fiche currentFiche = new Fiche(currentQuestion, currentAnswer, currentLesson);
                    ficheList.add(currentFiche);
                    currentLesson.getFiches().add(currentFiche);
                }
            }
        }
        cursor.close();
        if (ficheList.isEmpty()) {
            return null;
        }
        return ficheList;
    }
}
