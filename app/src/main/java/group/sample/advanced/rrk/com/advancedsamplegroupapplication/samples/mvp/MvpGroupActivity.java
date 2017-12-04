package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;

public class MvpGroupActivity extends AppCompatActivity implements MainGroupView,SampleListAdapter.ItemClickListener {


    @BindView(R.id.rvSampleList)
    RecyclerView rvSampleList;


    private MainPresenter presenter;

    SampleListAdapter adapter;
    List<SampleItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_group);

        ButterKnife.bind(this);





        presenter = new MainPresenterImpl(this,new FindItmesInteractorImpl());
        adapter = new SampleListAdapter(this,list,this);

        rvSampleList.setAdapter( adapter);
        rvSampleList.setLayoutManager( new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setItem(List<SampleItem> Items) {

        Log.d(getClass().getSimpleName(),"setItem() itemsSize : " + Items.size() );
        list.clear();
        list.addAll( Items);
        adapter.notifyDataSet(Items);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    @Override
    public void showMessage() {
        Toast.makeText(this, " blank Message is showing ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(boolean isError, String Message) {

        if ( isError ){
            Toast.makeText(this,"error : " + Message,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,Message,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ItemClicked(int position) {
        presenter.onItemClicked(position);


        Intent i = new Intent(this, list.get(position).getClazz());
        startActivity( i );
    }
}
