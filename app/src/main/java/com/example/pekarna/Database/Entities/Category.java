package com.example.pekarna.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey (autoGenerate = true)
    public int categoryID;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "title")
    public String title;
}
