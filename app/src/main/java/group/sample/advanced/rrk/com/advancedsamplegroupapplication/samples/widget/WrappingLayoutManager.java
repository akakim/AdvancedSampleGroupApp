package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by RyoRyeong Kim on 2018-04-06.
 */

public class WrappingLayoutManager extends LinearLayoutManager {
    public WrappingLayoutManager(Context context) {
        super(context);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        }catch ( IndexOutOfBoundsException e ){

            Log.e(getClass().getSimpleName(),"",e.getCause());
        }
    }


}
