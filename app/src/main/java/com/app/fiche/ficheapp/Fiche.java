package com.app.fiche.ficheapp;


public class Fiche {
    private int id;
    private String question;
    private String answer;
    private Lesson lesson;

    public Fiche (int id, String question, String answer, Lesson lesson){
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.lesson = lesson;
    }

    public Fiche(String question, String answer, Lesson lesson) {
        this(DataRepository.getNextFicheId(), question, answer, lesson);
    }

    public Fiche(String question, String answer){
        this(question, answer, null);
    }



    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
