package com.example.math_for_elementary_school_students.model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String question;
    private int image;
    private QuestionAnswers answers;

    public Question() {
        answers = new QuestionAnswers();
        answers.setAnswers(new String[4]);
    }

    public Question(int id, String question, int image, QuestionAnswers answers) {
        this.id = id;
        this.question = question;
        this.image = image;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public QuestionAnswers getAnswers() {
        return answers;
    }

    public void setAnswers(QuestionAnswers answers) {
        this.answers = answers;
    }
}
