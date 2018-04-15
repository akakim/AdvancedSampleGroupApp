package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.Photo;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SingleChoice;


/**
 * Created by KIM on 2017-11-12.
 */

public class SampleListAdapter extends RecyclerView.Adapter<SampleListAdapter.SampleViewHolder> {

    Context context;

    ItemClickListener itemClickListener;
    List <? extends Object> sampleItems = new ArrayList<>();
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();

    SparseBooleanArray sparseBooleanArray;
    public SampleListAdapter(Context context) {
        this.context = context;
        this.sampleItems = null;
    }


    public SampleListAdapter(Context context, List sampleItems,ItemClickListener itemClickListener){

        super();
        this.context = context;
        this.sampleItems.addAll( sampleItems );
        this.itemClickListener= itemClickListener;

    }

    public void notifyDataSet(List sampleItems){
        this.sampleItems.addAll(sampleItems);
        notifyDataSetChanged();
    }



    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context ).inflate( R.layout.sample_list_item, parent,false );

        return new SampleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {

        holder.foregroundLayout.setTag(position);

//        holder.btnDelete.setTag(position);
//        holder.btnCancel.setTag(position);

        final Object obj = sampleItems.get(position);
        if( obj instanceof SampleItem ) {
            holder.tvActivityName.setText( ((SampleItem)obj).getClazz().getSimpleName());

            holder.tvContent.setText(  ((SampleItem)obj).getDescription() );

        }else if(obj instanceof SingleChoice){
            holder.tvActivityName.setText( " 간편 예제 ");

            holder.tvContent.setText(  ((SingleChoice)obj).getContent() );
        }
//        holder.btnDelete.setText("삭제");


//        holder.btnDelete.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                sampleItems.remove ( holder.getAdapterPosition() );
//                notifyItemRemoved(holder.getAdapterPosition() );
//            }
//        });
    }

    @Override
    public int getItemCount() { return (sampleItems == null ) ? 0 : sampleItems.size(); }

    public void removeItem(int position){
        this.sampleItems.remove(position);
        notifyItemRemoved(position);
    }





    public void saveState(Bundle bundle){
        binderHelper.saveStates( bundle);
    }

    public void restoreState(Bundle outState){
        binderHelper.restoreStates( outState );
    }



    public class SampleViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.foregroundLayout)
        public FrameLayout foregroundLayout;

//      @BindView(R.id.backLayout)
//      public FrameLayout backLayout;

        @Nullable
        @BindView(R.id.tvActivityName)
        public TextView tvActivityName;

        @Nullable
        @BindView(R.id.tvContent)
        public TextView tvContent;

//      @BindView(R.id.btnCancel)
//      public Button btnCancel;
//      @BindView(R.id.btnDeleteItem)
//      public TextView btnDelete;

        public SampleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            foregroundLayout.setOnClickListener(
                    (View) -> {

                        if(itemClickListener != null){
                            itemClickListener.ItemClicked(
                                    (int)View.getTag()
                            );
                        }
                    }
            );

        }
    }


    public interface ItemClickListener{
        public void ItemClicked(int position);
        void cancelClicked(int position );
        void deleteClicked(int position);
    }
}
