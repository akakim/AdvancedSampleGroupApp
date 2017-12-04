package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

import java.util.List;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;

/**
 * Created by RyoRyeong Kim on 2017-12-04.
 * 이곳은 네트워크 로직 혹은 데이터를 불러오는 시점을 서술한다.
 */

public interface FindItemsInteractor {

   interface OnFinishedListener{
       void onFinished( List<SampleItem> items);
   }

   void findItems(OnFinishedListener listener);
}
