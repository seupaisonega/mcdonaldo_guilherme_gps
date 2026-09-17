package com.example.mcdonaldo_guilherme;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnPedidos = findViewById(R.id.button);
        btnPedidos.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,
                    pedidos.class);

            startActivity(intent);
        });
        Button btnOfertas = findViewById(R.id.button2);
        btnOfertas.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ofertas.class);
            startActivity(intent);
        });
        Button btnGps = findViewById(R.id.button5);
        btnGps.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, gps.class);
            startActivity(intent);
        });
        }
    }
