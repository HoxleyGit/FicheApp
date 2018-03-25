package com.app.fiche.ficheapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AllCategoriesActivity extends AppCompatActivity {

    private View.OnClickListener backToMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent menu = new Intent(getBaseContext(), MenuActivity.class);
            startActivity(menu);
            finish();
        }
    };
    private View.OnClickListener addCategory = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent addEditCategory = new Intent(getBaseContext(), AddEditCategoryActivity.class);
            startActivity(addEditCategory);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        Button backButton = findViewById(R.id.button_to_menu);
        backButton.setOnClickListener(backToMenu);

        final View addCategoryImage = findViewById(R.id.categories_image_button);
        addCategoryImage.setOnClickListener(addCategory);
    }

}
