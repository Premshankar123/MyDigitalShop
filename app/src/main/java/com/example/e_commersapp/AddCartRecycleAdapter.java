package com.example.e_commersapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_commersapp.ui.slideshow.SlideshowFragment;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddCartRecycleAdapter extends RecyclerView.Adapter<AddCartRecycleAdapter.MyViewHolder> {
    public static String pct_id;
    List<CardDetail> details;
    Context context;
    SlideshowFragment fragment;
   public static  String ss;
   public static int s=0;

    String JSON_URL = "https://unreposeful-rheosta.000webhostapp.com/RemoveSqlCode.php";


    public AddCartRecycleAdapter(List<CardDetail> details, Context context) {
        this.details = details;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_card_str,parent,false);
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

        String str=details.get(position).getPctprice();
         ss=str.replace("₹","");
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CardDetail data=details.get(position);
                pct_id=data.getId();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, JSON_URL,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equalsIgnoreCase("Record deleted successfully")){
                            Toast.makeText(context,"Record deleted successfully",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        params.put("id",pct_id);
                        return params;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);

            }

        });
    }

    @Override
    public int getItemCount() {

        return details.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,about,price,off,Id,size,qty;
        ImageView img;
        Button btn;
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
            btn=(Button)itemView.findViewById(R.id.card_remove_btn);

        }
    }
}
