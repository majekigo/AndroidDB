package com.example.androiddb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText mangaName, mangaAuthor, mangaAdditionalInfo; // Добавьте это поле
    Button addManga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mangaName = findViewById(R.id.manga_name);
        mangaAuthor = findViewById(R.id.manga_author);
        mangaAdditionalInfo = findViewById(R.id.manga_additional_info); // Используйте это поле
        addManga = findViewById(R.id.add_manga);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        addManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Manga manga = new Manga(0, mangaName.getText().toString(),
                        mangaAuthor.getText().toString(), "");

                // Получаем значение из нового поля "Дополнительная информация"
                manga.setAdditionalInfo(mangaAdditionalInfo.getText().toString());

                dataBaseHelper.addManga(manga);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}