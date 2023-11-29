package com.example.androiddb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Manga> mangaList;

    public RecyclerViewAdapter(Context context, ArrayList<Manga> mangaList) {
        this.context = context;
        this.mangaList = mangaList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.manga_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Manga manga = mangaList.get(position);
        holder.mangaName.setText(manga.getManga_Name());
        holder.mangaAuthor.setText(manga.getManga_Author());

        // Добавляем обработчик для кнопки удаления
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Получаем позицию элемента в списке
                int adapterPosition = holder.getAdapterPosition();

                // Проверяем, чтобы позиция была корректной
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Удаляем элемент из списка и из базы данных
                    Manga deletedManga = mangaList.remove(adapterPosition);
                    DataBaseHelper dbHelper = new DataBaseHelper(context);
                    dbHelper.deleteManga(deletedManga);

                    // Уведомляем адаптер об изменениях
                    notifyItemRemoved(adapterPosition);
                }
            }
        });

        // Добавляем обработчик для элемента списка
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаем Intent для перехода на DetailActivity
                Intent intent = new Intent(context, DetailActivity.class);
                // Передаем ID манги в Intent
                intent.putExtra("manga_id", manga.getID_Manga());
                // Запускаем активность с подробной информацией
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mangaName;
        TextView mangaAuthor;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mangaName = itemView.findViewById(R.id.m_name);
            mangaAuthor = itemView.findViewById(R.id.m_author);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}

