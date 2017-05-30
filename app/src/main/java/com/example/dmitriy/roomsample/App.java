package com.example.dmitriy.roomsample;

import android.app.Application;
import android.arch.persistence.room.Room;


public class App extends Application {

    private static App instance;
    private RoomDb roomDb;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        roomDb = Room.databaseBuilder(getApplicationContext(), RoomDb.class, "room_db").build();
    }

    public RoomDb getRoomDb() {
        return roomDb;
    }
}
