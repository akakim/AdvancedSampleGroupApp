package group.sample.advanced.rrk.com.advancedsamplegroupapplication.util;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;

/**
 * Created by RyoRyeong Kim on 2018-01-10.
 *
 * referenced : https://www.androidhive.info/2017/09/android-recyclerview-swipe-delete-undo-using-itemtouchhelper/
 */

public class RecyclerItemDeleteItem extends ItemTouchHelper.SimpleCallback {


    ItemTouchListener listener;

    public RecyclerItemDeleteItem(int dragDirs, int swipeDirs, ItemTouchListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }


    // 선택의 변화
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if( viewHolder != null){
            final View foregroundView = ((SampleListAdapter.SampleViewHolder) viewHolder).foregroundLayout;

            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        final View foregroundView = ((SampleListAdapter.SampleViewHolder) viewHolder ).foregroundLayout;
        getDefaultUIUtil().onDrawOver(c, recyclerView,foregroundView,dX, dY,actionState,isCurrentlyActive);
    }


    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        final View foregroundView = ((SampleListAdapter.SampleViewHolder) viewHolder ).foregroundLayout;
        foregroundView.setVisibility(View.GONE);
//        getDefaultUIUtil().clearView( foregroundView );
    }

    // ???
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface ItemTouchListener{
        void onSwiped( RecyclerView.ViewHolder holder,int direction, int position);
    }
}
