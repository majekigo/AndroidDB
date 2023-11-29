package com.example.androiddb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
            TextView mangaAdditionalInfoTextView = findViewById(R.id.manga_additional_info_detail);

            mangaNameTextView.setText(manga.getManga_Name());
            mangaAuthorTextView.setText(manga.getManga_Author());
            mangaAdditionalInfoTextView.setText(manga.getAdditionalInfo());

            Button editButton = findViewById(R.id.editButton);
            if (editButton != null) {
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailActivity.this, EditActivity.class);
                        intent.putExtra("manga_id", manga.getID_Manga());
                        intent.putExtra("manga_name", manga.getManga_Name());
                        intent.putExtra("manga_author", manga.getManga_Author());
                        intent.putExtra("manga_additional_info", manga.getAdditionalInfo());
                        startActivity(intent);
                    }
                });
            } else {
                Toast.makeText(this, "Button not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid manga ID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
