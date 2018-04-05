package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by RyoRyeong Kim on 2018-04-05.
 */

public class FloodingPresenter {


    private boolean                                        isMoreLoading = false;


    private RecyclerView                                  recyclerView;
    private FloodingAdapter                               adapter;
    private LinearLayoutManager                           layoutManager;

    private FloodingInterface                             floodingInterface;


    int firstVisibleItem, visibleItemCount, totalItemCopunt, lastVisibleItem;

    ArrayList<? extends ViewData> viewData;

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


                visibleItemCount = recyclerView.getChildCount();

            }
        }
    };


    public FloodingPresenter(RecyclerView recyclerView, ArrayList<? extends ViewData> viewData, LinearLayoutManager layoutManager) {

        this ( recyclerView, viewData , layoutManager , null );
    }

    public FloodingPresenter(RecyclerView recyclerView, ArrayList<? extends ViewData> viewData, LinearLayoutManager layoutManager, FloodingInterface floodingInterface) {

        this.recyclerView       = recyclerView;
        this.viewData           = viewData;
        this.layoutManager      = layoutManager;
        this.floodingInterface  = floodingInterface;
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

    public RecyclerView.Adapter<RecyclerView.ViewHolder> getAdapter() {
        return adapter;
    }

    public void setAdapter(FloodingAdapter adapter) {

        recyclerView.removeOnScrollListener( onScrollListener );
        this.adapter = adapter;
        recyclerView.setAdapter( adapter );
        recyclerView.addOnScrollListener( onScrollListener );

    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void moreLoading(){


        if( floodingInterface != null ){

            isMoreLoading = true ;
            // TODO : somehing added
            adapter.notifyItemInserted( adapter.getItemCount() - 1 );
        }
    }


    public interface FloodingInterface{


        void onLoad();
        void onEndedLoad();
    }
}
