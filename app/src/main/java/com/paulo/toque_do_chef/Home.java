package com.paulo.toque_do_chef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int cadastroId = prefs.getInt("cadastroId", -1);


        // Ação do botão goToRecipeButton
        ImageButton goToRecipeButton = findViewById(R.id.goToRecipeButton);
        goToRecipeButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Receita.class);
            startActivity(intent);
        });

        ImageButton goTohomeMenu2 = findViewById(R.id.homeMenu2);
        goTohomeMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Home.class);
            startActivity(intent);
        });

        ImageButton goToRecipeButton2 = findViewById(R.id.goToRecipeButton2);
        goToRecipeButton2.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Receita.class);
            startActivity(intent);
        });

        //Botão de Categorias está indo para Lista de Receitas
        ImageButton goToListaReceitas = findViewById(R.id.categoriaMenu2);
        goToListaReceitas.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, ListaReceitasActivity.class);
            startActivity(intent);
        }); //implementando

        ImageButton goToperfilMenu2 = findViewById(R.id.perfilMenu2);
        goToperfilMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Editar.class);
            intent.putExtra("cadastroId",cadastroId); // Envia o ID do cadastro
            startActivity(intent);
        });




    }
}
