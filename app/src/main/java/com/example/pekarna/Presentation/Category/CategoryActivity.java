package com.example.pekarna.Presentation.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pekarna.Database.Data;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.Presentation.Product.ProductActivity;
import com.example.pekarna.R;
import com.example.pekarna.databinding.CategoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> products;
    LayoutInflater layoutInflater;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        TextView title = findViewById(R.id.categoryET);
        recyclerView=findViewById(R.id.ProductRecycler);
        layoutInflater=getLayoutInflater();
        products= new ArrayList<>();
        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
        int id = getIntent().getIntExtra(Data.ID,0);
        title.setText(getIntent().getStringExtra(Data.TITLE));
        data = Data.getInstance(this);
        data.getCurrentCategoryProduct(id).observe(CategoryActivity.this, new Observer<List<Product>>() {
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
            CategoryItemBinding binding = CategoryItemBinding.inflate(layoutInflater,parent,false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Product product = products.get(position);

            holder.binding.TVTitle.setText(product.TitleProduct);
            holder.binding.TVPrice.setText(String.valueOf(product.Price));
            data.loadImage(product.URLPhotoProduct,holder.binding.ProductImage);
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
            CategoryItemBinding binding;
            public ViewHolder(@NonNull CategoryItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}