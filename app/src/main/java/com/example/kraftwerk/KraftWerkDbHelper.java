package com.example.kraftwerk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        db.execSQL("DROP TABLE IF EXISTS " + KraftWerkdb.BestBfEntry.TABLE_NAME);
        onCreate(db);
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
    public void updateData(int lineNumber, int addBestbefore, String formatStyle, int anzahldisc,int f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KraftWerkdb.BestBfEntry.LINE_COL, lineNumber);
        values.put(KraftWerkdb.BestBfEntry.MHD_COL, addBestbefore);
        values.put(KraftWerkdb.BestBfEntry.FORMAT_COL, formatStyle);
        values.put(KraftWerkdb.BestBfEntry.SDISC_COL, anzahldisc);
        values.put(KraftWerkdb.BestBfEntry.FLAG_COL, f);

        db.update(KraftWerkdb.BestBfEntry.TABLE_NAME, values, "line=?", new String[]{""+lineNumber});
        db.close();
    }
    public void changeFlag(int flag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KraftWerkdb.BestBfEntry.FLAG_COL, 0);

        db.update(KraftWerkdb.BestBfEntry.TABLE_NAME, values, "lastactivity=?", new String[]{""+flag});
        db.close();
    }
    public List<Serializable> setData(int linie) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selection = KraftWerkdb.BestBfEntry.LINE_COL + " = ?" ;
        String[] selectionArgs = {""+linie};

        Cursor cursor = db.query(
                KraftWerkdb.BestBfEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null,            // The sort order
                null

        );

        List<java.io.Serializable> feld = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int linehelp = cursor.getColumnIndex(KraftWerkdb.BestBfEntry.LINE_COL);
            feld.add(cursor.getInt(linehelp));
            int mhdhelp = cursor.getColumnIndex(KraftWerkdb.BestBfEntry.MHD_COL);
            feld.add(cursor.getInt(mhdhelp));
            int formathelp = cursor.getColumnIndex(KraftWerkdb.BestBfEntry.FORMAT_COL);
            feld.add(cursor.getString(formathelp));
            int anzdischelp = cursor.getColumnIndex(KraftWerkdb.BestBfEntry.SDISC_COL);
            feld.add(cursor.getInt(anzdischelp));
            int flaghelp = cursor.getColumnIndex(KraftWerkdb.BestBfEntry.FLAG_COL);
            if (flaghelp ==1)
                changeFlag(flaghelp);

        }
        else
             erstelleFirstTable();
        cursor.close();
        db.close();
        return feld;

    }

    public void delete(){
        SQLiteDatabase db = this. getWritableDatabase();
        db. execSQL(KraftWerkdb.BestBfEntry.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

}