package com.example.pekarna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.pekarna.Database.Entities.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    RequestManager glide;
    List<Category>  categories;
    LayoutInflater layoutInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.categoryRecycler);
        glide= Glide.with(this);
        layoutInflater=getLayoutInflater();
        categories= new ArrayList<>();
        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);

    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =layoutInflater.inflate(R.layout.main_menu_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Category category = categories.get(position);
            holder.titleView.setText(category.Title);
            
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final TextView titleView;
            final ImageView productImagel;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                titleView=itemView.findViewById(R.id.TV_title);
                productImagel = itemView.findViewById(R.id.image_Product);
            }
        }
    }
}