package com.app.fiche.ficheapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExamQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_question);

        //Button that onClick transport us to CurrentLessonActivity
        Button abandonButton = findViewById(R.id.exam_question_abandon);
        abandonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent examQuestionToCurrentLessonIntent = new Intent(getBaseContext(), CurrentLessonActivity.class);
                startActivity(examQuestionToCurrentLessonIntent);
            }
        });
    }
}
