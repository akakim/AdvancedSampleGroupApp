package rrk.dev.andcodephilo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by RyoRyeong Kim on 2018-02-05.
 */

public class WindowPopupManager implements View.OnClickListener{
    private static final WindowPopupManager ourInstance = new WindowPopupManager();
    private static Context sContext;

    private static View inflatedView;

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
        inflatedView.findViewById(R.id.btnConfirm).setOnClickListener( this  );
        inflatedView.findViewById(R.id.btnCancel).setOnClickListener( this  );
        return this;
    }

    @Override
    public void onClick(View v) {



    }


//    public WindowPopupManager s

    public interface OnPopupClickListener{
        void onConfirmClicked(final Runnable action);
        void onCancelClicked(final Runnable action);
    }

}
