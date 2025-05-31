package com.paulo.toque_do_chef;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CadastroAdapter extends RecyclerView.Adapter<CadastroAdapter.ViewHolder> {

    private List<Cadastro> lista;

    public CadastroAdapter(List<Cadastro> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtTelefone, txtEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtTelefone = itemView.findViewById(R.id.txtTelefone);
            txtEmail = itemView.findViewById(R.id.txtEmail);
        }
    }

    @Override
    public CadastroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_cadastro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CadastroAdapter.ViewHolder holder, int position) {
        Cadastro c = lista.get(position);
        holder.txtNome.setText("Nome: " + c.getNome());
        holder.txtTelefone.setText("Telefone: " + c.getTelefone());
        holder.txtEmail.setText("Email: " + c.getEmail());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
