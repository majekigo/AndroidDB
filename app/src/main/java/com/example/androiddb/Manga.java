package com.example.androiddb;

public class Manga {
    private int ID_Manga;
    private String manga_Name;
    private String manga_Author;
    private String additionalInfo; // Добавьте поле

    public Manga(int ID_Manga, String manga_Name, String manga_Author, String additionalInfo) {
        this.ID_Manga = ID_Manga;
        this.manga_Name = manga_Name;
        this.manga_Author = manga_Author;
        this.additionalInfo = additionalInfo;
    }


    // Добавьте геттеры и сеттеры
    public int getID_Manga() {
        return ID_Manga;
    }

    public String getManga_Name() {
        return manga_Name;
    }

    public String getManga_Author() {
        return manga_Author;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
