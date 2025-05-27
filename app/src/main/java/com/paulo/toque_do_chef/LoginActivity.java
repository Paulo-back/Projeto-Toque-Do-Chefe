package com.paulo.toque_do_chef;

import android.content.Intent;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referências dos campos
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        editSenha = findViewById(R.id.editTextTextEmailAddress2);
        btnLogin = findViewById(R.id.imageButton4);

        // Ajuste de margem (mantido da estrutura EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        // Clique do botão de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Toast.makeText(LoginActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, pesquisarReceitas.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "E-mail ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
