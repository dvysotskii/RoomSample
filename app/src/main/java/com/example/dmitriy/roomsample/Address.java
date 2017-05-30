package com.example.dmitriy.roomsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "addresses", foreignKeys = @ForeignKey(entity = Shop.class,
        parentColumns = Shop.SHOP_ID,
        childColumns = Address.FOREIGN_SHOP_ID, onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.NO_ACTION))
public class Address {

    public static final String FOREIGN_SHOP_ID = "shop_id";
    @PrimaryKey
    private long id;

    private String address;

    @ColumnInfo(name = FOREIGN_SHOP_ID)
    private long shopId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }
}
