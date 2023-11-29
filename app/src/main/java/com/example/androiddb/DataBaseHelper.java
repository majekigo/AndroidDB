package com.example.androiddb;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "manga.db";
    private static final int SCHEMA = 2;
    static final String TABLE_NAME = "manga";

    public static final String COLUM_ID = "id_manga";
    public static final String COLUM_NAME = "manga_name";
    public static final String COLUMN_AUTHOR = "manga_author";
    public static final String COLUMN_ADDITIONAL_INFO = "additional_info";

    public DataBaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUM_NAME + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_ADDITIONAL_INFO + " TEXT DEFAULT '');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addManga(Manga manga) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_NAME, manga.getManga_Name());
        contentValues.put(COLUMN_AUTHOR, manga.getManga_Author());
        contentValues.put(COLUMN_ADDITIONAL_INFO, manga.getAdditionalInfo()); // новый столбец

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


    public void deleteManga(Manga manga) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUM_ID + " = ?", new String[]{String.valueOf(manga.getID_Manga())});
        db.close();
    }

    public ArrayList<Manga> getMangaList() {
        ArrayList<Manga> listManga = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (result != null) {
            while (result.moveToNext()) {
                @SuppressLint("Range") int id = result.getInt(result.getColumnIndex(COLUM_ID));
                @SuppressLint("Range") String mangaName = result.getString(result.getColumnIndex(COLUM_NAME));
                @SuppressLint("Range") String mangaAuthor = result.getString(result.getColumnIndex(COLUMN_AUTHOR));

                // Проверяем наличие столбца в результирующем наборе
                int additionalInfoIndex = result.getColumnIndex(COLUMN_ADDITIONAL_INFO);
                String additionalInfo = additionalInfoIndex != -1 ? result.getString(additionalInfoIndex) : null;

                Manga manga = new Manga(id, mangaName, mangaAuthor, additionalInfo);
                listManga.add(manga);
            }
            result.close();
        }

        return listManga;
    }

    public Manga getMangaById(int mangaId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUM_ID + "=?", new String[]{String.valueOf(mangaId)});
        Manga manga = null;
        if (result.moveToFirst()) {
            int id = result.getInt(0);
            String mangaName = result.getString(1);
            String mangaAuthor = result.getString(2);
            String additionalInfo = result.getString(3);

            manga = new Manga(id, mangaName, mangaAuthor, additionalInfo);
        }
        result.close();
        return manga;
    }

    public void updateManga(Manga manga) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUM_NAME, manga.getManga_Name());
        values.put(COLUMN_AUTHOR, manga.getManga_Author());
        values.put(COLUMN_ADDITIONAL_INFO, manga.getAdditionalInfo());

        db.update(TABLE_NAME, values, COLUM_ID + " = ?", new String[]{String.valueOf(manga.getID_Manga())});
        db.close();
    }

}
