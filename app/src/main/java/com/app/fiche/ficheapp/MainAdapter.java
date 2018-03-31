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

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private ArrayList<CategoriesDataBase> categoriesDataset;

    public MainAdapter(ArrayList<CategoriesDataBase> categoriesDataset) {
        this.categoriesDataset = categoriesDataset;
}

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_row,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;


    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, final int position) {

        holder.categoriesTitle.setText(categoriesDataset.get(position).getName());
        holder.categoriesRecyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLessons = new Intent(view.getContext(), AllLessonsActivity.class);
                int categoryINeed = position;
                goToLessons.putExtra("CATEGORY", categoryINeed);
                view.getContext().startActivity(goToLessons);
                if (position == 0) {

                    Intent addEditCategory = new Intent(view.getContext(), AddEditCategoryActivity.class);
                    view.getContext().startActivity(addEditCategory);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Button categoriesRecyclerViewButton;

        public TextView categoriesTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            categoriesTitle = itemView.findViewById(R.id.categoriesTitleButton);
            categoriesRecyclerViewButton = itemView.findViewById(R.id.categoriesTitleButton);
        }

    }
}
