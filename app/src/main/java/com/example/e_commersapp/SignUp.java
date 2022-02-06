package com.example.e_commersapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SignUp extends AppCompatActivity {
    private  static  final String JSON_URL="https://unreposeful-rheosta.000webhostapp.com/ecommers_signup.php";
EditText username,email,mobile,password;
TextView login;
Button sign_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username=(EditText)findViewById(R.id.username_sign_up);
        email=(EditText)findViewById(R.id.email_sign_up);
        mobile=(EditText)findViewById(R.id.mobile_sign_up);
        password=(EditText)findViewById(R.id.password_sign_up);
        sign_btn=(Button)findViewById(R.id.signup_btn);
        login=(TextView)findViewById(R.id.sign_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signData();
            }
            });
        }

            private void signData() {

                String susername=username.getText().toString().trim();
                String semail=email.getText().toString().trim();
                String smobile=mobile.getText().toString().trim();
                String spassword=password.getText().toString().trim();
              final ProgressDialog progressDialog=new ProgressDialog(this);
                progressDialog.setMessage("Loading...");
                if(susername.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter username",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (semail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter email id",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (smobile.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter mobile number",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (spassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    progressDialog.show();
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, JSON_URL,new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.equalsIgnoreCase("Registation success")){
                                        Toast.makeText(getApplicationContext(),"Registation success",Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                                    }

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
                            params.put("susername",susername);
                            params.put("semail",semail);
                            params.put("smobile",smobile);
                            params.put("spassword",spassword);
                            return params;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(SignUp.this);
                    requestQueue.add(stringRequest);

                }

            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
