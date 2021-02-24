package com.example.pekarna.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pekarna.Database.Entities.Category;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.ProductWithCategory;

import java.util.List;

@Dao
public interface ProductDao
{

    @Query("Select * from Product,category where CategoryID = :CurrentCategory")
    LiveData<List<Product>> GetAllProductInCategory(int CurrentCategory);
    @Query("Select * from Product where ProductID = :CurrentProduct")
    LiveData<List<Product>> GetProductInID(int CurrentProduct);

    @Insert
    void insert(Product product);

    @Delete
    void delete (Product product);


}
