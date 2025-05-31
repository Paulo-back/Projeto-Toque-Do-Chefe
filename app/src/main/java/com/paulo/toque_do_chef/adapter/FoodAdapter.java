package com.paulo.toque_do_chef.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paulo.toque_do_chef.Receita;
import com.paulo.toque_do_chef.databinding.FoodItemBinding;
import com.paulo.toque_do_chef.model.Food;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private final ArrayList<Food> foodList;
    private final Context context;

    public FoodAdapter(ArrayList<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodItemBinding listItem = FoodItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FoodViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position); // Recupera o item da posição atual

        // Exibe os dados nos componentes da ViewHolder
        holder.binding.imgFood.setBackgroundResource(food.getImgFood());
        holder.binding.txtFoodName.setText(food.getFoodName());
        holder.binding.txtFoodDescription.setText(food.getFoodDescription());

        // Configura clique no item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Receita.class);
            intent.putExtra("imageResId", food.getImgFood());
            intent.putExtra("title", food.getFoodName());
            intent.putExtra("description", food.getFoodDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        FoodItemBinding binding;

        public FoodViewHolder(FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
