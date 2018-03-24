package com.app.fiche.ficheapp;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

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

    }
}
