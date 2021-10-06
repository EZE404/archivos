package com.albornoz.archivos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etEdad;
    private Button btGuardar, btMostrar;
    private TextView tvMensaje;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarViews();
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        viewModel.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvMensaje.setText(s);
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.guardar(etNombre.getText().toString(), etEdad.getText().toString());
            }
        });

        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    private void inicializarViews() {
        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        btGuardar = findViewById(R.id.btGuardar);
        btMostrar = findViewById(R.id.btMostrar);
        tvMensaje = findViewById(R.id.tvMensaje);
    }
}