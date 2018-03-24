package com.app.fiche.ficheapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kamil on 24.03.18.
 */

public class FicheAppDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FicheApp.db";


    private final String ALL_CATEGORIES_QUERY = "SELECT * FROM "
            + CategoriesReaderContract.CategoriesEntry.TABLE_NAME;
    private final String ALL_LESSONS_QUERY = "SELECT * FROM "
            + LessonsReaderContract.LessonsEntry.TABLE_NAME;
    private final String ALL_FICHES_QUERY = "SELECT * FROM "
            + FichesReaderContract.FichesEntry.TABLE_NAME;


    public FicheAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriesReaderContract.CategoriesEntry.SQL_CREATE_ENTRIES);
        db.execSQL(LessonsReaderContract.LessonsEntry.SQL_CREATE_ENTRIES);
        db.execSQL(FichesReaderContract.FichesEntry.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(CategoriesReaderContract.CategoriesEntry.SQL_DELETE_ENTRIES);
        db.execSQL(LessonsReaderContract.LessonsEntry.SQL_DELETE_ENTRIES);
        db.execSQL(FichesReaderContract.FichesEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addCategory(SQLiteDatabase db, Category category) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesReaderContract.CategoriesEntry.COLUMN_NAME_NAME, category.getName());
        db.insert(CategoriesReaderContract.CategoriesEntry.TABLE_NAME, null, contentValues);
    }

    public void addLesson(SQLiteDatabase db, Category category, Lesson lesson) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_NAME, lesson.getName());
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_DESCRIPTION, lesson.getDesc());
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_CATEGORY, category.getName());
        db.insert(LessonsReaderContract.LessonsEntry.TABLE_NAME, null, contentValues);
    }

    public void addFiche(SQLiteDatabase db, Lesson lesson, Fiche fiche) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_QUESTION, fiche.getQuestion());
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_ANSWER, fiche.getAnswer());
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_LESSONS, lesson.getName());
        db.insert(FichesReaderContract.FichesEntry.TABLE_NAME, null, contentValues);
    }

    public DataRepository transportDbToRepo(SQLiteDatabase db) {
        List<Category> categories = getAllCategories(db);

        List<Lesson> lessons = getAllLessons(db, categories);

        List<Fiche> fiches = getAllFiches(db, lessons);

        return new DataRepository(categories, lessons, fiches);
    }

    private List<Category> getAllCategories(SQLiteDatabase db){
        List<Category> categoryList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_CATEGORIES_QUERY, null);
        while(cursor.moveToNext()){
            String currentName = cursor.getString(0);
            if(!currentName.equals("")){
                categoryList.add(new Category(currentName, null));
            }
        }
        if(categoryList.isEmpty()){
            return null;
        }
        return categoryList;
    }

    private List<Lesson> getAllLessons(SQLiteDatabase db, List<Category> categories) {
        if(categories == null){
            return null;
        }

        List<Lesson> lessonList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_LESSONS_QUERY, null);
        while(cursor.moveToNext()){
            String currentName = cursor.getString(0);
            String currentDesc = cursor.getString(1);
            String currentCategoryName = cursor.getString(2);
            if(!currentName.equals("") && !currentDesc.equals("") && !currentCategoryName.equals("")){
                Category currentCategory = null;
                for(Category category : categories){
                    if(currentCategoryName.equals(category.getName()));
                    currentCategory = category;
                }
                if(currentCategory != null) {
                    Lesson currentLesson = new Lesson(currentName, currentDesc, null, currentCategory);
                    lessonList.add(currentLesson);
                    currentCategory.getLessons().add(currentLesson);
                }
            }
        }
        if(lessonList.isEmpty()){
            return null;
        }
        return lessonList;
    }

    private List<Fiche> getAllFiches(SQLiteDatabase db, List<Lesson> lessons){
        if(lessons == null){
            return null;
        }

        List<Fiche> ficheList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_FICHES_QUERY, null);
        while(cursor.moveToNext()){
            String currentQuestion = cursor.getString(0);
            String currentAnswer = cursor.getString(1);
            String currentLessonName = cursor.getString(2);
            if(!currentQuestion.equals("") && !currentAnswer.equals("") && !currentLessonName.equals("")){
                Lesson currentLesson = null;
                for(Lesson lesson : lessons){
                    if(currentLessonName.equals(lesson.getName()));
                    currentLesson = lesson;
                }
                if(currentLesson != null) {
                    Fiche currentFiche = new Fiche(currentQuestion, currentAnswer, currentLesson);
                    ficheList.add(currentFiche);
                    currentLesson.getFiches().add(currentFiche);
                }
            }
        }
        if(ficheList.isEmpty()){
            return null;
        }
        return ficheList;
    }
}
