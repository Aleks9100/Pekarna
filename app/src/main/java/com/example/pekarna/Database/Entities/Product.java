package com.example.pekarna.Database.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public  int ProductID;

    public String URLPhotoProduct;
    public  String TitleProduct;
    public double Price;

    public String  getPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00 Р");
        return decimalFormat.format(Price);
    }
    public String Description;
    public int KKal;
    public int Protein;
    public int Carbohydrates;
    public int Fat;
    public  int ProductCategoryID;
}
