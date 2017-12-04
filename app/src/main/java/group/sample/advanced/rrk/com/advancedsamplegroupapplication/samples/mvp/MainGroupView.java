package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

import java.util.List;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.SampleItem;

/**
 * Created by RyoRyeong Kim on 2017-12-04.
 * view에서 작업하는 일반적인 로직들을 나열하였다.
 * 이 순서나 시점은 Presenter에서 정리가 된다.
 */

public interface MainGroupView {

    void showProgress();
    void hideProgress();
    void setItem(List<SampleItem> Items);
    void showMessage();
    void showMessage(boolean isError,String Message);
}
