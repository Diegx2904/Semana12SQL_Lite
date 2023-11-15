package com.example.semana12sql_lite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.semana12sql_lite.Controlador.ConexionHelper;
import com.example.semana12sql_lite.Controlador.Utility;
import com.example.semana12sql_lite.Modelo.Usuario;
import com.example.semana12sql_lite.R;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {

    ListView listViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;
    ConexionHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listViewUsuarios = findViewById(R.id.listView);
        conn = new ConexionHelper(getApplicationContext(), "bd_usuarios",null,1);
        consultarListaUsuarios();
    }
    private void consultarListaUsuarios(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utility.TABLA_USUARIO, null);
        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setCorreo(cursor.getString(2));
            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }
}