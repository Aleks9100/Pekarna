package com.example.pekarna.Presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.pekarna.Database.Data;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> products;
    RequestManager glide;
    LayoutInflater layoutInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        TextView title = findViewById(R.id.categoryET);
        recyclerView=findViewById(R.id.ProductRecycler);
        layoutInflater=getLayoutInflater();
        products= new ArrayList<>();
        glide= Glide.with(this);
        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
        int id = getIntent().getIntExtra(Data.ID,0);
        title.setText(getIntent().getStringExtra(Data.TITLE));
        Data.getInstance(this).getCurrentCategoryProduct(id).observe(CategoryActivity.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productsValue) {
                products=productsValue;
                adapter.notifyDataSetChanged();
            }
        });
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.category_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Product product = products.get(position);

            holder.TV_Title.setText(product.TitleProduct);
            holder.TV_Price.setText(String.valueOf(product.Price));
            glide.load(product.URLPhotoProduct).placeholder(R.drawable.ic_launcher_background).into(holder.productView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategoryActivity.this, ProductActivity.class);
                    intent.putExtra(Data.ID,product.ProductID);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return products.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final TextView TV_Price;
            final TextView TV_Title;
            final ImageView productView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                TV_Price = itemView.findViewById(R.id.TV_Price);
                TV_Title = itemView.findViewById(R.id.TV_title);
                productView = itemView.findViewById(R.id.ProductImage);
            }
        }
    }
}