package com.app.fiche.ficheapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewEditFicheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit_fiche);

        //Button that onClick transport us to CurrentLessonActivity
        Button cancelButton = findViewById(R.id.new_edit_fiche_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CurrentLessonActivity.class);
                startActivity(intent);
            }
        });
    }
}
