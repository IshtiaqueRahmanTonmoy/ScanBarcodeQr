package com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.AdapterClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TONMOYPC on 6/22/2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "scannerdb";

    // Contacts table name
    private static final String TABLE_SCAN = "scan";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "scanname";
    private static final String KEY_RESULT = "scanresult";
    private static final String KEY_DATE = "date";
    private static final String KEY_NOTE = "note";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SCAN + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_RESULT + " TEXT," + KEY_DATE + " TEXT,"+KEY_NOTE+"TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCAN);

        // Create tables again
        onCreate(db);
    }

    public void addData(AdapterClass adapter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, adapter.getScannedbyname());
        values.put(KEY_RESULT, adapter.getScannedresult());
        values.put(KEY_DATE, adapter.getDate());
        values.put(KEY_NOTE, adapter.getNote());

        // Inserting Row
        db.insert(TABLE_SCAN, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
     public AdapterClass getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SCAN, new String[] { KEY_ID,
                        KEY_NAME, KEY_RESULT,KEY_DATE,KEY_NOTE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        AdapterClass adapterclass = new AdapterClass(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));
        // return contact
        return adapterclass;
    }

    public List<AdapterClass> getAllContacts() {
        List<AdapterClass> contactList = new ArrayList<AdapterClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SCAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AdapterClass adapterclass = new AdapterClass();
                adapterclass.setId(Integer.parseInt(cursor.getString(0)));
                adapterclass.setScannedbyname(cursor.getString(1));
                adapterclass.setScannedresult(cursor.getString(2));
                adapterclass.setDate(cursor.getString(2));
                adapterclass.setNote(cursor.getString(2));
                // Adding contact to list
                contactList.add(adapterclass);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public boolean isExist(String result) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SCAN + " WHERE scanresult = '" + result + "'", null);
        boolean exist = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exist;
    }
}