package com.app.fiche.ficheapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Łukasz on 26.03.2018.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<String> categoriesDataset;

    public MainAdapter(ArrayList<String> categoriesDataset) {
        this.categoriesDataset = categoriesDataset;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_row,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {

        holder.categoriesTitle.setText(categoriesDataset.get(position));

    }

    @Override
    public int getItemCount() {
        return categoriesDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView categoriesTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            categoriesTitle = itemView.findViewById(R.id.categoriesTitle);
        }
    }
}
