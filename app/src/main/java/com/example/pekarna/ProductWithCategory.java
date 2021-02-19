package com.example.pekarna;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.pekarna.Database.Entities.Category;
import com.example.pekarna.Database.Entities.Product;

import java.util.List;

public class ProductWithCategory {

    @Embedded
    public Category category;

    @Relation(parentColumn = "CategoryID",entityColumn = "CategoryID")
    public List<Product> products;
}
