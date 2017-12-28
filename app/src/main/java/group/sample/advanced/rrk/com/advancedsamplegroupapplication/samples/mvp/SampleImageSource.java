package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 */

public interface SampleImageSource {
    interface LoadImageCallback{
        void onImageLoad(ArrayList<ImageItem> list);
    }

    void getImages(Context context,int size,LoadImageCallback loadImageCallbakc);
}
