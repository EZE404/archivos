package com.albornoz.archivos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvMostrar;
    private MainActivity2ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvMostrar = findViewById(R.id.tvMostrar);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivity2ViewModel.class);

        viewModel.getMostrar().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvMostrar.setText(s);
            }
        });

        viewModel.leer();

    }
}