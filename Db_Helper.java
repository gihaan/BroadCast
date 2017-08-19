package com.example.gihan.broadcast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gihan on 8/18/2017.
 */

public class Db_Helper extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "numberdb";
    private final static int DATABSE_VERSION = 1;
    private final String CRATE = "create table " + DB_Contract.TABLE_NAME +
            "(id integer primary key autoincrement," + DB_Contract.INCOMING_NUMBER +" text);";
    private final static String DROP_TABLE = "drop table if exists " +DB_Contract.TABLE_NAME;


    public Db_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CRATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    public void saveNumber(String phoneNumber,SQLiteDatabase database){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DB_Contract.INCOMING_NUMBER,phoneNumber);
        database.insert(DB_Contract.TABLE_NAME,null,contentValues);
    }

    public Cursor readNumber(SQLiteDatabase database){

        String progection[]={"id",DB_Contract.INCOMING_NUMBER};
        return database.query(DB_Contract.TABLE_NAME,progection,null,null,null,null,null);


    }
}
