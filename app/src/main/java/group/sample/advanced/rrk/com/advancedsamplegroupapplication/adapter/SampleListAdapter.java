package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.Photo;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;


/**
 * Created by KIM on 2017-11-12.
 */

public class SampleListAdapter extends RecyclerView.Adapter<SampleListAdapter.SampleViewHolder> {

    Context context;

    ItemClickListener itemClickListener;
    List <SampleItem> sampleItems = new ArrayList<>();

    public SampleListAdapter(Context context) {
        this.context = context;
        this.sampleItems = null;
    }


    public SampleListAdapter(Context context,SampleItem[] sampleItems,ItemClickListener itemClickListener){
        super();
        this.context = context;
//        this.sampleItems = sampleItems;
        this.itemClickListener= itemClickListener;

        for( SampleItem i : sampleItems ){
            this.sampleItems.add(i);
        }
    }

    public SampleListAdapter(Context context, List<SampleItem> sampleItems,ItemClickListener itemClickListener){

        super();
        this.context = context;
        this.sampleItems.addAll( sampleItems );
        this.itemClickListener= itemClickListener;
    }

    public void notifyDataSet(List<SampleItem> sampleItems){
        this.sampleItems.addAll(sampleItems);
        notifyDataSetChanged();
    }
    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context ).inflate( R.layout.sample_list_item, null );

        return new SampleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {

        holder.itemView.setTag(position);

        if( sampleItems.get(position).getClazz() == null){
            Log.e("TAG","getClaxx is null ");
        }else if(holder.tvActivityName == null ){

            Log.e("TAG","tvActivity Name is null");
        }
        else{
            holder.tvActivityName.setText(sampleItems.get(position).getClazz().getSimpleName());
        }
        holder.tvContent.setText( sampleItems.get(position).getDescription() );
    }

    @Override
    public int getItemCount() { return (sampleItems == null ) ? 0 : sampleItems.size(); }

    public void removeItem(int position){
        this.sampleItems.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(SampleItem item, int position) {
        sampleItems.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }




    public class SampleViewHolder extends RecyclerView.ViewHolder {



      @BindView(R.id.foregroundLayout)
      public RelativeLayout foregroundLayout;

      @BindView(R.id.backLayout)
      public RelativeLayout backLayout;

      @Nullable
      @BindView(R.id.tvActivityName)
      public TextView tvActivityName;

      @Nullable
      @BindView(R.id.tvContent)
      public TextView tvContent;

      @BindView(R.id.btnDeleteItem)
      public Button btnDelete;

        public SampleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

            foregroundLayout.setOnClickListener(
                    (View) -> {

                        if(itemClickListener != null){
                            itemClickListener.ItemClicked(
                                    (int)View.getTag()
                            );
                        }
                    }
            );

            btnDelete.setOnClickListener(
                    (View) -> {

                        if(itemClickListener != null){
                            itemClickListener.deleteClicked(
                                    (int)View.getTag()
                            );
                        }
                    }
            );
        }

    }

    public interface ItemClickListener{
        public void ItemClicked(int position);
        void deleteClicked(int position);
    }
}
