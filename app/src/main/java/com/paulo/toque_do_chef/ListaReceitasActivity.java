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
                "Arroz Branco",
                "Confira como fazer arroz branco soltinho de forma simples e tradicional. Essa receita incrível é perfeita para acompanha um feijãozinho bem temperado! Um arroz soltinho para ninguém botar defeito!"
        );
        foodList.add(food1);

        Food food2 = new Food(
                R.drawable.bolo,
                "Torta de Banana com Creme",
                "Prepare-se para saborear uma Torta de Banana com Creme que é pura indulgência! Essa receita rápida e deliciosa é perfeita para qualquer hora do dia." +
                        " Com uma massa macia e um recheio cremoso de banana, é a opção ideal para um lanche da tarde ou uma sobremesa especial. Simplesmente irresistível!"
        );
        foodList.add(food2);

        Food food3 = new Food(
                R.drawable.frangoassado,
                "Frango Assado na Mostarda e Mel",
                "Transforme seu almoço ou jantar com este Frango Assado na Mostarda e Mel!" +
                        " Uma receita de carnes e aves que une o agridoce da mostarda e do mel em um frango suculento e cheio de sabor." +
                        " Crocante por fora e macio por dentro, é a pedida certa para impressionar sem complicação."
        );
        foodList.add(food3);

        Food food4 = new Food(
                R.drawable.sopadeoutono,
                "Sopa de Legumes Fácil",
                "Aqueça a alma com esta Sopa de Legumes Fácil! Perfeita para os dias mais frios ou para uma refeição leve e nutritiva." +
                        " Repleta de vegetais frescos e um caldo saboroso," +
                        " essa sopa é descomplicada de fazer e um abraço em forma de comida. Uma opção prática e reconfortante para toda a família."
        );
        foodList.add(food4);

        Food food5 = new Food(
                R.drawable.smoothiesdecenoura,
                "Suco Natural de Laranja com Cenoura e Gengibre",
                "Refresque-se e energize-se com este Suco Natural de Laranja com Cenoura e Gengibre! Uma combinação vibrante e nutritiva," +
                        " essa bebida é perfeita para começar o dia ou para um boost de vitalidade a qualquer momento." +
                        " Além de delicioso, é cheio de vitaminas e um toque picante que vai te surpreender."
        );
        foodList.add(food5);
    }

}