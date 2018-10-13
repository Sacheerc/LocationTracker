package com.example.sachintha.findme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class home extends AppCompatActivity {
    Button btnfind,btnprotect,btnbalance,btnhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnfind =(Button) findViewById(R.id.find);
        btnbalance =(Button) findViewById(R.id.balance);
        btnhelp =(Button) findViewById(R.id.help);
        btnprotect =(Button) findViewById(R.id.protect);

        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(MainActivity.number,null, "Where",null,null);
                    Toast.makeText(home.this,"Sent..!",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(home.this,"Failed..!",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(home.this,SplashActivity.class);
                startActivity(intent);
            }
        });

        btnbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(MainActivity.number,null, "Balance",null,null);
                    Toast.makeText(home.this,"Sent..!",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(home.this,"Failed..!",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(home.this,SplashActivity.class);
                startActivity(intent);
            }
        });

        btnhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(MainActivity.number,null, "Help",null,null);
                    Toast.makeText(home.this,"Sent..!",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(home.this,"Failed..!",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(home.this,SplashActivity.class);
                startActivity(intent);
            }
        });

        btnprotect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,getmylocation.class);
                startActivity(intent);
            }
        });
    }

}
