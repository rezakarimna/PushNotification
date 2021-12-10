package com.pushnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("reza")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "subscribed to this topic", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        switch (view.getId()) {
            case R.id.btnSimpleNotification:
                SimpleNotification();
                break;
            case R.id.btnBigPictureStyleNotification:
                bigPictureStyleNotification();
                break;
            case R.id.btnBigTextStyleNotification:
                bigTextStyleNotification();
                break;
            case R.id.btnInBoxStyleNotification:
                inboxStyleNotification();
                break;
        }
    }

    private void SimpleNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, "nyApp")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("My Notification")
                .setContentText("This is my first notification")
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(new Random().nextInt(), notification);

    }

    private void bigPictureStyleNotification() {
        Notification bigPictureNotification = new NotificationCompat.Builder(this, "nyApp")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("My Notification")
                .setContentText("This is my first notification")
                .setStyle(new NotificationCompat.BigPictureStyle().
                        bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ax)).setBigContentTitle("BigPictureStyle")
                        .setSummaryText("rezaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
                .build();
        notificationManager.notify(new Random().nextInt(), bigPictureNotification);
    }

    private void bigTextStyleNotification() {
        Notification bigTextNotification = new NotificationCompat.Builder(this, "nyApp")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("My Notification")
                .setContentText("This is my first notification")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is my first notification This is my first notification This is my first notification This is my first notification This is my first notificationThis is my first notificationThis is my first notificationThis is my first notificationThis is my first notification"))
                .build();
        notificationManager.notify(new Random().nextInt(), bigTextNotification);
    }

    private void inboxStyleNotification() {
        Notification inboxStyleNotification = new NotificationCompat.Builder(this, "nyApp")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("My Notification")
                .setContentText("This is my first notification")
                .setStyle(new NotificationCompat.InboxStyle().addLine("This is my 3 notification")
                        .addLine("This is my two notification"))
                .build();
        notificationManager.notify(new Random().nextInt(), inboxStyleNotification);
    }

}