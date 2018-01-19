package group.sample.advanced.rrk.com.advancedsamplegroupapplication;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle.MainParseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.MpAndroidChartListJavaActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.database.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.util.RecyclerItemDeleteItem;
import io.fabric.sdk.android.Fabric;

public class MainListActivity extends BaseActivity implements SampleListAdapter.ItemClickListener ,RecyclerItemDeleteItem.ItemTouchListener{


    @BindView(R.id.rvSampleList)
    RecyclerView rvSampleList;

    SampleListAdapter sampleListAdapter;

    List<SampleItem> sampleItems = new ArrayList<>();


    List<SampleItem> sampleInitItems = new ArrayList<>();

    @BindView( R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

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


        sampleItems.addAll(sampleInitItems);
        sampleItems.addAll(sampleInitItems);
        sampleListAdapter = new SampleListAdapter(this, sampleItems , this  );


        rvSampleList.setAdapter( sampleListAdapter );

        rvSampleList.setLayoutManager( new LinearLayoutManager(this ));
        rvSampleList.setItemAnimator(new DefaultItemAnimator());
        rvSampleList.setHasFixedSize( true );
//        rvSampleList.addOnItemTouchListener( this );
//        rvSampleList.addItemDecoration( );
        rvSampleList.setNestedScrollingEnabled( false );
//        rvSampleList.

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
        Intent i = new Intent(this, sampleItems.get(position).getClazz() );
        startActivity( i ) ;
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

            final SampleItem deleteItem = sampleItems.get(holder.getAdapterPosition());
            final int deletedIndex = holder.getAdapterPosition();


            sampleListAdapter.removeItem(holder.getAdapterPosition());
            sampleListAdapter.notifyItemChanged(deletedIndex);
        }
    }
}
