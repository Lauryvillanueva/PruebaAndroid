package com.prueba.pruebaandroid;

/**
 * Created by LauryV on 26/07/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.prueba.pruebaandroid.TareaDet.TareaEntry;


public class TareasDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    public TareasDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TareaEntry.TABLE_NAME + " ("
                + TareaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TareaEntry.ID + " TEXT NOT NULL,"
                + TareaEntry.NOMBRE + " TEXT NOT NULL,"
                + TareaEntry.DESCRIPCION + " TEXT NOT NULL,"
                + "UNIQUE (" + TareaEntry.ID + "))");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }
    public long saveTareas(Tarea tarea ) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                TareaEntry.TABLE_NAME,
                null,
                tarea.toContentValues());

    }
    public Cursor getAllTareas() {
        return getReadableDatabase()
                .query(
                        TareaEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }
    public Cursor getTareaById(String tareaId) {
        Cursor c = getReadableDatabase().query(
                TareaEntry.TABLE_NAME,
                null,
                TareaEntry.ID + " LIKE ?",
                new String[]{tareaId},
                null,
                null,
                null);
        return c;
    }

    public int deleteTarea(String tareaId) {
        return getWritableDatabase().delete(
                TareaEntry.TABLE_NAME,
                TareaEntry.ID + " LIKE ?",
                new String[]{tareaId});
    }

    public int updateTarea(Tarea tarea, String tareaId) {
        return getWritableDatabase().update(
                TareaEntry.TABLE_NAME,
                tarea.toContentValues(),
                TareaEntry.ID + " LIKE ?",
                new String[]{tareaId}
        );
    }


}
