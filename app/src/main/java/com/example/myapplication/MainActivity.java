package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Знаходимо наш TextView на екрані
        TextView textView = findViewById(R.id.textView);

        // 2. Створюємо "контейнер" для тексту, який ми отримаємо з БД
        StringBuilder stringBuilder = new StringBuilder();

        // 3. Робимо ЗАПИТ до нашого AppProvider
        // Ми використовуємо URI з FriendsContract, щоб сказати, які дані нам потрібні
        Cursor cursor = getContentResolver().query(
                FriendsContract.CONTENT_URI, // URI (адреса) наших "friends"
                null, // Які стовпці (null = всі)
                null, // Фільтр WHERE
                null, // Аргументи для фільтра
                null  // Сортування
        );

        // 4. Обробляємо відповідь
        if (cursor != null) {
            // Поки є рядки в таблиці, переходимо до наступного
            while (cursor.moveToNext()) {

                // 5. Отримуємо індекси стовпців (де знаходиться "Name", "Phone" і т.д.)
                // (Ви можете додати Email, якщо хочете)
                int nameIndex = cursor.getColumnIndex(FriendsContract.Columns.NAME);
                int phoneIndex = cursor.getColumnIndex(FriendsContract.Columns.PHONE);

                // 6. Отримуємо самі дані (текст) з цих стовпців
                String name = cursor.getString(nameIndex);
                String phone = cursor.getString(phoneIndex);

                // 7. Додаємо дані до нашого контейнера
                stringBuilder.append(name).append(" - ").append(phone).append("\n");
            }
            // 8. Закриваємо "курсор", це важливо для економії пам'яті
            cursor.close();

            // 9. Встановлюємо весь зібраний текст у наш TextView
            textView.setText(stringBuilder.toString());

        } else {
            // Якщо сталася помилка і "курсор" порожній
            textView.setText("Помилка: не вдалося завантажити дані.");
        }
    }
}