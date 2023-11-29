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

        int mangaId = getIntent().getIntExtra("manga_id", -1);

        if (mangaId != -1) {
            DataBaseHelper dbHelper = new DataBaseHelper(this);
            Manga manga = dbHelper.getMangaById(mangaId);

            TextView mangaNameTextView = findViewById(R.id.manga_name_detail);
            TextView mangaAuthorTextView = findViewById(R.id.manga_author_detail);
            TextView mangaAdditionalInfoTextView = findViewById(R.id.manga_additional_info_detail); // Добавьте это поле

            mangaNameTextView.setText(manga.getManga_Name());
            mangaAuthorTextView.setText(manga.getManga_Author());
            mangaAdditionalInfoTextView.setText(manga.getAdditionalInfo()); // Установите текст дополнительной информации
        } else {
            Toast.makeText(this, "Invalid manga ID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
