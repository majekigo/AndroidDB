package com.example.androiddb;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Получаем переданный ID манги
        int mangaId = getIntent().getIntExtra("manga_id", -1);

        // Если ID корректен, получаем подробную информацию из базы данных
        if (mangaId != -1) {
            DataBaseHelper dbHelper = new DataBaseHelper(this);
            Manga manga = dbHelper.getMangaById(mangaId);

            // Теперь у вас есть объект Manga с подробной информацией, и вы можете отобразить её в вашем макете
            // Например, используя TextView:
            TextView mangaNameTextView = findViewById(R.id.manga_name_detail);
            TextView mangaAuthorTextView = findViewById(R.id.manga_author_detail);

            mangaNameTextView.setText(manga.getManga_Name());
            mangaAuthorTextView.setText(manga.getManga_Author());
        } else {
            // Обработка ошибки, например, если ID не был передан корректно
            Toast.makeText(this, "Invalid manga ID", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем активность, так как ID некорректен
        }
    }
}
