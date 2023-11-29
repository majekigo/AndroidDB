package com.example.androiddb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listManga;
    Button goAddManga;
    Button refreshButton; // Добавили кнопку обновления

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listManga = findViewById(R.id.list_manga);
        goAddManga = findViewById(R.id.go_add_manga);
        refreshButton = findViewById(R.id.refresh_button); // Инициализируем кнопку обновления

        goAddManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Обновляем данные в RecyclerViewAdapter
                updateRecyclerView();
            }
        });

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        listManga.setLayoutManager(new LinearLayoutManager(this));
        listManga.setHasFixedSize(true);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dataBaseHelper.getMangaList());
        listManga.setAdapter(adapter);
    }

    // Метод для обновления данных в RecyclerViewAdapter
    private void updateRecyclerView() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) listManga.getAdapter();
        ArrayList<Manga> newMangaList = dataBaseHelper.getMangaList();
        adapter.updateData(newMangaList);
    }
}
