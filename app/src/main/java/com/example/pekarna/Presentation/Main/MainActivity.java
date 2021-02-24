package com.example.pekarna.Presentation.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pekarna.Database.Data;
import com.example.pekarna.Database.Entities.Category;
import com.example.pekarna.Presentation.Category.CategoryActivity;
import com.example.pekarna.R;
import com.example.pekarna.databinding.CategoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    List<Category>  categories;
    LayoutInflater layoutInflater;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.categoryRecycler);
        layoutInflater=getLayoutInflater();
        categories= new ArrayList<>();
        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);

        data=data.getInstance(this);
        data.getCategoryAll().observe(MainActivity.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                MainActivity.this.categories = categories;
                adapter.notifyDataSetChanged();
            }
        });
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           CategoryItemBinding binding =CategoryItemBinding.inflate(layoutInflater,parent,false);
            return new ViewHolder(binding);
    }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Category category = categories.get(position);

            holder.binding.TVTitle.setText(category.Title);
            data.loadImage(category.URLPhoto,holder.binding.ProductImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    intent.putExtra(Data.ID,category.CategoryID);
                    intent.putExtra(Data.TITLE,category.Title);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            CategoryItemBinding binding;
            public ViewHolder(@NonNull CategoryItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}