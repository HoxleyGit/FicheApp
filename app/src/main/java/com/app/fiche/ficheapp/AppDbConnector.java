package com.app.fiche.ficheapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by kamil on 25.03.18.
 */

public class AppDbConnector {
    private DataRepository dataRepository;
    private SQLiteDatabase db;
    private FicheAppDbHelper ficheAppDbHelper;

    public AppDbConnector(Context context) {
        context.deleteDatabase("FicheApp.db");
        ficheAppDbHelper = new FicheAppDbHelper(context);
        db = ficheAppDbHelper.getWritableDatabase();
        ficheAppDbHelper.onCreate(db);
        refreshRepo();
    }


    public boolean addCategory(String categoryName) {
        return addCategory(new Category(categoryName));
    }

    /**
     * Method used for adding new category to database and dataRepository
     *
     * @param category
     * @return
     */
    private boolean addCategory(Category category) {
        boolean success = ficheAppDbHelper.addCategory(db, category);
        if (success) {
            refreshRepo();
            return true;
        }
        return false;
    }

    public boolean addLesson(String lessonName, String lessonDesc, Category category){
        return addLesson(new Lesson(lessonName, lessonDesc), category);
    }


    /**
     * Method used for adding new fiche to database and dataRepository
     *
     * @param lesson
     * @param category
     * @return true/false - lesson added/lesson not added
     */
    private boolean addLesson(Lesson lesson, Category category) {
        boolean success = ficheAppDbHelper.addLesson(db, lesson, category);
        if (success) {
            refreshRepo();
            return true;
        }
        return false;
    }

    public boolean addFiche(String question, String answer, Lesson lesson) {
        return addFiche(new Fiche(question, answer), lesson);
    }

    /**
     * Method used for adding new fiche to database and dataRepository
     *
     * @param fiche
     * @param lesson
     * @return true/false - fiche added/fiche not added
     */
    private boolean addFiche(Fiche fiche, Lesson lesson) {
        boolean success = ficheAppDbHelper.addFiche(db, fiche, lesson);
        if (success) {
            refreshRepo();
            return true;
        }
        return false;
    }


    /**
     * Method used for deleting category from database and dataRepository
     *
     * @param category
     * @return true/false - category deleted/category not deleted
     */
    public boolean deleteCategory(Category category) {
        boolean success = ficheAppDbHelper.deleteCategory(db, category);
        if (success) {
            refreshRepo();
            return true;
        }
        return false;
    }


    /**
     * Method used for deleting lesson from database and dataRepository
     *
     * @param lesson
     * @param category
     * @return true/false - lesson deleted/lesson not deleted
     */
    public boolean deleteLesson(Lesson lesson, Category category) {
        boolean success = ficheAppDbHelper.deleteLesson(db, lesson, category);
        if (success) {
            refreshRepo();
            return true;
        }
        return false;
    }


    /**
     * Method used for deleting fiche from database and dataRepository
     *
     * @param fiche
     * @param lesson
     * @return true/false - fiche deleted/fiche not deleted
     */
    public boolean deleteFiche(Fiche fiche, Lesson lesson) {
        boolean success = ficheAppDbHelper.deleteFiche(db, fiche, lesson);
        if (success) {
            refreshRepo();
            return true;
        }
        return false;
    }

    public boolean updateCategory(Category category, String newCategoryName) {
        if(!category.getName().equals(newCategoryName)){
            boolean success = ficheAppDbHelper.updateCategory(db, category, newCategoryName);
            if(success) {
                refreshRepo();
                return true;
            }
        }
        return false;
    }


    public boolean updateLesson(Lesson lesson, String newLessonName, String newLessonDesc) {
        if(!lesson.getName().equals(newLessonName) || !lesson.getDesc().equals(newLessonDesc)){
            boolean success = ficheAppDbHelper.updateLesson(db, lesson, newLessonName, newLessonDesc);
            if(success) {
                refreshRepo();
                return true;
            }
        }
        return false;
    }


    public boolean updateFiche(Fiche fiche, String newQuestion, String newAnswer) {
        if(!fiche.getQuestion().equals(newQuestion) || !fiche.getAnswer().equals(newAnswer)){
            boolean success = ficheAppDbHelper.updateFiche(db, fiche, newQuestion, newAnswer);
            if(success) {
                refreshRepo();
                return true;
            }
        }
        return false;
    }


    /**
     * Method used to refresh repository basing on state of database
     */
    private void refreshRepo() {
        dataRepository = transportDbToRepo();
    }

    /**
     * Method used to generate object of class DataRepository
     * which handles data downloaded from database and which is used to operate this data though
     * appliaction
     *
     * @return object of class DataRepository used to operate data downloaded from database
     */
    private DataRepository transportDbToRepo() {
        List<Category> categories = ficheAppDbHelper.getAllCategories(db);
        List<Lesson> lessons = ficheAppDbHelper.getAllLessons(db, categories);
        List<Fiche> fiches = ficheAppDbHelper.getAllFiches(db, lessons);
        return new DataRepository(categories, lessons, fiches);
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }
}
