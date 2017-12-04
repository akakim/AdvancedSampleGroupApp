package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

import android.os.Handler;

import java.util.Arrays;
import java.util.List;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.*;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.charsample.MpAndroidChartListJavaActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.database.GetAllOfMusicItemsActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget.TextInputLayoutCustomActivity;

/**
 * Created by RyoRyeong Kim on 2017-12-04.
 */

public class FindItmesInteractorImpl implements FindItemsInteractor{



    private List<SampleItem> createSampleList (){

        return Arrays.asList(
                new SampleItem(IdentifyActivity.class,"Application 고유 ID 와 device의 고유 ID"),
                new SampleItem(GetAllOfMusicItemsActivity.class,"음악 아이템을 모아놓음"),
                new SampleItem(TextInputLayoutCustomActivity.class,"TextInput Custom 예제 "),
                new SampleItem(MpAndroidChartListJavaActivity.class,"MPAndroidChart 예제들")
        );
    }

    @Override
    public void findItems(OnFinishedListener listener) {
        new Handler().post(()->{
           listener.onFinished( createSampleList() );
        });
    }
}


/*
  sampleItems.add( new SampleItem(IdentifyActivity.class,"Application 고유 ID 와 device의 고유 ID"));
        sampleItems.add( new SampleItem(OpenViewPagerActivity.class,"뷰페이저 테스트를위한 페이지"));
        sampleItems.add( new SampleItem(GetAllOfMusicItemsActivity.class,"음악 아이템을 모아놓음"));
        sampleItems.add( new SampleItem(TextInputLayoutCustomActivity.class,"TextInput Custom 예제 "));
        sampleItems.add( new SampleItem(MpAndroidChartListJavaActivity.class,"MPAndroidChart 예제들"));
 */