package com.example.androiddb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "manga.db";
    private static final int SCHEMA = 1;
    static final String TABLE_NAME = "manga";

    public static final String COLUM_ID = "id_manga";
    public static final String COLUM_NAME = "manga_name";
    public static final String COLUMN_AUTHOR = "manga_author";


    public DataBaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUM_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUM_NAME
                + " TEXT, " + COLUMN_AUTHOR + " INTEGER);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addManga(Manga manga){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(COLUM_ID, manga.getID_Manga());
        contentValues.put(COLUM_NAME, manga.getManga_Name());
        contentValues.put(COLUMN_AUTHOR, manga.getManga_Author());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Manga> getMangaList(){

        ArrayList<Manga> listManga = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if(result.moveToFirst()){
            while (result.moveToNext()){
                int id = result.getInt(0);
                String mangaName = result.getString(1);
                String mangaAuthor = result.getString(2);
                Manga manga = new Manga(id, mangaName, mangaAuthor);
                listManga.add(manga);
            }
        }
        result.close();
        return listManga;
    }
}
