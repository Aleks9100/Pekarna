package com.example.pekarna.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pekarna.Database.Entities.Category;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.ProductWithCategory;

import java.util.List;

@Dao
public interface ProductDao
{

    @Query("Select * from Product,Category where categoryID = :CurrentCategory")
    LiveData<List<Product>> getAllProductInCategory(int CurrentCategory);
    @Query("Select * from Product where ProductID = :CurrentProduct")
    LiveData<Product> getProductInID(int CurrentProduct);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Delete
    void delete (Product product);


}
