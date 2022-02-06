package com.example.e_commersapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenTshirtActivity extends AppCompatActivity {
    String JSON_URL="https://unreposeful-rheosta.000webhostapp.com/MensTshirtActivityApi.php";
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    WomenSareeRecycleAdapter adapter;
    List<WomenSareeDetails> detail;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_tshirt);
        recyclerView=(RecyclerView)findViewById(R.id.men_tshirt_recycle_view);
        gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.isSmoothScrollbarEnabled();
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        detail = new ArrayList<>();
        getDetailJson();
    }
    private void getDetailJson() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        WomenSareeDetails mydetail = new WomenSareeDetails(jsonObject.getString("name"),jsonObject.getString("price"),jsonObject.getString("imageurl"),jsonObject.getString("about_pct"),jsonObject.getString("pct_off"));
                        detail.add(mydetail);
                        adapter = new WomenSareeRecycleAdapter(detail, MenTshirtActivity.this);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


}