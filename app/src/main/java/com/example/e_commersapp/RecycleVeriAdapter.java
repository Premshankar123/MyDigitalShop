package com.example.e_commersapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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

public class RecycleVeriAdapter extends RecyclerView.Adapter<RecycleVeriAdapter.MyViewHolder> {
List<Detail_Veri_Pct> detail;
Context context;
static String pct_name;
static String pct_img;
static String pct_price;
static String about_pct;
static String pct_off;

    public RecycleVeriAdapter(List<Detail_Veri_Pct> detail, Context context) {
        this.detail = detail;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.veri_pct_str,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(detail.get(position).getName());
        holder.price.setText(detail.get(position).getPrice());
        holder.pct_off.setText(detail.get(position).getPct_off());
        holder.about_pct.setText(detail.get(position).getAbout_pct());
        Picasso.with(context)
                .load(detail.get(position).getImageurl())
                .into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
               final  Detail_Veri_Pct data= detail.get(position);
               pct_name=data.getName();
               pct_img=data.getImageurl();
               pct_price=data.getPrice();
               about_pct=data.getAbout_pct();
               pct_off=data.getPct_off();


                Intent intent=new Intent(context,Product_Buy.class);
                context.startActivity(intent);




            }
        });


    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,price,about_pct,pct_off;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.veri_product_img);
            name=(TextView)itemView.findViewById(R.id.veri_product_name);
            price=(TextView)itemView.findViewById(R.id.veri_product_price);
           about_pct=(TextView)itemView.findViewById(R.id.veri_product_about);
           pct_off=(TextView)itemView.findViewById(R.id.veri_product_off);
        }
    }
}
