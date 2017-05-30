package com.example.dmitriy.roomsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Shop.class}, version = 1)
public abstract class RoomDb extends RoomDatabase {

    public abstract ShopDao shopDao();
}
