package com.example.math_for_elementary_school_students.model;

import java.util.Map;

public class QuestionAnswers {
    private String[] answers;
    private String correctAnswer;

    public QuestionAnswers() {}

    public QuestionAnswers(String[] answers, String correctAnswer) {
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
