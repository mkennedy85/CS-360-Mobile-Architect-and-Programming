package com.snhu.eventsapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import java.util.List;
import java.util.ArrayList;

public class EventDatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "events.db";

    // Singleton of the database
    private static EventDatabase sEventDatabase;

    public static EventDatabase getInstance(Context context) {
        if (sEventDatabase == null) {
            sEventDatabase = new EventDatabase(context);
        }
        return sEventDatabase;
    }

    private EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final class UserTable {
        private static final String TABLE = "users";
        private static final String COL_ID = "_id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    private static final class EventTable {
        private static final String TABLE = "events";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_DATE = "date";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.TABLE + " (" +
                UserTable.COL_ID + " integer primary key autoincrement, " +
                UserTable.COL_USERNAME + " text," +
                UserTable.COL_PASSWORD + " text)");
        db.execSQL("create table " + EventTable.TABLE + " (" +
                EventTable.COL_ID + " integer primary key autoincrement, " +
                EventTable.COL_NAME + " text," +
                EventTable.COL_DATE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EventTable.TABLE);
        onCreate(db);
    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + EventTable.TABLE;
        Cursor cursor = db.rawQuery(sql, new String[]{});
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                events.add(new Event(id, name, date));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return events;
    }

    public boolean addEvent(String name, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventTable.COL_NAME, name);
        values.put(EventTable.COL_DATE, date);

        // Insert row
        long itemId = db.insert(EventTable.TABLE, null, values);

        return itemId != -1;
    }

    public boolean deleteEvent(Event event) {
        // Get a writeable instance of the database
        SQLiteDatabase db = getWritableDatabase();

        // Delete the item matching the given item's ID
        int rowsDeleted = db.delete(EventTable.TABLE, EventTable.COL_ID + " = ?",
                new String[]{String.valueOf(event.getId())});

        // Check that the row was removed from the database
        return rowsDeleted > 0;
    }

    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserTable.COL_USERNAME, username);
        values.put(UserTable.COL_PASSWORD, password);

        // Insert row
        long itemId = db.insert(UserTable.TABLE, null, values);

        return itemId != -1;
    }
}
