package com.app.fiche.ficheapp;


import android.content.Intent;
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
        AppDbConnector appDbConnector = new AppDbConnector(getBaseContext());
        appDbConnector.addCategory("Angielski");
        appDbConnector.addCategory("Polski");
        appDbConnector.addCategory("Dom");
        appDbConnector.addCategory("Kupeczka");

        appDbConnector.addLesson("dom", "", appDbConnector.getDataRepository().getCategories().get(3));
        boolean success = appDbConnector.addFiche("pytanie", "odpowiedz", appDbConnector.getDataRepository().getLessons().get(0));
        if(success) {
            Button button = findViewById(R.id.start_learning_button);
            button.setText(appDbConnector.getDataRepository().getCategories().get(3).getLessons().get(0).getName());
            //tested - working a little bit
            button.setOnClickListener(menuListiner);
        }
    }
}
