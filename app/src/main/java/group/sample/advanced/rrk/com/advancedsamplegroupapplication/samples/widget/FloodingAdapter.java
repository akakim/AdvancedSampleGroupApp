package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter.BottomEndlessRefreshAdapter;


/**
 * Created by RyoRyeong Kim on 2018-04-05.
 */

public class FloodingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    Context context;
    ArrayList<? extends  ViewData > viewArrayList;


    public FloodingAdapter(Context context, ArrayList<? extends ViewData> viewArrayList) {
        this.context = context;
        this.viewArrayList = viewArrayList;
    }


    public void setViewArrayList(ArrayList<? extends ViewData> viewArrayList) {
        this.viewArrayList = viewArrayList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch ( viewType ){
            case ViewData.VIEW_ITEM:
                return new ItemViewHolder(LayoutInflater.from(context).inflate( R.layout.item_list_content,parent,false));
            case ViewData.VIEW_HEADER:
                return new ItemViewHolder(LayoutInflater.from(context).inflate( R.layout.item_list_content,parent,false));
            case ViewData.VIEW_PROGRESS:
                return new ProgressViewHolder(LayoutInflater.from(context).inflate( R.layout.item_progress,parent,false));
            default:
                return new ProgressViewHolder(LayoutInflater.from(context).inflate( R.layout.item_progress,parent,false));
        }

    }







    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if( holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;

            itemViewHolder.titleTextView.setText( viewArrayList .get(position).getTitle());
            itemViewHolder.contetnTextView.setText( viewArrayList.get(position ).getContent());
        }
    }




    @Override
    public int getItemViewType(int position) {
        return viewArrayList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return viewArrayList.size();
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


    class ProgressViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind( this ,itemView);

        }
    }
}
