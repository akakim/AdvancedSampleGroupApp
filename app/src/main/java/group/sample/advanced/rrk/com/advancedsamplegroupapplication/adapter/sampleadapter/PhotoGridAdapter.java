package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.MainActivityJava;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.Photo;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.PhotoHolder;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-13
 * @since 0.0.1
 */

public class PhotoGridAdapter extends DragSelectRecyclerViewAdapter<PhotoGridAdapter.PhotoViewHolder> {

    private final MainActivityJava mainActivityJava;
    private Photo[] photos;

    public PhotoGridAdapter(MainActivityJava mainActivityJava) {
        this.mainActivityJava = mainActivityJava;
    }

    @Override
    public void saveInstanceState(Bundle out) {
        super.saveInstanceState(out);
        if (photos != null) {
            out.putSerializable("photos", new PhotoHolder(photos));
        }
    }
    @Override
    public void restoreInstanceState(Bundle in) {
        super.restoreInstanceState(in);
        if (in != null && in.containsKey("photos")) {
            PhotoHolder ph = (PhotoHolder) in.getSerializable( "photos");
            if (ph != null) {
                setPhotos(ph.photos);
            }
        }
    }


    public void setPhotos(Photo[] photos){
        for(Photo p : photos){
            Log.d("TAG","get URI " + p._data );
//      p.getUri()
        }
        this.photos = photos;
        notifyDataSetChanged();
    }
    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from( mainActivityJava )
                .inflate( viewType == 0 ? R.layout.griditem_browse : R.layout.griditem_photo,parent,false);
        return new PhotoViewHolder( v );
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if( mainActivityJava == null || mainActivityJava.isFinishing() ){
            return;
        }

        if( position == 0){
            return ;
        }


//        if( photos[position -1 ]._data)

        Log.d("TAG<","data : "+ photos[position -1 ]._data );
//        Glide.with(mainActivityJava).load(photos[position -1 ].getUri()).into( holder.image);

//        if (isIndexSelected(position)) {
//            holder.check.setVisibility(View.VISIBLE);
//            holder.circle.setActivated(true);
//            holder.image.setActivated(true);
//        } else {
//            holder.check.setVisibility(View.GONE);
//            holder.circle.setActivated(false);
//            holder.image.setActivated(false);
//        }

    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.length + 1 : 0;
    }


    class PhotoViewHolder extends RecyclerView.ViewHolder{


        @Nullable
        @BindView(R.id.image)
        ImageView image;

        @Nullable
        @BindView(R.id.check)
        View check;

        @Nullable
        @BindView(R.id.circle)
        View circle;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);


//            itemView.setOnClickListener(
//                    v -> {
//                        if (getAdapterPosition() == 0) {
//                            context.browseExternalPhotos();
//                            return;
//                        }
//                        toggleSelected(getAdapterPosition());
//                    });
//
//            if (image != null) {
//                itemView.setOnLongClickListener(
//                        v -> {
//                            toggleSelected(getAdapterPosition());
//                            context.list.setDragSelectActive(true, getAdapterPosition());
//                            return false;
//                        });
//            }
        }
    }
}
