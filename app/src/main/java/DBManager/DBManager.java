package DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * Created by HMonroy on 25/06/18.
 */

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context) {
        super(context, "empleados.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table empleados (id int primary key, nombres text, paterno text, materno text, correo text, calle text, exterior text, interior text, colonia text, cp text, delegacion text, estado text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor listaEmpleados(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from empleados", null);

        return cursor;

    }

    public boolean insertaEmpleado(String nombre, String paterno, String materno, String correo, String calle, String numExt, String numInt, String cp, String colonia, String delegacion, String estado){
        SQLiteDatabase dbGraba = this.getWritableDatabase();

        Cursor cursor = dbGraba.rawQuery("select * from empleados", null);

        int id = cursor.getCount();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("nombres", nombre);
        contentValues.put("paterno", paterno);
        contentValues.put("materno", materno);
        contentValues.put("correo", correo);
        contentValues.put("calle", calle);
        contentValues.put("exterior", numExt);
        contentValues.put("interior", numInt);
        contentValues.put("cp", cp);
        contentValues.put("colonia", colonia);
        contentValues.put("delegacion", delegacion);
        contentValues.put("estado", estado);
        long response = dbGraba.insert("empleados", null, contentValues);

        if (response == -1) {
            return false;//tardo
        } else {
            return true;//lo hizo
        }
    }

    public Cursor empleado(String id){
        SQLiteDatabase dbConsulta = this.getReadableDatabase();

        int idEmpleado = Integer.parseInt(id);

        String query = "select * from empleados where id = " + idEmpleado;

        Cursor cursor = dbConsulta.rawQuery(query, null);

        return cursor;
    }



}
