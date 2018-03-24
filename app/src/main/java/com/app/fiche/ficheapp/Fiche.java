package com.app.fiche.ficheapp;


public class Fiche {
    private String question;
    private String answer;
    private Lesson lesson;

    public Fiche(String question, String answer, Lesson lesson) {
        this.question = question;
        this.answer = answer;
        this.lesson = lesson;
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
