package com.example.e_commersapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PaymentMode extends AppCompatActivity {
    TextView client,address,mobile;
    RecycleVeriAdapter adpter;
    DeliveryAddress deliveryAddress;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Product_Buy pct;
    Button pay;
    private static final String JSON_URL = "https://unreposeful-rheosta.000webhostapp.com/ByeProduct.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode);
        client=(TextView)findViewById(R.id.client_name);
        address=(TextView)findViewById(R.id.address);
        mobile=(TextView)findViewById(R.id.mobile_no);
        pay=(Button)findViewById(R.id.pay_btn);
        radioGroup=(RadioGroup) findViewById(R.id.radio_group);
        client.setText(deliveryAddress.client_name);
        address.setText(deliveryAddress.pre_no+", "+deliveryAddress.colony1+", "+deliveryAddress.city_name1+", "+deliveryAddress.pincode1+", "+deliveryAddress.district1+", "+deliveryAddress.state1+", "+deliveryAddress.country1);
        mobile.setText(deliveryAddress.mobile1+", "+deliveryAddress.altr_mobile1);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton=(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                String text=radioButton.getText().toString();

                 ByeProduct();


            }
        });

    }

    private void ByeProduct() {
        String name =pct.bye_name.getText().toString().trim();
        String pctoff =pct.pct_off.getText().toString().trim();
        String price = pct.pctprice.getText().toString().trim();
        String aboutpct =pct.about_pct.getText().toString().trim();
        String img =adpter.pct_img;
        String addr= deliveryAddress.client_name+","+deliveryAddress.pre_no+", "+deliveryAddress.colony1+", "+deliveryAddress.city_name1+","+deliveryAddress.district1+", "+deliveryAddress.state1+","+deliveryAddress.pincode1+",  "+deliveryAddress.country1+", "+deliveryAddress.mobile1+", "+deliveryAddress.altr_mobile1;


        StringRequest stringRequest=new StringRequest(Request.Method.POST, JSON_URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("Product Bye successfully")){
                    Toast.makeText(getApplicationContext(),"Product Bye successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
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
                params.put("size",pct.size);
                params.put("qty",pct.qty);
                params.put("addr",addr);

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


}



