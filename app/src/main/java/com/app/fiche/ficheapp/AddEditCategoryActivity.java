package com.app.fiche.ficheapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

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

        Button addEditCategoryButton = findViewById(R.id.confirm_add_edit_category);
        addEditCategoryButton.setOnClickListener(addEditCategoryListiner);


    }


    private View.OnClickListener addEditCategoryListiner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText categoryName = findViewById(R.id.nameofcategory1);
            String categoryNameString = categoryName.getText().toString();

            if (categoryNameString.equals(getResources().getString(R.string.add_name_category))) {
                Toast toast = Toast.makeText(getBaseContext(), getResources().getString(R.string.no_category), Toast.LENGTH_SHORT);
                toast.show();
            } else {
                AppDbConnector appDbConnector = new AppDbConnector(getBaseContext());
                appDbConnector.addCategory(categoryNameString);
                Intent addEditCategoryIntent = new Intent(getBaseContext(), AllLessonsActivity.class);
                startActivity(addEditCategoryIntent);

                finish();

            }
        }
    };

}
