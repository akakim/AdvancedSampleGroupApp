package rrk.dev.andcodephilo;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.method.Touch;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by RyoRyeong Kim on 2018-02-05.
 */

public class WindowPopupManager implements View.OnClickListener,View.OnTouchListener{

    final String TAG = getClass().getSimpleName();
    private static final WindowPopupManager ourInstance = new WindowPopupManager();
    private static Context sContext;

    private static View inflatedView;

    boolean isUpdateable = false;

    OnPopupClickListener onPopupClickListener;

    public static WindowPopupManager getInstance(Context context) {
        return getInstance(context,-1);
    }


    public static WindowPopupManager getInstance(Context context, @LayoutRes int res) {
        sContext = context;

        if( res == -1 ){
           inflatedView = LayoutInflater.from(sContext).inflate(R.layout.popup_window_sample,null,false);
        }else {
            inflatedView = LayoutInflater.from(sContext).inflate(res,null,false);
        }

        return ourInstance;
    }


    // 윈도우 메니저는 상태를 알아 낼 수 없으므로.. 간섭도 안되므로 ..
    //  상태를일일히 이쪽에서 체크를 해준다.
    public static final String WINDOW_INIT = "initialize";
    public static final String WINDOW_IS_SHOWING = "showing";
    // volatile은 변수에 변화가 생기면 바로바로 적용하라 라는 의미이다.
    // 다시말해 컴파일러에 의해 변수의 변화가 최적화되면 안되고, 동기화가 되어야되는 변수의 특징을
    // 위함이다.
    private static volatile String windowPopupStatus;

    private static WindowManager.LayoutParams windowLayoutParams;


    private WindowPopupManager() {

        windowPopupStatus = WINDOW_INIT;
        windowLayoutParams = new WindowManager.LayoutParams(

                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        windowLayoutParams.gravity = Gravity.CENTER;
    }

    public WindowPopupManager setTitle(String title){

        ((TextView)inflatedView.findViewById(R.id.tvPopupTitle)).setText( title);
        return this;
    }

    public WindowPopupManager setContent(String content){

        ((TextView)inflatedView.findViewById(R.id.tvTitle)).setText(content);
        return this;
    }



    public void setOnPopupClickListener(OnPopupClickListener onPopupClickListener) {
        this.onPopupClickListener = onPopupClickListener;
    }

    public WindowPopupManager setOnConfirmCliekcListener(Runnable confirmActionRunnable , Runnable onCancelAction ){

        return this;
    }

    public void show(){


        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M && !Settings.canDrawOverlays( sContext )  ){

            Intent intent = new Intent( GlobalActionConstant.ACTION_NO_OVERLAY_PERMISSION );
            sContext.sendBroadcast( intent );
        }else {
            inflatedView.findViewById(R.id.btnConfirm).setOnClickListener(this);
            inflatedView.findViewById(R.id.btnCancel).setOnClickListener(this);


//        inflatedView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_BUTTON_PRESS:
//                    break;
//                }
//                // touch를 하면 움직 일수있도록 정의할 수 있을까... ?
//                return false;
//            }
//        });
            WindowManager windowManager = (WindowManager) sContext.getSystemService(Context.WINDOW_SERVICE);
            if (WINDOW_INIT.equals(windowPopupStatus)) {


                windowManager.addView(inflatedView, windowLayoutParams);
                windowPopupStatus = WINDOW_IS_SHOWING;
            } else {

                windowManager.updateViewLayout(inflatedView, windowLayoutParams);
            }

        }
    }


    public void dismiss(){
        if( WINDOW_IS_SHOWING.equals( windowPopupStatus) ){

            WindowManager manager = (WindowManager ) sContext.getSystemService(Context.WINDOW_SERVICE);
            manager.removeViewImmediate( inflatedView );
        }else {
            Log.e( TAG , "window status error" );
        }
    }
    @Override
    public void onClick(View v) {



        if( v.getId() == R.id.btnConfirm){

            if(onPopupClickListener != null){
                onPopupClickListener.onConfirmClicked();
            }
            dismiss();
        }else if ( v.getId() == R.id.btnCancel ){

            if(onPopupClickListener != null){

                onPopupClickListener.onCancelClicked();
            }
            dismiss();
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        WindowManager windowManager = (WindowManager) sContext.getSystemService(Context.WINDOW_SERVICE);

//        windowLayoutParams.x = event.getX();
//        windowLayoutParams.x = event.getX();
        switch ( event.getAction()){

            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                // save position

//                event.get

//                windowManager.getDefaultDisplay().
                break;
        }
        return false;
    }


//    public WindowPopupManager s

    public interface OnPopupClickListener{
        void onConfirmClicked();
        void onCancelClicked();
    }

}
