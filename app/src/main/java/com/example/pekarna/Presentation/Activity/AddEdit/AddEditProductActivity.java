package com.example.pekarna.Presentation.Activity.AddEdit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.pekarna.Database.Data;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.databinding.ActivityAddEditProductBinding;

public class AddEditProductActivity extends AppCompatActivity {

    ActivityAddEditProductBinding binding;
    int idCategory = 0, idProduct = 0;
    Data data;
    Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        idCategory = getIntent().getIntExtra(Data.ID,-1);
        idProduct = getIntent().getIntExtra(Data.ID_PRODUCT,-1);
        data=Data.getInstance(getApplicationContext());
        if(idProduct != -1)
        {
            data.getProductInID(idProduct).observe(AddEditProductActivity.this, new Observer<Product>() {
                @Override
                public void onChanged(Product productsValue) {
                    currentProduct =productsValue;
            binding.ETTitle.setText(currentProduct.TitleProduct);
            binding.ETCarbohydrates.setText(String.valueOf(currentProduct.Carbohydrates));
            binding.ETDescription.setText(currentProduct.Description);
            binding.ETFat.setText(String.valueOf(currentProduct.Fat));
            binding.ETKKal.setText(String.valueOf(currentProduct.KKal));
            binding.ETProtein.setText(String.valueOf(currentProduct.Protein));
            binding.ETPrice.setText(String.valueOf(currentProduct.Price));
            binding.ETPhoto.setText(currentProduct.URLPhotoProduct);
                }
            });
            binding.AddEditButton.setText("Редактировать");
        }
        else currentProduct = new Product();

        binding.ETPhoto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.loadImage(binding.ETPhoto.getText().toString(),binding.imageView5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void AddEditClick(View view) {
        currentProduct.TitleProduct=data.getStringEditText(binding.ETTitle);
        currentProduct.Price=Double.parseDouble(data.getStringEditText(binding.ETPrice));
        currentProduct.Carbohydrates=Integer.parseInt(data.getStringEditText(binding.ETCarbohydrates));
        currentProduct.Fat=Integer.parseInt(data.getStringEditText(binding.ETFat));
        currentProduct.Description=data.getStringEditText(binding.ETDescription);
        currentProduct.KKal=Integer.parseInt(data.getStringEditText(binding.ETKKal));
        currentProduct.Protein=Integer.parseInt(data.getStringEditText(binding.ETProtein));
        currentProduct.ProductCategoryID=idCategory;
        currentProduct.URLPhotoProduct=data.getStringEditText(binding.ETPhoto);
        data.db.productDao().insert(currentProduct);
    }
}