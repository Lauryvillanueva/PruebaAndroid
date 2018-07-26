package com.prueba.pruebaandroid;
import android.content.ContentValues;
import android.database.Cursor;
import com.prueba.pruebaandroid.TareaDet.TareaEntry;


import java.util.UUID;

/**
 * Created by LauryV on 26/07/2018.
 */

public class Tarea {
    private String id;
    private String nombre;
    private String descripcion;
    private String estado;

    public Tarea(String nombre,
                  String descripcion, String estado) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;

    }
    public Tarea(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(TareaEntry.ID));
        nombre = cursor.getString(cursor.getColumnIndex(TareaEntry.NOMBRE));
        descripcion = cursor.getString(cursor.getColumnIndex(TareaEntry.DESCRIPCION));
        //estado = cursor.getString(cursor.getColumnIndex(TareaEntry.ESTADO));

    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public String getEstado() {
        return estado;
    }


    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TareaDet.TareaEntry.ID, id);
        values.put(TareaDet.TareaEntry.NOMBRE, nombre);
        values.put(TareaEntry.DESCRIPCION, descripcion);
        values.put(TareaDet.TareaEntry.ESTADO, estado);
        return values;
    }
}

