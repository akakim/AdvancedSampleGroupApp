package group.sample.advanced.rrk.com.advancedsamplegroupapplication;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.design.widget.CoordinatorLayout;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle.MainParseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SingleChoice;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.MpAndroidChartListJavaActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.database.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.service.DummyService;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.util.RecyclerItemDeleteItem;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.CustomAlertDialog;
import io.fabric.sdk.android.Fabric;
import rrk.dev.andcodephilo.activity.TestWidgetActivity;

public class MainListActivity extends BaseActivity implements SampleListAdapter.ItemClickListener ,RecyclerItemDeleteItem.ItemTouchListener{


    @BindView(R.id.rvSampleList)
    RecyclerView rvSampleList;

    SampleListAdapter sampleListAdapter;

    List<Object> sampleItems = new ArrayList<>();


    List<Object> sampleInitItems = new ArrayList<>();

    @BindView( R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;


    ServiceConnection serviceConnection;
    Messenger remoteMessenger;
    IntentFilter filter;

    Handler serviceHandler;

    View view;

    /** 서비스에서 activity로 통신하기 위한 인스턴스 */
    private BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Fabric.with(this, new Crashlytics());

        ButterKnife.bind(this);

        sampleInitItems.add( new SampleItem(IdentifyActivity.class,"Application 고유 ID 와 device의 고유 ID"));
//        sampleItems.add( new SampleItem(OpenViewPagerActivity.class,"뷰페이저 테스트를위한 페이지"));
        sampleInitItems.add( new SampleItem(GetAllOfMusicItemsActivity.class,"음악 아이템을 모아놓음"));
        sampleInitItems.add( new SampleItem(TextInputLayoutCustomActivity.class,"TextInput Custom 예제 "));
        sampleInitItems.add( new SampleItem(MpAndroidChartListJavaActivity.class,"MPAndroidChart 예제들"));
        sampleInitItems.add( new SampleItem(MvpGroupActivity.class,"MVP로 구현한 예제"));
        sampleInitItems.add( new SampleItem(TypeFaceActivity.class,"폰트 라이브러리 구현 예제 "));
        sampleInitItems.add( new SampleItem(WebViewTesterActivity.class,"웹뷰 테스터 "));
        sampleInitItems.add( new SampleItem(RSSActivity.class,"RSS 테스터 "));
        sampleInitItems.add( new SampleItem(JSoupActivity.class,"JSoup HTML 파서 . "));
        sampleInitItems.add( new SampleItem(CrashActivity.class,"firebase Crash 보고서  "));
        sampleInitItems.add( new SampleItem(MainParseActivity.class,"MainParse Activity cradle의 시작 "));
        sampleInitItems.add( new SampleItem(CoordinatorSamples.class,"CoordinatorSample 예제들 Activity"));
        sampleInitItems.add( new SingleChoice( " 오버레이 권한을 이용한 pop창 예제 "));
        sampleInitItems.add( new SampleItem( ViewPagerActivity.class ," 뷰페이저를 안에 RecyclerView를 적용 , Listview로  응용하면 onMeasure 에서 뷰의 크기를 측정한다. "));
        sampleInitItems.add( new SampleItem( TestWidgetActivity.class ," 뷰페이저를 안에 RecyclerView를 적용 , Listview로  응용하면 onMeasure 에서 뷰의 크기를 측정한다. "));
        sampleInitItems.add( new SampleItem( BottomRefreshActivity.class ,"Bottom Refresh Layout.... " ));
        sampleInitItems.add( new SampleItem( CustomViewActivity.class ,"CustomWidgetView 현재 다이얼로그를 위한 뷰생성됨. " ));
        sampleInitItems.add( new SampleItem( ChronometerTimerExample.class ,"타이머 설정" ));
        sampleInitItems.add( new SampleItem( CurrentTimeActivity.class ,"현재 시간 알아내는 예제 " ));
        sampleInitItems.add( new SampleItem( ScrollStickActivity.class ," 딱달라붙는 해더 뷰 " ));
        sampleInitItems.add( new SampleItem( VideoViewActivity.class ," VideoView Sample " ));
        sampleInitItems.add( new SampleItem( DynamicButtonCreateActivity.class ," DynamicButtonCreateActivity 서버에서 어떻게하면 좌표를 뿌려줄까?  " ));



        //TODO 구현
        /**
         *  InputMethodManager inputManager = (InputMethodManager) getSystemService( INPUT_METHOD_SERVICE );
         //                    inputManager.hideSoftInputFromWindow(edId.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
         //                    inputManager.hideSoftInputFromWindow(edPassword.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
         */

        sampleItems.addAll(sampleInitItems);
        sampleListAdapter = new SampleListAdapter(this, sampleItems , this  );


        rvSampleList.setAdapter( sampleListAdapter );
        normalDecoration = new NormalDecoration( this );
//        normalDecoration.setDividerColor(0xffff00);


        rvSampleList.setLayoutManager( new LinearLayoutManager(this ));
        rvSampleList.setItemAnimator(new DefaultItemAnimator());
        rvSampleList.addItemDecoration( normalDecoration);
        rvSampleList.setHasFixedSize( true );
        rvSampleList.setNestedScrollingEnabled( false );

        serviceHandler = new Handler();
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                remoteMessenger     = new Messenger(service);

                if( remoteMessenger == null) {
                    Log.e(getClass().getSimpleName(),"onServiceConnected Messenger is null ");
                }else {
                    Log.d(getClass().getSimpleName(),"onServiceConnected Messenger not null");
//                    Message msg = Message.obtain();
//                    msg.what = 0;
//                    msg.obj = new Messenger(serviceHandler);
//                    try {
//                        remoteMessenger.send(msg);
//                    } catch (RemoteException r) {
//                        r.printStackTrace();
//                    }
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                remoteMessenger = null;
//                serviceHandler = null;
            }
        };

