package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Клас для управління базою даних
public class AppDatabase extends SQLiteOpenHelper {

    // Назва файлу бази даних та її версія
    public static final String DATABASE_NAME = "friends.db";
    public static final int DATABASE_VERSION = 1;

    // Використання шаблону Singleton для єдиного екземпляра класу
    private static AppDatabase instance = null;

    private AppDatabase(Context context) {
        // Тут була помилка: ви скопіювали в кінець рядка
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Метод для отримання єдиного екземпляра AppDatabase
    static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabase(context);
        }
        return instance;
    }

    // Цей метод викликається при створенні бази даних
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL-запит для створення таблиці "friends"
        // ВИПРАВЛЕНО: SQL-запит був розбитий на багато окремих рядків.
        // Я об'єднав його в один правильний рядок.
        String sql = "CREATE TABLE " + FriendsContract.TABLE_NAME + " (" +
                FriendsContract.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                FriendsContract.Columns.NAME + " TEXT NOT NULL, " +
                FriendsContract.Columns.EMAIL + " TEXT, " +
                FriendsContract.Columns.PHONE + " TEXT NOT NULL)";

        db.execSQL(sql);

        // Додаємо початкові дані (двох друзів)
        // ВИПРАВЛЕНО: Ці команди також були розбиті неправильно.
        db.execSQL("INSERT INTO " + FriendsContract.TABLE_NAME + " (" +
                FriendsContract.Columns.NAME + ", " +
                FriendsContract.Columns.PHONE +
                ") VALUES ('Tom', '+12345678990');");

        db.execSQL("INSERT INTO " + FriendsContract.TABLE_NAME + " (" +
                FriendsContract.Columns.NAME + ", " +
                FriendsContract.Columns.EMAIL + ", " +
                FriendsContract.Columns.PHONE +
                ") VALUES ('Bob', 'bob@gmail.com', '+13456789102');");
    }

    // Цей метод викликається при оновленні версії бази даних
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Поки що нічого не робимо
    }
}