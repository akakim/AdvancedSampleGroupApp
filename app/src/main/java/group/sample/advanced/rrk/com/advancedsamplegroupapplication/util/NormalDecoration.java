package group.sample.advanced.rrk.com.advancedsamplegroupapplication.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.support.v7.widget.DividerItemDecoration;


import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2018-04-16.
 */

public class NormalDecoration extends RecyclerView.ItemDecoration {

    private static final int [] ATTRS = new int [] { android.R.attr.listDivider };
    private Drawable divider;

    private Context context;
    public NormalDecoration(Context context ) {
        final TypedArray styledAttributes = context.obtainStyledAttributes( ATTRS);

        this.context = context;

        divider = styledAttributes.getDrawable( 0 );
        styledAttributes.recycle();
    }



    public void setDivicerColor(@ColorRes int colorInt ){


        divider.setTint( context.getColor( colorInt ));
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//
//        int childCount = parent.getChildCount();
//
//
//        for ( int i = 0 ;i< childCount ; i++){
//            View child = parent.getChildAt( i );
//
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams ) child.getLayoutParams();
//            int top = child.getBottom() + params.bottomMargin;
//            int bottom = top  + divider.getIntrinsicHeight();
//
//            divider.setBounds( left, top , right, bottom );
//            divider.draw( c );
//        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if( parent.getChildAdapterPosition( view ) != parent.getAdapter().getItemCount() -1 ){
            outRect.bottom = 10;
            view.setBackgroundColor( 0xffffff00);
        }
    }
}
