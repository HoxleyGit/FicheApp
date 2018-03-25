package com.app.fiche.ficheapp;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private View.OnClickListener menuListiner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent menu_all_categories = new Intent(getBaseContext(), AllCategoriesActivity.class);
            startActivity(menu_all_categories);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //only for tests
        getBaseContext().deleteDatabase("FicheApp.db");
        FicheAppDbHelper ficheAppDbHelper = new FicheAppDbHelper(getBaseContext());
        SQLiteDatabase fichedb = ficheAppDbHelper.getWritableDatabase();
        ficheAppDbHelper.onCreate(fichedb);
        ficheAppDbHelper.addCategory(fichedb, new Category("Angielski", null));
        DataRepository dataRepository = ficheAppDbHelper.transportDbToRepo(fichedb);
        Button button = findViewById(R.id.start_learning_button);
        button.setText(dataRepository.getCategories().get(0).getName());
        //tested - working a little bit
        button.setOnClickListener(menuListiner);
    }
}
