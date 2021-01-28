package com.example.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "registration.db";
    public static final int DATABASE_VERSION = 11;

    public static final String TABLE_RIDERS = "riders";
    public static final String TABLE_GROUPS = "user_groups";
    public static final String TABLE_CITY = "user_city";
    public static final String TABLE_CARS = "user_cars";
    public static final String TABLE_ROOM = "user_room";

    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_CARS = "cars";
    public static final String COLUMN_INFO = "user_full_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_GROUP_ID = "group_id";
    public static final String COLUMN_GROUP_INFO = "group_name";
    public static final String COLUMN_SUM = "group_sum";
    public static final String COLUMN_ROOM = "room_number";

    Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_RIDERS +
                " (" + COLUMN_INFO + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_GROUP_ID + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_GROUPS +
                " (" + COLUMN_GROUP_INFO + " TEXT, " +
                COLUMN_SUM + " INTEGER, " +
                COLUMN_GROUP_ID + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_CITY +
                " (" + COLUMN_COUNTRY + " TEXT, " +
                COLUMN_CITY + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_CARS +
                " (" + COLUMN_CARS + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_ROOM +
                " (" + COLUMN_ROOM + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_RIDERS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CITY);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CARS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_ROOM);

        onCreate(db);
        initGroups(db);
        initCity(db);
        initUniversity(db);
        initRoom(db);
    }

    private void initRoom(SQLiteDatabase db) {
        ContentValues room1 = new ContentValues();
        room1.put(COLUMN_ROOM, 0);
        db.insert(TABLE_ROOM, null, room1);

        ContentValues room2 = new ContentValues();
        room1.put(COLUMN_ROOM, 1);
        db.insert(TABLE_ROOM, null, room2);

        ContentValues room3 = new ContentValues();
        room1.put(COLUMN_ROOM, 2);
        db.insert(TABLE_ROOM, null, room3);

        ContentValues room4 = new ContentValues();
        room1.put(COLUMN_ROOM, 3);
        db.insert(TABLE_ROOM, null, room4);

        ContentValues room5 = new ContentValues();
        room1.put(COLUMN_ROOM, 4);
        db.insert(TABLE_ROOM, null, room5);

        ContentValues room6 = new ContentValues();
        room1.put(COLUMN_ROOM, 5);
        db.insert(TABLE_ROOM, null, room6);

        ContentValues room7 = new ContentValues();
        room1.put(COLUMN_ROOM, 6);
        db.insert(TABLE_ROOM, null, room7);

        ContentValues room8 = new ContentValues();
        room1.put(COLUMN_ROOM, 7);
        db.insert(TABLE_ROOM, null, room8);

        ContentValues room9 = new ContentValues();
        room1.put(COLUMN_ROOM, 8);
        db.insert(TABLE_ROOM, null, room9);

        ContentValues room10 = new ContentValues();
        room1.put(COLUMN_ROOM, 9);
        db.insert(TABLE_ROOM, null, room10);
    }

    public void initGroups(SQLiteDatabase db){
        ContentValues group1 = new ContentValues();
        group1.put(COLUMN_GROUP_INFO, "Germany");
        group1.put(COLUMN_SUM, 25);
        group1.put(COLUMN_GROUP_ID, "Mercedes");
        db.insert(TABLE_GROUPS, null, group1);

        ContentValues group2 = new ContentValues();
        group2.put(COLUMN_GROUP_INFO, "USA");
        group2.put(COLUMN_SUM, 25);
        group2.put(COLUMN_GROUP_ID, "Tesla");
        db.insert(TABLE_GROUPS, null, group2);

        ContentValues group3 = new ContentValues();
        group3.put(COLUMN_GROUP_INFO, "Korea");
        group3.put(COLUMN_SUM, 27);
        group3.put(COLUMN_GROUP_ID, "KIA");
        db.insert(TABLE_GROUPS, null, group3);

        ContentValues group4 = new ContentValues();
        group4.put(COLUMN_GROUP_INFO, "Japon");
        group4.put(COLUMN_SUM, 30);
        group4.put(COLUMN_GROUP_ID, "Toyoto");
        db.insert(TABLE_GROUPS, null, group4);
    }

    public void initCity(SQLiteDatabase db){
        ContentValues city1 = new ContentValues();
        city1.put(COLUMN_COUNTRY, "Kazkhstan");
        city1.put(COLUMN_CITY, "Taraz");
        db.insert(TABLE_CITY, null, city1);

        ContentValues city2 = new ContentValues();
        city2.put(COLUMN_GROUP_INFO, "Kazkhstan");
        city2.put(COLUMN_GROUP_ID, "Almaty");
        db.insert(TABLE_CITY, null, city2);

        ContentValues city3 = new ContentValues();
        city3.put(COLUMN_GROUP_INFO, "Kazkhstan");
        city3.put(COLUMN_GROUP_ID, "Astana");
        db.insert(TABLE_CITY, null, city3);
    }

    public void initUniversity(SQLiteDatabase db){
        ContentValues uni1 = new ContentValues();
        uni1.put(COLUMN_CARS, "Audi");
        db.insert(TABLE_CARS, null, uni1);

        ContentValues uni2 = new ContentValues();
        uni2.put(COLUMN_CARS, "Mercedes");
        db.insert(TABLE_CARS, null, uni2);

        ContentValues uni3 = new ContentValues();
        uni3.put(COLUMN_CARS, "Lexus");
        db.insert(TABLE_CARS, null, uni3);
    }


}
