package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.Photo;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;


/**
 * Created by KIM on 2017-11-12.
 */

public class SampleListAdapter extends RecyclerView.Adapter<SampleListAdapter.SampleViewHolder> {

    Context context;
    SampleItem [] sampleItems;

    public SampleListAdapter(Context context) {
        this(context,null);
    }

    public SampleListAdapter(Context context,SampleItem[] sampleItems){
        super();
        this.context = context;
        this.sampleItems = sampleItems;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context ).inflate( R.layout.sample_list_item, parent );

        return new SampleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {

        holder.tvActivityName.setText ( sampleItems[position].getClazz().getName() ) ;
        holder.tvContent.setText( sampleItems[position].getDescription() );
    }

    @Override
    public int getItemCount() { return (sampleItems == null ) ? 0 : sampleItems.length; }



    public void setSampleItems(SampleItem[] sampleItems) {
        this.sampleItems = sampleItems;
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder {


      @Nullable
      @BindView(R.id.tvActivityName)
      TextView tvActivityName;

      @Nullable
      @BindView(R.id.tvContent)
      TextView tvContent;

        public SampleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(itemView);
        }
    }

}
