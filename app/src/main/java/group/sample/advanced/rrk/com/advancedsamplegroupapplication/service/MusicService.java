package group.sample.advanced.rrk.com.advancedsamplegroupapplication.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BuildConfig;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2017-12-04.
 * 음악 서비스 구현체,
 *
 */

public class MusicService extends Service implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnSeekCompleteListener{

    private NotificationManager mNM;

    private Messenger messenger;

    MediaPlayer mediaPlayer;

    /**
     * current MediaPlayer 상태를 의미하는 상수 값.
     */
    public static  final String idleStatus="idle";
    public static final String startStatus="start";
    public static final String pauseStatus="pause";
    public static final String stopStatus="last_stop";
    public static final String errorStatus = "error";

    /**
     * activity와 통신시, msg.what으로 어떤 명령을 실행 할 지 결정한다.
     *
     */
    public static final int stopAndPlay = 1;
    public static final int pause = 2;
    public static final int play = 3;
    public static final int playAndPause = 4;
    public static final int fastwind = 5;
    public static final int nextwind = 6;
    public static final int last_stop = 7;
    public static final int reset = 8;
    public static final int showControlloer = 9;

    public static final int updateNetworkWind = 11;
    public static final int updateNotiBitmap  = 12;

    /**
     * Notification request
     */
    public static final int REQUEST_CODE = 1000;


    /**
     * 서비스에서 Activity 로 갈 때는 Handler를 이용하는 것이 아닌 , BroadCastReceiver 로 전달한다.
     * 그래서 작성한 Intent 이다.
     */
    public static final String PLAY_AND_PAUSE_ACTION = "com.tripath.muse.service.MusicService.playAndPauseIntent";
    public static final String NEXT_WIND_ACTION =  "com.tripath.muse.service.MusicService.nextWindIntent";
    public static final String NETWORK_ERROOR_ACTION =  "com.tripath.muse.service.MusicService.netWorkErrorIntent";


    int currentIndex=-1;
    String currentStatus = idleStatus;
    String currentSelectedUrl="";

    final String TAG = getClass().getSimpleName();


    final int NOTIFICATION_ID= 4001;
    private  NotificationManagerCompat mNotificationManager;

    boolean isInit = true;

    String currentTitle="";
    String currentSubTitle = "";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        /**
         * 전역변수로 두고, handler를 이용하려한다면 무한 루프가 걸린다.
         */
        return new Messenger(new RemoteHandler()).getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer =new MediaPlayer();
        mediaPlayer.setOnPreparedListener( this );
        mediaPlayer.setOnSeekCompleteListener( this );
        mediaPlayer.setOnCompletionListener( this );
        mediaPlayer.setOnErrorListener( this );
        mediaPlayer.setVolume(100,100);

        currentStatus = idleStatus;     // 초기화시 idle;

        mNotificationManager = NotificationManagerCompat.from(this);

//        Notification notification = createNotification();
//        mNotificationManager.notify(0,notification);
//        if()

