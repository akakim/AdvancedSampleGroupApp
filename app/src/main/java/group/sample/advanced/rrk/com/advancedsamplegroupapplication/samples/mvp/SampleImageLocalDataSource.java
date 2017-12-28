package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 * Model For Local Data
 */

public class SampleImageLocalDataSource implements SampleImageSource{


    @Override
    public void getImages(Context context, int size, LoadImageCallback loadImageCallback) {

        ArrayList<ImageItem> items = new ArrayList<>();

        for( int i = 0; i< size; i++){
            final int random = ( int ) ( Math.random() * 15);
            final String name = String.format("sample_%02d",random);
//            final int resources = context.getResources().getIdentifier(name"drawable",context.getApplicationContext().getPackageName());
//            items.add(new ImageItem(resources,name));
        }
    }
}
