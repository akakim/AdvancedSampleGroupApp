package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

import android.util.Log;

import java.util.List;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;

/**
 * Created by RyoRyeong Kim on 2017-12-04.
 */

public class MainPresenterImpl implements MainPresenter,FindItemsInteractor.OnFinishedListener {


    /**
     * View 단의 로직을 정리한다.
     */
    private MainGroupView mainGroupView;

    /**
     * 데이터를 불러오는 로직을 정리한다.
     * 네트워크 , DB 등등.
     */
    private FindItemsInteractor findItemsInteractor;


    public MainPresenterImpl(MainGroupView mainGroupView, FindItemsInteractor findItemsInteractor) {
        this.mainGroupView = mainGroupView;
        this.findItemsInteractor = findItemsInteractor;
        this.findItemsInteractor.findItems(this);
    }

    @Override
    public void onFinished(List<SampleItem> items) {
        Log.d(getClass().getSimpleName(),"onFinished()" + items.size() );
        mainGroupView.setItem(items);
    }

    @Override
    public void onResume() {

        mainGroupView.showMessage();
    }

    @Override
    public void onItemClicked(int position) {
        this.mainGroupView.showMessage(false,"position :  " +  position + " is Clicked.. " );
    }

    @Override
    public void onDestroy() {
        mainGroupView = null;   // 만약 필요하다면, 초기화 코드를 넣는는다.

    }
}
