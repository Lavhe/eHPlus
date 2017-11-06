package com.example.sivhabu.ekhack.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sivhabu.ekhack.MainActivity;
import com.example.sivhabu.ekhack.R;
import com.example.sivhabu.ekhack.common.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Created by root on 11/5/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0 )
        {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            String title = remoteMessage.getData().get("title");
            String message = remoteMessage.getData().get("text");
            String username = remoteMessage.getData().get("username");
            String uid = remoteMessage.getData().get("uid");
            String fcmToken ="dLvxim56XtQ:APA91bHxyB4s4P_56KLPTXVYPXyGxoH2txoAIS94dhAVqeF79fcLjkXjQSWzFNCcNaiUyVGyhfdEgQFrqudzCWAWx22lMSeJYiuosqq-jzSU1fD6ahfJ_Q1wDSA-bxNz8cuC0gKlqhBe"; //remoteMessage.getData().get("fcm_token");
           // String res_id = remoteMessage.getData().get("STUD_RES_ID");
            //String stud_id = remoteMessage.getData().get("STUD_UID");
           // String senderUid = remoteMessage.getData().get("senderUid");



                receiveNotification(title,
                        message,
                        username,
                        uid,
                        fcmToken);

        }

    }


    private void receiveNotification(String title, String message, String receiver, String receiverUid, String firebaseToken)
    {

        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.ARG_RECEIVER, receiver);
        intent.putExtra(Constants.ARG_RECEIVER_UID, receiverUid);
        intent.putExtra(Constants.ARG_FIREBASE_TOKEN, firebaseToken);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10002, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_red)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(m, notificationBuilder.build());



    }

}
