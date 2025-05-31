package com.paulo.toque_do_chef;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cadastro {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String senha;
    private String telefone;

    private String email;

    private boolean isAdmin;


    public Cadastro() {
    }

    public Cadastro(int id, String nome, String telefone,String senha, String email,boolean isAdmin) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.isAdmin = isAdmin;
    }
    public Cadastro( String nome,String telefone,String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;

    }

    @Override
    public String toString() {
        return "Registro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", Senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ",email='" + email +'\n'+
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
