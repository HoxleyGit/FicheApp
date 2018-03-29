package com.app.fiche.ficheapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Łukasz on 26.03.2018.
 */

class MainAdapterLessons extends RecyclerView.Adapter<MainAdapterLessons.ViewHolder> {


    private ArrayList<LessonsDataBase> lessonsDataBase;

    public MainAdapterLessons(ArrayList<LessonsDataBase> lessonsDataBase) {
        this.lessonsDataBase = lessonsDataBase;
    }


    @Override
    public MainAdapterLessons.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lessons_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainAdapterLessons.ViewHolder holder, final int position) {

        holder.lessonsTitle.setText(lessonsDataBase.get(position).getName());
        holder.lessonsRecyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Pozycja: " + position, Toast.LENGTH_SHORT).show();
             /*   if (position == 0) {

                    Intent addEditCategory = new Intent(view.getContext(), AddEditCategoryActivity.class);
                    view.getContext().startActivity(addEditCategory);
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return lessonsDataBase.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button lessonsRecyclerViewButton;

        public TextView lessonsTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            lessonsTitle = itemView.findViewById(R.id.lessonsTextView);
            lessonsRecyclerViewButton = itemView.findViewById(R.id.lessonsTitleButton);
        }

    }
}
