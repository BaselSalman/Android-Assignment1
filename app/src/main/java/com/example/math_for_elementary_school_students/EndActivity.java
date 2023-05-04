package com.example.math_for_elementary_school_students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    private Button btnEnd;
    private TextView txtFaultsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        btnEnd = findViewById(R.id.btnEnd);
        txtFaultsCount = findViewById(R.id.txtFaultsCount);
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