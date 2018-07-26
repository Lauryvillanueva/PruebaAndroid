package com.prueba.pruebaandroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.content.Intent;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Intent intent = new Intent(Home.this, Home.class);
        //startActivity(intent);
    }

    public void onClick_Iniciar(View view) {
        showDialog(true);
    }

    public void onClick_Registrarse(View view) {
        showDialog(false);
    }

    private void showDialog(final boolean type) {
        LayoutInflater li = LayoutInflater.from(this);

        View prompt = li.inflate(R.layout.loginregister_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(prompt);
        final TextInputLayout usernameWrapper = (TextInputLayout) prompt.findViewById(R.id.input_username);

        final EditText username = (EditText) prompt.findViewById(R.id.login_username);
        final EditText password = (EditText) prompt.findViewById(R.id.login_password);
        final TextView title = (TextView) prompt.findViewById(R.id.toolbar_title);

        //fb
        final LinearLayout linear_or = (LinearLayout) prompt.findViewById(R.id.login_layout_or);
        //
        if (type) {
            usernameWrapper.setHint("Usuario");
            title.setText("Ingresar");
        } else {
            linear_or.setVisibility(View.GONE);
            title.setText("Registro");
        }
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancelar", null);

        //-------------------------------------------------------
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (type) {
                            //LOGIN
                            if (!TextUtils.isEmpty(username.getText().toString())
                                    && !TextUtils.isEmpty(password.getText().toString())) {
                                //verificar en BD
                                Intent i = new Intent(Home.this, ActividadTareas.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(Home.this, "Hay Campos Vacios", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            //REGISTRAR
                            if (!TextUtils.isEmpty(username.getText().toString())
                                    && !TextUtils.isEmpty(password.getText().toString())) {

                                //INGRESAR EN BD
                                progressDialog = new ProgressDialog(Home.this);
                                progressDialog.setIndeterminate(true);
                                progressDialog.setMessage("Creando usuario...");
                                progressDialog.show();


                            } else {
                                Toast.makeText(Home.this, "Hay Campos Vacios", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
            }
        });
        alertDialog.show();
    }
}




