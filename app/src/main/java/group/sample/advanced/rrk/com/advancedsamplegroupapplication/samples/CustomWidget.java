package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2018-01-23.
 */

public class CustomWidget extends AppWidgetProvider {

    public static final String ACTION_GET_EVENT =  "rrk.com.samples.customWidget.ACTION_GET_EVENT";
    public static final String ACTION_SHOWTOAST =  "rrk.com.samples.customWidget.ACTION_SHOWTOAST";
    public static final String ACTION_DIALOG =  "rrk.com.samples.customWidget.ACTION_DIALOG";


    public static final int REQUEST_SIMPLE_METHOD= 1000;
    private static final String TAG = "CustomWidget";
    private Context context;
    RemoteViews views;

    /**
     * BroadCastReciever를 이용하여 구현하기 때문에 , 대부분의 기능을 여기서 구현하게 된다.
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();

        Log.d(getClass().getSimpleName(),"action : " + action);

        switch ( action){
            case AppWidgetManager.ACTION_APPWIDGET_ENABLED:
                break;
            case AppWidgetManager.ACTION_APPWIDGET_UPDATE:
                AppWidgetManager manager = AppWidgetManager.getInstance(context);
                initUI( context, manager,manager.getAppWidgetIds( new ComponentName( context, getClass())));
                break;
            case AppWidgetManager.ACTION_APPWIDGET_DELETED:
                break;
            case ACTION_DIALOG:
//                AppWidgetManager managerActionDialog = AppWidgetManager.getInstance(context);
//                managerActionDialog.
//                views.setTextViewText(R.id.tvResult,"DIALOG IS SHOIWNG! ");

                updateUI(context, AppWidgetManager.getInstance(context) ,AppWidgetManager.getInstance(context).getAppWidgetIds( new ComponentName( context, getClass())),"modified ... ");
                break;
            case ACTION_SHOWTOAST:
                Toast.makeText(context,"ACTION_SHOW TOAST Clicked",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * Meta Data에서 지정해준 updateperiod Millis 값에 따라 주기적으로 호출됩니다. 또한 처음 widget
     * 화면에 붙을 때 init() 자겅ㅂ을 해주기 위해 call 된다.
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        this.context = context;
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG,"==========onEnable()============");

        for( int k = 0; k<appWidgetIds.length;k++){
            int appWidgetId = appWidgetIds[k];
//            RemoteViews remoteViews = new RemoteViews( context.getPackageName(),R.layout.layout_app_widget );
            appWidgetManager.updateAppWidget(appWidgetId,views);
        }
    }

    /**
     * app widget이 widgeHost로 부터 삭제 될 때 호출됩ㄴ다.
     * @param context
     * @param appWidgetIds
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        Log.d(TAG,"onDelete()");
    }

    /**
     * App Widget을 처음 생성 될때 불려진다.  첫 위겟이 생성되면 호출되고 같은 위겟이
     * 더 추가 되면 더이상 호출되지 않는다.
     * @param context
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    void initUI(Context context,AppWidgetManager appWidgetManager,int[] appWidgetIds ){
        Log.d(TAG,"------initUI()-----");

        views = new RemoteViews(context.getPackageName(), R.layout.layout_app_widget );

        Intent getEventIntent  = new Intent( ACTION_GET_EVENT );
        Intent dialogIntent  = new Intent( ACTION_DIALOG );
        Intent showToastIntent  = new Intent( ACTION_SHOWTOAST );

        PendingIntent pendingIntentGetEventAction = PendingIntent.getBroadcast( context, REQUEST_SIMPLE_METHOD,getEventIntent, 0 );
        PendingIntent pendingIntentDialogIntent = PendingIntent.getBroadcast( context, REQUEST_SIMPLE_METHOD,dialogIntent, 0 );
        PendingIntent pendingIntentShowToastIntent= PendingIntent.getBroadcast( context, REQUEST_SIMPLE_METHOD,showToastIntent, 0 );

        views.setOnClickPendingIntent( R.id.btnGetEvent , pendingIntentGetEventAction );
        views.setOnClickPendingIntent( R.id.btnToast , pendingIntentDialogIntent );
        views.setOnClickPendingIntent( R.id.btnShowdialog , pendingIntentShowToastIntent );

        for(int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


    void updateUI(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds,String content ){
        Log.d(TAG,"------updateUI()-----");

        if (views == null ){
            Log.e(TAG,"views is nulll ");
            views = new RemoteViews(context.getPackageName(), R.layout.layout_app_widget );
        }

        views.setTextViewText( R.id.tvResult, content );
        for(int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}


