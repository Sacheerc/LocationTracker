package com.example.sachintha.findme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import static com.example.sachintha.findme.MainActivity.number;
import static com.example.sachintha.findme.MainActivity.numbercondition;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    static double ad1;
    static double ad2;
    public static final String SMS_BUNDLE = "pdus";

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        String numb="";
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();
                numb=address;

                smsMessageStr += "Bag Buddy Sent Location!!!. Click to View\n";
            }

            //this will update the UI with message
            if(numb.equals(numbercondition)) {
                Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();
                SmsActivity inst = SmsActivity.instance();
                inst.updateList(smsMessageStr);
                location(smsMessageStr);
            }
        }
    }

    public static void location(String msg){
        int len=msg.length();
        String a1="";
        String a2="";
        int i=0;
        while(i<len){
            if(msg.charAt(i)=='('){
                do{
                    i++;
                    a2+=msg.charAt(i);
                }while(msg.charAt(i+1)!=',');
                i++;
                System.out.println();
                do{
                    i++;
                    a1+=msg.charAt(i);
                }while(msg.charAt(i+1)!=')');
            }
            i++;
        }
        ad1=Double.parseDouble(a1);
        ad2=Double.parseDouble(a2);
//
//    System.out.println(ad1);
//    System.out.println(ad2);
    }
}
