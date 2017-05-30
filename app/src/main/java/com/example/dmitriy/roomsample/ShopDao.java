package com.example.dmitriy.roomsample;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ShopDao {

    @Query("SELECT * FROM " + Shop.TABLE_NAME)
    List<Shop> queryForAll();

    @Query("SELECT name FROM " + Shop.TABLE_NAME + " WHERE id = :shopId")
    double queryShopById(long shopId);

    @Insert
    long create(Shop shop);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> createAll(List<Shop> shops);

    @Delete
    void delete(Shop shop);

    @Update
    int updateShop(Shop shop);
}
