package com.example.semana12sql_lite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.semana12sql_lite.Controlador.ConexionHelper;
import com.example.semana12sql_lite.Controlador.Utility;
import com.example.semana12sql_lite.R;

public class Registro extends AppCompatActivity {

    EditText txtID;
    EditText txtNombre;
    EditText txtCorreo;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtID = findViewById(R.id.txtID2);
        txtNombre = findViewById(R.id.txtNombre2);
        txtCorreo = findViewById(R.id.txtCorreo2);
        btnRegistrar = findViewById(R.id.btnRegistrarUsuario);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuarios();
            }
        });
    }
    private void registrarUsuarios(){
        ConexionHelper conn = new ConexionHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Utility.CAMPO_ID,txtID.getText().toString());
        contentValues.put(Utility.CAMPO_NOMBRE,txtNombre.getText().toString());
        contentValues.put(Utility.CAMPO_CORREO,txtCorreo.getText().toString());

        Long idResultante = db.insert(Utility.TABLA_USUARIO,Utility.CAMPO_ID, contentValues);
        Toast.makeText(getApplicationContext(),"ATENCION, id Registrado..."+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }
    private void registrarUsuariosSql(){
        ConexionHelper conn = new ConexionHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String insert = "INSERT INTO"+Utility.TABLA_USUARIO+"("+Utility.CAMPO_ID+","+Utility.CAMPO_NOMBRE+","+Utility.CAMPO_CORREO+")"+
                " VALUES ("+txtID.getText().toString()+"','"+txtNombre.getText().toString()+"','"+txtCorreo.getText().toString()+"')";
        db.execSQL(insert);
        Toast.makeText(getApplicationContext(),"ATENCION, id Registrado..."+txtID.getText().toString(), Toast.LENGTH_SHORT).show();
        db.close();
    }
}