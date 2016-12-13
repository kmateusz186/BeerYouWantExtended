package com.example.przemek.beeryouwantv2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        String message = intent.getExtras().getString("message");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
