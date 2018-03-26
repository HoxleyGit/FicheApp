package com.app.fiche.ficheapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AllCategoriesActivity extends AppCompatActivity {

    /**
     *  Implementation of View, Adapter and List
     */

    private RecyclerView categoriesRecyclerView;
    //private RecyclerView.LayoutManager categoriesLayoutManager;
    private RecyclerView.Adapter categoriesAdapter;
    private ArrayList<String> categoriesDataset;

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

        categoriesDataset = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            categoriesDataset.add("New title " + i);
        }

        /**
         * Setting RecyclerView in activitity
         * Layout manager - how to display list
         * And setting adapter to Recyclerview
         */


        categoriesRecyclerView = findViewById(R.id.recycler_view_categories);
        categoriesRecyclerView.setHasFixedSize(true);
        GridLayoutManager categoriesGridLayoutManager = new GridLayoutManager(this, 3);
        //categoriesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(categoriesGridLayoutManager);
        categoriesAdapter = new MainAdapter(categoriesDataset);
        categoriesRecyclerView.setAdapter(categoriesAdapter);


        Button backButton = findViewById(R.id.button_to_menu);
        backButton.setOnClickListener(backToMenu);

        final View addCategoryImage = findViewById(R.id.categories_image_button);
        addCategoryImage.setOnClickListener(addCategory);
    }

}
