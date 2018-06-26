package com.example.hmonroy.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DBManager.DBManager;

public class MainActivity extends AppCompatActivity {

    //public Integer[] arrEmpleados;
    public ListView lstEmpleados;
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstEmpleados = (ListView)findViewById(R.id.lvEmpleados);

        DBManager dbManager = new DBManager(this);

        Cursor empleados = dbManager.listaEmpleados();

        if(empleados.getCount() > 0){
            empleados.moveToFirst();
            for(int i = 0;i<empleados.getCount(); i++) {
                list.add(empleados.getString(1) + ' ' + empleados.getString(2) + ' ' + empleados.getString(3));

                empleados.moveToNext();
            }
            ArrayAdapter<String> empleadosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            lstEmpleados.setAdapter(empleadosAdapter);
        }else{
            Toast.makeText(MainActivity.this, "No hay empleados!!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, NuevoEmpleadoActivity.class);
            startActivity(intent);
        }

        lstEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(MainActivity.this, ResumenActivity.class);
                intent.putExtra("id", String.valueOf(pos));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.principal, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nuevo:
                Intent intent = new Intent(MainActivity.this, NuevoEmpleadoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



}
