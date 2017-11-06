package com.example.sivhabu.ekhack.fcm;

import android.util.Log;

import com.example.sivhabu.ekhack.common.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by root on 11/5/17.
 */

public class MyFirebaseInstanceIDService  extends FirebaseInstanceIdService {


    private static final String TAG = "MyFirebaseIDService";

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);

    }



  /*  private void sendPushNotificationToReceiver(String username,
                                                String message,
                                                String uid,
                                                String firebaseToken,
                                                String receiverFirebaseToken) {
        FcmNotificationBuilder.initialize()
                .title(username)
                .message(message)
                .username(username)
                .uid(uid)
                .firebaseToken(firebaseToken)
                .receiverFirebaseToken(receiverFirebaseToken)
                .send();
    }*/


    private void sendRegistrationToServer(final String token) {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseDatabase.getInstance()
                    .getReference()
                    .child("User")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(Constants.ARG_FIREBASE_TOKEN)
                    .setValue(token);
            Log.d(TAG, "Refreshed token: " + token);
        }
    }
}