        // Cancel all notifications to handle the case where the Service was killed and
        // restarted by the system.
        mNotificationManager.cancelAll();

//        mNotificationManager.notify(0,notification);
//        requestGetMusicListSwipeEvent();
    }

    /**
     * 서비스의 특성상 무한루프의 상태에 있어야된다.
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    /**
     * 서비스가 종료되면 그에 맞게 메모리를 해체한다.
     */
    @Override
    public void onDestroy() {

        Log.d(TAG,"onDestroy()");
        if( mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }


    /**
     * stop
     * @param mp
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        currentStatus=stopStatus;
        mp.reset();
        currentStatus=idleStatus;

        Log.d(TAG,"onCompletion : sendIntent");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        currentStatus = errorStatus;

        if( startStatus.equals(currentStatus)){
            mp.stop();
        }
        mp.reset();

        currentStatus = idleStatus;

        StringBuilder builder = new StringBuilder();
        builder.append("오류 : " );
        switch (what){
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                builder.append("알수없는 에러");
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                builder.append("미디어 플레이어가 다운됬습니다. ");
                break;
        }

        builder.append("\n세부내용 : " + extra + " ");

        switch(extra){
            case MediaPlayer.MEDIA_ERROR_IO:
                builder.append("파일을 찾지 못했습니다. 네트워크 상태를 확인해주세요");
                break;
            case MediaPlayer.MEDIA_ERROR_MALFORMED:
                builder.append("파일을 읽을 수 없습니다. ");
                break;

            case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                builder.append("지원하지않는 파일 형식입니다.  ");
                break;

            case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                builder.append("응답이 없습니다.");
                break;
            case -2147483648:
                builder.append("시스템 로우 레벨 ");

//                if( !NetworkUtil.isNetworkOn(this)){
//                    Intent networkErrorAction = new Intent(NETWORK_ERROOR_ACTION);
//                    sendBroadcast(networkErrorAction);
//                }
                break;
            default:
                builder.append("알수없는 에러 ");

                break;
        }

        Log.e(TAG,"OnError " + builder.toString());
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        currentStatus=startStatus;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
//        mp.last_stop();
//        currentStatus=stopStatus;
//        mp.reset();
//        currentStatus=idleStatus;

        Log.d(TAG,"onSeekCompletion : ");
    }


    private class RemoteHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            /**
             * public static final int stopAndPlay = 1;
             public static final int pause = 2;
             public static final int play = 3;
             public static final int playAndPause = 4;
             public static final int fastwind = 5;
             public static final int nextwind = 6;
             */
//            StringBuilder builder = new StringBuilder();
//            final MusicItemModel musicModel;
//            switch (msg.what) {
//                case stopAndPlay:
//
//                    musicModel = (MusicItemModel) msg.obj;
//
//                    builder.append(StopAndStart(musicModel));
//
//
//                    if(isInit){
//                        Bitmap bitmap  = drawableToBitmap(musicModel.getMusicAlbumDrawable ());
//                        Notification notification = createNotification(musicModel.getTitle(), musicModel.getArtist(), musicModel.getImgPath());
//                        startForeground(NOTIFICATION_ID, notification);
//                        isInit = false;
//                    }else if (startStatus.equals(currentStatus)){
//                        Bitmap bitmap  = drawableToBitmap(musicModel.getMusicAlbumDrawable ());
//                        Notification notification = createNotification(musicModel.getTitle(),musicModel.getArtist(), musicModel.getImgPath());
//                        mNotificationManager.notify(NOTIFICATION_ID,notification);
//                    }
////                        mNotificationManager.notify(NOTIFICATION_ID,notification);
////                        isInit = false;
////                    }
////                    remoteSendMessage( stopAndPlay );
//                    break;
//                case pause:
//                    musicModel = (MusicItemModel) msg.obj;
//                    Bitmap pauseBitmap  = drawableToBitmap(musicModel.getMusicAlbumDrawable ());
//                    Notification pausNotification = createNotification(musicModel.getTitle(), musicModel.getArtist(), musicModel.getImgPath(),true);
//                    startOrPause();
//                    mNotificationManager.notify(NOTIFICATION_ID,pausNotification);
//                    break;
//                case play:
//                    musicModel = (MusicItemModel) msg.obj;
//
////                    Bitmap playbitmap  = drawableToBitmap(musicModel.getMusicAlbumDrawable ());
//                    Notification playNotification = createNotification(musicModel.getTitle(), musicModel.getArtist(), musicModel.getImgPath(),false);
//
//
//                    startOrPause();
//                    mNotificationManager.notify(NOTIFICATION_ID,playNotification);
//                    break;
//                case playAndPause:
//                    musicModel = (MusicItemModel) msg.obj;
//                    Bitmap playAnPauseBitmap  = drawableToBitmap(musicModel.getMusicAlbumDrawable ());
//
//                    boolean currentStatusIsPause = false;
//                    if(startStatus.equals(currentStatus)){
//                        currentStatusIsPause = false;
//                    }else {
//                        currentStatusIsPause = true;
//                    }
//                    Notification playAndPauseNotification = createNotification(musicModel.getTitle(), musicModel.getArtist(), playAnPauseBitmap,currentStatusIsPause);
//
//
//                    startOrPause();
//                    mNotificationManager.notify(NOTIFICATION_ID,playAndPauseNotification);
//                    break;
//                case fastwind :
//                    break;
//                case nextwind :
//                    musicModel = (MusicItemModel) msg.obj;
//
//                    builder.append(StopAndStart(musicModel));
//
//
//                    Notification nextWindNotification = createNotification(musicModel.getTitle(),musicModel.getArtist(),musicModel.getImgPath());
//                    mNotificationManager.notify(NOTIFICATION_ID,nextWindNotification);
//
//                    break;
//                case updateNetworkWind:
//
//                    musicModel = (MusicItemModel) msg.obj;
//
//                    builder.append(StopAndStart(musicModel));
//
//
//
//                    break;
//                case updateNotiBitmap:
//                    Bitmap updateNetworkWindBitmap = (Bitmap)msg.obj;
//
//
//                    Notification updateNetworkWindNotification = createNotification(currentTitle, currentSubTitle, updateNetworkWindBitmap);
//                    mNotificationManager.notify(NOTIFICATION_ID,updateNetworkWindNotification);
//
//                    break;
//                case last_stop:
//                    currentStatus = idleStatus;
//                    mediaPlayer.reset();
//                    break;
//
//                case reset:
//                    if(currentStatus.equals(startStatus)) {
//                        mediaPlayer.stop();
//                    }else if( currentStatus.equals(pauseStatus)) {
//                        mediaPlayer.stop();
//                    }
//                    mediaPlayer.reset();
//                    currentStatus=idleStatus;
//                    break;
//                case showControlloer:
//
//
//                    break;
//                default:
//
//                    break;
//            }
        }
    }

