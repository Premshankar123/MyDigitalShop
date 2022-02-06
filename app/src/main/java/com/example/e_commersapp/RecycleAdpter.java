package com.example.e_commersapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleAdpter extends RecyclerView.Adapter<RecycleAdpter.MyViewHolder> {

    List<Detail> deatil;
    Context context;

    public RecycleAdpter(List<Detail> deatil, Context context) {
        this.deatil = deatil;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizantal_product_str,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {



        viewHolder.name.setText(deatil.get(i).getName());
        Picasso.with(context)
                .load(deatil.get(i).getimageurl())
                .into(viewHolder.hori_img);
        viewHolder.hori_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Detail data=deatil.get(i);

                Toast.makeText(context,data.getName(),Toast.LENGTH_SHORT).show();
                if(data.getName().equals("Women saree")){
                    Intent intent=new Intent(context,WomenSareeActivity.class);
                    context.startActivity(intent);
                }
                else if(data.getName().equals("Mens tshirt")){
                    Intent intent=new Intent(context,MenTshirtActivity.class);
                    context.startActivity(intent);
                }
                else if(data.getName().equals("Girls watch")){
                    Intent intent=new Intent(context,GirlsWatchActivity.class);
                    context.startActivity(intent);
                }
                else if(data.getName().equals("Boys watch")){
                    Intent intent=new Intent(context,BoysWatchActivity.class);
                    context.startActivity(intent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return deatil.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView hori_img;
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            hori_img=(ImageView)itemView.findViewById(R.id.hari_product_img);
            name=(TextView)itemView.findViewById(R.id.hari_product_name);

        }
    }
}
