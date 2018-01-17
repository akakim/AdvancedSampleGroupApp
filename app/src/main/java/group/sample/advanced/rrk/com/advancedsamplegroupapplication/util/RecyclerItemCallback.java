package group.sample.advanced.rrk.com.advancedsamplegroupapplication.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by RyoRyeong Kim on 2018-01-17.
 */

public class RecyclerItemCallback extends ItemTouchHelper.Callback implements ItemTouchHelper.ViewDropHandler {


    @Override
    public void prepareForDrop(View view, View target, int x, int y) {

    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
