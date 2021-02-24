package com.example.pekarna.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pekarna.Dao.CategoryDao;
import com.example.pekarna.Dao.ProductDao;
import com.example.pekarna.Database.Entities.Category;
import com.example.pekarna.Database.Entities.Product;

@Database(entities = {Category.class, Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public  abstract CategoryDao categoryDao();
}
