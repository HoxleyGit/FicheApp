package com.app.fiche.ficheapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddEditCategoryActivity extends AppCompatActivity {

    private View.OnClickListener backToAllCategories = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent backToAllCategoriesIntent = new Intent(getBaseContext(), AllCategoriesActivity.class);
            startActivity(backToAllCategoriesIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_category);

        Button backToAllCategoriesButton = findViewById(R.id.button_back_to_all_categories);
        backToAllCategoriesButton.setOnClickListener(backToAllCategories);

    }
}
