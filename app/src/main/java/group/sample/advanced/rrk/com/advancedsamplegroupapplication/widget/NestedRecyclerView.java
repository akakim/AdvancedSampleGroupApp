package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by RyoRyeong Kim on 2018-01-29.
 */

public class NestedRecyclerView extends RecyclerView{
    private static final int MAXIMUM_LIST_ITEMS_VIEWABLE = 99;


    public NestedRecyclerView(Context context) {
        super(context);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);

        int newHeight = 0;
        final int heightMode = MeasureSpec.getMode( heightSpec );
        int heightSize = MeasureSpec.getSize( heightSpec );

        if( heightMode != MeasureSpec.EXACTLY ){

            // view의크기를 측정하는 로직


            Adapter<ViewHolder> adapter = getAdapter();

            if( adapter != null && adapter.getItemCount() >=1 ){
                int listPosition = 0 ;
                for ( listPosition = 0; listPosition < adapter.getItemCount()
                        && listPosition < MAXIMUM_LIST_ITEMS_VIEWABLE; listPosition++ ){
//                    View listItem

                }
            }
        } else {
            newHeight = getMeasuredHeight();
        }

        setMeasuredDimension( getMeasuredWidth() , newHeight );
    }
}
