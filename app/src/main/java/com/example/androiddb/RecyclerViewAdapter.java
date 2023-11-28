package com.example.androiddb;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Manga> mangaList;

    public RecyclerViewAdapter(Context context, ArrayList<Manga> mangaList){
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
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mangaName;
        TextView mangaAuthor;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mangaName = itemView.findViewById(R.id.m_name);
            mangaAuthor = itemView.findViewById(R.id.m_author);
        }
    }

}
