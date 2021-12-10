package com.pushnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() == null)
            return;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification(notificationManager , remoteMessage);


    }
    private void showNotification(NotificationManager notificationManager , RemoteMessage remoteMessage) {
        Notification notification = new NotificationCompat.Builder(this, "nyApp")
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .build();
        notificationManager.notify(new Random().nextInt(), notification);
    }
}
