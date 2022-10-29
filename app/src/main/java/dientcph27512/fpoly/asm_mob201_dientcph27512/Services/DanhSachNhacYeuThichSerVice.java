package dientcph27512.fpoly.asm_mob201_dientcph27512.Services;

import static dientcph27512.fpoly.asm_mob201_dientcph27512.Application.MyApplication.CHANNEL_ID;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import dientcph27512.fpoly.asm_mob201_dientcph27512.BroadcastReciver.MyReciverDanhSachNhac;
import dientcph27512.fpoly.asm_mob201_dientcph27512.BroadcastReciver.MyReciverDanhSachNhacYeuThich;
import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.DanhSachYeuThich;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.MainActivity;

public class DanhSachNhacYeuThichSerVice extends Service {
    private MediaPlayer mMediaplay;
    private static final int PAUSE = 1;
    private static final int RESUME = 2;
    private static final int CLEAR = 3;
    private boolean isPlaying;
    private DanhSachYeuThich nhacDTO;

    public DanhSachNhacYeuThichSerVice() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaplay != null) {
            mMediaplay.release();
            mMediaplay = null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            DanhSachYeuThich danhSachNhacDTO = (DanhSachYeuThich) bundle.get("song");
            if (danhSachNhacDTO != null) {
                nhacDTO = danhSachNhacDTO;
                startMusic(danhSachNhacDTO);
            }
        }
        int actionAd = intent.getIntExtra("action_ad",0);
        handleActiconMusic(actionAd);
        int action = intent.getIntExtra("action_br", 0);
        handleActiconMusic(action);
        return START_NOT_STICKY;

    }

    private void startMusic(DanhSachYeuThich danhSachNhacDTO) {
        if (mMediaplay == null) {
            mMediaplay = MediaPlayer.create(getApplicationContext(), danhSachNhacDTO.getNhac());
        }
        mMediaplay.start();
        isPlaying = true;
        runnable.run();
    }

    private void handleActiconMusic(int action) {
        switch (action) {
            case PAUSE:
                pauseMusic();
                break;
            case RESUME:
                resumeMusic();
                break;
            case CLEAR:
                stopSelf();
                break;
        }
    }

    private void pauseMusic() {
        if (mMediaplay != null && isPlaying) {
            mMediaplay.pause();
            isPlaying = false;
            sendNotification(nhacDTO);
            senActionFagment(PAUSE);
        }
    }

    private void senActionFagment(int action) {
        Intent intent = new Intent("send_data_to_danh_sach_yeu");
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_song_ds_iu", nhacDTO);
        bundle.putBoolean("staus_ds_iu", isPlaying);
        bundle.putInt("action_ds_nhac_iu", action);
        intent.putExtras(bundle);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void resumeMusic() {
        if (mMediaplay != null && !isPlaying) {
            mMediaplay.start();
            isPlaying = true;
            sendNotification(nhacDTO);
            senActionFagment(RESUME);
        }
    }
    Handler handler = new Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isPlaying) {
                sendNotification(nhacDTO);
                handler.postDelayed(this, 1000);
            }
        }
    };
    private void sendNotification(DanhSachYeuThich danhSachNhacDTO) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), danhSachNhacDTO.getAnh());
        @SuppressLint("RemoteViewLayout") RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
        remoteViews.setTextViewText(R.id.title_song, danhSachNhacDTO.getTen());
        remoteViews.setImageViewBitmap(R.id.img_song, bitmap);
        remoteViews.setImageViewResource(R.id.play, R.drawable.ic_baseline_pause_24);
        if (isPlaying) {
            remoteViews.setImageViewResource(R.id.play, R.drawable.ic_baseline_pause_24);
            remoteViews.setOnClickPendingIntent(R.id.play, getPendingInten(this, PAUSE));
        } else {
            remoteViews.setImageViewResource(R.id.play, R.drawable.ic_baseline_play_arrow_24);
            remoteViews.setOnClickPendingIntent(R.id.play, getPendingInten(this, RESUME));
        }
        remoteViews.setOnClickPendingIntent(R.id.clear, getPendingInten(this, CLEAR));
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_none_24)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .setSound(null)
                .build();
        if (mMediaplay != null) {
            int totalTime = mMediaplay.getDuration();
            int currentTime = mMediaplay.getCurrentPosition();
            if (currentTime < totalTime) {
                remoteViews.setProgressBar(R.id.musicBar, mMediaplay.getDuration(), mMediaplay.getCurrentPosition(), false);
            } else {
                remoteViews.setImageViewResource(R.id.play, R.drawable.ic_baseline_play_arrow_24);
            }
        }
        startForeground(1, notification);
    }

    private PendingIntent getPendingInten(Context context, int action) {
        Intent intent = new Intent(this, MyReciverDanhSachNhacYeuThich.class);
        intent.putExtra("action", action);
        return PendingIntent.getBroadcast(context.getApplicationContext(), action, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}