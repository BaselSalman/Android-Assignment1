package com.example.math_for_elementary_school_students.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.math_for_elementary_school_students.R;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDao {

    private List<Question> questions;
    private final AnswersDao answersDao;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public QuestionDao(Context context) {
        answersDao = new AnswersDao();
        questions = new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        if(!preferences.getBoolean("areQuestionsSaved", false)) {
            setQuestions();
            saveOnSharedPreferences(questions);
        } else {
            questions = getFromSharedPreferences();
        }
    }


    public List<Question> getAllQuestions() {
        return questions;
    }

    private void setQuestions() {
        addQuestion(new Question(
                0,
                "Find the area of the rectangle.",
                R.drawable.q1,
                answersDao.getAnswers(0)
        ));
        addQuestion(new Question(
                1,
                "Calculate the result of this arithmetic operation.",
                R.drawable.q2,
                answersDao.getAnswers(1)
        ));
        addQuestion(new Question(
                2,
                "Calculate the value of m in this equation.",
                R.drawable.q3,
                answersDao.getAnswers(2)
        ));
        addQuestion(new Question(
                3,
                "What decimal number is illustrated?",
                R.drawable.q4,
                answersDao.getAnswers(3)
        ));
        addQuestion(new Question(
                4,
                "How do you write this number using words?",
                R.drawable.q5,
                answersDao.getAnswers(4)
        ));
        addQuestion(new Question(
                5,
                "How many faces does this shape have?",
                R.drawable.q6,
                answersDao.getAnswers(5)
        ));
        addQuestion(new Question(
                6,
                "Find the missing number in this sequence.",
                R.drawable.q7,
                answersDao.getAnswers(6)
        ));
        addQuestion(new Question(
                7,
                "What is the correct number to make the addition result correct?",
                R.drawable.q8,
                answersDao.getAnswers(7)
        ));
        addQuestion(new Question(
                8,
                "Which sign makes the statement true?",
                R.drawable.q9,
                answersDao.getAnswers(8)
        ));
        addQuestion(new Question(
                9,
                "What is the greatest whole number you can make with the following digits?",
                R.drawable.q10,
                answersDao.getAnswers(9)
        ));
        addQuestion(new Question(
                10,
                "Choose two numbers from the box to complete the division sentence.",
                R.drawable.q11,
                answersDao.getAnswers(10)
        ));
    }

    private void addQuestion(Question question) {
        questions.add(question);
    }

    private void saveOnSharedPreferences(List<Question> questions) {
        Gson gson = new Gson();
        String questionsJson = gson.toJson(questions);
        editor.putBoolean("areQuestionsSaved", true);
        editor.putString("questions", questionsJson);
        editor.commit();
    }

    private List<Question> getFromSharedPreferences() {
        Gson gson = new Gson();
        String questionsJson = preferences.getString("questions", null);
        Question[] questionsArray = gson.fromJson(questionsJson, Question[].class);
        return Arrays.asList(questionsArray);
    }

    public int getNumOfQuestions() {
        return questions.size();
    }

}
