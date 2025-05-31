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

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Map;
import java.util.HashMap;


import com.paulo.toque_do_chef.model.Food;

import java.util.ArrayList;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        EditText searchBar = findViewById(R.id.editTextText);
        ImageButton btnBuscar = findViewById(R.id.btnBuscar);

// Mapeamento de sinônimos
        Map<String, String> sinônimos = new HashMap<>();
        sinônimos.put("rice", "arroz");
        sinônimos.put("arroz", "arroz");
        sinônimos.put("massa", "macarrão");
        sinônimos.put("pasta", "macarrão");
// Adicione mais conforme necessário

        btnBuscar.setOnClickListener(v -> {
            String searchText = searchBar.getText().toString().trim().toLowerCase();

            // Normaliza a busca para sinônimos
            if (sinônimos.containsKey(searchText)) {
                searchText = sinônimos.get(searchText);
            }

            ArrayList<Food> receitas = ListaReceitasActivity.getFoodList();
            boolean encontrou = false;

            for (int i = 0; i < receitas.size(); i++) {
                Food receita = receitas.get(i);
                if (receita.getFoodName().toLowerCase().contains(searchText)) {
                    encontrou = true;

                    Intent intent = new Intent(Home.this, Receita.class);
                    intent.putExtra("recipe_index", i);
                    startActivity(intent);
                    break;
                }
            }

            if (!encontrou && !searchText.isEmpty()) {
                Toast.makeText(Home.this, "Nenhuma receita encontrada!", Toast.LENGTH_SHORT).show();
            }
        });


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
            int index = 1; // por exemplo, primeira receita da lista
            Intent intent = new Intent(Home.this, Receita.class);
            intent.putExtra("recipe_index", index);
            startActivity(intent);
        });


        ImageButton goTohomeMenu2 = findViewById(R.id.homeMenu2);
        goTohomeMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Home.class);
            startActivity(intent);
        });

        ImageButton goToRecipeButton2 = findViewById(R.id.goToRecipeButton2);
        goToRecipeButton2.setOnClickListener(v -> {
            int index = 0; // por exemplo, terceira receita
            Intent intent = new Intent(Home.this, Receita.class);
            intent.putExtra("recipe_index", index);
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
            Intent intent = new Intent(Home.this, Perfil.class);
            intent.putExtra("cadastroId",cadastroId); // Envia o ID do cadastro
            startActivity(intent);
        });




    }
}
