package group.sample.advanced.rrk.com.advancedsamplegroupapplication.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.SampleListAdapter;

/**
 * Created by RyoRyeong Kim on 2018-01-10.
 *
 * referenced : https://www.androidhive.info/2017/09/android-recyclerview-swipe-delete-undo-using-itemtouchhelper/
 */

/**
 * This is the standard support library way of implementing "swipe to delete" feature. You can do custom drawing in onChildDraw method
 * but whatever you draw will disappear once the swipe is over, and while the items are animating to their new position the recycler view
 * background will be visible. That is rarely an desired effect.
 */

public class RecyclerItemDeleteItem extends ItemTouchHelper.SimpleCallback {


    ItemTouchListener listener;

    Drawable background;
    boolean initiated;
    public RecyclerItemDeleteItem(int dragDirs, int swipeDirs, ItemTouchListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    private void init (){
        background = new ColorDrawable(Color.WHITE );

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
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {


        View itemView = viewHolder.itemView;

        if( viewHolder.getAdapterPosition() == -1 ){
            // not interested in those
            return;
        }


        // draw red background
        if (!initiated) {
            init();
        }
        background.setBounds( itemView.getRight() + ( int ) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        background.draw(c );
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
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
//        foregroundView.setVisibility(View.GONE);
        getDefaultUIUtil().clearView( foregroundView );
    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
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
