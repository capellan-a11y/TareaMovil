package com.example.nutricerdos.matenimientos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nutricerdos.R;

import java.util.ArrayList;
import java.util.List;

import Services.Conex;
import models.Alimentacion;
import models.Alimento;
import models.Callback;
import models.GlobalData;
import models.TipoAlimento;
import models.Unidad;

public class AlimentosMant extends AppCompatActivity {
    private List<Alimento> alimentos;
    private List<TipoAlimento> tiposAlimentos;
    private List<Unidad> unidades;

    private Spinner alimentosSp;
    private Spinner tipoSp;
    private Spinner unidadesSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alimentos_mant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.alimentosSp = findViewById(R.id.alimentos);
        this.tipoSp = findViewById(R.id.tipo);
        this.unidadesSp = findViewById(R.id.unidad);

        onInit();
    }

    private void onInit(){
        getAlimentos(this);
        getTipos(this);
        getUnidades(this);
    }

    private void getAlimentos(AlimentosMant context){
        Conex<Alimento> conn = new Conex<>(GlobalData.path + "/alimento", Alimento.class);

        conn.getAll(new Callback<List<Alimento>>() {
            @Override
            public void onSuccess(List<Alimento> result) {
                context.alimentos = result;
                ArrayList<String> aliStr = new ArrayList<>();
                aliStr.add("Nuevo");
                for(Alimento a: result){
                    aliStr.add(a.getId() + ". " + a.getNombre());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_spinner_item,
                        aliStr
                );

                adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

                runOnUiThread(() -> {
                    context.alimentosSp.setAdapter(adapter);
                });
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(context, "ERROR CARGANDO ALIMENTOS", Toast.LENGTH_SHORT).show();
                });
                Log.e("ERROR ALIMENTOS", e.getMessage());
            }
        });
    }

    private void getTipos(AlimentosMant context){
        Conex<TipoAlimento> conn = new Conex<>(GlobalData.path + "/tipo-alimento", TipoAlimento.class);

        conn.getAll(new Callback<List<TipoAlimento>>() {
            @Override
            public void onSuccess(List<TipoAlimento> result) {
                context.tiposAlimentos = result;

                ArrayList<String> aliStr = new ArrayList<>();
                for(TipoAlimento a: result){
                    aliStr.add(a.getId() + ". " + a.getDescr());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_spinner_item,
                        aliStr
                );

                adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

                runOnUiThread(() -> {
                    context.tipoSp.setAdapter(adapter);
                });
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(context, "ERROR AL CARGAR TIPOS ALIMENTOS", Toast.LENGTH_SHORT).show();
                });
                Log.e("ERROR TIPO ALIMENTOS", e.getMessage());
            }
        });
    }

    private void getUnidades(AlimentosMant context){
        Conex<Unidad> conex = new Conex<>(GlobalData.path +"/unidades", Unidad.class);

        conex.getAll(new Callback<List<Unidad>>() {
            @Override
            public void onSuccess(List<Unidad> result) {
                context.unidades = result;

                ArrayList<String> aliStr = new ArrayList<>();
                for(Unidad a: result){
                    aliStr.add(a.getId() + ". " + a.getNombre());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_spinner_item,
                        aliStr
                );

                adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

                runOnUiThread(() -> {
                    context.unidadesSp.setAdapter(adapter);
                });
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(context, "ERROR CON UNIDADES", Toast.LENGTH_SHORT);
                });
                Log.e("ERROR UNIDADES", e.getMessage());
            }
        });
    }
}