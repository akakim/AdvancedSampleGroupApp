package group.sample.advanced.rrk.com.advancedsamplegroupapplication;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;
import com.afollestad.inquiry.Inquiry;
import com.afollestad.inquiry.InquiryBase;
import com.afollestad.inquiry.callbacks.GetCallback;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter.PhotoGridAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.Photo;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.utils.Prefs;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.utils.Utils;
import static group.sample.advanced.rrk.com.advancedsamplegroupapplication.utils.Utils.log;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.ColorCircleView;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class MainActivityJava extends AppCompatActivity implements DragSelectRecyclerViewAdapter.SelectionListener{

    private static final int PERMISSION_RC = 69;
    private static final int BROWSE_RC = 21;


    PhotoGridAdapter adapter;

    @BindView(R.id.appbar_toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;


    @BindView(R.id.affixButton)
    Button btnAffix;

    @BindView(R.id.settingsFrame)
    ViewGroup settingsFrame;

    @BindView(R.id.empty)
    TextView empty;

    @BindView(R.id.stackHorizontallyLabel)
    TextView stackHorizontallyLabel;

    @BindView(R.id.stackHorizontallySwitch)
    CheckBox stackHorizontallyCheck;

    @BindView(R.id.bgFillColorCircle)
    ColorCircleView bgFillColorCircle;

    @BindView(R.id.bgFillColorLabel)
    TextView bgFillColorLabel;

    @BindView(R.id.imagePaddingLabel)
    TextView imagePaddingLabel;

    @BindView(R.id.removeBgButton)
    Button removeBgFillBtn;

    @BindView(R.id.scalePriorityLabel)
    TextView scalePriorityLabel;

    @BindView(R.id.scalePrioritySwitch)
    CheckBox scalePrioritySwitch;

    private int traverseIndex;
    private boolean autoSelectFirst;

    private int originalSettingsFrameHeight = -1;
    private ValueAnimator settingsFrameAnimator;

    private Unbinder unbinder;

    private Photo[] selectedPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        recyclerView = ( RecyclerView ) findViewById( R.id.recyclerView);

        unbinder = ButterKnife.bind( this );
        Inquiry.newInstance(this, null).build();

        toolbar.inflateMenu( R.menu.menu_main );
        // 원형
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                return false;
//            }
//        });
        toolbar.setOnMenuItemClickListener(
                item -> {
                    if (item.getItemId() == R.id.clear) {
//                        clearSelection();
                        return true;
                    } else if (item.getItemId() == R.id.about) {
//                        AboutDialog.show(MainActivity.this);
                        return true;
                    }else {
                        return false;
                    }
                }
        );

        recyclerView.setLayoutManager (
                new GridLayoutManager(
                this,
                        getResources().getInteger(R.integer.grid_width))
        );

        adapter = new PhotoGridAdapter(this);
        adapter.restoreInstanceState(savedInstanceState);
        adapter.setSelectionListener( this );

        recyclerView.setAdapter( adapter );

        //TODO : 어떤 영향인가..
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setSupportsChangeAnimations( false );
        recyclerView.setItemAnimator(animator );


//        final boolean stackHorizontally = Prefs.stackHorizontally(this);
//        stackHorizontallyCheck.setChecked(stackHorizontally);
//        stackHorizontallyLabel.setText(
//                stackHorizontally ? R.string.stack_horizontally : R.string.stack_vertically);
//
//        final boolean scalePriority = Prefs.scalePriority(this);
//        scalePrioritySwitch.setChecked(scalePriority);
//        scalePriorityLabel.setText(
//                scalePriority ? R.string.scale_priority_on : R.string.scale_priority_off);
//
//        final int bgFillColor = Prefs.bgFillColor(this);
//        bgFillColorCircle.setColor(bgFillColor);
//        final int[] padding = Prefs.imageSpacing(this);
//        imagePaddingLabel.setText(getString(R.string.image_spacing_x, padding[0], padding[1]));
//
//        if (bgFillColor != Color.TRANSPARENT) {
//            removeBgFillBtn.setVisibility(View.VISIBLE);
//            bgFillColorLabel.setText(R.string.background_fill_color);
//        } else {
//            bgFillColorLabel.setText(R.string.background_fill_color_transparent);
//        }

//        processIntent( getIntent() );
    }


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onDragSelectionChanged(int count) {

    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        processIntent( intent );
    }

    /**
     * 이미지 선택한 것을 fullsize로 볼때 이용하는것 미구현 .
     * @param intent
     */
    private void processIntent(Intent intent){
        if( intent != null && Intent.ACTION_SEND_MULTIPLE.equals( intent.getAction() )){
            ArrayList<Uri> uris = intent.getParcelableArrayListExtra( Intent.EXTRA_STREAM );

            if(uris != null && uris.size() >1 ){
                selectedPhotos = new Photo[uris.size()];
                for(int i = 0; i< uris.size(); i++){
                    selectedPhotos[i] = new Photo(uris.get(i));

                }

                beginProcessing();
            }else {
                Toast.makeText( this, R.string.need_two_or_more,Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void beginProcessing(){

        // 중복 클릭 방지
        btnAffix.setEnabled( false );

        try{
            startProcessing();
        }catch (OutOfMemoryError error){
            Utils.showMemoryError(MainActivityJava.this);
        }

        btnAffix.setEnabled( true );

    }

    private void startProcessing(){

        // Lock orientation so the Activity won't change configuration during processing
        // 연산을 하는 동안 회전을 멈춘다. 아.. 그러고보니 회전을 하게 되면 라이프사이클 다시 돌게 되니까 그런거같다.

        Utils.lockOrientation( this );


        final int [] imageSpacing = Prefs.imageSpacing(MainActivityJava.this);
        final int spacingHorizontal = imageSpacing[ 0 ];
        final int spacingVertical = imageSpacing[ 1 ];

        final boolean horizontal = stackHorizontallyCheck.isChecked();

        int resultWidth;
        int resultHeight;

        log(this.getClass().getSimpleName(),"------");

        if( horizontal ){

            log(this.getClass().getSimpleName(),"HorizontalStacking");

            // the width of the resulting image will be the largest width of the selected images
            // the height of the resulting image will be the sum of all the selected images's heights;

            int maxHeight = -1;
            int minHeight = -1;

            // traverse all selected images to find largest and smallest height
            traverseIndex = -1;

            int [] size ;
//            while (size != getNextBitmapSize() ! =null)
        }

    }


//    @Size(2)
//    private int [] getNextBitmapSize(){
//        if (selectedPhotos == null || selectedPhotos.length == 0){
//            selectedPhotos = adapter
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (adapter != null) {
            adapter.saveInstanceState(outState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
        unbinder = null;
    }


    private void refresh(){
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_RC);
            return;
        }


        // no inspection for visible for Tests

        Inquiry.get(this)
                .selectFrom(Uri.parse("content://media/external/images/media"), Photo.class)
                .sort("datetaken DESC")
                .where("_data IS NOT NULL")
                .all(
//                        photos -> {
//                            if (isFinishing()) {
//                                return;
//                            }
//                            if (empty != null) {
//                                adapter.setPhotos(photos);
//                                empty.setVisibility(
//                                        photos == null || photos.length == 0 ? View.VISIBLE : View.GONE);
//                                if (photos != null && photos.length > 0 && autoSelectFirst) {
////                                    adapter.shiftSelections();
//                                    adapter.setSelected(1, true);
//                                    autoSelectFirst = false;
//                                }
//                            }
//                        }
//
                        new GetCallback<Photo>() {

                            @Override
                            public void result(@Nullable Photo[] result) {
                                if (isFinishing()) {
                                return;
                            }
                            if (empty != null) {
                                adapter.setPhotos(result);
                                empty.setVisibility(
                                        result == null || result.length == 0 ? View.VISIBLE : View.GONE);
                                if (result != null && result.length > 0 && autoSelectFirst) {
//                                    adapter.shiftSelections();
                                    adapter.setSelected(1, true);
                                    autoSelectFirst = false;
                                }
                            }
                            }
                        }
                );
    }
}
