package com.paulo.toque_do_chef;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Receita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receita);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recebendo os dados da receita via Intent
        int imageResId = getIntent().getIntExtra("imageResId", 0);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

// Depois, use esses valores para exibir na tela com TextView, ImageView, etc.


        // Preenchendo os elementos da tela
        ImageView imageView = findViewById(R.id.imageView5);
        TextView textTitle = findViewById(R.id.textView5);
        TextView textDesc = findViewById(R.id.textView14);

        imageView.setImageResource(imageResId);
        textTitle.setText(title);
        textDesc.setText(description);

        // Botões de navegação
        ImageButton goToPerfil3 = findViewById(R.id.perfilMenu3);
        goToPerfil3.setOnClickListener(v -> startActivity(new Intent(Receita.this, Editar.class)));

        ImageButton goTohomeMenu2 = findViewById(R.id.homeMenu3);
        goTohomeMenu2.setOnClickListener(v -> startActivity(new Intent(Receita.this, Home.class)));

        ImageButton goTocategoriaMenu2 = findViewById(R.id.categoriaMenu3);
        goTocategoriaMenu2.setOnClickListener(v -> startActivity(new Intent(Receita.this, CategoriasActivity.class)));
    }

}