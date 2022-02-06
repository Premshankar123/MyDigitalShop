package com.example.e_commersapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Product_Buy extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    RecycleVeriAdapter adapter;
   static TextView bye_name,pctprice,about_pct,pct_off;
   Button add_card,buy_btn;
 static  String size,qty;
    ImageView img;
    private static final String JSON_URL = "https://unreposeful-rheosta.000webhostapp.com/AddCard.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__buy);
        bye_name=(TextView)findViewById(R.id.bye_name);
        pctprice=(TextView) findViewById(R.id.product_price);
        about_pct=(TextView) findViewById(R.id.about_pct);
        pct_off=(TextView)findViewById(R.id.product_off);
        img=(ImageView)findViewById(R.id.img_bye);
        bye_name.setText(adapter.pct_name);
        pctprice.setText(adapter.pct_price);
        about_pct.setText(adapter.about_pct);
        pct_off.setText(adapter.pct_off);
        add_card=(Button)findViewById(R.id.add_card_btn) ;
        buy_btn=(Button)findViewById(R.id.bye_pct_btn);
        Picasso.with(getApplicationContext()).load(adapter.pct_img).into(img);
        Spinner spinner=findViewById(R.id.size_spinner);
        Spinner spinner1=findViewById(R.id.qty_spinner);
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(this,R.array.size, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
       spinner.setOnItemSelectedListener(this);
       spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter1=ArrayAdapter.createFromResource(this,R.array.quantity,R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCartData();
            }
        });
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),DeliveryAddress.class);
                startActivity(intent);
            }
        });

    }


    public void AddCartData() {

            String name =bye_name.getText().toString().trim();
            String pctoff =pct_off.getText().toString().trim();
            String price = pctprice.getText().toString().trim();
            String aboutpct =about_pct.getText().toString().trim();
            String img =adapter.pct_img;

        StringRequest stringRequest=new StringRequest(Request.Method.POST, JSON_URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("Product added successfully")){
                    Toast.makeText(getApplicationContext(),"Product added successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();


            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("name",name);
                params.put("price",price);
                params.put("pctoff",pctoff);
                params.put("aboutpct",aboutpct);
                params.put("img",img);
                params.put("size",size);
                params.put("qty",qty);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
       if(text.equals("S") || text.equals("M") || text.equals("L") || text.equals("XL")){
           size=text;
       }
       else{
           qty=text;

        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