//    public String StopAndStart(MusicItemModel musicModel ){
//
//        try {
//            String url = musicModel.getSite() +"?name=" + URLEncoder.encode(musicModel.getSource(), "UTF-8");
//            Log.d(TAG,"current State : " + currentStatus);
//            Log.d(TAG,"url : " + url);
//            if (mediaPlayer == null ) {


//                mediaPlayer = new MediaPlayer();
//                mediaPlayer.setOnPreparedListener(this);
//                mediaPlayer.setOnSeekCompleteListener(this);
//                mediaPlayer.setOnCompletionListener(this);
//                mediaPlayer.setOnErrorListener(this);
//                currentStatus = idleStatus;
//            }else if (errorStatus.equals(currentStatus)){
//                mediaPlayer.reset();
//                currentStatus =idleStatus;
//            }
//            else if (idleStatus.equals(currentStatus)) {
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setDataSource(url);
//                Log.d("setDataSource","mediaPlayer is start");
//                mediaPlayer.prepareAsync();
//            }else if (pauseStatus.equals(currentStatus)){
//                mediaPlayer.stop();
//                currentStatus = stopStatus;
//                mediaPlayer.reset();
//                currentStatus = idleStatus;
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setDataSource( url) ;
//                mediaPlayer.prepareAsync();
//                currentStatus = startStatus;
//            } else if (mediaPlayer.isPlaying()) {
//                Log.d("setDataSource","mediaPlayer is Playing");
//
//                mediaPlayer.stop();
//                currentStatus = stopStatus;
//                mediaPlayer.reset();
//                currentStatus = idleStatus;
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setDataSource( url) ;
//                mediaPlayer.prepareAsync();
//                currentStatus = startStatus;
//            } else if(startStatus.equals(currentStatus)){
//                mediaPlayer.stop();
//                currentStatus = stopStatus;
//                mediaPlayer.reset();
//                currentStatus = idleStatus;
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setDataSource( url) ;
//                mediaPlayer.prepareAsync();
//                currentStatus = startStatus;
//            } else if(stopStatus.equals(currentStatus)){
//                mediaPlayer.reset();
//                currentStatus = idleStatus;
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setDataSource( url) ;
//                mediaPlayer.prepareAsync();
//                currentStatus = startStatus;
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return "UnsupportedEncodingException" + e.getMessage();
//        } catch (IOException e){
//            e.printStackTrace();
//            return "IOException" + e.getMessage();
//        } catch (IllegalStateException e){
//            return "IllegalStateException "+ e.getMessage();
//        }
//
//        catch ( Exception e){
//            e.printStackTrace();
//            return "Exception" +  e.getMessage();
//        }
//        return "success";
//    }

    public void startOrPause(){
        if(mediaPlayer!= null){

            switch ( currentStatus ){
                case startStatus:
                    mediaPlayer.pause();
                    currentStatus = pauseStatus;
                    break;
                case pauseStatus:
                    mediaPlayer.start();
                    currentStatus = startStatus;
                    break;
            }
        }
    }


