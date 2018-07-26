package com.prueba.pruebaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class ActividadTareas extends AppCompatActivity {
    public static final int REQUEST_ADD_TAREA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_tareas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String Tareaid = getIntent().getStringExtra(ActivityTareas.EXTRA_TAREA_ID);

        setTitle(Tareaid == null ? "Añadir Tarea" : "Editar tarea");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActividadTareas.this,Agregar.class);
                startActivity(i);

            }
        });
    }



}
