package com.example.e_commersapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Hori_fragment extends Fragment {
   String JSON_URL="https://unreposeful-rheosta.000webhostapp.com/Product.php";

    RecyclerView recyclerView;
    RecycleAdpter recycleAdapter;
    List<Detail> deatil;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_hori_fragment, container, false);
        recyclerView = root.findViewById(R.id.hori_recycle);
       LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
       layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        deatil = new ArrayList<>();

        getDetailJSON();
        return root;


    }

    private void getDetailJSON() {
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("product");
                    for(int i = 0;i<jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Detail mydetail = new Detail(jsonObject.getString("name"),jsonObject.getString("imageurl"));
                        deatil.add(mydetail);
                        recycleAdapter = new RecycleAdpter(deatil,getContext());
                        recyclerView.setAdapter(recycleAdapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
