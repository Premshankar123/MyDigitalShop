package com.example.e_commersapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HorizantalAdapter extends RecyclerView.Adapter<HorizantalAdapter.MyViewHolder> {
    List<HarizantalProductModal> product;
    Context context;

    public HorizantalAdapter(List<HarizantalProductModal> product, Context context) {
        this.product = product;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizantal_product_str,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pct_name.setText(product.get(position).getName());
        holder.pct_price.setText(product.get(position).getPrice());
        Picasso.with(context).load(product.get(position).getImageurl()).into(holder.product_img);
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView pct_name,pct_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
