package com.example.movierating.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.movierating.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MovieRating";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "movie";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DES = "description";
    private static final String KEY_TRAILER = "linkTrailer";
    private static final String KEY_IMG = "linkImg";
    private static final String KEY_RATING = "rating";
    private static final String KEY_YEAR = "year";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_DES, KEY_TRAILER,KEY_IMG,KEY_RATING,KEY_YEAR);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);
        onCreate(db);
    }

    public void add(Movie student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,student.getId());
        values.put(KEY_NAME, student.getMovieName());
        values.put(KEY_DES, student.getDescription());
        values.put(KEY_TRAILER, student.getLinkTrailer());
        values.put(KEY_IMG, student.getLinkImg());
        values.put(KEY_RATING, student.getRating());
        values.put(KEY_YEAR, student.getYear());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Movie> getAllMoive() {
        List<Movie>  studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Movie student = new Movie( cursor.getString(2),cursor.getInt(0), cursor.getString(4), cursor.getString(3),cursor.getString(1),cursor.getDouble(5),cursor.getInt(6));
            studentList.add(student);
            cursor.moveToNext();
        }
        return studentList;
    }
    public void setRating(int id, double rating){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RATING,rating);
        // updating row
         db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}
