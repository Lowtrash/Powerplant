package com.example.kraftwerk;

import android.provider.BaseColumns;

public final class KraftWerkdb {
    private KraftWerkdb(MainActivity mainActivity) {
    }

    public static class BestBfEntry implements BaseColumns {
        public static final String TABLE_NAME = "bestBefor";
        public static final String LINE_COL = "line";
        public static final String MHD_COL = "mhd";
        public static final String FORMAT_COL = "format";
        public static final String SDISC_COL = "fanzahld";
        public static final String FLAG_COL = "lastactivity";
    }
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + KraftWerkdb.BestBfEntry.TABLE_NAME + " ("
                    + BestBfEntry._ID + " INTEGER PRIMARY KEY,"
                    + BestBfEntry.LINE_COL + " INTEGER NOT NULL,"
                    + BestBfEntry.MHD_COL + " INTEGER NOT NULL,"
                    + BestBfEntry.FORMAT_COL + " TEXT NOT NULL,"
                    + BestBfEntry.SDISC_COL + " TEXT NOT NULL,"
                    + BestBfEntry.FLAG_COL + " INTEGER NOT NULL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + KraftWerkdb.BestBfEntry.TABLE_NAME;


}