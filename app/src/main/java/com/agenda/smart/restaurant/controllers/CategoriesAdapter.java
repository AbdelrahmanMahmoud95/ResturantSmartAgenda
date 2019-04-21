package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.Categories;
import com.agenda.smart.restaurant.views.ItemsActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Abdelrahman on 8/1/2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    Context context;
    Categories categories;

    public CategoriesAdapter(Context context, Categories categories) {
        this.context = context;
        this.categories = categories;

    }

    @Override
    public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories, parent, false);
        CategoriesAdapter.CategoriesViewHolder categoriesViewHolder = new CategoriesAdapter.CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.CategoriesViewHolder holder, final int position) {
        holder.name.setText(categories.getCategories().get(position).getName());
        Picasso.with(context).load(String.valueOf(categories.getCategories().get(position).getImage())).into(holder.categoryImage);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int categoryId = categories.getCategories().get(position).getId();
                Intent intent = new Intent(context, ItemsActivity.class);
                intent.putExtra("categoryId",categoryId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.getCategories().size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView categoryImage;
        RelativeLayout layout;


        public CategoriesViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.categories_name);
            categoryImage = itemView.findViewById(R.id.categories_image);
            layout = itemView.findViewById(R.id.categories_layout);
        }
    }
}
