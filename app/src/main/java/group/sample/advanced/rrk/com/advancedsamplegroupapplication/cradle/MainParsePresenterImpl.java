package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

/**
 * Created by RyoRyeong Kim on 2017-12-20.
 */

public class MainParsePresenterImpl implements MainParsePresenter,ParseMainInteractor.OnFinishedListener{


    MainParseUIView mainParseUIView;
    ParseMainInteractorImpl parseMainInteractor;

    public MainParsePresenterImpl(MainParseUIView mainParseUIView) {
        this.mainParseUIView = mainParseUIView;
        parseMainInteractor = new ParseMainInteractorImpl();
    }


    @Override
    public void initContent(Runnable runnable) {


        mainParseUIView.showMessage("데이터 로딩중 ");
        parseMainInteractor.init(runnable);

    }

    @Override
    public void onItemClicked(int position) {

    }


    @Override
    public String onFinished() {
        return null;
    }
}