//    public Notification createNotification(final String title,final String subTitle,Bitmap albumArt ){
//        return  createNotification(title,subTitle,albumArt,true ,"");
//    }
//
//    public Notification createNotification(final String title,final String subTitle,String albumArt ){
//        return  createNotification(title,subTitle,albumArt,true );
//    }

//    public Notification createNotification(final String title,final String subTitle,Bitmap albumArt ,boolean isNextStatusPause,String url){
//        Log.d(TAG,"createNotification : is next status pause? " + isNextStatusPause);
//
//        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this);
//
//        Bitmap bitmap = null;
//
//        bitmap = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.no_image);
//
//        RemoteViews smallViews = new RemoteViews(getPackageName(),R.layout.small_music_controller);
//        RemoteViews bigViews = new RemoteViews(getPackageName(),R.layout.big_music_controller);
//
//
//        PendingIntent playAndPausePendingIntent;
//        Intent playAndPauseIntent;
//        if( isNextStatusPause ){
//
//            smallViews.setViewVisibility( R.id.layoutPlay, View.INVISIBLE);
//            smallViews.setViewVisibility( R.id.layoutPause, View.VISIBLE);
//
//            playAndPauseIntent = new Intent( PLAY_AND_PAUSE_ACTION );
//            playAndPauseIntent.setPackage( this.getPackageName() );
//
//            playAndPausePendingIntent = PendingIntent.getBroadcast( this , REQUEST_CODE, playAndPauseIntent ,PendingIntent.FLAG_CANCEL_CURRENT) ;
//
//            smallViews.setOnClickPendingIntent( R.id.pause,playAndPausePendingIntent );
//            smallViews.setOnClickPendingIntent( R.id.layoutPlay,playAndPausePendingIntent );
//
//
//        }else {
//
//            smallViews.setViewVisibility( R.id.layoutPlay, View.VISIBLE);
//            smallViews.setViewVisibility( R.id.layoutPause, View.INVISIBLE);
//
//            playAndPauseIntent = new Intent( PLAY_AND_PAUSE_ACTION );
//            playAndPauseIntent.setPackage( this.getPackageName() );
//
//            playAndPausePendingIntent = PendingIntent.getBroadcast( this , REQUEST_CODE, playAndPauseIntent ,PendingIntent.FLAG_CANCEL_CURRENT) ;
//
//            smallViews.setOnClickPendingIntent( R.id.play,playAndPausePendingIntent );
//            smallViews.setOnClickPendingIntent( R.id.layoutPause,playAndPausePendingIntent );
//        }
//
//        Intent nextWindIntent = new Intent (NEXT_WIND_ACTION);
//        nextWindIntent.setPackage( this.getPackageName() );
//        PendingIntent pendingNextWindIntent = PendingIntent.getBroadcast( this , REQUEST_CODE, nextWindIntent ,PendingIntent.FLAG_CANCEL_CURRENT) ;
//
//        smallViews.setOnClickPendingIntent( R.id.layoutFastWind,pendingNextWindIntent );
//        smallViews.setOnClickPendingIntent( R.id.fastWind,pendingNextWindIntent );
//
//
//        currentTitle = title;
//        currentSubTitle = subTitle;
//        smallViews.setTextViewText( R.id.textTitle, currentTitle);
//        smallViews.setTextViewText( R.id.textSubTitle, currentSubTitle);
//
//
//        if( albumArt == null ) {
//            albumArt = BitmapFactory.decodeResource(getResources(), R.drawable.no_image);
//            Log.d(TAG,"albumArt is NULL...");
//
//        }
//        smallViews.setImageViewBitmap( R.id.albumArt, albumArt );
//
//        notiBuilder.setSmallIcon(R.drawable.ic_notification)
//                .setVisibility(android.support.v7.app.NotificationCompat.VISIBILITY_PUBLIC)
//                .setUsesChronometer(true)
//                .setCustomContentView(smallViews)
//                .setLargeIcon(bitmap);
//
//        return notiBuilder.build();
//    }


