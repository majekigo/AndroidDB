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

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();

                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Manga deletedManga = mangaList.remove(adapterPosition);
                    DataBaseHelper dbHelper = new DataBaseHelper(context);
                    dbHelper.deleteManga(deletedManga);
                    notifyItemRemoved(adapterPosition);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("manga_id", manga.getID_Manga());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    // Добавленный метод для обновления данных
    public void updateData(ArrayList<Manga> newMangaList) {
        mangaList.clear();
        mangaList.addAll(newMangaList);
        notifyDataSetChanged();
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