        Intent demoService=  new Intent(this,DummyService.class);


        bindService(demoService, serviceConnection  , Context.BIND_AUTO_CREATE  );

        /**
         * Swipe dismiss 예제 구현 . ItemTouch Listner를 지원받아서 하는데 swipe를 하는순간 취소 혹은 삭제를 보여줄 수 없다.
         * swip를 하는 순간 바로 지워 지고 어떻게 제어를 할 수 가 없다.
         *
         */
//        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemDeleteItem(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
//
//
//        new ItemTouchHelper( itemTouchHelperCallback ).attachToRecyclerView(rvSampleList);
//
//        rvSampleList.addItemDecoration(new RecyclerView.ItemDecoration() {
//            // we want to cache this and not allocate anything repeatedly in the onDraw method
//            Drawable background;
//            boolean initiated;
//
//            private void init() {
//                background = new ColorDrawable(Color.WHITE);
//                initiated = true;
//            }
//
//            @Override
//            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                if (!initiated) {
//                    init();
//                }
//
//                // only if animation is in progress
//                if (parent.getItemAnimator().isRunning()) {
//                    // some items might be animating down and some items might be animating up to close the gap left by the removed item
//                    // this is not exclusive, both movement can be happening at the same time
//                    // to reproduce this leave just enough items so the first one and the last one would be just a little off screen
//                    // then remove one from the middle
//
//                    // find first child with translationY > 0
//                    // and last one with translationY < 0
//                    // we're after a rect that is not covered in recycler-view views at this point in time
//                    View lastViewComingDown = null;
//                    View firstViewComingUp = null;
//                    // this is fixed
//                    int left = 0;
//                    int right = parent.getWidth();
//
//                    // this we need to find out
//                    int top = 0;
//                    int bottom = 0;
//                    // find relevant translating views
//                    int childCount = parent.getLayoutManager().getChildCount();
//                    for (int i = 0; i < childCount; i++) {
//                        View child = parent.getLayoutManager().getChildAt(i);
//                        if (child.getTranslationY() < 0) {
//                            // view is coming down
//                            lastViewComingDown = child;
//                        } else if (child.getTranslationY() > 0) {
//                            // view is coming up
//                            if (firstViewComingUp == null) {
//                                firstViewComingUp = child;
//                            }
//                        }
//                    }
//
//                    if (lastViewComingDown != null && firstViewComingUp != null) {
//                        // views are coming down AND going up to fill the void
//                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
//                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
//                    } else if (lastViewComingDown != null) {
//                        // views are going down to fill the void
//                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
//                        bottom = lastViewComingDown.getBottom();
//                    } else if (firstViewComingUp != null) {
//                        // views are coming up to fill the void
//                        top = firstViewComingUp.getTop();
//                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
//                    }
//
//                    background.setBounds(left, top, right, bottom);
//                    background.draw(c);
//
//                    super.onDraw(c, parent, state);
//                }
//            }
//        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();
                switch ( action ){
                    case DummyService.SHOW_ALERT_POPUP_ACTION:
                        initWindowLayout();
                        break;
                    default:
                        Log.d(getClass().getSimpleName(),"get DefaultAction");
                        break;
                }
            }
        };

        filter= new IntentFilter();
        filter.addAction( DummyService.SHOW_ALERT_POPUP_ACTION );
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver( broadcastReceiver, filter);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        if ( broadcastReceiver != null ){
            unregisterReceiver( broadcastReceiver);
        }
        if (serviceConnection != null ) {
            unbindService(serviceConnection);
        }
        destroyWindowLayout();

        if( serviceHandler != null){
            serviceHandler = null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(getClass().getSimpleName(),"onsavedIstnae State" + outState.toString() );

        if( sampleListAdapter != null){
            sampleListAdapter.saveState(outState );
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(getClass().getSimpleName(),"onRestoreInstanceState " + savedInstanceState.toString() );

        if( sampleListAdapter != null){
            sampleListAdapter.restoreState(savedInstanceState );
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void ItemClicked(int position) {


        if( sampleItems.get(position ) instanceof SampleItem ){

            Intent i = new Intent(this, ((SampleItem) sampleItems.get(position)).getClazz());
            startActivity( i ) ;
        }else if( sampleItems.get(position ) instanceof SingleChoice ){

            Message msg = Message.obtain();

            msg.what = DummyService.SHOW_ALERT_POPUP;
            msg.obj = "dummy Text ";
            remoteMessage( msg );

        }

    }

    @Override
    public void cancelClicked(int position) {
//        sampleListAdapter.switchView(position);
    }

    @Override
    public void deleteClicked(int position) {
        sampleItems.remove(position);
        rvSampleList.getAdapter().notifyItemRemoved(position);
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder holder, int direction, int position) {

        if( holder instanceof SampleListAdapter.SampleViewHolder){

            /**
             * example 1
             */

//            sampleListAdapter.switchView( position );
//            // get the removed item name to display it is snack bar
//            String name = sampleItems.get(holder.getAdapterPosition()).getClazz().getSimpleName();
//
//            // backup of removed item for undo purpose
//
//            final SampleItem deleteItem = sampleItems.get(holder.getAdapterPosition());
//            final int deletedIndex = holder.getAdapterPosition();
//
//            // remove the item from recycler view
//            sampleListAdapter.removeItem(holder.getAdapterPosition());
//
//            // showing snack bar with undo option
//            Snackbar snackbar =
//                    Snackbar.make(coordinatorLayout,name +" removed from Activity List",Snackbar.LENGTH_LONG)
//                    .setAction("UNDO",new View.OnClickListener(){
//                        @Override
//                        public void onClick(View v) {
//                            sampleListAdapter.restoreItem( deleteItem,deletedIndex);
//                        }
//                    });
//
//            snackbar.setActionTextColor(Color.BLUE);
//            snackbar.show();

//            final SampleItem deleteItem = sampleItems.get(holder.getAdapterPosition());
//            final int deletedIndex = holder.getAdapterPosition();
//
//
//            sampleListAdapter.removeItem(holder.getAdapterPosition());
//            sampleListAdapter.notifyItemChanged(deletedIndex);
        }
    }

    @OnClick(R.id.btnShowPopup)
    public void onClick(){

//        CustomAlertDialog customAlertDialog =  new CustomAlertDialog(this);
        new CustomAlertDialog(this).setMessage("알림","네트워크 오류가 발생했습니다.\n 관리자에게 문의하세요").show();
    }
    /**
     * 서비스로 음악의 상태를 변경하는 메세지를 보내는 메소드
     * @param msg  음악의 상태값과 음악 아이템의 정보가 담긴 message
     * @throws RemoteException
     */
    public void remoteMessage(Message msg) {
        try {
            if (remoteMessenger != null) {
                remoteMessenger.send(msg);
            } else {
                Log.e(getClass().getSimpleName(), "remote object is null");

            }
        }catch ( RemoteException e ){
            e.printStackTrace();
        }
    }


    public void initWindowLayout (){


        if (view == null){

            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_window_view,null,false);
        }

        // 희안하네 권한을 분명 줬는데 WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY -> WindowManager.LayoutParams.TYPE_PHONE을 줘야만한다.
        // 왜 Deprecate가 됬지 ???
        WindowManager.LayoutParams windowLayoutParams = new WindowManager.LayoutParams(

                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT );

        windowLayoutParams.gravity = Gravity.CENTER;

        WindowManager windowManager = (WindowManager) getSystemService( WINDOW_SERVICE );

        if( view != null ) {
            windowManager.addView(view, windowLayoutParams);
        }
    }

    public void destroyWindowLayout(){
        WindowManager windowManager = (WindowManager) getSystemService( WINDOW_SERVICE );


        if( view != null ) {
            windowManager.removeViewImmediate(view);
        }

//        windowManager.addView( view, layoutParams);
    }
}
