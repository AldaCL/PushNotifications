package com.aladar.pushnotifications;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;




public class NotificationService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }

    public void showNotification(String title, String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
            .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());

    }

}

//
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//
//
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        }
//
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.
//
//
//
//        Intent i = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i ,PendingIntent.FLAG_ONE_SHOT);
//
//
//        Uri soundnotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//
//        //int notifyID = 3;
//        String CHANNEL_ID = "my_channel";// The id of the channel.
//        CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//        notificationManager.createNotificationChannel(mChannel);
//
//
//        NotificationCompat.Builder notificacion_local = new NotificationCompat.Builder(this,CHANNEL_ID)
//                //.setSmallIcon(R.drawable.ic_action_warning)
//                //.setContentTitle("Atención")
//                .setContentText(remoteMessage.getNotification().getBody())
//                .setSound(soundnotification)
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent)
//                .setChannelId(CHANNEL_ID);
//
//
//
//        notificationManager.notify(3, notificacion_local.build());
//
//    }
//    // [END receive_message]
//
//
//    // [START on_new_token]
//
//    /**
//     * Called if InstanceID token is updated. This may occur if the security of
//     * the previous token had been compromised. Note that this is called when the InstanceID token
//     * is initially generated so this is where you would retrieve the token.
//     */
//
//
//
//
//}
