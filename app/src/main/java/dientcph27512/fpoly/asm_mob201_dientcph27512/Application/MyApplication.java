package dientcph27512.fpoly.asm_mob201_dientcph27512.Application;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {
    public static final String CHANNEL_ID = "channel_service_nhac";
    @Override
    public void onCreate() {
        super.onCreate();
        createChanelNotification();
    }

    private void createChanelNotification() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"channel_service_nhac", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            channel.setSound(null,null);
            if(manager != null){
                manager.createNotificationChannel(channel);
            }
        }
    }
}
