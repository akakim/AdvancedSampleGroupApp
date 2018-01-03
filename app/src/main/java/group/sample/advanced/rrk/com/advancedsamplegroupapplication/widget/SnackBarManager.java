package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by RyoRyeong Kim on 2018-01-03.
 */

public class SnackBarManager {

    private static final int MSG_TIMEOUT = 0;

    private static final int SHORT_DURATION_MS = 1500;
    private static final int LONG_DURATION_MS = 2750;

    private static SnackBarManager snackBarManager;


    static SnackBarManager getInstance(){
        if(snackBarManager == null){
            snackBarManager = new SnackBarManager();
        }

        return snackBarManager;

    }

    private final Object lock;
    private final Handler handler;


    private SnackBarRecord currentSnackbar;
    private SnackBarRecord nextSnackbar;

    private SnackBarManager(){
        lock = new Object();
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case MSG_TIMEOUT:
                        handleTimeout( (SnackBarRecord) msg.obj);
                        return true;
                }
                return false;
            }
        });
    }


    /**
     * snackBar를 생성한다.
     * @param record
     */
    private void handleTimeout( SnackBarRecord record){
        // TODO : 싱크로나이즈에 왜 객체를 걸어놓아야하는거지 ?
        synchronized ( lock ){
            if( currentSnackbar == record || nextSnackbar == record){
                cancelSnackBarLock(record,TSnackBar.Callback.DISMISS_EVENT_TIMEOUT);
            }
        }
    }

    public void show( int duration ,Callback callback){
        synchronized (lock){

        }
    }

    private void showNextSnackBarLocked(){
        if( nextSnackbar != null){
            currentSnackbar = nextSnackbar;
            nextSnackbar = null;

            final Callback callback = currentSnackbar.callback.get();
            if( callback != null){
                callback.show();
            }else {
                // callback이 더이상 존재하지않을때 snackBar를 초기화한다.
                currentSnackbar = null;
            }
        }
    }

    /**
     * 스낵바가 사라졌다는 callback을 날린다.
     * @param record 스낵바를 넣을 개체 ?
     * @param event 스낵바 이벤트 타입
     * @return callback의 실행 유무
     */
    private boolean cancelSnackBarLock(SnackBarRecord record, int event){
        final Callback callback = record.callback.get();
        if(callback != null){
            callback.dimsiss(event);
            return true;

        }
        return false;
    }


    private boolean isCurrentSnackBar( Callback callback){
        return currentSnackbar!= null && currentSnackbar.isSnackBar( callback );
    }

    private boolean isNextSnackBar( Callback callback){
        return nextSnackbar!= null && nextSnackbar.isSnackBar( callback );
    }


    interface Callback{
        void show();

        void dimsiss(int event);
    }
    private static class SnackBarRecord{
        private final WeakReference<Callback> callback;
        private int duration;

        SnackBarRecord(int duration, Callback callback){
           this.callback = new WeakReference<Callback>(callback);
            this.duration = duration;
        }

        boolean isSnackBar (Callback callback){
            return callback != null && this.callback.get() == callback;
        }
    }
}
