package rrk.dev.andcodephilo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by RyoRyeong Kim on 2018-02-05.
 */

public class WindowNotificationService extends Service{

    Timer timer;
    TimerTask task;
    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer("requestTimer");
        task = new TimerTask() {
            @Override
            public void run() {
                // request something..


//                Intent i = new Intent( GlobalActionConstant.ACTION_NOTIFICATION );
//                sendBroadcast( i );
            }
        };


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        if( task != null){
            task.cancel();
        }
        if(timer != null){
            timer.cancel();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
