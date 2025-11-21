package com.example.myapplication;

import android.content.ContentUris;
import android.net.Uri;

public class FriendsContract {

    // Назва таблиці, до якої будемо звертатися
    // ВИПРАВЛЕНО: додано "public"
    public static final String TABLE_NAME = "friends";

    // Унікальна назва провайдера контенту
    // ВИПРАВЛЕНО: додано "public"
    public static final String CONTENT_AUTHORITY = "com.example.friendsprovider";

    // Базовий URI для звернення до провайдера
    // ВИПРАВЛЕНО: додано "public"
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Тип вмісту для повернення набору даних (кілька рядків)
    // ВИПРАВЛЕНО: додано "public"
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    // Тип вмісту для повернення одного об'єкта (один рядок)
    // ВИПРАВЛЕНО: додано "public"
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    // Допоміжний URI для прямого доступу до таблиці friends
    // ВИПРАВЛЕНО: додано "public"
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    // Внутрішній клас, що описує стовпці таблиці
    public static class Columns {
        public static final String _ID = "_id";
        public static final String NAME = "Name";
        public static final String EMAIL = "Email";
        public static final String PHONE = "Phone";

        private Columns() {
            // Приватний конструктор, щоб клас не можна було створити
        }
    }

    // Створює URI для конкретного друга за його ID
    // ВИПРАВЛЕНО: додано "public"
    public static Uri buildFriendUri(long friendId) {
        return ContentUris.withAppendedId(CONTENT_URI, friendId);
    }

    // Отримує ID друга з URI
    // ВИПРАВЛЕНО: додано "public"
    public static long getFriendId(Uri uri) {
        // У PDF помилка "parseld", виправлено на "parseId"
        return ContentUris.parseId(uri);
    }
}