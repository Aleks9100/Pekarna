package com.example.pekarna.Database.Entities;

import android.widget.EditText;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public  int ProductID;

    @ColumnInfo(name = "urlPhotoProduct")
    public String urlPhotoProduct;
    @ColumnInfo (name = "titleProduct")
    public  String titleProduct;
    @ColumnInfo (name = "price")
    public double price;

    public String  getPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00 Р");
        return decimalFormat.format(price);
    }
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo (name = "kKal")
    public int kKal;
    @ColumnInfo (name = "protein")
    public int protein;
    @ColumnInfo (name = "carbohydrates")
    public int carbohydrates;
    @ColumnInfo (name ="fat")
    public int fat;
    @ColumnInfo (name = "productCategoryID")
    public  int productCategoryID;


}
