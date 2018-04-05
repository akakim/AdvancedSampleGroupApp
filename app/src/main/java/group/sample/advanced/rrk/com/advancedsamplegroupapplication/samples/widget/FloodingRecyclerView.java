package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by RyoRyeong Kim on 2018-04-05.
 */

public class FloodingRecyclerView extends RecyclerView {


    final OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };
    public FloodingRecyclerView(Context context) {
        super(context);
    }

    public FloodingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloodingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }


    public void addFloodingScrollListener(){

    }
}
