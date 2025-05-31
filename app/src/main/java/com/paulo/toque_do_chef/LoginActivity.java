package com.paulo.toque_do_chef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private ImageButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppDatabase db1 = AppDatabase.getInstance(this);
        CadastroDao dao1 = db1.registroDao();

        // Verifica se já existe o admin
        Cadastro adminExistente = dao1.login("admin@toque.com", "admin123");

        if (adminExistente == null) {
            Cadastro admin = new Cadastro();
            admin.setNome("Admin");
            admin.setEmail("admin@toque.com");
            admin.setTelefone("11999999999");
            admin.setSenha("admin123");
            admin.setAdmin(true);

            dao1.inserir(admin);
        }//admin


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referências dos campos
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        editSenha = findViewById(R.id.editTextTextEmailAddress2);
        btnLogin = findViewById(R.id.imageButton4);

        // Ajuste de margem
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        btnLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            AppDatabase db = AppDatabase.getInstance(LoginActivity.this);
            CadastroDao dao = db.registroDao();
            Cadastro usuario = dao.login(email, senha);

            if (usuario != null) {
                // Salvar o ID no SharedPreferences
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("cadastroId", usuario.getId());
                editor.apply();

                Toast.makeText(LoginActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                Intent intent;
                if (usuario.isAdmin()) {
                    intent = new Intent(LoginActivity.this, ListaActivity.class); // Tela do administrador
                } else {
                    intent = new Intent(LoginActivity.this, Home.class); // Tela padrão
                }
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "E-mail ou senha incorretos!", Toast.LENGTH_SHORT).show();
            }

        });

        ImageButton goToCadastrar = findViewById(R.id.imageButton5);
        goToCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, DataBaseActivity.class);
            startActivity(intent);
        });
    }


}
