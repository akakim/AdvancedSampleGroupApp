package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by RyoRyeong Kim on 2018-01-03.
 * 왜 이런 코드가 나왔지 ??
 */

public class SnackBarManager {

    private static final int MSG_TIMEOUT = 0;

    private static final int SHORT_DURATION_MS = 1500;
    private static final int LONG_DURATION_MS = 2750;

    private static SnackBarManager snackBarManager;


    /**
     * SignleTon 패턴으로 제작한 Manager
     * @return
     */
    static SnackBarManager getInstance(){
        if(snackBarManager == null){
            snackBarManager = new SnackBarManager();
        }

        return snackBarManager;

    }

    /**
     * Synchronize를 위한 객체
     */
    private final Object lock;

    /**
     *
     */
    private final Handler handler;


    /**
     * Queue에 넣을
     */
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

            // 현재 보여지는 snackBar
            // 다음작업이  이미 있는 큐안에 콜백이 있는경우 duration만 갱신한다.
            // 현재 스낵바가 보이는 사애ㅌ이면
            if ( isCurrentSnackBar(callback)) {
                // Means that the callback is already in the queue. we'll just update the duration
                currentSnackbar.duration = duration;

                //if this is the snackbar currently being shown, call re-schedule it's timeout
                //
                handler.removeCallbacksAndMessages(currentSnackbar);
                scheduleTimeoutLocked(currentSnackbar);
            } else if ( isNextSnackBar(callback )) {
                // we'll just update the duration
                nextSnackbar.duration = duration;
            } else {
                // 그 이외의 경우 새로운 레코드를 큐에 넣는다.

                nextSnackbar = new SnackBarRecord(duration,callback);
            }

            if( currentSnackbar != null && cancelSnackBarLock( currentSnackbar,
                    TSnackBar.Callback.DISMISS_EVENT_CONSECUTIVE )){
                // if we currently have a TSnackBar, try and cancel it and wait in line
                return;
            }else {
                currentSnackbar = null;
                showNextSnackBarLocked();
            }
        }
    }

    public void dismiss( Callback callback,int event){
        synchronized (lock){
            if( isCurrentSnackBar( callback)){
                cancelSnackBarLock( currentSnackbar, event);
            }else if ( isNextSnackBar(callback)){
                cancelSnackBarLock( nextSnackbar,event);
            }
        }
    }

    /**
     * TSnackBar가 더이상 보이지않을때 호출되어야한다.
     * 이것은 animation이 종료된 시점에
     * @param callback
     */
    public void onDismissed(Callback callback){
        synchronized ( lock ){
            if( isCurrentSnackBar( callback )){
                //
                currentSnackbar = null;
                if(nextSnackbar != null){
                    showNextSnackBarLocked();
                }
            }
        }
    }

    /**
     * Should be called when TSnackbar is being shown.
     * this is after any entrance animation has finished
     * TSnackBar가 보여진 상태일때 호출해야만한다.
     *
     */

    public void onShown(Callback callback){
        synchronized (lock){
            if( isCurrentSnackBar( callback)){
                scheduleTimeoutLocked( currentSnackbar);
            }
        }
    }

    /**
     * 현재 핸들러에 들어간 event들을 지운다.
     * @param callback
     */
    public void cancelTimeout(Callback callback) {
        synchronized (lock) {
            if (isCurrentSnackBar(callback)) {
                handler.removeCallbacksAndMessages(currentSnackbar);
            }
        }
    }

    public void restoreTimeOut(Callback callback){
        synchronized (lock){
            if( isCurrentSnackBar( callback)){
                scheduleTimeoutLocked( currentSnackbar);
            }
        }
    }

    /**
     * 핸들러로 이벤트를 정해진 시간만큼 보내줌
     * @param record
     */
    private void scheduleTimeoutLocked(SnackBarRecord record){
        if(record.duration == TSnackBar.LENGTH_INDEFINITE){
            // if we are set to indefinite, we don't want to set a timeout
            return;
        }

        int durationMs = LONG_DURATION_MS;

        if(record.duration > 0){
            durationMs  =record.duration;
        }else if( record.duration == TSnackBar.LENGTH_SHORT){
            durationMs = SHORT_DURATION_MS;
        }

        handler.removeCallbacksAndMessages( record );
        handler.sendMessageDelayed( Message.obtain( handler, MSG_TIMEOUT,record ), durationMs );
    }

    /**
     *
     */
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
            callback.dismiss(event);
            return true;

        }
        return false;
    }


    /**
     * callback 객체가 , 현재 SnackBar의 Callback과 일치 유/무
     * @param callback c확인할 Callback
     * @return
     */
    private boolean isCurrentSnackBar( Callback callback){
        return currentSnackbar!= null && currentSnackbar.isSnackBar( callback );
    }

    /**
     * callback 객체가 , 현재 SnackBar의 Callback과 일치 유/무
     * @param callback 확인할 Callback
     * @return
     */
    private boolean isNextSnackBar( Callback callback){
        return nextSnackbar!= null && nextSnackbar.isSnackBar( callback );
    }


    public boolean isCurrentOrNext(Callback callback){
        synchronized (lock){
            return isCurrentSnackBar( callback ) || isNextSnackBar(callback);
        }
    }

    interface Callback{
        void show();

        void dismiss(int event);
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
