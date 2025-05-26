package com.paulo.toque_do_chef;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CadastroAdapter adapter;
    private List<Cadastro> lista;

    private Button btnVoltarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        AppDatabase db = AppDatabase.getInstance(this);
        CadastroDao dao = db.registroDao();
        lista = dao.listarTodos();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CadastroAdapter(lista);
        recyclerView.setAdapter(adapter);

        btnVoltarCadastro = findViewById(R.id.btnVoltarCadastro);
        btnVoltarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, DataBaseActivity.class);
                startActivity(intent);
            }
        });


    }
}
