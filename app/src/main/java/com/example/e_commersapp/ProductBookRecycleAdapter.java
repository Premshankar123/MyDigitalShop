package com.example.e_commersapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ProductBookRecycleAdapter extends RecyclerView.Adapter<ProductBookRecycleAdapter.MyViewHolder> {
    public static String pct_id;
    List<BookProductDetails> details;
    Context context;
    public ProductBookRecycleAdapter(List<BookProductDetails> details, Context context) {
        this.details = details;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_book_str,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(details.get(position).getPctname());
        holder.about.setText(details.get(position).getPctabout());
        holder.price.setText(details.get(position).getPctprice());
        holder.off.setText(details.get(position).getPctoff());
        holder.Id.setText(details.get(position).getId());
        holder.size.setText(details.get(position).getSize());
        holder.qty.setText(details.get(position).getQty());
        Picasso.with(context).load(details.get(position).getPctimg()).into(holder.img);

    }

    @Override
    public int getItemCount() {

        return details.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,about,price,off,Id,size,qty;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.cart_pctname);
            about=(TextView)itemView.findViewById(R.id.card_about_pct);
            price=(TextView)itemView.findViewById(R.id.card_price);
            off=(TextView)itemView.findViewById(R.id.card_off);
            Id=(TextView)itemView.findViewById(R.id.cart_pctId);
            size=(TextView)itemView.findViewById(R.id.pct_size);
            qty=(TextView) itemView.findViewById(R.id.pct_qty);
            img=(ImageView)itemView.findViewById(R.id.card_img);

        }
    }
}
