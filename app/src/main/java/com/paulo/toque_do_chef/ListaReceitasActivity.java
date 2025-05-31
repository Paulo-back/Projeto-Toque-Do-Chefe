package com.paulo.toque_do_chef;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paulo.toque_do_chef.adapter.FoodAdapter;
import com.paulo.toque_do_chef.databinding.ActivityListaReceitasBinding;
import com.paulo.toque_do_chef.model.Food;

import java.util.ArrayList;

public class ListaReceitasActivity extends AppCompatActivity {
    private ActivityListaReceitasBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaReceitasBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        RecyclerView recyclerViewFood = binding.RecyclerViewFood;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFood.setHasFixedSize(true);
        foodAdapter = new FoodAdapter(foodList, this);
        recyclerViewFood.setAdapter(foodAdapter);
        getFood();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getFood(){
        Food food1 = new Food(
                R.drawable.arroz,
                "Receita 1",
                "Confira como fazer arroz branco soltinho de forma simples e tradicional. Essa receita incrível é perfeita para acompanha um feijãozinho bem temperado! Um arroz soltinho para ninguém botar defeito!"
        );
        foodList.add(food1);

        Food food2 = new Food(
                R.drawable.arroz,
                "Receita 2",
                "Confira como fazer arroz branco soltinho de forma simples e tradicional. Essa receita incrível é perfeita para acompanha um feijãozinho bem temperado! Um arroz soltinho para ninguém botar defeito!"
        );
        foodList.add(food2);

        Food food3 = new Food(
                R.drawable.arroz,
                "Receita 3",
                "Confira como fazer arroz branco soltinho de forma simples e tradicional. Essa receita incrível é perfeita para acompanha um feijãozinho bem temperado! Um arroz soltinho para ninguém botar defeito!"
        );
        foodList.add(food3);

        Food food4 = new Food(
                R.drawable.arroz,
                "Receita 4",
                "Confira como fazer arroz branco soltinho de forma simples e tradicional. Essa receita incrível é perfeita para acompanha um feijãozinho bem temperado! Um arroz soltinho para ninguém botar defeito!"
        );
        foodList.add(food4);

        Food food5 = new Food(
                R.drawable.arroz,
                "Receita 5",
                "Confira como fazer arroz branco soltinho de forma simples e tradicional. Essa receita incrível é perfeita para acompanha um feijãozinho bem temperado! Um arroz soltinho para ninguém botar defeito!"
        );
        foodList.add(food5);
    }

}