package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Map;

/**
 * Created by RyoRyeong Kim on 2017-12-20.
 */

public class MainParsePresenterImpl implements MainParsePresenter,ParseMainInteractor.OnFinishedListener{


    MainParseUIView mainParseUIView;
    ParseMainInteractorImpl parseMainInteractor;
    AppCompatActivity acvContext;

    public MainParsePresenterImpl(MainParseUIView mainParseUIView,AppCompatActivity acvContext) {
        this.mainParseUIView = mainParseUIView;
        parseMainInteractor = new ParseMainInteractorImpl(this);
        this.acvContext = acvContext;

    }


    @Override
    public void initContent() {


        mainParseUIView.showMessage("데이터 로딩중 ");
        parseMainInteractor.init(acvContext);


    }

    @Override
    public void onItemClicked(int position) {

    }


    @Override
    public void onFinished(final boolean isError,final String responseData,final JSONObject data) {

        if( isError ){
            mainParseUIView.responseFailed(responseData);
        }else {
            mainParseUIView.responseSuccess(responseData,data);
        }
    }
}
