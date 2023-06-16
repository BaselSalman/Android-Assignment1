package com.example.math_for_elementary_school_students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    private Button btnEnd;
    private TextView txtFaultsCount;
    private TextView txtEnd;
    private Animation textAnimation;
    private Animation buttonAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        btnEnd = findViewById(R.id.btnEnd);
        txtFaultsCount = findViewById(R.id.txtFaultsCount);
        txtEnd = findViewById(R.id.txtEnd);

        textAnimation = AnimationUtils.loadAnimation(this, R.anim.start_screen_text_animation);
        buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.start_screen_button_animation);
        txtEnd.startAnimation(textAnimation);
        txtFaultsCount.startAnimation(textAnimation);
        btnEnd.startAnimation(buttonAnimation);

        Intent questionsIntent = getIntent();
        int faultsCount = questionsIntent.getIntExtra("faultsCount", 0);
        txtFaultsCount.append(Integer.toString(faultsCount));
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}