package com.example.pekarna.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.pekarna.Database.Entities.Category;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.ProductWithCategory;

import java.util.List;

public class Data {
    private static Data instance;
    AppDatabase db;
    public Data(Context context) {
         db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    public static Data getInstance(Context context) {
        if(instance == null) instance=new Data(context);

        return instance;
    }

        public LiveData<List<Product>> getCurrentCategoryProduct(int categoryID)
        {
        return db.productDao().GetAllProductInCategory(categoryID);
        }
    //Вывод категории , продукты по категории, продукт по айди, Вставка и удаление продукта
        public LiveData<List<Category>> getCategoryAll ()
        {
            return db.categoryDao().getCategoryAll();
        }
        public  LiveData<List <Product>> getProductInID(int id)
        {
            return db.productDao().GetProductInID(id);
        }

}
