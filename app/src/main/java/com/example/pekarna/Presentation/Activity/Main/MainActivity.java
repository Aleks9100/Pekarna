package com.example.pekarna.Presentation.Activity.Main;

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
import com.example.pekarna.Presentation.Activity.Category.CategoryActivity;
import com.example.pekarna.databinding.ActivityMainBinding;
import com.example.pekarna.databinding.MainMenuItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter adapter;
    List<Category> categoryList;
    LayoutInflater layoutInflater;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        layoutInflater = getLayoutInflater();
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter();
        binding.categoryRecycler.setAdapter(adapter);


        data =Data.getInstance(getApplicationContext());
        data.getCategoryAll().observe(MainActivity.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryList = categories;
                adapter.notifyDataSetChanged();
            }
        });
        if(categoryList.size() == 0) {
        category();
        }
    }

    public void category()
      {
          Category categorys = new Category();
        categorys.title ="Печенья";
        categorys.url = "https://ne-dieta.ru/wp-content/uploads/2019/03/final_1200-7.jpg";
      data.db.categoryDao().insert(categorys);
}
    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           MainMenuItemBinding binding =MainMenuItemBinding.inflate(layoutInflater,parent,false);
            return new ViewHolder(binding);
    }


        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Category category = categoryList.get(position);

            holder.binding.TVTitle.setText(category.title);
            data.loadImage(category.url,holder.binding.imageProduct);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    intent.putExtra(Data.ID,category.categoryID);
                    intent.putExtra(Data.TITLE,category.title);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return categoryList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            MainMenuItemBinding binding;
            public ViewHolder(@NonNull MainMenuItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

}