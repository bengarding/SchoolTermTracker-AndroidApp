package com.bengarding.wgutermtracker.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.bengarding.wgutermtracker.R;

public class Receiver extends BroadcastReceiver {
    static int notificationId;
    public static int alertNumber = 0;
    final String CHANNEL_ID = "wgu_term_tracker";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, ("Notification made for: " + intent.getStringExtra("title")), Toast.LENGTH_LONG).show();

        createNotificationChannel(context, CHANNEL_ID);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_school_24)
                .setContentText(intent.getStringExtra("text"))
                .setContentTitle(intent.getStringExtra("title")).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId++, notification);
    }

    private void createNotificationChannel(Context context, String CHANNEL_ID) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
