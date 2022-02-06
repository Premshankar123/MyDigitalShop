package com.example.e_commersapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Navigation_Header extends AppCompatActivity {
    String JSON_URL="https://unreposeful-rheosta.000webhostapp.com/NavDetailsApi.php";
    List<NavHeaderDetail> detail;
    Context context;
    Login login;
    TextView user,email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        user=(TextView) findViewById(R.id.nav_user);
        email=(TextView) findViewById(R.id.nav_email);
        user.setText("Prem shankar");
        detail = new ArrayList<>();
        generateApi();

    }

    private void generateApi() {
        String JSON_URL_Api="https://unreposeful-rheosta.000webhostapp.com/NavDetailsApi.php";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, JSON_URL_Api,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                params.put("username",login.user_name);
                params.put("password",login.user_pass);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(Navigation_Header.this);
        requestQueue.add(stringRequest);

    }


     void getDetailJson() {
         user.setText("shankar");
         email.setText("abc@gmail.com");
     }


}