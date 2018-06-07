package group.sample.advanced.rrk.com.advancedsamplegroupapplication.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2018-04-16.
 *
 * 전체 길이로 Divider를 설정하는게 아닌 Divider를 자를 수 있는 Deocrater가 필요하다.
 */

public class ClipDecoration extends RecyclerView.ItemDecoration {



    Context context;

    private Drawable divider;

    boolean showFirstDivider ;
    boolean showLastDivider  ;

    public ClipDecoration(Context context, AttributeSet attrs) {
        this.context = context;


        final TypedArray a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.listDivider });
        divider = a.getDrawable(0);
        a.recycle();
    }

    public ClipDecoration(Context context) {
        this.context = context;


        divider = context.getDrawable(R.drawable.list_item_single_divider);
    }

//    public NormalDecoration(Context context,AttributeSet attrs,boolean showFirstDivider ,boolean showLastDivider)

    /**
     * 그려지는 시점
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c,parent,state);
//        c.save();
//        Rect mBound= new Rect(c.getClipBounds());
//
//        final int leftWithMargin = convertToDpToPixel( 56 );
//        final int right = parent.getWidth();
//
//        final int childCount = parent.getChildCount();
//
//
//        for( int i = 0; i< childCount ; i++){
//
//
//            final View child=  parent.getChildAt( i ) ;
//
//            int adapterPosition = parent.getChildAdapterPosition( child ); // 어뎁터의입장에서 가져온다.
//
//            int left = ( adapterPosition%5 == 0 ) ? 0 : leftWithMargin;
//
//
//            parent.getDecoratedBoundsWithMargins( child , mBound );
//            final int bottom = mBound.bottom + Math.round( child.getTranslationY());
//            final int top = bottom - divider.getIntrinsicHeight();
//
//
//
//            divider.setBounds( left, top, right, bottom );
//            divider.draw( c ) ;
//
//
//        }
//
//        c.restore();
    }


    public Drawable getDivider() {
        return divider;
    }

    public void setDivider(Drawable divider) {
        this.divider = divider;
    }

    public void setDividerColor( @ColorInt int color){

        this.divider.setTint( color );
    }

    /**
     * 그려지구 난 이후 시점 OverLayer와 같은 효과를 주나보다. ( TODO : 예제 찾기 )
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        if( divider == null )  return;

//        if(parent.getChildPosition( parent ) < 1  ) return ;

        if( getOrientation( parent ) == LinearLayoutManager.VERTICAL ){

            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight() - convertToDpToPixel(100);
            final int childCount = parent.getChildCount();

            for (int i = 1 ; i <childCount; i++){

                final View child = parent.getChildAt ( i ) ;
                final RecyclerView.LayoutParams layoutParams = ( RecyclerView.LayoutParams) child.getLayoutParams() ;
                final int size=  divider.getIntrinsicHeight();
                final int top = child.getTop() - layoutParams.topMargin;
                final int bottom = top + size;
                divider.setTint(0xffff0000);
                divider.setBounds( left,top,right,bottom );

                divider.draw( c );
            }

        }else {

            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();
            final int childCount = parent.getChildCount();

            for (int i=1; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int size = divider.getIntrinsicWidth();
                final int left = child.getLeft() - params.leftMargin;
                final int right = left + size;
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }


    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


        if( divider == null )  return;

//        if(parent.getChildPosition( parent ) < 1  ) return ;

        if( getOrientation( parent ) == LinearLayoutManager.VERTICAL )
            outRect.top = divider.getIntrinsicHeight();
        else
            outRect.left= divider.getIntrinsicWidth();

    }


    private int getOrientation( RecyclerView view ){
        if( view.getLayoutManager() instanceof LinearLayoutManager ){
            LinearLayoutManager layoutManager = ( LinearLayoutManager ) view.getLayoutManager();
            return layoutManager.getOrientation();
        }else
            throw new IllegalStateException( "Divider Item Decoration can only be used with a LinearLaoutManager");
    }


    /**
     * px = dp * dpi
     * dp = pixel / density
     * @param dp
     * @return
     */
    private int convertToDpToPixel( int dp ){
        float density = context.getResources().getDisplayMetrics().density;
        return dp * (int)density ;
    }

}
