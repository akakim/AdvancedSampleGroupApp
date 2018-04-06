package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2018-04-02.
 */

public class BottomEndlessRefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int   VIEW_ITEM = 1;
    private final int   VIEW_PROG = 0;

    private boolean     isMoreLoading = false;
    private int         visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCopunt, lastVisibleItem;

    Context             context;
    ArrayList<String>   titleArrayList;
    ArrayList<String>   contentArrayList;


    OnLoadMoreListener  listener ;
    LinearLayoutManager linearLayoutManager;

    public BottomEndlessRefreshAdapter(Context context, ArrayList<String> titleArrayList, ArrayList<String> contentArrayList ,OnLoadMoreListener listener) {
        this.context            = context;
        this.titleArrayList     = titleArrayList;
        this.contentArrayList   = contentArrayList;
        this.listener           = listener;
    }


    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if( viewType == VIEW_ITEM ){

          return  new ItemViewHolder(LayoutInflater.from( context ).inflate(R.layout.item_list_content,null,false));

        }else {
            return new ProgressViewHolder(LayoutInflater.from( context).inflate(R.layout.item_progress , parent,false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if( holder instanceof  ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;

            itemViewHolder.titleTextView.setText( titleArrayList .get(position));
            itemViewHolder.contetnTextView.setText( contentArrayList.get(position ));
        }

    }


    public void addAll( ArrayList<String> titleArrayList , ArrayList<String> contentArrayList ){

        this.titleArrayList .addAll( titleArrayList );
        this.contentArrayList .addAll( contentArrayList );
    }


    public void setMoreLoading(boolean moreLoading) {
        isMoreLoading = moreLoading;
    }

    public void setProgressMore ( final boolean isProgress){

        if (isProgress ){
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    titleArrayList.add(null);
                    contentArrayList.add(null);
                    notifyItemInserted( titleArrayList.size() -1 );

                }
            });
        }else {
            titleArrayList.remove(titleArrayList.size()- 1);
            contentArrayList.remove(contentArrayList.size()- 1);

            notifyItemRemoved(titleArrayList.size());


        }
    }

    @Override
    public int getItemCount() {
        return titleArrayList.size();
    }


    // TODO Refactoring
    public void setRecyclerView ( RecyclerView recyclerView){

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCopunt = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();


                if( !isMoreLoading && ( totalItemCopunt - visibleItemCount ) <= (firstVisibleItem + visibleThreshold )){
                    if( listener != null ){
                        listener.onLoadMore();
                        // something executed..
                        // reset ...
                    }
                    isMoreLoading = true;
                }
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return titleArrayList.get(position) != null ? VIEW_ITEM : VIEW_PROG;

    }

    class ItemViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.id_text)
        TextView titleTextView;

        @BindView(R.id.content)
        TextView contetnTextView;


        public ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind( this ,itemView);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }


    class ProgressViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind( this ,itemView);

        }
    }
}
