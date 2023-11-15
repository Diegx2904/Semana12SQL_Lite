package com.example.semana12sql_lite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.semana12sql_lite.Controlador.ConexionHelper;
import com.example.semana12sql_lite.Controlador.Utility;
import com.example.semana12sql_lite.R;

public class Mantenimiento extends AppCompatActivity {

    EditText txtid;
    EditText txtnombre;
    EditText txtcorreo;
    Button btnConsultar,btnActualizar,btnEliminar;
    ConexionHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento);
        conn = new ConexionHelper(getApplicationContext(),"bd_usuarios",null,1);
        txtid = findViewById(R.id.txtID2);
        txtnombre = findViewById(R.id.txtNombre2);
        txtcorreo = findViewById(R.id.txtCorreo2);
        btnConsultar = findViewById(R.id.btnBuscar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarUsuario();
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarUsuario();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();
            }
        });
    }
    private void consultarUsuario(){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {txtid.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_NOMBRE + "," + Utility.CAMPO_CORREO +
                    " FROM " + Utility.TABLA_USUARIO + " WHERE " + Utility.CAMPO_ID + "=? " , parametros);
            cursor.moveToFirst();
            txtnombre.setText(cursor.getString(0));
            txtcorreo.setText(cursor.getString(1));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "ATENCION, usuario no existe",
                    Toast.LENGTH_LONG).show();
            limpiar();
        }
    }
    private void actualizarUsuario(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtid.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utility.CAMPO_NOMBRE, txtnombre.getText().toString());
        values.put(Utility.CAMPO_CORREO, txtcorreo.getText().toString());

        db.update(Utility.TABLA_USUARIO, values, Utility.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCION, se actualizo el usuario",
                Toast.LENGTH_LONG).show();
        limpiar();
        db.close();
    }
    private void eliminarUsuario(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtid.getText().toString()};

        db.delete(Utility.TABLA_USUARIO, Utility.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCION, se elimino el usuario",
                Toast.LENGTH_LONG).show();
        limpiar();
        db.close();
    }
    private void limpiar(){
        txtnombre.setText("");
        txtcorreo.setText("");
    }
}