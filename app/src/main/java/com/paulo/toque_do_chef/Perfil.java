package com.paulo.toque_do_chef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Perfil extends AppCompatActivity {

    private CadastroDao cadastroDao;
    private EditText editNome, editSenha, editEmail, editTelefone;
    private ImageButton btnAtualizar, btnExcluir;

    private int cadastroId; // id do cadastro que está editando

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        cadastroDao = AppDatabase.getInstance(this).registroDao();

        editNome = findViewById(R.id.editTextText3);
        editSenha = findViewById(R.id.editTextTextPassword2);
        editEmail = findViewById(R.id.editTextTextEmailAddress4);
        editTelefone = findViewById(R.id.editTextPhone3);

        btnAtualizar = findViewById(R.id.buttonEditar);
        btnExcluir = findViewById(R.id.buttonExcluir);
        ImageButton btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(v -> deslogar());

        // Pega o id via Intent
        cadastroId = getIntent().getIntExtra("cadastroId", -1);

        if (cadastroId != -1) {
            carregarDados(cadastroId);
        } else {
            Toast.makeText(this, "Erro ao carregar dados do usuário.", Toast.LENGTH_SHORT).show();
            deslogar(); // Caso id inválido, já desloga por segurança
        }

        btnAtualizar.setOnClickListener(v -> atualizarCadastro());
        btnExcluir.setOnClickListener(v -> excluirCadastro());
    }

    private void carregarDados(int id) {
        new Thread(() -> {
            Cadastro cadastro = cadastroDao.buscarPorId(id);
            runOnUiThread(() -> {
                if (cadastro != null) {
                    editNome.setText(cadastro.getNome());
                    editSenha.setText(cadastro.getSenha());
                    editEmail.setText(cadastro.getEmail());
                    editTelefone.setText(cadastro.getTelefone());
                } else {
                    Toast.makeText(Perfil.this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
                    deslogar();
                }
            });
        }).start();
    }

    private void atualizarCadastro() {
        String nome = editNome.getText().toString();
        String senha = editSenha.getText().toString();
        String email = editEmail.getText().toString();
        String telefone = editTelefone.getText().toString();

        Cadastro cadastroAtualizado = new Cadastro();
        cadastroAtualizado.setId(cadastroId);
        cadastroAtualizado.setNome(nome);
        cadastroAtualizado.setSenha(senha);
        cadastroAtualizado.setEmail(email);
        cadastroAtualizado.setTelefone(telefone);

        new Thread(() -> {
            cadastroDao.atualizar(cadastroAtualizado);
            runOnUiThread(() -> Toast.makeText(Perfil.this, "Cadastro atualizado!", Toast.LENGTH_SHORT).show());
        }).start();
    }

    private void excluirCadastro() {
        new Thread(() -> {
            Cadastro cadastro = cadastroDao.buscarPorId(cadastroId);
            if (cadastro != null) {
                cadastroDao.excluir(cadastro);
                runOnUiThread(() -> {
                    Toast.makeText(Perfil.this, "Cadastro excluído!", Toast.LENGTH_SHORT).show();
                    deslogar(); // Desloga e redireciona após exclusão
                });
            } else {
                runOnUiThread(() -> Toast.makeText(Perfil.this, "Usuário não encontrado para exclusão!", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    // Método para limpar SharedPreferences e redirecionar ao Login
    private void deslogar() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        prefs.edit().clear().apply();

        Intent intent = new Intent(Perfil.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(Perfil.this, "Deslogado!", Toast.LENGTH_SHORT).show();
        finish();


    }

}
