package group.sample.advanced.rrk.com.advancedsamplegroupapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;

public class MainListActivity extends BaseActivity{


    @BindView(R.id.rvSampleList)
    RecyclerView rvSampleList;

    SampleListAdapter sampleListAdapter;

    ArrayList<SampleItem> sampleItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);


        ButterKnife.bind(this);

        sampleListAdapter = new SampleListAdapter(this, (SampleItem[]) sampleItems.toArray() );

    }
}
