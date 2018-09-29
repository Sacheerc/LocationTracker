package com.example.sachintha.findme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button,btnmap;
    EditText editText,editText2;
    static String number;
    static String numbercondition;
    int numberint;
    String sms="Hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS )){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.SEND_SMS},1);
            }else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.SEND_SMS},1);
            }

        }else{
            //do nothing
        }

        button =(Button) findViewById(R.id.button);
        editText=(EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbercondition="+94";
                numberint= Integer.parseInt(editText.getText().toString());
                numbercondition+=Integer.toString(numberint);
//                try {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(number,null,sms,null,null);
//                    Toast.makeText(MainActivity.this,"Sent..!",Toast.LENGTH_SHORT).show();
//                }catch (Exception e){
//                    Toast.makeText(MainActivity.this,"Failed..!",Toast.LENGTH_SHORT).show();
//                }
                Intent intent = new Intent(MainActivity.this,SmsActivity.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted..!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "No Permission Granted..!",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}

