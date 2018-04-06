package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.BottomRefreshActivity;

/**
 * Created by RyoRyeong Kim on 2018-04-05.
 */

public class FloodingPresenter implements NetworkInteractor {


    private boolean                                        isMoreLoading = false;


    private RecyclerView                                  recyclerView;
    private FloodingAdapter                               adapter;
    private WrappingLayoutManager                         layoutManager;

    private LoadListener                                    loadListener;


    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount, lastVisibleItem;

    ArrayList<? extends ViewData> viewData;


    private Handler handler = new Handler();


    final RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int newState = recyclerView.getScrollState();
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING ){


                visibleItemCount    = recyclerView.getChildCount();
                totalItemCount      = layoutManager.getItemCount();
                firstVisibleItem    = layoutManager.findFirstCompletelyVisibleItemPosition();
                lastVisibleItem     = layoutManager.findLastVisibleItemPosition();


                if( !isMoreLoading && ( totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold) ){

                    isMoreLoading = true;

                    if( loadListener != null ){


                        // Scroll Listener 안에서 adapter와같은 UI 를 갱신하는 것과 관련 에러 회피?
                        handler.post(new Runnable() {

                            @Override
                            public void run() {

                                adapter.addDummyProgressView();
                            }
                        });
                        loadListener.onLoad();
                    }

                }

            }
        }
    };




    public FloodingPresenter(RecyclerView recyclerView, ArrayList<? extends ViewData> viewData , LoadListener loadListener ) {

        this ( recyclerView, viewData , new WrappingLayoutManager( recyclerView.getContext() ) , loadListener );
    }



    public FloodingPresenter(RecyclerView recyclerView, ArrayList<? extends ViewData> viewData, WrappingLayoutManager layoutManager, LoadListener loadListener) {

        this.recyclerView       = recyclerView;
        this.viewData           = viewData;
        this.layoutManager      = layoutManager;
        this.loadListener       = loadListener;


        adapter = new FloodingAdapter(recyclerView.getContext(),viewData );


        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( adapter );





        recyclerView.addOnScrollListener( onScrollListener );
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public FloodingAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(FloodingAdapter adapter) {


        this.adapter = adapter;
        recyclerView.setAdapter( adapter );
        recyclerView.addOnScrollListener( onScrollListener );

    }

    public WrappingLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(WrappingLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

//    public void moreLoading( final boolean isProgress){
//
//
//        if( isProgress ){
//
//        }
//        if( loadListener != null ){
//
//            isMoreLoading = true ;
//            // TODO : somehing added
//            viewData.add(null);
//            adapter.notifyItemInserted( adapter.getItemCount() - 1 );
//
//            loadListener.onLoad();
//        }
//    }

    @Override
    public void onResponse() {


        this.isMoreLoading = false;

        final int lastIndex = viewData.size() -1 ;
        viewData.remove(viewData.size()-1);
        adapter.notifyItemRemoved( lastIndex );

    }

    @Override
    public void addItems(int startIndex,int updatedItemSize) {
        adapter.notifyItemRangeChanged( startIndex, updatedItemSize );
    }


    public boolean isMoreLoading() {
        return isMoreLoading;
    }

    public void setMoreLoading(boolean moreLoading) {
        isMoreLoading = moreLoading;
    }

    public interface LoadListener {


        void onLoad();
        void onEndedLoad();
    }

}
