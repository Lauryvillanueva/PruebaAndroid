package com.prueba.pruebaandroid;

import android.provider.BaseColumns;

/**
 * Created by LauryV on 25/07/2018.
 */

public class UsersContract {
    public static abstract class UsersEntry implements BaseColumns{
        public static final String TABLE_NAME ="users";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PASSWORD = "passw";

    }

}





