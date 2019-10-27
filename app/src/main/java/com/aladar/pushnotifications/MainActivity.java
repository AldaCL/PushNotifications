package com.aladar.pushnotifications;

import java.util.List;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aladar.pushnotifications.RestAPI.Endospoints;
import com.aladar.pushnotifications.RestAPI.adapter.RestAPIAdapter;
import com.aladar.pushnotifications.RestAPI.model.UsuarioResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String DATA_taxi = "Vehiculo";
    private static final String DATA_alerta = "Alerta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                NotificationChannel channel =
                        new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);

                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);


        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "OK";
                        if (!task.isSuccessful()) {
                            msg = "FAIL";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        Button logTokenButton = findViewById(R.id.btn_Notification);
        logTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()){
                                    Log.w(TAG, "getInstanced failed", task.getException());
                                    return;
                                }
                                String token = task.getResult().getToken();
                                String ide = task.getResult().getId();
                                enviarTokenRegistro(token);

                                // Log and toast

                                Log.d("ID_FIREBASE",ide);
                                Log.d("TOKEN",token);
                                String msg = token;
                                Log.d(TAG, msg);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }



//    public void lanzarNotificacion(View view){
//
//        Intent i = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i ,PendingIntent.FLAG_ONE_SHOT);
//
//        Uri soundnotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//
//        int notifyID = 1;
//        String CHANNEL_ID = "my_channel_01";// The id of the channel.
//        CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//        notificationManager.createNotificationChannel(mChannel);
//
//
//
//
//        NotificationCompat.Builder notificacion_local = new NotificationCompat.Builder(this,CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_action_warning)
//                .setContentTitle("Atención")
//                .setContentText("Por favor muevase a la verga")
//                .setSound(soundnotification)
//                .setContentIntent(pendingIntent)
//                .setChannelId(CHANNEL_ID);
//
//
//        notificationManager.notify(0, notificacion_local.build());
//
//
//    }
    public  void enviarToken(View V){
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }


    private void enviarTokenRegistro (String token){
        Log.d("TOKEN",token);
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Endospoints endospoints = restAPIAdapter.establecerConexionRestAPI();

        Call<UsuarioResponse> usuarioResponseCall = endospoints.registrarTokenID(token);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>(){
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                //Log.d(TAG, String.valueOf(usuarioResponses.get(2)));
                Log.d("USUARIO_FIREBASE",usuarioResponse.getToken());
                Log.d("ID_FIREBASE",usuarioResponse.getId());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }


//            @Override
//            public void  onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response){
//                UsuarioResponse usuarioResponse = response.body();
//                Log.d("ID_FIREBASE",usuarioResponse.getId());
//                Log.d("USUARIO_FIREBASE",usuarioResponse.getToken());
//            }
//            @Override
//            public void onFailure(Call <UsuarioResponse> call, Throwable t){
//
//            }
        });
    }






}
