package com.app.fiche.ficheapp;


import java.util.List;

public class DataRepository {
    private static List<Category> categories;
    private static List<Lesson> lessons;
    private static List<Fiche> fiches;

    public DataRepository(List<Category> categories, List<Lesson> lessons, List<Fiche> fiches) {
        this.categories = categories;
        this.lessons = lessons;
        this.fiches = fiches;
    }

    public static int getNextLessonId() {
        if(lessons != null && !lessons.isEmpty()){
            int max = Integer.MIN_VALUE;
            for(Lesson lesson : lessons){
                int currentId = lesson.getId();
                if(currentId>max){
                    max = currentId;
                }
            }
            return (max+1);
        }
        return 1;
    }

    public static int getNextFicheId(){
        if(fiches != null && !fiches.isEmpty()) {
            int max = Integer.MIN_VALUE;
            for (Fiche fiche : fiches) {
                int currentId = fiche.getId();
                if (currentId > max) {
                    max = currentId;
                }
            }
            return (max + 1);
        }
        return 1;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Fiche> getFiches() {
        return fiches;
    }
}
