package group.sample.advanced.rrk.com.advancedsamplegroupapplication.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2018-01-24.
 */

public class DummyService extends Service {


    public static final int SHOW_ALERT_POPUP = 2000;

    public static final String SHOW_ALERT_POPUP_ACTION = "DummyService.SHOW_ALERT_POPUP_ACTION";
    public static final String ERROR_ACTION = "DummyService.ERROR_ACTION";

    View view;
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(new RemoteHandler()).getBinder();
    }


    @Override
    public void onDestroy() {
        destroyWindowLayout();
        super.onDestroy();

    }

    public void initWindowLayout (){


        if (view == null){

            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_window_view,null,false);
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(

                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT );
        layoutParams.gravity = Gravity.CENTER;

        WindowManager windowManager = (WindowManager) getSystemService( WINDOW_SERVICE );



        windowManager.addView( view, layoutParams);
    }

    public void destroyWindowLayout(){
        WindowManager windowManager = (WindowManager) getSystemService( WINDOW_SERVICE );


        if( view != null )
            windowManager.removeViewImmediate( view );

//        windowManager.addView( view, layoutParams);
    }
    private class RemoteHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.d(getClass().getSimpleName(),"get Message what? " + msg.what);
//            Intent sendAction;
            switch (msg.what ){
                case SHOW_ALERT_POPUP:
//                    sendAction = new Intent(SHOW_ALERT_POPUP_ACTION );
//                    initWindowLayout();

                    Intent intent = new Intent(SHOW_ALERT_POPUP_ACTION );
                    sendBroadcast( intent );
                    break;
                default:
//                    sendAction = new Intent( ERROR_ACTION );
                    break;
            }
        }
    }
}
