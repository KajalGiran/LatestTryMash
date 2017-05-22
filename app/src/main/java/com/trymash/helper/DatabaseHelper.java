package com.trymash.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.trymash.model.ScoreBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desktop on 13-04-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "trymashData";

    // Contacts table name
    private static final String TABLE_SCORE = "score";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE = "userScore";
    private static final String KEY_TIME = "time";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SCORE + " INTEGER,"
                + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        // Create tables again
        onCreate(db);
    }

    // Adding new score
    public void addScoreData(ScoreBoard scoreBoard) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, scoreBoard.getScore()); // Contact Name
        values.put(KEY_TIME, scoreBoard.getTime()); // Contact Phone Number
        // Inserting Row
        db.insert(TABLE_SCORE, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Scores
    public List<ScoreBoard> getAllScores() {
        List<ScoreBoard> scoreBoards = new ArrayList<ScoreBoard>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SCORE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ScoreBoard scoreBoard = new ScoreBoard();
                scoreBoard.setScore(cursor.getInt(1));
                scoreBoard.setTime(cursor.getString(2));
                // Adding contact to list
                scoreBoards.add(scoreBoard);
            } while (cursor.moveToNext());
        }

        // return contact list
        return scoreBoards;
    }
}
