package com.app.fiche.ficheapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AllCategoriesActivity extends AppCompatActivity {


    /**
     *  Implementation of View, Adapter and List
     */

    private RecyclerView categoriesRecyclerView;
    //private RecyclerView.LayoutManager categoriesLayoutManager;
    private RecyclerView.Adapter categoriesAdapter;
    //private ArrayList<CategoriesDataBase> categoriesDataset;
    private CategoriesStaff categoriesStaff = new CategoriesStaff();




    private View.OnClickListener backToMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent menu = new Intent(getBaseContext(), MenuActivity.class);
            startActivity(menu);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        AppDbConnector appDbConnector = new AppDbConnector(getBaseContext());


        categoriesStaff.addCategory(new CategoriesDataBase((getResources().getString(R.string.add_category))));

        for(int i = 0; i < appDbConnector.getDataRepository().getCategories().size(); i++) {
            categoriesStaff.addCategory(new CategoriesDataBase(appDbConnector.getDataRepository().getCategories().get(i).getName()));
        }


        /**
         * Setting RecyclerView in activitity
         * Layout manager - how to display list
         * And setting adapter to Recyclerview
         */


        categoriesRecyclerView = findViewById(R.id.recycler_view_categories);
        categoriesRecyclerView.setHasFixedSize(true);
        GridLayoutManager categoriesGridLayoutManager = new GridLayoutManager(this, 3);
        //LinearLayoutManager categoriesLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(categoriesGridLayoutManager);
        categoriesAdapter = new MainAdapter(categoriesStaff.getCategoriesDataset());
        categoriesRecyclerView.setAdapter(categoriesAdapter);


        Button backButton = findViewById(R.id.button_to_menu);
        backButton.setOnClickListener(backToMenu);



    }
}
