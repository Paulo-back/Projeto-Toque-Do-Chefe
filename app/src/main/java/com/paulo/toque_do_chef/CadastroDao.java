package com.paulo.toque_do_chef;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CadastroDao {
    @Insert
    void inserir(Cadastro cadastro);

    @Update
    void atualizar(Cadastro cadastro);

    @Delete
    void excluir(Cadastro cadastro);

    @Query("SELECT * FROM Cadastro")
    List<Cadastro> listarTodos();

    @Query("SELECT * FROM Cadastro WHERE id = :id")
    Cadastro buscarPorId(int id);


    @Query("SELECT * FROM Cadastro WHERE nome = :nome LIMIT 1")
    Cadastro buscarPeloNome(String nome);

    @Query("SELECT * FROM Cadastro WHERE email = :email AND senha = :senha LIMIT 1")
    Cadastro login(String email, String senha);
}
