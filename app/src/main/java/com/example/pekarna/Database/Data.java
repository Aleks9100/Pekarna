package com.example.pekarna.Database;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pekarna.Database.Entities.Category;
import com.example.pekarna.Database.Entities.Product;
import com.example.pekarna.R;

import java.util.List;

public class Data {
    private static Data instance;
    public final static String ID = "id";
    public final static String ID_PRODUCT = "id_product";
    public final static String TITLE="title";
    public final static String USER="user";
    public boolean admin = true;
    public  AppDatabase db;
    RequestManager glide;
    private Data(Context context) {
         db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
         glide = Glide.with(context);
    }
    public  void loadImage(String url, ImageView imageView)
    {
        glide.load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);
    }

    public static Data getInstance(Context context) {
        if(instance == null) instance=new Data(context);

        return instance;
    }

    public String getStringEditText(EditText text)
    {
        return text.getText().toString();
    }

        public LiveData<List<Product>> getCurrentCategoryProduct(int categoryID)
        {
        return db.productDao().getAllProductInCategory(categoryID);
        }
    //Вывод категории , продукты по категории, продукт по айди, Вставка и удаление продукта
        public LiveData<List<Category>> getCategoryAll ()
        {
            return db.categoryDao().getCategoryAll();
        }
        public  LiveData<Product> getProductInID(int id)
        {
            return db.productDao().getProductInID(id);
        }


    public boolean etIsNUll(String string)
    {
        if(string == null) return false;
        else return string.length() != 0;
    }

}
