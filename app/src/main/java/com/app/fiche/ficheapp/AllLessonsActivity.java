package com.app.fiche.ficheapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AllLessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lessons);

        Button backToAddEditCategoryButton = findViewById(R.id.button_to_categories);
        backToAddEditCategoryButton.setOnClickListener(backToAddEditCategoryListiner);
    }

    private View.OnClickListener backToAddEditCategoryListiner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent backToAddEditCategoryIntent = new Intent(getBaseContext(),AddEditCategoryActivity.class);
            startActivity(backToAddEditCategoryIntent);
            finish();
        }
    };
}
