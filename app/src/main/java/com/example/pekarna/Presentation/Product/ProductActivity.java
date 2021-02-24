package com.example.pekarna.Presentation.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.bumptech.glide.RequestManager;
import com.example.pekarna.Database.Data;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.databinding.ActivityProductBinding;

import java.util.List;

public class ProductActivity extends AppCompatActivity {

    ActivityProductBinding binding;
//    TextView TV_Price,TV_title,TV_Description,TV_Kkal,ET_protein,TV_CH,TV_Fats;
//    ImageView photo;
    RequestManager glide;
    Data data;
    List<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Init();
        int id = getIntent().getIntExtra(Data.ID,0);
        data=Data.getInstance(this);
        data.getProductInID(id).observe(ProductActivity.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productsValue) {
                products=productsValue;
                binding.TVPrice.setText(String.valueOf(products.get(id).Price));
                binding.TVTitle.setText(products.get(id).TitleProduct);
                binding.TVDescription.setText(products.get(id).Description);
                binding.TVKkal.setText(products.get(id).KKal);
                binding.ETProtein.setText(products.get(id).Protein);
                binding.TVCH.setText(products.get(id).Carbohydrates);
                binding.TVFats.setText(products.get(id).Fat);
                data.loadImage(products.get(id).URLPhotoProduct,binding.imageView4);

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
}