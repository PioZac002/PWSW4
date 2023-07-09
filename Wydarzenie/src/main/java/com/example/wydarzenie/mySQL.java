package com.example.wydarzenie;

public class mySQL {
    public static String login="";        // do zapamietania loginu
    public static String passwd="";       // do zapamietania hasła
    public static String select = "";     // do zapytania select
    public static String execute = "";    // do zapytania Insert
    public static String param = "";      // parametry w zapytaniu SQL
    public static int idUser=0;           // tymczasow do zapamietania id usera
    public static int idEvent=0;          // tymczasowo do zapamiętania id wydarzenia
    public static boolean status=false;   // status wyszukiwania SQL
    public static boolean czyDopis=false; // do rozpoznania czy modyfikuję lub  czy dopisuje dane do tablicy events
    public static int checkID=0;          // status dla okna logowania
    public mySQL() { }                    // konstruktor


}
