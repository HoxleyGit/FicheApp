package com.app.fiche.ficheapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CurrentLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_lesson);

        //Button that onClick transport us to ExamQuestionActivity
        Button examButton = findViewById(R.id.current_lesson_exam_button);
        examButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentLessonToExamIntent = new Intent(getBaseContext(), ExamQuestionActivity.class);
                startActivity(currentLessonToExamIntent);
            }
        });
    }
}
