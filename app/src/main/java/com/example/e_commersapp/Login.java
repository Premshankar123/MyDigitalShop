package com.example.e_commersapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Login extends AppCompatActivity {
    private  static  final String JSON_URL="https://unreposeful-rheosta.000webhostapp.com/ecommers_login.php";
    EditText user,pass;
    static String user_name,user_pass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText)findViewById(R.id.login_user);
        pass=(EditText)findViewById(R.id.login_pass);
        btn=(Button)findViewById(R.id.login_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginData();
            }
        });
    }
    private  void loginData(){
       final String username=user.getText().toString().trim();
       final String password=pass.getText().toString().trim();
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter username",Toast.LENGTH_SHORT).show();
            return ;
        }
        else if (password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            StringRequest stringRequest=new StringRequest(Request.Method.POST, JSON_URL,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if(response.equals("logged in successfully")) {
                          user_name=user.getText().toString().trim();
                          user_pass=pass.getText().toString().trim();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        user.setText("");
                        pass.setText("");
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);

                    }

                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("username",username);
                    params.put("password",password);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(Login.this);
            requestQueue.add(stringRequest);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
