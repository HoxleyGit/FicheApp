package com.app.fiche.ficheapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AllLessonsActivity extends AppCompatActivity {

    private RecyclerView lessonsRecyclerView;
    private RecyclerView.Adapter lessonsAdapter;
    private LessonsStaff lessonsStaff = new LessonsStaff();


    private View.OnClickListener backToAddEditCategoryListiner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent backToAddEditCategoryIntent = new Intent(getBaseContext(), AllCategoriesActivity.class);
            startActivity(backToAddEditCategoryIntent);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDbConnector appDbConnector = new AppDbConnector(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lessons);

        int passedCategory;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                passedCategory = -1;
            } else {
                passedCategory = extras.getInt("CATEGORY");
            }
        } else {
            passedCategory = (Integer) savedInstanceState.getSerializable("CATEGORY");
        }
        appDbConnector.addLesson("TESTLESSON", "", appDbConnector.getDataRepository().getCategories().get(3));
        Toast.makeText(getBaseContext(), "fddf" + appDbConnector.getDataRepository().getCategories().get(3).getName(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < appDbConnector.getDataRepository().getCategories().get(3).getLessons().size(); i++) {
            lessonsStaff.addLesson(new LessonsDataBase(appDbConnector.getDataRepository().getCategories().get(3).getLessons().get(i).getName()));
        }

        lessonsRecyclerView = findViewById(R.id.recycler_view_lessons);
        lessonsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerLessons = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lessonsRecyclerView.setLayoutManager(linearLayoutManagerLessons);
        lessonsAdapter = new MainAdapterLessons(lessonsStaff.getLessonsDataSet());
        lessonsRecyclerView.setAdapter(lessonsAdapter);


        Button backToAddEditCategoryButton = findViewById(R.id.button_to_categories);
        backToAddEditCategoryButton.setOnClickListener(backToAddEditCategoryListiner);
    }

}

