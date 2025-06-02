package com.paulo.toque_do_chef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.paulo.toque_do_chef.model.Food;
import com.paulo.toque_do_chef.ListaReceitasActivity;

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

        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int cadastroId = prefs.getInt("cadastroId", -1);

        // Componentes de UI
        ImageView imageView = findViewById(R.id.imageView5);
        TextView textTitle = findViewById(R.id.textView5);
        TextView textDesc = findViewById(R.id.textView14);
        TextView textPreparo = findViewById(R.id.txtModoPreparo);
        TextView textIngredients = findViewById(R.id.txtIngredientes);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        textIngredients.setMovementMethod(new ScrollingMovementMethod());
        textPreparo.setMovementMethod(new ScrollingMovementMethod());

        // Verifica se recebeu um índice ou dados diretos
        Intent intent = getIntent();
        if (intent.hasExtra("recipe_index")) {
            // ✅ RECEBENDO POR ÍNDICE
            int index = intent.getIntExtra("recipe_index", -1);
            if (index != -1) {
                Food receita = ListaReceitasActivity.getFoodList().get(index);

                imageView.setImageResource(receita.getImgFood());
                textTitle.setText(receita.getFoodName());
                textDesc.setText(receita.getFoodDescription());
                textIngredients.setText(receita.getFoodRecipe());
                textPreparo.setText(receita.getFoodPrepare());
                ratingBar.setRating(receita.getRating());
            }
        } else {
            // ✅ RECEBENDO DADOS DIRETAMENTE
            int imageResId = intent.getIntExtra("imageResId", 0);
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String modoDePreparo = intent.getStringExtra("FoodPrepare");
            String ingredients = intent.getStringExtra("foodIngredients");
            float rating = intent.getFloatExtra("Rating", 0f);

            imageView.setImageResource(imageResId);
            textTitle.setText(title);
            textDesc.setText(description);
            textIngredients.setText(ingredients);
            textPreparo.setText(modoDePreparo);
            ratingBar.setRating(rating);
        }

        // Navegação
        ImageButton goToPerfil3 = findViewById(R.id.perfilMenu);
        goToPerfil3.setOnClickListener(v -> {
            Intent perfilIntent = new Intent(Receita.this, Perfil.class);
            perfilIntent.putExtra("cadastroId", cadastroId);
            startActivity(perfilIntent);
        });

        ImageButton goTohomeMenu2 = findViewById(R.id.homeMenu);
        goTohomeMenu2.setOnClickListener(v -> startActivity(new Intent(Receita.this, Home.class)));

        ImageButton goTocategoriaMenu2 = findViewById(R.id.categoriaMenu);
        goTocategoriaMenu2.setOnClickListener(v -> startActivity(new Intent(Receita.this, ListaReceitasActivity.class)));
    }
}
