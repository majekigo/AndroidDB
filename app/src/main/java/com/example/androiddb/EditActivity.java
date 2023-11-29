package com.example.androiddb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Получаем данные из Intent
        int mangaId = getIntent().getIntExtra("manga_id", -1);
        String mangaName = getIntent().getStringExtra("manga_name");
        String mangaAuthor = getIntent().getStringExtra("manga_author");
        String mangaAdditionalInfo = getIntent().getStringExtra("manga_additional_info");

        // Находим элементы интерфейса
        EditText editMangaName = findViewById(R.id.edit_manga_name);
        EditText editMangaAuthor = findViewById(R.id.edit_manga_author);
        EditText editMangaAdditionalInfo = findViewById(R.id.edit_manga_additional_info);
        Button saveButton = findViewById(R.id.save_button);

        // Устанавливаем данные в EditText
        editMangaName.setText(mangaName);
        editMangaAuthor.setText(mangaAuthor);
        editMangaAdditionalInfo.setText(mangaAdditionalInfo);

        // Устанавливаем слушатель для кнопки "Сохранить"
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Получаем измененные данные из EditText
                String updatedMangaName = editMangaName.getText().toString();
                String updatedMangaAuthor = editMangaAuthor.getText().toString();
                String updatedMangaAdditionalInfo = editMangaAdditionalInfo.getText().toString();

                // Ваш код для сохранения изменений в базе данных
                // Например, можно использовать метод updateManga из вашего DataBaseHelper
                DataBaseHelper dbHelper = new DataBaseHelper(EditActivity.this);
                Manga updatedManga = new Manga(mangaId, updatedMangaName, updatedMangaAuthor, updatedMangaAdditionalInfo);
                dbHelper.updateManga(updatedManga);

                // Дополнительные действия по сохранению изменений

                Toast.makeText(EditActivity.this, "Изменения сохранены", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // ... Другие действия, если необходимо
    }
}
