package com.paulo.toque_do_chef;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CadastroAdapter extends RecyclerView.Adapter<CadastroAdapter.ViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(Cadastro cadastro, int position);
    }

    private List<Cadastro> lista;
    private OnDeleteClickListener deleteClickListener;

    public CadastroAdapter(List<Cadastro> lista, OnDeleteClickListener listener) {
        this.lista = lista;
        this.deleteClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtTelefone, txtEmail;
        ImageButton btnDeletar;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtTelefone = itemView.findViewById(R.id.txtTelefone);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            btnDeletar = itemView.findViewById(R.id.btnDeletar);
        }
    }

    @Override
    public CadastroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cadastro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CadastroAdapter.ViewHolder holder, int position) {
        Cadastro c = lista.get(position);
        holder.txtNome.setText("Nome: " + c.getNome());
        holder.txtTelefone.setText("Telefone: " + c.getTelefone());
        holder.txtEmail.setText("Email: " + c.getEmail());

        holder.btnDeletar.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(c, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void removerItem(int position) {
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lista.size());
    }
}
