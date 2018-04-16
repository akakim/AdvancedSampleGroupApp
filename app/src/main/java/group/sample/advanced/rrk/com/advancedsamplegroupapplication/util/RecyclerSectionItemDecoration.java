package group.sample.advanced.rrk.com.advancedsamplegroupapplication.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by RyoRyeong Kim on 2018-04-16.
 */

public class RecyclerSectionItemDecoration extends RecyclerView.ItemDecoration {



    private float density;
    private int dpValue;
    boolean isSticky;
    SectionCallback sectionCallback;


    View headerView;
    TextView header;

    public RecyclerSectionItemDecoration(float density,int dpValue, boolean isSticky, SectionCallback sectionCallback) {
        this.density = density;
        this.dpValue = dpValue;
        this.isSticky = isSticky;
        this.sectionCallback = sectionCallback;


    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        if( headerView == null ) {
            headerView =inflateHeaderView(parent);
            header = (TextView)headerView;

            fixLayoutSize( headerView, parent );
        }


        CharSequence previousHeader = "";


        // title을 동적으로 만들어주기 위한 코드
        for (int i = 0; i< parent.getChildCount(); i++){
            View child = parent.getChildAt( i );

            final  int position = parent.getChildAdapterPosition( child ) ;
            CharSequence title = sectionCallback.getSectionHeader(position);

            Log.d(getClass().getSimpleName(),"title : " + title );
            header.setText( title );
            if( !previousHeader.equals(title) || sectionCallback.isSection( position ) ){
                drawHeader(c,child,headerView );
                previousHeader = title;
            }
        }
    }

    /**
     * 결과가 어찌되었든 Header값을 그려주는 함수 왜 Math.max가 들어가야하는걸까?
     * @param c
     * @param child
     * @param headerView
     */
    private void drawHeader( Canvas c,View child,View headerView ){
        // why?
        c.save();
        if( isSticky ){
            c.translate(0,Math.max(0,child.getTop() - headerView.getHeight()));
        }else {
            c.translate( 0,child.getTop() - headerView.getHeight() );
        }

        headerView.draw( c );
        // why ?
        c.restore();
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int post = parent.getChildAdapterPosition( view );
        if (sectionCallback.isSection(post)) {
            outRect.top = dpValue;
        }
    }

    private View inflateHeaderView(RecyclerView parent) {

        TextView textView = new TextView(parent.getContext());

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                dpValue
        );


        textView.setLayoutParams( layoutParams ) ;

        textView.setGravity( Gravity.CENTER );
        textView.setTextColor(Color.BLACK);
        textView.setPadding(10 *(int) density,0,10*(int)density,0);
        textView.setTextSize(12 * density);
        textView.setBackgroundColor(Color.CYAN);


        return textView;
//        return LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recycler_section_header, parent, false);
    }

    public interface SectionCallback{
        boolean isSection( int position);

        CharSequence getSectionHeader( int position );
    }


    /**
     * Measures the header view to make sure its size is greater than 0 and will be drawn
     * https://yoda.entelect.co.za/view/9627/how-to-android-recyclerview-item-decorations
     */
    private void fixLayoutSize(View view, ViewGroup parent) {

        // 부모 뷰그룹의 사이즈 측정
        int widthSpec = View.MeasureSpec.makeMeasureSpec( parent.getWidth() , View.MeasureSpec.EXACTLY );
        int heightSpec = View.MeasureSpec.makeMeasureSpec( parent.getHeight() , View.MeasureSpec.UNSPECIFIED);

        //
        int childWidth = ViewGroup.getChildMeasureSpec( widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(),view.getLayoutParams().width );
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,parent.getPaddingTop() + parent.getPaddingBottom(), view.getLayoutParams().height);


        view.measure( childWidth, childHeight ) ;
        view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
    }
}
