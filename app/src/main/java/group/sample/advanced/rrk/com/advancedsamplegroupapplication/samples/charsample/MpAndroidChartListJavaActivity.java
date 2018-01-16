package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;

public class MpAndroidChartListJavaActivity extends AppCompatActivity implements SampleListAdapter.ItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    SampleListAdapter sampleListAdapter;

    List<SampleItem> sampleItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_android_chart_list_java);


        ButterKnife.bind(this );


//        sampleItems.add( new SampleItem(IdentifyActivity.class,"Application 고유 ID 와 device의 고유 ID"));
//        sampleItems.add( new SampleItem(OpenViewPagerActivity.class,"뷰페이저 테스트를위한 페이지"));
//        sampleItems.add( new SampleItem(GetAllOfMusicItemsActivity.class,"음악 아이템을 모아놓음"));
//        sampleItems.add( new SampleItem(TextInputLayoutCustomActivity.class,"TextInput Custom 예제 "));
        sampleItems.add( new SampleItem(LinearChartOneActivity.class,"TextInput Custom 예제 "));
        sampleItems.add( new SampleItem(BarChartActivity.class,"BarChart 예제 .. "));
        sampleItems.add( new SampleItem(SampleDayOfKW.class,"어떤 해가 주어지면 일정한 month가 나오겟찌  "));


        sampleListAdapter = new SampleListAdapter(this, sampleItems , this  );


        recyclerView.setAdapter( sampleListAdapter );

        recyclerView.setLayoutManager( new LinearLayoutManager(this ));
    }

    @Override
    public void ItemClicked(int position) {
        Intent i = new Intent(this, sampleItems.get(position).getClazz() );
        startActivity( i ) ;
    }

    @Override
    public void deleteClicked(int position) {

    }
}
