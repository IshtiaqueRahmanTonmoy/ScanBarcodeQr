package com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;

import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.AdapterClass;
import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TONMOYPC on 6/22/2017.
 */
public class DatabaseHandler{

    //define static variable
    public static int dbversion =3;
    public static String dbname = "ScanDB";
    public static String dbTable = "scan";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context,dbname,null, dbversion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+dbTable+" (id INTEGER PRIMARY KEY autoincrement,scanname, scanresult, date, note)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+dbTable);
            onCreate(db);
        }
    }

    //establsh connection with SQLiteDataBase
    private final Context c;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlDb;

    public DatabaseHandler(Context context) {
        this.c = context;
    }
    public DatabaseHandler open() throws SQLException {
        dbHelper = new DatabaseHelper(c);
        sqlDb = dbHelper.getWritableDatabase();
        return this;
    }

    //insert data
    public void insert(String text2,String text3,String text4,String text5) {
        if(!isExist(text3)) {
            sqlDb.execSQL("INSERT INTO scan (scanname,scanresult,date,note) VALUES('"+text2+"','"+text3+"','"+text4+"','"+text5+"')");
        }
    }
    //check entry already in database or not
    public boolean isExist(String result){
        String query = "SELECT scanresult FROM scan WHERE scanresult='"+result+"' LIMIT 1";
        Cursor row = sqlDb.rawQuery(query, null);
        return row.moveToFirst();
    }

    //edit data
    public void update(int id, String text2, String text3, String text4, String text5) {
        sqlDb.execSQL("UPDATE "+dbTable+" SET name='"+text2+"', number='"+text3+"', email='"+text4+"', address='"+text5+"',   WHERE _id=" + id);
    }

    //delete data
    public void delete(int id) {
        sqlDb.execSQL("DELETE FROM "+dbTable+" WHERE _id="+id);
    }

    //fetch data
    public List<AdapterClass> getAllValues() {
        List<AdapterClass> contactList = new ArrayList<AdapterClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + dbTable;
        Cursor cursor = sqlDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AdapterClass contact = new AdapterClass();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setScannedbyname(cursor.getString(1));
                contact.setScannedresult(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setNote(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    //fetch data by filter
    public Cursor fetchdatabyfilter(String inputText,String filtercolumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM "+dbTable;
        if (inputText == null  ||  inputText.length () == 0)  {
            row = sqlDb.rawQuery(query, null);
        }else {
            query = "SELECT * FROM "+dbTable+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            row = sqlDb.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
}

