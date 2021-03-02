package com.example.pekarna.Presentation.Activity.AddEdit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.pekarna.Database.Data;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.Presentation.Activity.Category.CategoryActivity;
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
            binding.ETTitle.setText(currentProduct.titleProduct);
            binding.ETCarbohydrates.setText(String.valueOf(currentProduct.carbohydrates));
            binding.ETDescription.setText(currentProduct.description);
            binding.ETFat.setText(String.valueOf(currentProduct.fat));
            binding.ETKKal.setText(String.valueOf(currentProduct.kKal));
            binding.ETProtein.setText(String.valueOf(currentProduct.protein));
            binding.ETPrice.setText(String.valueOf(currentProduct.price));
            binding.ETPhoto.setText(currentProduct.urlPhotoProduct);
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
        if(data.etIsNUll(binding.ETCarbohydrates.getText().toString()) && data.etIsNUll(binding.ETDescription.getText().toString())
                && data.etIsNUll(binding.ETFat.getText().toString()) && data.etIsNUll(binding.ETKKal.getText().toString())
                && data.etIsNUll(binding.ETPrice.getText().toString()) && data.etIsNUll(binding.ETProtein.getText().toString()) && data.etIsNUll(binding.ETTitle.getText().toString())) {

            currentProduct.titleProduct = data.getStringEditText(binding.ETTitle);
            currentProduct.price = Double.parseDouble(data.getStringEditText(binding.ETPrice));
            currentProduct.carbohydrates = Integer.parseInt(data.getStringEditText(binding.ETCarbohydrates));
            currentProduct.fat = Integer.parseInt(data.getStringEditText(binding.ETFat));
            currentProduct.description = data.getStringEditText(binding.ETDescription);
            currentProduct.kKal = Integer.parseInt(data.getStringEditText(binding.ETKKal));
            currentProduct.protein = Integer.parseInt(data.getStringEditText(binding.ETProtein));
            currentProduct.productCategoryID = idCategory;
            currentProduct.urlPhotoProduct = data.getStringEditText(binding.ETPhoto);
            data.db.productDao().insert(currentProduct);
            Intent intent = new Intent(AddEditProductActivity.this, CategoryActivity.class);
            intent.putExtra(data.ID,idCategory);
            startActivity(intent);
        }
        else Toast.makeText(AddEditProductActivity.this,"Не все поля были введены",Toast.LENGTH_LONG);
    }
}