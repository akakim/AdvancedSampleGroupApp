package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle.MainParseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;


public class CoordinatorSamples extends BaseActivity implements SampleListAdapter.ItemClickListener {


    @BindView(R.id.rvSampleList)
    RecyclerView rvSampleList;

    SampleListAdapter sampleListAdapter;

    List<SampleItem> sampleItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        ButterKnife.bind(this);


        sampleItems.add(new SampleItem(FabAndSnackBarActivity.class,"FabAndSnackBarActivity"));
        sampleItems.add(new SampleItem(FabScrollBehavior.class,"FabScrollBehavior"));

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
