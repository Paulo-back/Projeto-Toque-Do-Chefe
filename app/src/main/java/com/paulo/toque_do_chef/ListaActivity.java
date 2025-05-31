package com.paulo.toque_do_chef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CadastroAdapter adapter;
    private List<Cadastro> lista;
    private CadastroDao dao;
    private Button btnVoltarCadastro;
    private Button btnVoltarCadastro2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        btnVoltarCadastro = findViewById(R.id.btnVoltarCadastro);
        btnVoltarCadastro2 = findViewById(R.id.btnVoltarCadastro2);

        // Configurar botão de novo cadastro
        btnVoltarCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(ListaActivity.this, DataBaseActivity.class);
            startActivity(intent);
        });

        // Configurar botão de logout
        btnVoltarCadastro2.setOnClickListener(v -> deslogar());

        // Configurar RecyclerView e Adapter
        AppDatabase db = AppDatabase.getInstance(this);
        dao = db.registroDao();
        lista = dao.listarTodos();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CadastroAdapter(lista, (cadastro, position) -> {
            dao.excluir(cadastro);
            adapter.removerItem(position);
        });
        recyclerView.setAdapter(adapter);
    }

    private void deslogar() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        prefs.edit().clear().apply();

        Toast.makeText(this, "Deslogado!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ListaActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
