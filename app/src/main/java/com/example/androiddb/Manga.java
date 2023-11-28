package com.example.androiddb;

public class Manga {

    private int ID_Manga;
    private String Manga_Name;
    private String Manga_Author;

    public Manga(int ID_Manga, String manga_Name, String manga_Author){
        this.ID_Manga = ID_Manga;
        Manga_Name = manga_Name;
        Manga_Author = manga_Author;
    }

    public int getID_Manga(){
        return ID_Manga;
    }

    public void setID_Manga(int ID_Manga){
        this.ID_Manga = ID_Manga;
    }

    public String getManga_Name(){
        return Manga_Name;
    }

    public void setManga_Name(String manga_Name){
        Manga_Name = manga_Name;
    }

    public String getManga_Author(){
        return Manga_Author;
    }
}
