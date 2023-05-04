package com.example.math_for_elementary_school_students.model;

import com.example.math_for_elementary_school_students.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswersDao {
    private List<QuestionAnswers> answersList;

    public AnswersDao() {
        answersList = new ArrayList<>();
        setAnswers();
    }

    public QuestionAnswers getAnswers(int id) {
        return answersList.get(id);
    }

    private void setAnswers() {
        answersList.add(new QuestionAnswers(
                new String[]{"9", "5", "14", "49"},
                "14"));
        answersList.add(new QuestionAnswers(
                new String[]{"36", "27", "28", "42"},
                "27"));
        answersList.add(new QuestionAnswers(
                new String[]{"5", "7", "4", "6"},
                "6"));
        answersList.add(new QuestionAnswers(
                new String[]{"0.40", "0.49", "0.50", "0.39"},
                "0.50"));
        answersList.add(new QuestionAnswers(
                new String[]{"three hundred sixty", "three hundred thirty-six", "thirty sixteen", "three hundred sixteen"},
                "three hundred sixteen"));
        answersList.add(new QuestionAnswers(
                new String[]{"4", "5", "6", "8"},
                "6"));
        answersList.add(new QuestionAnswers(
                new String[]{"3", "2", "6", "1"},
                "3"));
        answersList.add(new QuestionAnswers(
                new String[]{"1", "0", "2", "3"},
                "2"));
        answersList.add(new QuestionAnswers(
                new String[]{">", "<", "=", "<="},
                "<"));
        answersList.add(new QuestionAnswers(
                new String[]{"03479", "09743", "97430", "90743"},
                "97430"));
        answersList.add(new QuestionAnswers(
                new String[]{"63, 8", "95, 40", "95, 8", "40, 8"},
                "40, 8"));
    }
}
