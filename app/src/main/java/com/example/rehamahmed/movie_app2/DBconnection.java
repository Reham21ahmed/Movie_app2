package com.example.rehamahmed.movie_app2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reham ahmed on 1/27/2016.
 */
public class DBconnection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydatabase";
    public static final String CONTACTS_TABLE_NAME = "favourite";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "title";
    public static final String CONTACTS_COLUMN_poster = "poster";
    public static final String CONTACTS_COLUMN_overview = "overview";
    public static final String CONTACTS_COLUMN_releasedata = "releasedata";
    public static final String CONTACTS_COLUMN_voteavarage = "voteavarage";


    public DBconnection(Context context) {
        super(context,DATABASE_NAME, null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
   String table=
                "create table IF NOT EXISTS " + CONTACTS_TABLE_NAME +
                        "(" + CONTACTS_COLUMN_ID + " integer primary key, " + CONTACTS_COLUMN_NAME + " text, " + CONTACTS_COLUMN_overview + " text, " + CONTACTS_COLUMN_poster + " string, " + CONTACTS_COLUMN_releasedata + " text)";
        db.execSQL(table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXISTS admin");
        onCreate(db);

    }


    public void insertContact(String title,String poster ,int id, String overview,String releasedata){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME,title);
        contentValues.put(CONTACTS_COLUMN_ID, id);
        contentValues.put(CONTACTS_COLUMN_poster,poster);
        contentValues.put(CONTACTS_COLUMN_overview, overview);
        contentValues.put(CONTACTS_COLUMN_releasedata, releasedata);
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
    }


    public Cursor getallrecord() {


        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from favourite ", null);
        res.moveToFirst();


        return res;
    }



    public void deleteContact (Integer id)
    {

        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from  favourite  where movie_id='" + id + "'");
    }
    }







