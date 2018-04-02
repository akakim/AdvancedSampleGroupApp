package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter.BottomEndlessRefreshAdapter;

public class BottomRefreshActivity extends AppCompatActivity implements BottomEndlessRefreshAdapter.OnLoadMoreListener{



    @BindView(R.id.rv)
    RecyclerView rv;

    BottomEndlessRefreshAdapter adapter ;

    ArrayList<String> titleList= new ArrayList<>();
    ArrayList<String> contentList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_refresh);


        ButterKnife.bind(this);
        for (int k = 0; k< 20 ; k++ ){
            titleList.add( k + " 번째 타이틀");
            contentList.add( " content : " + k + " kkk ");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this );
        adapter = new BottomEndlessRefreshAdapter( this, titleList,contentList,this);

        rv.setLayoutManager(linearLayoutManager);
        adapter.setLinearLayoutManager( linearLayoutManager);
        adapter.setRecyclerView( rv );
        rv.setAdapter( adapter );


    }


    @Override
    public void onLoadMore() {

        adapter.setProgressMore( true );


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                titleList.clear();
//                contentList.clear();
                adapter.setProgressMore(false);

                // 여기부터는 커스터마이징

                int start = adapter.getItemCount();
                int end = start + 15;

                ArrayList<String> tmpTitles = new ArrayList<>();
                ArrayList<String> tmpContent = new ArrayList<>();

                for (int i = start + 1; i <= end; i++) {

                    tmpTitles.add(i + "title... ");
                    tmpContent.add(i+ "content " );
                }


//                for(int k = start +1;)


                adapter.addAll(tmpTitles,tmpContent );
                adapter.setMoreLoading( false );
            }
        },2000);
    }
}
