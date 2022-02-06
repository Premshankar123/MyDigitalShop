package com.example.e_commersapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import static android.widget.GridView.AUTO_FIT;
import static androidx.constraintlayout.helper.widget.MotionEffect.AUTO;


public class WomenSareeFragment extends Fragment {
    String JSON_URL="https://unreposeful-rheosta.000webhostapp.com/WomenSareeFragmentApi.php";
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    RecycleVeriAdapter recycleVeriAdapter;
    List<Detail_Veri_Pct> detail;
    TextView viewAll_btn;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_women_saree, container, false);
        recyclerView=(RecyclerView)root.findViewById(R.id.women_saree_recycle);
        viewAll_btn=(TextView)root.findViewById(R.id.text_btn);
        gridLayoutManager=new GridLayoutManager(getContext(),2);
        gridLayoutManager.isSmoothScrollbarEnabled();
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        viewAll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WomenSareeActivity.class);
                startActivity(intent);
            }
        });
        detail = new ArrayList<>();
        getDetailJson();
        return root;
    }


    private void getDetailJson() {
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Detail_Veri_Pct mydetail = new Detail_Veri_Pct(jsonObject.getString("name"),jsonObject.getString("price"),jsonObject.getString("imageurl"),jsonObject.getString("about_pct"),jsonObject.getString("pct_off"));
                        detail.add(mydetail);
                        recycleVeriAdapter = new RecycleVeriAdapter(detail, getContext());
                        recyclerView.setAdapter(recycleVeriAdapter);

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
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

}