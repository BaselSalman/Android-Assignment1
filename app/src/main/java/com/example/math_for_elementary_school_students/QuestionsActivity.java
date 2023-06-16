package com.example.math_for_elementary_school_students;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.math_for_elementary_school_students.model.AnswersDao;
import com.example.math_for_elementary_school_students.model.Question;
import com.example.math_for_elementary_school_students.model.QuestionDao;
import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        RecyclerView recyclerView = findViewById(R.id.question_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list of questions
        QuestionDao questionDao = new QuestionDao(this);
        List<Question> questionList = questionDao.getAllQuestions();

        QuestionAdapter questionAdapter = new QuestionAdapter(questionList, this, recyclerView);
        recyclerView.setAdapter(questionAdapter);
    }

}

