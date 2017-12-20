package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

/**
 * Created by RyoRyeong Kim on 2017-12-20.
 */

public class MainParsePresenterImpl implements MainParsePresenter,ParseMainInteractor.OnFinishedListener{


    MainParseUIView mainParseUIView;


    public MainParsePresenterImpl(MainParseUIView mainParseUIView) {
        this.mainParseUIView = mainParseUIView;


        mainParseUIView.showProgress();
    }

    @Override
    public void onItemClicked(int position) {

    }


    @Override
    public String onFinished() {
        return null;
    }
}