//    public Notification createNotification(final String title,final String subTitle,String albumArtUrl ,boolean isNextStatusPause){
//        Log.d(TAG,"createNotification : is next status pause? " + isNextStatusPause);
//
//        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this);
//
//        Bitmap defaultbitmap = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.no_image);
//
//        RemoteViews smallViews = new RemoteViews(getPackageName(),R.layout.small_music_controller);
//        RemoteViews bigViews = new RemoteViews(getPackageName(),R.layout.big_music_controller);
//
//
//        PendingIntent playAndPausePendingIntent;
//        Intent playAndPauseIntent;
//        if( isNextStatusPause ){
//
//            smallViews.setViewVisibility( R.id.layoutPlay, View.INVISIBLE);
//            smallViews.setViewVisibility( R.id.layoutPause, View.VISIBLE);
//
//            playAndPauseIntent = new Intent( PLAY_AND_PAUSE_ACTION );
//            playAndPauseIntent.setPackage( this.getPackageName() );
//
//            playAndPausePendingIntent = PendingIntent.getBroadcast( this , REQUEST_CODE, playAndPauseIntent ,PendingIntent.FLAG_CANCEL_CURRENT) ;
//
//            smallViews.setOnClickPendingIntent( R.id.pause,playAndPausePendingIntent );
//            smallViews.setOnClickPendingIntent( R.id.layoutPlay,playAndPausePendingIntent );
//
//
//        }else {
//
//            smallViews.setViewVisibility( R.id.layoutPlay, View.VISIBLE);
//            smallViews.setViewVisibility( R.id.layoutPause, View.INVISIBLE);
//
//            playAndPauseIntent = new Intent( PLAY_AND_PAUSE_ACTION );
//            playAndPauseIntent.setPackage( this.getPackageName() );
//
//            playAndPausePendingIntent = PendingIntent.getBroadcast( this , REQUEST_CODE, playAndPauseIntent ,PendingIntent.FLAG_CANCEL_CURRENT) ;
//
//            smallViews.setOnClickPendingIntent( R.id.play,playAndPausePendingIntent );
//            smallViews.setOnClickPendingIntent( R.id.layoutPause,playAndPausePendingIntent );
//        }
//
//        Intent nextWindIntent = new Intent (NEXT_WIND_ACTION);
//        nextWindIntent.setPackage( this.getPackageName() );
//        PendingIntent pendingNextWindIntent = PendingIntent.getBroadcast( this , REQUEST_CODE, nextWindIntent ,PendingIntent.FLAG_CANCEL_CURRENT) ;
//
//        smallViews.setOnClickPendingIntent( R.id.layoutFastWind,pendingNextWindIntent );
//        smallViews.setOnClickPendingIntent( R.id.fastWind,pendingNextWindIntent );
//
//
//        currentTitle = title;
//        currentSubTitle = subTitle;
//        smallViews.setTextViewText( R.id.textTitle, currentTitle);
//        smallViews.setTextViewText( R.id.textSubTitle, currentSubTitle);
//
//
//
////        Picasso.setSingletonInstance();
//
///*
//            albumArt = BitmapFactory.decodeResource(getResources(), R.drawable.no_image);
//            Log.d(TAG,"albumArt is NULL...");
//*/
//
//
////        smallViews.setImageViewBitmap( R.id.albumArt, albumArt );
//
//        notiBuilder.setSmallIcon(R.drawable.ic_notification)
//                .setVisibility(android.support.v7.app.NotificationCompat.VISIBILITY_PUBLIC)
//                .setUsesChronometer(true)
//                .setCustomContentView(smallViews);
////                .setLargeIcon(bitmap);
//
//        Picasso.with(this).load( albumArtUrl).into( smallViews, R.id.albumArt,NOTIFICATION_ID, notiBuilder.build());
//        return notiBuilder.build();
//    }


    public Bitmap drawableToBitmap(Drawable drawable){

        Bitmap inputBitmap = null;
        Canvas canvas  = null;
        if( drawable != null){

            if( drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0){
                inputBitmap = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
            }else {
                inputBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight() ,Bitmap.Config.ARGB_8888 );
            }
            canvas = new Canvas( inputBitmap);
            drawable.setBounds(0,0,canvas.getWidth(),canvas.getWidth());
            drawable.draw(canvas);
        }



        return inputBitmap;
    }
}
