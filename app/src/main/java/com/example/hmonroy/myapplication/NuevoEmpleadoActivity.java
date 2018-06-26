package com.example.hmonroy.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import DBManager.DBManager;

public class NuevoEmpleadoActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText paterno;
    private EditText materno;
    private EditText correo;
    private EditText calle;
    private EditText numExt;
    private EditText numInt;
    private EditText cp;
    private EditText colonia;
    private EditText delegacion;
    private EditText estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_empleado);

        setupViews();
    }

    public void setupViews(){
        nombre = (EditText)findViewById(R.id.txtNombres);
        paterno = (EditText)findViewById(R.id.txtPaterno);
        materno = (EditText)findViewById(R.id.txtMaterno);
        correo = (EditText)findViewById(R.id.txtCorreo);
        calle = (EditText)findViewById(R.id.txtCalle);
        numExt = (EditText)findViewById(R.id.txtNumExt);
        numInt = (EditText)findViewById(R.id.txtNumInt);
        cp = (EditText)findViewById(R.id.txtCP);
        colonia = (EditText)findViewById(R.id.txtColonia);
        delegacion = (EditText)findViewById(R.id.txtDelegacion);
        estado = (EditText)findViewById(R.id.txtEstado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nuevo_empleado, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.guardar:
                String sNombre = nombre.getText().toString().toUpperCase();
                String sPaterno = paterno.getText().toString().toUpperCase();
                String sMaterno = materno.getText().toString().toUpperCase();
                String sCorreo = correo.getText().toString().toLowerCase();
                String sCalle = calle.getText().toString().toUpperCase();
                String sNumExt = numExt.getText().toString().toUpperCase();
                String sNumInt = numInt.getText().toString().toUpperCase();
                String sColonia = colonia.getText().toString().toUpperCase();
                String sCP = cp.getText().toString();
                String sDelegacion = delegacion.getText().toString().toUpperCase();
                String sEstado = estado.getText().toString().toUpperCase();

                DBManager dbManager = new DBManager(this);

                Boolean guarda = false;

                if(sNombre.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa el Nombre", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(sPaterno.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa el Apellido Paterno", Toast.LENGTH_LONG).show();
                    return true;
                }

                if(sCorreo.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa el Correo Electrónico", Toast.LENGTH_LONG).show();
                    return true;
                }

                if(sCalle.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa el Nombre de la Calle", Toast.LENGTH_LONG).show();
                    return true;
                }


                if(sNumExt.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa el Número Exterior", Toast.LENGTH_LONG).show();
                    return true;
                }

                if(sColonia.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa la Colonia", Toast.LENGTH_LONG).show();
                    return true;
                }

                if(sCP.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa el Código Postal", Toast.LENGTH_LONG).show();
                    return true;
                }

                if(sDelegacion.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa la Delegación", Toast.LENGTH_LONG).show();
                    return true;
                }

                if(sEstado.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Ingresa el estado", Toast.LENGTH_LONG).show();
                    return true;
                }

                guarda = dbManager.insertaEmpleado(sNombre, sPaterno, sMaterno, sCorreo, sCalle, sNumExt, sNumInt, sCP, sColonia, sDelegacion, sEstado);

                if(guarda){
                    Toast.makeText(this, "guardado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "NO guardado", Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.cancelar:
                Intent intent = new Intent(NuevoEmpleadoActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
