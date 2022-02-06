package com.example.e_commersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeliveryAddress extends AppCompatActivity {
EditText name,premise_no,colony,city_name,pincode,district,state,country,mobile,altr_mobile;
Button addr_btn;
static String client_name,pre_no,colony1,city_name1,pincode1,district1,state1,country1,mobile1,altr_mobile1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);
        name=(EditText)findViewById(R.id.address_name);
        premise_no=(EditText)findViewById(R.id.premise_name);
        colony=(EditText)findViewById(R.id.colony);
        city_name=(EditText)findViewById(R.id.city_name);
        pincode=(EditText)findViewById(R.id.pincode);
        district=(EditText)findViewById(R.id.district_name);
        state=(EditText)findViewById(R.id.state_name);
        country=(EditText)findViewById(R.id.country_name);
        mobile=(EditText)findViewById(R.id.addr_mobile);
        altr_mobile=(EditText)findViewById(R.id.addr_mobile);
        addr_btn=(Button)findViewById(R.id.addr_btn);
        addr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client_name=name.getText().toString();
                pre_no=premise_no.getText().toString();
                colony1=colony.getText().toString();
                city_name1=city_name.getText().toString();
                pincode1=pincode.getText().toString();
                district1=district.getText().toString();
                state1=state.getText().toString();
                country1=country.getText().toString();
                mobile1=mobile.getText().toString();
                altr_mobile1=altr_mobile.getText().toString();
                if(client_name.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter your full name",Toast.LENGTH_SHORT).show();
                    return;
                }else if(pre_no.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Premise No./Name",Toast.LENGTH_SHORT).show();
                    return;
                }else if(city_name1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter city name",Toast.LENGTH_SHORT).show();
                    return;
                }else if(pincode1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter pincoe",Toast.LENGTH_SHORT).show();
                    return;
                }else if(district1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter district name",Toast.LENGTH_SHORT).show();
                    return;
                }else if(country1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter country name",Toast.LENGTH_SHORT).show();
                    return;
                }else if(mobile1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter mobile number",Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    Intent intent=new Intent(getApplicationContext(),PaymentMode.class) ;
                    startActivity(intent);
                }


            }
        });
    }
}