package com.example.pekarna.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public  int ProductID;

    public String urlPhotoProduct;
    public  String titleProduct;
    public double price;

    public String  getPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00 Р");
        return decimalFormat.format(price);
    }
    public String description;
    public int kKal;
    public int protein;
    public int carbohydrates;
    public int fat;
    public  int productCategoryID;
}
