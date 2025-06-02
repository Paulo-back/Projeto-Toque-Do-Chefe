package com.paulo.toque_do_chef;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DataBaseActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextTelefone;
    private EditText editTextSenha;

    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNome = findViewById(R.id.editTextNome);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextSenha = findViewById(R.id.editTextSenha);
        editTextEmail = findViewById(R.id.editTextEmail);


        findViewById(R.id.btnIrParaLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataBaseActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }

    public void cadastrar(View view) {
        AppDatabase db = AppDatabase.getInstance(this);
        CadastroDao dao = db.registroDao();

        String nome = editTextNome.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String senha = editTextSenha.getText().toString();
        String email = editTextEmail.getText().toString();

        if (nome.isEmpty() || telefone.isEmpty() || senha.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Cadastro novoCadastro = new Cadastro();
        novoCadastro.setNome(nome);
        novoCadastro.setTelefone(telefone);
        novoCadastro.setSenha(senha);
        novoCadastro.setEmail(email);
        novoCadastro.setAdmin(false); // <- Sempre usuário comum!

        dao.inserir(novoCadastro);
        Toast.makeText(this, "Cadastro realizado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DataBaseActivity.this, LoginActivity.class);
        startActivity(intent);
    }


    public void limpar(View view) {
        editTextNome.setText("");
        editTextTelefone.setText("");
        editTextSenha.setText("");
        editTextEmail.setText("");
    }

    public void buscar(View view) {
        AppDatabase db = AppDatabase.getInstance(this);
        CadastroDao dao = db.registroDao();

        String nome = editTextNome.getText().toString();
        Cadastro r = dao.buscarPeloNome(nome);  // Você precisa criar esse método no DAO!

        if (r != null) {
            editTextSenha.setText(r.getSenha());
            editTextTelefone.setText(r.getTelefone());
            editTextEmail.setText(r.getEmail());

        }
    }

    public void atualizar(View view) {
        AppDatabase db = AppDatabase.getInstance(this);
        CadastroDao dao = db.registroDao();

        String nome = editTextNome.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String senha = editTextSenha.getText().toString();
        String email = editTextEmail.getText().toString();

        dao.atualizar(new Cadastro(nome,telefone, senha, email));  // Nome como chave primária ou identificador único!
    }

    public void excluir(View view) {
        AppDatabase db = AppDatabase.getInstance(this);
        CadastroDao dao = db.registroDao();

        String nome = editTextNome.getText().toString();
        String senha = editTextSenha.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String email = editTextEmail.getText().toString();

        dao.excluir(new Cadastro(nome,telefone, senha, email));
    }

    public void listar(View view) {
        Intent intent = new Intent(this, ListaActivity.class);
        startActivity(intent);
    }




}
