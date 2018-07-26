package com.prueba.pruebaandroid;

import android.content.ContentValues;
import android.database.Cursor;
import com.prueba.pruebaandroid.UsersContract.UsersEntry;

import java.util.UUID;

/**
 * Created by LauryV on 26/07/2018.
 */

public class Users {
    private String id;
    private String name;
    private String passw;

    public Users(String name,
                  String passw) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.passw = passw;

    }
    public Users(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(UsersEntry.ID));
        name = cursor.getString(cursor.getColumnIndex(UsersEntry.NAME));
        passw = cursor.getString(cursor.getColumnIndex(UsersEntry.PASSWORD));

    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(UsersEntry.ID, id);
        values.put(UsersEntry.NAME, name);
        values.put(UsersEntry.PASSWORD, passw);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassw() {
        return passw;
    }
}
