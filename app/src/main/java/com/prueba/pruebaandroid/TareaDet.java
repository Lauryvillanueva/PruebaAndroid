package com.prueba.pruebaandroid;

import android.provider.BaseColumns;

/**
 * Created by LauryV on 26/07/2018.
 */

public class TareaDet {
    /**Esquema de la base de datos para las tareas */
    public static abstract class TareaEntry implements BaseColumns {
        public static final String TABLE_NAME ="tarea";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String DESCRIPCION = "descripcion";
        public static final String ESTADO = "estado";

    }
}



