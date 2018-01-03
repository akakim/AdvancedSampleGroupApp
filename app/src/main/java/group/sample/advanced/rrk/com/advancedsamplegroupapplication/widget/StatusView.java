package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

/**
 * Created by RyoRyeong Kim on 2018-01-02.
 * reference : https://github.com/iammert/StatusView
 */

public class StatusView extends RelativeLayout {

    public enum Status{
        IDLE,LOADING,ERROR,COMPLETE
    }
    private static final int DISMISS_ON_COMPLETE_DELAY = 1000;


    private Status currentStatus;

    private boolean hideOnComplete;


    /**
     * sampleView
     */
    private View completeView;
    private View errorView;
    private View loadingView;


    /**
     * fade in out animation
     */
    private Animation slideOut;
    private Animation slideIn;


    /**
     * layout inflater
     */
    private LayoutInflater inflater;

    private Handler handler;

    private Runnable autoDismissOnComplete = new Runnable() {
        @Override
        public void run() {
//            exitAnimation(getCurrentView(currentStatus));
            handler.removeCallbacks( autoDismissOnComplete );
        }
    };


    /**
     *
     * @param context
     */
    public StatusView(Context context) {
        super(context);
    }

    public StatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, @IntegerRes int completeLayout, @IntegerRes int errorLayout,@IntegerRes int loadingLayout){
        currentStatus = Status.IDLE;
        hideOnComplete = true;

    }
}
