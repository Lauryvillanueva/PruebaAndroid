package com.prueba.pruebaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ActivityTareas extends AppCompatActivity {

    public static final String EXTRA_TAREA_ID = "extra_tarea_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TareasFragment fragment = (TareasFragment)
                getSupportFragmentManager().findFragmentById(R.id.tareas_container);

        if (fragment == null) {
            fragment = TareasFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.tareas_container, fragment)
                    .commit();
        }
    }
}
