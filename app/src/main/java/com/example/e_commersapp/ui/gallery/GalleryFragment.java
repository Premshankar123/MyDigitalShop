package com.example.e_commersapp.ui.gallery;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.example.e_commersapp.AddCartRecycleAdapter;
import com.example.e_commersapp.BookProductDetails;
import com.example.e_commersapp.CardDetail;
import com.example.e_commersapp.ProductBookRecycleAdapter;
import com.example.e_commersapp.R;
import com.example.e_commersapp.SignUp;
import com.example.e_commersapp.ui.slideshow.SlideshowViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GalleryFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    public static TextView amount;
    List<BookProductDetails> details;
    String JSON_URL = "https://unreposeful-rheosta.000webhostapp.com/BookedProductApi.php";

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = root.findViewById(R.id.card_recycle_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        details = new ArrayList<>();
        amount=(TextView) root.findViewById(R.id.total_amount);
        getDetailJSON();
        return root;
    }

    private void getDetailJSON() {
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        BookProductDetails mydetail = new BookProductDetails(jsonObject.getString("id"), jsonObject.getString("pctname"),jsonObject.getString("pctprice"),jsonObject.getString("pctoff"),jsonObject.getString("pctabout"),jsonObject.getString("size"),jsonObject.getString("qty"),jsonObject.getString("pctimg"));
                        details.add(mydetail);
                        ProductBookRecycleAdapter adapter = new ProductBookRecycleAdapter(details, getContext());
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

}

