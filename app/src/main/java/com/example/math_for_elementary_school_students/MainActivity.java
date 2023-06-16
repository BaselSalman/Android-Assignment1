package com.example.math_for_elementary_school_students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtStart;
    private Button btnStart;
    private Animation textAnimation;
    private Animation buttonAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtStart = findViewById(R.id.txtEnd);
        btnStart = findViewById(R.id.btnStart);
        textAnimation = AnimationUtils.loadAnimation(this, R.anim.start_screen_text_animation);
        buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.start_screen_button_animation);
        txtStart.startAnimation(textAnimation);
        btnStart.startAnimation(buttonAnimation);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
                startActivity(intent);
            }
        });
    }

}

