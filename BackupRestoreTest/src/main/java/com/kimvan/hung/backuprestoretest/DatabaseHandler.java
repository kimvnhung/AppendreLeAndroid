package com.kimvan.hung.backuprestoretest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by h on 17/01/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VESION =1;
    private static final String DATABASE_NAME = "Information.db";
    private static final String TABLE_NAME="Infor";
    private static final String COLUMN_NAME="_name";
    private static final String COLUMN_DATE="_born_date";
    private static final String COLUMN_TELEPHONE_NUMBER="_telephone_number";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+
                "(" +
                COLUMN_NAME+" TEXT NOT NULL PRIMARY KEY,"+
                COLUMN_DATE+" TEXT ,"+
                COLUMN_TELEPHONE_NUMBER+" TEXT"+
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student student){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME,student.getName());
        values.put(COLUMN_DATE,student.getBornDate());
        values.put(COLUMN_TELEPHONE_NUMBER,student.getTelephoneNumber());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public ArrayList<Student> getInfor(){
        ArrayList<Student> result =  new ArrayList<>();

        SQLiteDatabase db =getWritableDatabase();

        Cursor c =db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            result.add(new Student(c.getString(c.getColumnIndex(COLUMN_NAME)),c.getString(c.getColumnIndex(COLUMN_DATE)),
                    c.getString(c.getColumnIndex(COLUMN_TELEPHONE_NUMBER))));
            c.moveToNext();
        }
        c.close();
        return result;
    }

    public void deleteStudent(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+" = \""+name+"\"");
    }
}
