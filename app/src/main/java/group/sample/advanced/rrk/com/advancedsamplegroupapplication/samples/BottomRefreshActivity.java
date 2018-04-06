package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter.BottomEndlessRefreshAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget.FloodingPresenter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget.FloodingRecyclerView;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget.NetworkInteractor;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget.ViewData;

public class BottomRefreshActivity extends AppCompatActivity implements FloodingPresenter.LoadListener{



    @BindView(R.id.rv)
    RecyclerView rv;


    NetworkInteractor networkInteractor;


//    ArrayList<String> titleList= new ArrayList<>();
//    ArrayList<String> contentList= new ArrayList<>();

    ArrayList<SimpleData> simpleDataArrayList = new ArrayList<>();


    FloodingPresenter floodingRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_refresh);


        ButterKnife.bind(this);
        for (int k = 0; k< 20 ; k++ ){

            SimpleData data = new SimpleData();

            data.setViewType( ViewData.VIEW_ITEM );
            data.setTitle(k + " 번째 타이틀" );
            data.setContent(   " content : " + k + " kkk ");

            simpleDataArrayList.add( data ) ;
        }


        networkInteractor = new FloodingPresenter( rv, simpleDataArrayList ,this);


        /**
         *
         */
        //        구 코드
        //        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this );
        //        adapter = new BottomEndlessRefreshAdapter( this, titleList,contentList,this);
        //
        //        rv.setLayoutManager(linearLayoutManager);
        //        adapter.setLinearLayoutManager( linearLayoutManager);
        //        adapter.setRecyclerView( rv );
        //        rv.setAdapter( adapter );

        //        FloodingPresenter presenter = new FloodingPresenter( rv, )

        /**
         *
         */
    }

    @Override
    public void onLoad() {

        Log.d(getClass().getSimpleName()," onLoad()");

//        floodingRecyclerView.setMoreLoading( true );

        new Handler().postDelayed(new Runnable() {

                                      @Override
                                      public void run() {


                                          networkInteractor.onResponse();

                                          int start = simpleDataArrayList.size();
                                          int size = 20;

                                          for (int k = start; k < start + size; k++) {

                                              SimpleData data = new SimpleData();

                                              data.setViewType(ViewData.VIEW_ITEM);
                                              data.setTitle(k + " 번째 타이틀");
                                              data.setContent(" content : " + k + " kkk ");

                                              simpleDataArrayList.add(data);
                                          }



                                          networkInteractor.addItems( start,size);


                                      }
                                  },500);

    }

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//
//                int start =  rv.getAdapter().getItemCount();
//                int size = 20;
//                for (int k = rv.getAdapter().getItemCount(); k< rv.getAdapter().getItemCount() + 20 ; k++ ){
//
//                    SimpleData data = new SimpleData();
//
//                    data.setViewType( ViewData.VIEW_ITEM );
//                    data.setTitle(k + " 번째 타이틀" );
//                    data.setContent(   " content : " + k + " kkk ");
//
//                    simpleDataArrayList.add( data ) ;
//                }
//
//                rv.getAdapter().notifyItemRangeChanged( start,size );
//
//
//                floodingRecyclerView.onResponse();
//
//
                // 뭔가 처리할 게 있다면 더 처리한다.
//
//                for (int i = start + 1; i <= end; i++) {
//
//                    tmpTitles.add(i + "title... ");
//                    tmpContent.add(i+ "content " );
//                }
//
//
////                for(int k = start +1;)
//
//
//                adapter.addAll(tmpTitles,tmpContent );
//                adapter.setMoreLoading( false );

//            }
//        },2000);


    @Override
    public void onEndedLoad() {

    }

//  구 코드
//    @Override
//    public void onLoadMore() {
//
//        adapter.setProgressMore( true );
//
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                titleList.clear();
////                contentList.clear();
//                adapter.setProgressMore(false);
//
//                // 여기부터는 커스터마이징
//
//                int start = adapter.getItemCount();
//                int end = start + 15;
//
//                ArrayList<String> tmpTitles = new ArrayList<>();
//                ArrayList<String> tmpContent = new ArrayList<>();
//
//                for (int i = start + 1; i <= end; i++) {
//
//                    tmpTitles.add(i + "title... ");
//                    tmpContent.add(i+ "content " );
//                }
//
//
////                for(int k = start +1;)
//
//
//                adapter.addAll(tmpTitles,tmpContent );
//                adapter.setMoreLoading( false );
//            }
//        },2000);
//    }


    public class SimpleData implements ViewData{

        private int viewType;
        private String title;
        private String content;


        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        @Override
        public int getViewType() {
            return viewType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
