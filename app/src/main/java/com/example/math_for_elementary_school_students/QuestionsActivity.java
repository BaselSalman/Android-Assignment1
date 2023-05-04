package com.example.math_for_elementary_school_students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.math_for_elementary_school_students.model.Question;
import com.example.math_for_elementary_school_students.model.QuestionAnswers;
import com.example.math_for_elementary_school_students.model.QuestionDao;

public class QuestionsActivity extends AppCompatActivity {

    private TextView txtQuestion;
    private ImageView imageView;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button btnSubmit;
    private QuestionDao questionDao;
    private Dialog dialog;
    private int faultsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        txtQuestion = findViewById(R.id.txtQestion);
        imageView = findViewById(R.id.questionImage);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnSubmit = findViewById(R.id.btnEnd);
        dialog = new Dialog(this);
        questionDao = new QuestionDao();
        showQuestion(0);
    }

    private void showQuestion(int questionNumber) {
        Question question = questionDao.getQuestion(questionNumber);
        QuestionAnswers answers = question.getAnswers();
        txtQuestion.setText(question.getQuestion());
        imageView.setImageResource(question.getImage());
        rb1.setText(answers.getAnswers()[0]);
        rb2.setText(answers.getAnswers()[1]);
        rb3.setText(answers.getAnswers()[2]);
        rb4.setText(answers.getAnswers()[3]);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRbId = radioGroup.getCheckedRadioButtonId();
                if(selectedRbId != -1) {
                    RadioButton selectedRb = findViewById(selectedRbId);
                    if(selectedRb.getText().equals(answers.getCorrectAnswer())) {
                        openSuccessfulDialog();
                        radioGroup.clearCheck();

                        // show the dialog for 2 seconds and then go to next question
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                if(questionNumber + 1 != questionDao.getNumOfQuestions())
                                    showQuestion(questionNumber + 1);
                                // else // go to final screen
                                else {
                                    Intent intent = new Intent(QuestionsActivity.this, EndActivity.class);
                                    intent.putExtra("faultsCount", faultsCount);
                                    startActivity(intent);
                                }
                            }
                        }, 2000);
                    } else {
                        openWrongDialog();
                        faultsCount++;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 2000);
                    }
                }
            }
        });
    }


    private void openSuccessfulDialog() {
        dialog.setContentView(R.layout.correct_answer_layout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void openWrongDialog() {
        dialog.setContentView(R.layout.wrong_answer_layout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}

