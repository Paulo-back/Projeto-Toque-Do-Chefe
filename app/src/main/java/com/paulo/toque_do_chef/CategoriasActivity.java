package com.paulo.toque_do_chef;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton goTohomeMenu = findViewById(R.id.homeMenu);
        goTohomeMenu.setOnClickListener(v -> {
            Intent intent = new Intent(CategoriasActivity.this, Home.class);
            startActivity(intent);
        });

        ImageButton goTocategoriaMenu = findViewById(R.id.categoriaMenu);
        goTocategoriaMenu.setOnClickListener(v -> {
            Intent intent = new Intent(CategoriasActivity.this, CategoriasActivity.class);
            startActivity(intent);
        }); //consertar


        ImageButton goToperfilMenu = findViewById(R.id.perfilMenu);
        goToperfilMenu.setOnClickListener(v -> {
            Intent intent = new Intent(CategoriasActivity.this, Editar.class);
            startActivity(intent);
        });

    }
}