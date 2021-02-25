package com.example.pekarna.Presentation.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.RequestManager;
import com.example.pekarna.Database.Data;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.Presentation.AddEdit.AddEditProductActivity;
import com.example.pekarna.databinding.ActivityProductBinding;

import java.util.List;

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
        Init();
         id= getIntent().getIntExtra(Data.ID,0);
        data=Data.getInstance(this);
        data.getProductInID(id).observe(ProductActivity.this, new Observer<Product>() {
            @Override
            public void onChanged(Product productsValue) {
                currentProduct=productsValue;
                binding.TVPrice.setText(String.valueOf(currentProduct.Price));
                binding.TVTitle.setText(currentProduct.TitleProduct);
                binding.TVDescription.setText(currentProduct.Description);
                binding.TVKkal.setText(currentProduct.KKal);
                binding.ETProtein.setText(currentProduct.Protein);
                binding.TVCH.setText(currentProduct.Carbohydrates);
                binding.TVFats.setText(currentProduct.Fat);
                data.loadImage(currentProduct.URLPhotoProduct,binding.imageView4);
                idCategory = currentProduct.ProductCategoryID;
            }
        });

    }

    private void Init() {
//        TV_Price = findViewById(R.id.TV_Price);
//        TV_title = findViewById(R.id.TV_title);
//        TV_Description = findViewById(R.id.TV_Description);
//        TV_Kkal = findViewById(R.id.TV_Kkal);
//        ET_protein = findViewById(R.id.ET_protein);
//        TV_CH = findViewById(R.id.TV_CH);
//        TV_Fats = findViewById(R.id.TV_Fats);
//        photo=findViewById(R.id.imageView4);
    }

    public void EditClick(View view) {
        Intent intent = new Intent(ProductActivity.this, AddEditProductActivity.class);
        intent.putExtra(Data.ID,idCategory);
        intent.putExtra(Data.ID_PRODUCT,id);
        startActivity(intent);
    }
}