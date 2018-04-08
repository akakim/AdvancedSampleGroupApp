package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.GradientData;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.CircleAngleAnimation;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget.CircleGradient;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-04-08
 * @since 0.0.1
 */

public class CustomCircleAdapter extends RecyclerView.Adapter<CustomCircleAdapter.ItemViewHolder> {



    Context context;
    ArrayList< ? extends GradientData> gradientList;

    OnClickStartAnimation onClickStartAnimation;
    public CustomCircleAdapter(Context context, ArrayList<? extends GradientData> gradientList,OnClickStartAnimation onClickStartAnimation) {
        this.context                = context;
        this.gradientList           = gradientList;
        this.onClickStartAnimation  = onClickStartAnimation;

        setHasStableIds( true );        // 고유한 아이디를 사용하도록 설정한다.
    }


    @Override
    public long getItemId(int position) {
        return gradientList.get(position).hashCode();


    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context ).inflate( R.layout.item_custom_circle,parent,false);
        return new ItemViewHolder( view ) ;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {


        final GradientData data =  this.gradientList.get( position );

        holder.tvSimpleMemo.setText(  data.getSimpleMemo() );
        holder.circleGradient.setDefaultSweepShader( data.getShader() );
//        CircleAngleAnimation animation = new CircleAngleAnimation(holder.circleGradient,240);
//
//        animation.setDuration( 2000  );
//
//        holder.circleGradient.setAnimation(
//                animation
//        );

        holder.itemView.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        onClickStartAnimation.onClick( holder.circleGradient );
                    }
        }) ;
    }

    @Override
    public int getItemCount() {
        return  this.gradientList.size();
    }

//    @Override
//    public void onClick(View v) {
//
//        CircleGradient view =  v.findViewById( R.id.circleGradient );
//
//
//
//        CircleAngleAnimation animation = new CircleAngleAnimation(view,240);
//
//        animation.setDuration( 2000  );
//
//        view.startAnimation( animation );
//
//        view.postInvalidateOnAnimation();
//
//        Toast.makeText( context, "Something is Clicked .... ",Toast.LENGTH_SHORT).show();
//    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView( R.id.tvSimpleMemo )
        TextView tvSimpleMemo;

        @BindView( R.id.circleGradient)
        CircleGradient circleGradient;
        public ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView );



        }
    }

    public interface OnClickStartAnimation{
        public void onClick(CircleGradient view );
    }
}
