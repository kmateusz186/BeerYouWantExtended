package com.example.przemek.beeryouwantv2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.Serializable;


/**
 * Created by Przemek on 12.12.2016.
 */

public class TimeOfAlcohol extends Service implements Serializable {

    private Handler handler;

    public double time;
        public void run() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), TimeOfAlcohol.class);
                    PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent, 0);
                    Notification notification = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Jesteś trzeźwy")
                            .setContentText("Dobra czas wstawać i do pracy")
                            .setSmallIcon(R.drawable.notification_image)
                            .setContentIntent(pIntent)
                            .setAutoCancel(true)
                            .setPriority(Notification.PRIORITY_MAX)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .build();
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(0, notification);
                }
            },(int) time * 1000 * 5 );
        }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        time = intent.getDoubleExtra("UniqueKeyV1",0);
        handler = new Handler();
        run();
        return super.onStartCommand(intent, flags, startId);
    }

@Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
