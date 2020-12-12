package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "felhasznalo.db";
    public static final int DB_VERSION = 1;

    public static final  String DB_TABLE     = "felhasznalo";
    public static final  String TABLE_ID     = "id";
    public static final  String TABLE_EMAIL  = "email";
    public static final  String TABLE_FELHNEV = "felhnev ";
    public static final  String TABLE_JELSZO = "jelszo";
    public static final  String TABLE_TELJESNEV = "teljesnev";

    public SQLDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+DB_TABLE+"(" +
                TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                TABLE_EMAIL+" VARCHAR(255) NOT NULL, "+
                TABLE_FELHNEV+" VARCHAR(255) NOT NULL, " +
                TABLE_JELSZO+" VARCHAR(255) NOT NULL, " +
                TABLE_TELJESNEV+" VARCHAR(255) NOT NULL " +
                ")" ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+DB_TABLE;
        db.execSQL(sql);
    }

    public boolean adatRogzites(String email, String fNev,String fJelszo, String teljesNev){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new  ContentValues();
        values.put(TABLE_EMAIL,email);
        values.put(TABLE_FELHNEV,fNev);
        values.put(TABLE_JELSZO,fJelszo);
        values.put(TABLE_TELJESNEV,teljesNev);
        long result = db.insert(DB_TABLE,null,values);
        return result != -1;
    }

    public Cursor adatLekerdezes(String nev){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT teljesnev FROM "+ DB_TABLE +" WHERE felhnev = ?",new String[]{nev});
        //return db.query(DB_TABLE, new  String[]{TABLE_ID,TABLE_EMAIL,TABLE_FELHNEV,TABLE_JELSZO,TABLE_FELHNEV},null,null,null,null,null,null);
    }

    public Cursor adatLekerdezesEmail(String emil){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT teljesnev FROM "+ DB_TABLE +" WHERE email = ?",new String[]{emil});
        //return db.query(DB_TABLE, new  String[]{TABLE_ID,TABLE_EMAIL,TABLE_FELHNEV,TABLE_JELSZO,TABLE_FELHNEV},null,null,null,null,null,null);
    }
}
