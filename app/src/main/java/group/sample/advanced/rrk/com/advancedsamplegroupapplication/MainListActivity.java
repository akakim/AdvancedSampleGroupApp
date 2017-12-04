package group.sample.advanced.rrk.com.advancedsamplegroupapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.MpAndroidChartListJavaActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.database.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp.*;

public class MainListActivity extends BaseActivity implements SampleListAdapter.ItemClickListener {


    @BindView(R.id.rvSampleList)
    RecyclerView rvSampleList;

    SampleListAdapter sampleListAdapter;

    List<SampleItem> sampleItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);


        ButterKnife.bind(this);

        sampleItems.add( new SampleItem(IdentifyActivity.class,"Application 고유 ID 와 device의 고유 ID"));
        sampleItems.add( new SampleItem(OpenViewPagerActivity.class,"뷰페이저 테스트를위한 페이지"));
        sampleItems.add( new SampleItem(GetAllOfMusicItemsActivity.class,"음악 아이템을 모아놓음"));
        sampleItems.add( new SampleItem(TextInputLayoutCustomActivity.class,"TextInput Custom 예제 "));
        sampleItems.add( new SampleItem(MpAndroidChartListJavaActivity.class,"MPAndroidChart 예제들"));
        sampleItems.add( new SampleItem(MvpGroupActivity.class,"MVP로 구현한 예제"));


        sampleListAdapter = new SampleListAdapter(this, sampleItems , this  );


        rvSampleList.setAdapter( sampleListAdapter );

        rvSampleList.setLayoutManager( new LinearLayoutManager(this ));
    }

    @Override
    public void ItemClicked(int position) {
        Intent i = new Intent(this, sampleItems.get(position).getClazz() );
        startActivity( i ) ;
    }
}
