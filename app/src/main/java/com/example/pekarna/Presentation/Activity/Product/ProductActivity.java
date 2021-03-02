package com.example.pekarna.Presentation.Activity.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pekarna.Database.Data;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.Presentation.Activity.AddEdit.AddEditProductActivity;
import com.example.pekarna.databinding.ActivityProductBinding;

public class ProductActivity extends AppCompatActivity {

    ActivityProductBinding binding;
//    TextView TV_Price,TV_title,TV_Description,TV_Kkal,ET_protein,TV_CH,TV_Fats;
//    ImageView photo;

    Data data;
    Product currentProduct;
    int id = 0,idCategory=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        id = getIntent().getIntExtra(Data.ID_PRODUCT,0);
        idCategory=getIntent().getIntExtra(Data.ID,0);
        data=Data.getInstance(this);
        data.getProductInID(id).observe(ProductActivity.this, new Observer<Product>() {
            @Override
            public void onChanged(Product productsValue) {
                if (productsValue == null) {
                    finish();
                } else {
                    currentProduct = productsValue;
                    binding.TVPrice.setText(String.valueOf(currentProduct.getPrice()));
                    binding.TVTitle.setText(currentProduct.titleProduct);
                    binding.TVDescription.setText(currentProduct.description);

                    binding.TVKkal.setText(String.valueOf(currentProduct.kKal));
                    binding.ETProtein.setText(String.valueOf(currentProduct.protein));
                    binding.TVCH.setText(String.valueOf(currentProduct.carbohydrates));
                    binding.TVFats.setText(String.valueOf(currentProduct.fat));
                    data.loadImage(currentProduct.urlPhotoProduct, binding.imageView4);
                }
            }
        });

    }

    public void DeleteProduct(View view) {
        data.db.productDao().delete(currentProduct);
    }
    public void EditClick(View view) {
        Intent intent = new Intent(ProductActivity.this, AddEditProductActivity.class);
        intent.putExtra(Data.ID,idCategory);
        intent.putExtra(Data.ID_PRODUCT,id);
        startActivity(intent);
    }
}