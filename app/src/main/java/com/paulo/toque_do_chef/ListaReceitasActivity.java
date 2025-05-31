package com.paulo.toque_do_chef;

import android.content.Intent;
import android.content.SharedPreferences;
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
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int cadastroId = prefs.getInt("cadastroId", -1);


        ImageButton goTohomeMenu2 = findViewById(R.id.homeMenu2);
        goTohomeMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(ListaReceitasActivity.this, Home.class);
            startActivity(intent);
        });


        //Botão de Categorias está indo para Lista de Receitas
        ImageButton goToListaReceitas = findViewById(R.id.categoriaMenu2);
        goToListaReceitas.setOnClickListener(v -> {
            Intent intent = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
            startActivity(intent);
        }); //implementando

        ImageButton goToPerfil3 = findViewById(R.id.perfilMenu2);
        goToPerfil3.setOnClickListener(v -> {
            Intent intent = new Intent(ListaReceitasActivity.this, Perfil.class);
            intent.putExtra("cadastroId", cadastroId);
            startActivity(intent);
        });



    }



    private void getFood(){
        foodList.clear();
        Food food1 = new Food(
                R.drawable.arroz,
                "Arroz Branco",
                "Confira como fazer arroz branco soltinho de forma simples e tradicional. Essa receita incrível é perfeita para acompanha um feijãozinho bem temperado! Um arroz soltinho para ninguém botar defeito!",
                "Ingredientes:\n" +
                        "1 xícara de arroz branco\n" +
                        "2 xícaras de água fervente\n" +
                        "1 colher de sopa de óleo (ou azeite)\n" +
                        "1 dente de alho picado (opcional)\n" +
                        "Sal a gosto","Lave e escorra o arroz.\n" +
                "Em uma panela, refogue o alho (opcional) no óleo.\n" +
                "Adicione o arroz e refogue por 2 minutos.\n" +
                "Acrescente a água fervente e o sal. Mexa uma vez.\n" +
                "Tampe, cozinhe em fogo baixo por 15-20 minutos (até secar). Não destampe!\n" +
                "Desligue e deixe descansar 5-10 minutos com a panela tampada.\n" +
                "Solte com um garfo e sirva." ,5f);
        foodList.add(food1);

        Food food2 = new Food(
                R.drawable.bolo,
                "Torta de Banana com Creme",
                "Prepare-se para saborear uma Torta de Banana com Creme que é pura indulgência! Essa receita rápida e deliciosa é perfeita para qualquer hora do dia." +
                        " Com uma massa macia e um recheio cremoso de banana, é a opção ideal para um lanche da tarde ou uma sobremesa especial. Simplesmente irresistível!",
                "Ingredientes:\n"+"Massa: Farinha de trigo, açúcar, manteiga (ou margarina), ovos.\n" +
                        "Recheio: Bananas maduras, creme de leite (ou leite condensado/creme confeiteiro), açúcar, canela (opcional).","Prepare a massa: Misture os ingredientes da massa até formar uma bola. Forre uma forma.\n" +
                "Corte as bananas: Fatie as bananas e distribua sobre a massa.\n" +
                "Faça o creme: Misture os ingredientes do recheio cremoso e cubra as bananas.\n" +
                "Asse: Leve ao forno até dourar e o creme firmar.\n" +
                "Sirva: Deixe esfriar um pouco antes de servir.",4.3f
        );
        foodList.add(food2);

        Food food3 = new Food(
                R.drawable.frangoassado,
                "Frango Assado na Mostarda e Mel",
                "Transforme seu almoço ou jantar com este Frango Assado na Mostarda e Mel!" +
                        " Uma receita de carnes e aves que une o agridoce da mostarda e do mel em um frango suculento e cheio de sabor." +
                        " Crocante por fora e macio por dentro, é a pedida certa para impressionar sem complicação.",
                "Ingredientes:\n" +
                        "Frango: Cortes de frango (coxas, sobrecoxas, asinhas ou um frango inteiro).\n" +
                        "Molho: Mostarda (Dijon ou amarela), mel, azeite, alho picado, sal, pimenta-do-reino e temperos a gosto (páprica, alecrim).",
                "Prepare o molho: Misture todos os ingredientes do molho em uma tigela.\n" +
                        "Tempere o frango: Besunte bem o frango com o molho, garantindo que todos os pedaços estejam cobertos. Se tiver tempo, deixe marinar por pelo menos 30 minutos (ou na geladeira por algumas horas).\n" +
                        "Asse: Disponha o frango em uma assadeira e leve ao forno pré-aquecido a 180-200°C.\n" +
                        "Acompanhe o cozimento: Asse por aproximadamente 45-60 minutos, virando o frango na metade do tempo e regando com o molho que se forma na assadeira, até ficar dourado e cozido por dentro.\n" +
                        "Sirva: Retire do forno e sirva imediatamente, acompanhado do molho.",5f
        );
        foodList.add(food3);

        Food food4 = new Food(
                R.drawable.sopadeoutono,
                "Sopa de Legumes Fácil",
                "Aqueça a alma com esta Sopa de Legumes Fácil! Perfeita para os dias mais frios ou para uma refeição leve e nutritiva." +
                        " Repleta de vegetais frescos e um caldo saboroso," +
                        " essa sopa é descomplicada de fazer e um abraço em forma de comida. Uma opção prática e reconfortante para toda a família.",
                "Ingredientes:\n" +
                        "\n" +
                        "Legumes Variados: Cenoura, batata, abobrinha, chuchu, vagem, cebola, alho (ou seus favoritos).\n" +
                        "Caldo: Água ou caldo de legumes.\n" +
                        "Tempero: Sal, pimenta-do-reino, cheiro-verde e temperos a gosto.\n" +
                        "Azeite: Para refogar.",
                "Prepare os legumes: Pique todos os legumes em cubos pequenos.\n" +
                "Refogue: Em uma panela grande, aqueça o azeite e refogue a cebola e o alho.\n" +
                "Adicione os legumes: Acrescente os legumes picados e refogue por alguns minutos.\n" +
                "Cozinhe: Cubra com a água ou caldo, tempere com sal e pimenta. Cozinhe até os legumes ficarem macios.\n" +
                "Finalize: Adicione o cheiro-verde e ajuste os temperos. Sirva quente!",3.5f
        );
        foodList.add(food4);

        Food food5 = new Food(
                R.drawable.smoothiesdecenoura,
                "Suco Natural de Laranja com Cenoura e Gengibre",
                "Refresque-se e energize-se com este Suco Natural de Laranja com Cenoura e Gengibre! Uma combinação vibrante e nutritiva," +
                        " essa bebida é perfeita para começar o dia ou para um boost de vitalidade a qualquer momento." +
                        " Além de delicioso, é cheio de vitaminas e um toque picante que vai te surpreender.",
                "Ingredientes:\n" +
                        "\n" +
                        "Laranjas: Laranjas frescas (quantidade a gosto, para o suco).\n" +
                        "Cenoura: Cenoura(s) picada(s) ou ralada(s).\n" +
                        "Gengibre: Um pedaço pequeno de gengibre fresco (a gosto, sem casca).\n" +
                        "Opcional: Água, gelo.",
                "Esprema o suco das laranjas.\n" +
                        "No liquidificador, coloque o suco de laranja, a cenoura picada e o gengibre.\n" +
                        "Bata bem até a mistura ficar homogênea e sem pedaços.\n" +
                        "Se quiser, coe para um suco mais fino (opcional).\n" +
                        "Sirva com gelo e desfrute!",4.6f
        );
        foodList.add(food5);

        var food6 = new Food(R.drawable.unsplash_ry5pwzgazj8,"Macarrão Indiano",
                "um macarrão extremamente delicioso!!!." +
                " Feito na rua de bangcok nas mais higienicas ruas indianas",
                "Ingredientes:\n" +"\n"+
                "250 ml de leite\n" +
                "2 ovos\n" +
                "sal a gosto\n" +
                "queijo ralado a gosto\n" +
                "3/4 de xícara (chá) de óleo",
                "Cozinhe e escorra o macarrão.\n" +
                "Na panela, refogue alho e cebola no óleo.\n" +
                "Adicione os vegetais e refogue brevemente.\n" +
                "Misture o shoyu, açúcar e o macarrão cozido.\n" +
                "Sirva quente!",2.5f);
        foodList.add(food6);

        foodAdapter.notifyDataSetChanged();
    }
    public static ArrayList<Food> getFoodList() {
        ArrayList<Food> foodList = new ArrayList<>();

        // Recria os mesmos dados que estão no método getFood()
        foodList.add(new Food(R.drawable.arroz, "Arroz Branco",
                "Confira como fazer arroz branco soltinho de forma simples e tradicional." +
                        " Essa receita incrível é perfeita para acompanha um feijãozinho bem temperado!" +
                        "Um arroz soltinho para ninguém botar defeito!",
                "1 xícara de arroz branco\n" +
                        "2 xícaras de água fervente\n" +
                        "1 colher de sopa de óleo (ou azeite)\n" +
                        "1 dente de alho picado (opcional)\n" +
                        "Sal a gosto",
                "Lave e escorra o arroz.\n" +
                        "Em uma panela, refogue o alho (opcional) no óleo.\n" +
                        "Adicione o arroz e refogue por 2 minutos.\n" +
                        "Acrescente a água fervente e o sal. Mexa uma vez.\n" +
                        "Tampe, cozinhe em fogo baixo por 15-20 minutos (até secar). Não destampe!\n" +
                        "Desligue e deixe descansar 5-10 minutos com a panela tampada.\n" +
                        "Solte com um garfo e sirva.",
                5f));

        foodList.add(new Food(R.drawable.unsplash_ry5pwzgazj8,
                "Macarrão Indiano",
                "um macarrão extremamente delicioso!!!." +
                        " Feito na rua de bangcok nas mais higienicas ruas indianas\n" +
                        "\n",
                "Ingredientes: Macarrão, óleo, alho, cebola, cenoura, repolho," +
                        " shoyu, açúcar (opcional).",
                "Cozinhe e escorra o macarrão.\n" +
                        "Na panela, refogue alho e cebola no óleo.\n" +
                        "Adicione os vegetais e refogue brevemente.\n" +
                        "Misture o shoyu, açúcar e o macarrão cozido.\n" +
                        "Sirva quente!", 2.5f));

        foodList.add(new Food(R.drawable.bolo,"Torta de Banana com Creme",
                "Prepare-se para saborear uma Torta de Banana com Creme que é pura indulgência!" +
                        " Essa receita rápida e deliciosa é perfeita para qualquer hora do dia.+\n" +
                "\n" +" Com uma massa macia e um recheio cremoso de banana," +
                        " é a opção ideal para um lanche da tarde ou uma sobremesa especial." +
                        " Simplesmente irresistível!",
                "Massa: Farinha de trigo, açúcar, manteiga (ou margarina), ovos.\n" +
                "Recheio: Bananas maduras, creme de leite (ou leite condensado/creme confeiteiro), açúcar," +
                        " canela (opcional).",
                "Prepare a massa: Misture os ingredientes da massa até formar uma bola. Forre uma forma.\n" +
                        "Corte as bananas: Fatie as bananas e distribua sobre a massa.\n" +
                        "Faça o creme: Misture os ingredientes do recheio cremoso e cubra as bananas.\n" +
                        "Asse: Leve ao forno até dourar e o creme firmar.\n" +
                        "Sirva: Deixe esfriar um pouco antes de servir.",4.3f));

        foodList.add(new Food(R.drawable.smoothiesdecenoura,"Suco de Laranja, Cenoura e Gengibre",
                "Refresque-se e energize-se com este Suco Natural de Laranja com Cenoura e Gengibre!" +
                        " Uma combinação vibrante e nutritiva," +" essa bebida é perfeita para começar o dia" +
                        " ou para um boost de vitalidade a qualquer momento." +
                        " Além de delicioso, é cheio de vitaminas e um toque picante que vai te surpreender.",
                "Ingredientes:"+"\n"+"Laranjas: Laranjas frescas (quantidade a gosto, para o suco).\n" +
                        "Cenoura: Cenoura(s) picada(s) ou ralada(s).\n" +
                        "Gengibre: Um pedaço pequeno de gengibre fresco (a gosto, sem casca).\n" +
                        "Opcional: Água, gelo.",
                "Esprema o suco das laranjas.\n" +
                        "No liquidificador, coloque o suco de laranja, a cenoura picada e o gengibre.\n" +
                        "Bata bem até a mistura ficar homogênea e sem pedaços.\n" +
                        "Se quiser, coe para um suco mais fino (opcional).\n" +
                        "Sirva com gelo e desfrute!",
                4.6f));

        foodList.add(new Food(R.drawable.frangoassado,"Frango Assado na Mostarda e Mel",
                "Transforme seu almoço ou jantar com este Frango Assado na Mostarda e Mel!" +
                        " Uma receita de carnes e aves que une o agridoce da mostarda e do mel em um frango suculento e cheio de sabor." +
                        " Crocante por fora e macio por dentro, é a pedida certa para impressionar sem complicação.",
                "Frango: Cortes de frango (coxas, sobrecoxas, asinhas ou um frango inteiro).\n" +
                        "Molho: Mostarda (Dijon ou amarela), mel, azeite, alho picado, sal, pimenta-do-reino e" +
                        " temperos a gosto (páprica, alecrim).",
                "Misture a mostarda, mel, alho, limão, sal e pimenta.\n" +
                        "Passe essa mistura no frango e deixe marinar por 30 minutos (se puder).\n" +
                        "Coloque o frango em uma assadeira, regue com um fio de azeite.\n" +
                        "Asse em forno preaquecido a 200 °C por cerca de 45 minutos, ou até dourar.\n",
                5f));

        foodList.add(new Food(R.drawable.sopadeoutono,"Sopa de Legumes Fácil",
                "Aqueça a alma com esta Sopa de Legumes Fácil! Perfeita para os dias mais frios" +
                        " ou para uma refeição leve e nutritiva." +
                        " Repleta de vegetais frescos e um caldo saboroso," +
                        " essa sopa é descomplicada de fazer e um abraço em forma de comida." +
                        " Uma opção prática e reconfortante para toda a família.",
                "Ingredientes:\n" +
                        "\n" +
                        "Legumes Variados: Cenoura, batata, abobrinha, chuchu, vagem, cebola, alho" +
                        " (ou seus favoritos).\n" +
                        "Caldo: Água ou caldo de legumes.\n" +
                        "Tempero: Sal, pimenta-do-reino, cheiro-verde e temperos a gosto.\n" +
                        "Azeite: Para refogar.",
                "Refogue a cebola e o alho no óleo até dourar.\n"+
                        "Adicione os legumes e o tomate. Refogue por 2 minutos."+
                        "Acrescente a água, tempere com sal e pimenta.\n"+
                        "Cozinhe por cerca de 20–25 minutos ou até os legumes ficarem macios.\n"+
                        "Finalize com cheiro-verde picado.\n",
                3.5f));

        return foodList;
    }




}