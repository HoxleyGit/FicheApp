package com.app.fiche.ficheapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExamResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result);

        //Button that onClick transport us to CurrentLessonActivity
        Button exitFromResult = findViewById(R.id.exam_result_exit);
        exitFromResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CurrentLessonActivity.class);
                startActivity(intent);
            }
        });

        //Button that onClick transport us to ExamQuestionActivity
        Button tryAgainButton = findViewById(R.id.exam_result_try_again);
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ExamQuestionActivity.class);
                startActivity(intent);
            }
        });


    }
}
