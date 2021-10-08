package com.example.kraftwerk;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class KraftWerkDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Kraftwerk.db";



    public KraftWerkDbHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(KraftWerkdb.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void erstelleFirstTable() {


        SQLiteDatabase db = this.getWritableDatabase();


        int help =22;
        int i = 0;
        do{
            i++;
            ContentValues values = new ContentValues();
            values.put(KraftWerkdb.BestBfEntry._ID, i);
            values.put(KraftWerkdb.BestBfEntry.LINE_COL, help);
            values.put(KraftWerkdb.BestBfEntry.MHD_COL, 330);
            values.put(KraftWerkdb.BestBfEntry.FORMAT_COL, "NORMAL");
            values.put(KraftWerkdb.BestBfEntry.SDISC_COL, 8);
            values.put(KraftWerkdb.BestBfEntry.FLAG_COL, 0);
            db.insert(KraftWerkdb.BestBfEntry.TABLE_NAME, null, values);
            help ++;

        }while (help < 30);




        db.close();
    }
    public void updateData(int lineNumber, int addBestbefore, String formatStyle, int anzahldisc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KraftWerkdb.BestBfEntry.LINE_COL, lineNumber);
        values.put(KraftWerkdb.BestBfEntry.MHD_COL, addBestbefore);
        values.put(KraftWerkdb.BestBfEntry.FORMAT_COL, formatStyle);
        values.put(KraftWerkdb.BestBfEntry.SDISC_COL, anzahldisc);
        values.put(KraftWerkdb.BestBfEntry.FLAG_COL, 1);

        db.update(KraftWerkdb.BestBfEntry.TABLE_NAME, values, "line=?", new String[]{""+lineNumber});
        db.close();
    }
}