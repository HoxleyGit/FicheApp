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
     * @param db database
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriesReaderContract.CategoriesEntry.SQL_CREATE_ENTRIES);
        db.execSQL(LessonsReaderContract.LessonsEntry.SQL_CREATE_ENTRIES);
        db.execSQL(FichesReaderContract.FichesEntry.SQL_CREATE_ENTRIES);
    }


    /**
     * Method used for upgrade database if needed (old and new version are not implemented)
     *
     * @param db         database
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
     * @param db         database
     * @param oldVersion
     * @param newVersion
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    /**
     * Method used to add new Category to database
     *
     * @param db           database
     * @param category
     * @return true - category added, false - category cannot be added
     */
    public boolean addCategory(SQLiteDatabase db, Category category) {
        if (category.getName().equals("")) {
            //cannot be added
            return false;
        }
        //adding
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesReaderContract.CategoriesEntry.COLUMN_NAME_NAME, category.getName());
        db.insert(CategoriesReaderContract.CategoriesEntry.TABLE_NAME, null, contentValues);
        //added
        return true;
    }


    /**
     * Method used to add new lesson to database
     *
     * @param db           database
     * @param lesson
     * @param category
     * @return true - lesson added, false - lesson cannot be added
     */
    public boolean addLesson(SQLiteDatabase db, Lesson lesson, Category category) {
        if (lesson.getName().equals("")
                || category.getName().equals("")) {
            //cannot be added
            return false;
        }

        //checks if category is defined in database
        boolean categoryNotFound = !isCategory(db, category);
        if (categoryNotFound) {
            return false;
        }

        //adding
        ContentValues contentValues = new ContentValues();
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_NAME, lesson.getName());
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_DESCRIPTION, lesson.getDesc());
        contentValues.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_CATEGORY, category.getName());
        db.insert(LessonsReaderContract.LessonsEntry.TABLE_NAME, null, contentValues);
        //added
        return true;
    }


    /**
     * Method that checks if is in database a category with name given as a parameter
     *
     * @param db           database
     * @param category
     * @return true/false - found/not found
     */
    private boolean isCategory(SQLiteDatabase db, Category category) {
        List<Category> categories = getAllCategories(db);
        if (categories == null || categories.isEmpty()) {
            return false;
        }
        for (Category c : categories) {
            if (c.getName().equals(category.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method used to add new Fiche to database
     *
     * @param db            database
     * @param fiche
     * @param lesson
     * @return true - fiche added, false - fiche cannot be added
     */
    public boolean addFiche(SQLiteDatabase db, Fiche fiche, Lesson lesson) {
        if (fiche.getQuestion().equals("")
                || fiche.getAnswer().equals("")
                || lesson.getName().equals("")) {
            //cannot be added
            return false;
        }

        //checks if lesson is defined in database
        boolean lessonNotFound = !isLesson(db, lesson);
        if (lessonNotFound) {
            return false;
        }

        //adding
        ContentValues contentValues = new ContentValues();
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_QUESTION, fiche.getQuestion());
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_ANSWER, fiche.getAnswer());
        contentValues.put(FichesReaderContract.FichesEntry.COLUMN_NAME_LESSONS, lesson.getId());
        db.insert(FichesReaderContract.FichesEntry.TABLE_NAME, null, contentValues);
        //added
        return true;
    }


    /**
     * Method that checks if is in database a lesson with name given as a parameter
     *
     * @param db         database
     * @param lesson
     * @return true/false - found/not found
     */
    private boolean isLesson(SQLiteDatabase db, Lesson lesson) {
        List<Lesson> lessons = getAllLessons(db);
        if (lessons == null || lessons.isEmpty()) {
            return false;
        }
        for (Lesson l : lessons) {
            if (l.getId() == lesson.getId()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method that is used to generate a list of categories downloaded from database,
     * but every single category doesn't have any lessons yet
     *
     * @param db database
     * @return list of categories without lessons or null
     */
    public List<Category> getAllCategories(SQLiteDatabase db) {
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
     * @param db         database
     * @param categories list of categories to fill
     * @return list of categories without lessons or null
     */
    public List<Lesson> getAllLessons(SQLiteDatabase db, List<Category> categories) {
        if (categories == null) {
            return null;
        }

        List<Lesson> lessonList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_LESSONS_QUERY, null);
        while (cursor.moveToNext()) {
            int currentId = cursor.getInt(0);
            String currentName = cursor.getString(1);
            String currentDesc = cursor.getString(2);
            String currentCategoryName = cursor.getString(3);
            if (!currentName.equals("") && !currentCategoryName.equals("")) {
                Category currentCategory = null;
                for (Category category : categories) {
                    if (currentCategoryName.equals(category.getName())) ;
                    currentCategory = category;
                }
                if (currentCategory != null) {
                    Lesson currentLesson = new Lesson(currentId, currentName, currentDesc, currentCategory);
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
     * Method that is used to generate a list of lessons downloaded from database,
     * but every single lesson doesn't have any fiche yet, this method doesn't
     * fill any lesson to category
     *
     * @param db database
     * @return
     */
    private List<Lesson> getAllLessons(SQLiteDatabase db) {
        List<Lesson> lessonList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_LESSONS_QUERY, null);
        while (cursor.moveToNext()) {
            int currentId = cursor.getInt(0);
            String currentName = cursor.getString(1);
            String currentDesc = cursor.getString(2);
            if (!currentName.equals("")) {
                Lesson currentLesson = new Lesson(currentId, currentName, currentDesc);
                lessonList.add(currentLesson);
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
     * @param db      database
     * @param lessons list of lessons to fill
     * @return list of fiches or null
     */
    public List<Fiche> getAllFiches(SQLiteDatabase db, List<Lesson> lessons) {
        if (lessons == null) {
            return null;
        }

        List<Fiche> ficheList = new ArrayList<>();
        Cursor cursor = db.rawQuery(ALL_FICHES_QUERY, null);
        while (cursor.moveToNext()) {
            int currentId = cursor.getInt(0);
            String currentQuestion = cursor.getString(1);
            String currentAnswer = cursor.getString(2);
            int currentLessonId = cursor.getInt(3);
            if (!currentQuestion.equals("") && !currentAnswer.equals("") && !(String.valueOf(currentLessonId).equals(""))) {
                Lesson currentLesson = null;
                for (Lesson lesson : lessons) {
                    if (currentLessonId == lesson.getId()) ;
                    currentLesson = lesson;
                }
                if (currentLesson != null) {
                    Fiche currentFiche = new Fiche(currentId, currentQuestion, currentAnswer, currentLesson);
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


    /**
     * Method that delete fiche from given lesson
     *
     * @param db     database
     * @param fiche
     * @param lesson of fiche
     * @return true/false deleted/not deleted
     */
    public boolean deleteFiche(SQLiteDatabase db, Fiche fiche, Lesson lesson) {
        // Define 'where' part of query.
        String selection = FichesReaderContract.FichesEntry.COLUMN_NAME_ANSWER + " LIKE ?"
                + "AND " + FichesReaderContract.FichesEntry.COLUMN_NAME_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {fiche.getAnswer(), String.valueOf(lesson.getId())};
        int deletedRows = db.delete(FichesReaderContract.FichesEntry.TABLE_NAME, selection, selectionArgs);
        if (deletedRows > 0) {
            return true;
        }
        return false;
    }


    /**
     * Method used for delete all fiches from database whose are related to given lesson
     *
     * @param db     databse
     * @param lesson
     */
    private void deleteAllFiches(SQLiteDatabase db, Lesson lesson) {
        List<Fiche> fiches = lesson.getFiches();
        if (fiches != null && !fiches.isEmpty()) {
            String selection = FichesReaderContract.FichesEntry.COLUMN_NAME_LESSONS + " LIKE ?";
            String[] selectionArgs = {String.valueOf(lesson.getId())};
            db.delete(FichesReaderContract.FichesEntry.TABLE_NAME, selection, selectionArgs);
        }
    }


    /**
     * Method used to delete lesson from database with all fiches in this lesson
     *
     * @param db       database
     * @param lesson
     * @param category
     * @return true if count of deleted rows is >0, false if <0
     */
    public boolean deleteLesson(SQLiteDatabase db, Lesson lesson, Category category) {
        // Define 'where' part of query.
        String selection = LessonsReaderContract.LessonsEntry.COLUMN_NAME_ID + " LIKE ?"
                + "AND " + LessonsReaderContract.LessonsEntry.COLUMN_NAME_CATEGORY + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(lesson.getId()), category.getName()};
        int deletedRows = db.delete(LessonsReaderContract.LessonsEntry.TABLE_NAME, selection, selectionArgs);

        deleteAllFiches(db, lesson);
        if (deletedRows > 0) {
            return true;
        }
        return false;
    }


    /**
     * Method used for delete all lessons from database whose are related to given category
     *
     * @param db       database
     * @param category
     */
    private void deleteAllLessons(SQLiteDatabase db, Category category) {
        List<Lesson> lessons = category.getLessons();
        if (lessons != null && !lessons.isEmpty()) {
            String selection = LessonsReaderContract.LessonsEntry.COLUMN_NAME_CATEGORY + " LIKE ?";
            String[] selectionArgs = {category.getName()};
            db.delete(LessonsReaderContract.LessonsEntry.TABLE_NAME, selection, selectionArgs);
            for (Lesson lesson : lessons) {
                deleteAllFiches(db, lesson);
            }
        }
    }


    /**
     * Method used to delete category from database with all lessons and fiches in this category
     *
     * @param db       database
     * @param category
     * @return true if count of deleted rows is >0, false if <0
     */
    public boolean deleteCategory(SQLiteDatabase db, Category category) {
        // Define 'where' part of query.
        String selection = CategoriesReaderContract.CategoriesEntry.COLUMN_NAME_NAME + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {category.getName()};
        int deletedRows = db.delete(CategoriesReaderContract.CategoriesEntry.TABLE_NAME, selection, selectionArgs);

        deleteAllLessons(db, category);
        if (deletedRows > 0) {
            return true;
        }
        return false;
    }



    public boolean updateCategory(SQLiteDatabase db, Category category, String newCategoryName) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(CategoriesReaderContract.CategoriesEntry.COLUMN_NAME_NAME, newCategoryName);

        // Which row to update, based on the title
        String selection = CategoriesReaderContract.CategoriesEntry.COLUMN_NAME_NAME + " LIKE ?";
        String[] selectionArgs = { category.getName() };

        int count = db.update(
                CategoriesReaderContract.CategoriesEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(count>0){
            return true;
        }
        return false;
    }

    public boolean updateLesson(SQLiteDatabase db, Lesson lesson, String newLessonName, String newLessonDesc) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_NAME, newLessonName);
        values.put(LessonsReaderContract.LessonsEntry.COLUMN_NAME_DESCRIPTION, newLessonDesc);

        // Which row to update, based on the title
        String selection = LessonsReaderContract.LessonsEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(lesson.getId()) };

        int count = db.update(
                LessonsReaderContract.LessonsEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(count>0){
            return true;
        }
        return false;
    }

    public boolean updateFiche(SQLiteDatabase db, Fiche fiche, String newQuestion, String newAnswer) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FichesReaderContract.FichesEntry.COLUMN_NAME_QUESTION, newQuestion);
        values.put(FichesReaderContract.FichesEntry.COLUMN_NAME_ANSWER, newAnswer);

        // Which row to update, based on the title
        String selection = FichesReaderContract.FichesEntry.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(fiche.getId()) };

        int count = db.update(
                FichesReaderContract.FichesEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(count>0){
            return true;
        }
        return false;
    }
}
