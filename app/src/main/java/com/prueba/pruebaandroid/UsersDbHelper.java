package com.prueba.pruebaandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.prueba.pruebaandroid.UsersContract.UsersEntry;

/**
 * Created by LauryV on 26/07/2018.
 */

public class UsersDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UsersEntry.TABLE_NAME + " ("
                + UsersEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UsersEntry.ID + " TEXT NOT NULL,"
                + UsersEntry.NAME + " TEXT NOT NULL,"
                + UsersEntry.PASSWORD + " TEXT NOT NULL,"
                + "UNIQUE (" + UsersEntry.ID + "))");



        // Insertar datos para prueba
        prueba(db);

    }
    private void prueba(SQLiteDatabase sqLiteDatabase) {
        pruebaUsers(sqLiteDatabase, new Users("LauryV", "123456"));
    }

    public long pruebaUsers(SQLiteDatabase db, Users users) {
        return db.insert(
                UsersEntry.TABLE_NAME,
                null,
                users.toContentValues());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }
    public long saveUsers(Users users ) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                UsersEntry.TABLE_NAME,
                null,
                users.toContentValues());

    }
    public Cursor getAllUsers() {
        return getReadableDatabase()
                .query(
                        UsersEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }



}
