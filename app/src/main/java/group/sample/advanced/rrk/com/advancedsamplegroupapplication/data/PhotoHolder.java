package group.sample.advanced.rrk.com.advancedsamplegroupapplication.data;

import android.support.v7.widget.RecyclerView;

import java.io.Serializable;

/**
 * Created by KIM on 2017-11-12.
 */

public class PhotoHolder implements Serializable {

    public Photo [] photos;


    public PhotoHolder(){
        photos = null;
    }
    public PhotoHolder(Photo[] photos) {
        this.photos = photos;
    }
}
