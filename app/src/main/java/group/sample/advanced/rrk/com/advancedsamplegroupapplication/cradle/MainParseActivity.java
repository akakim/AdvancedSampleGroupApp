package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

import android.os.AsyncTask;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.jsoup.Jsoup;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class MainParseActivity extends BaseActivity implements MainParseUIView{


    MainParsePresenter mainParsePresenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parse);


        mainParsePresenter = new MainParsePresenterImpl( this );



//        progressBar.setScrollBar;
        showProgress();

    }


    @Override
    public void showProgress() {


        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }
        }.execute();
        if( ! progressBar.isShowing() ){
            progressBar.show();
        }

        initContent();
    }

    @Override
    public void initContent() {

        Jsoup jsoup

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showAlert() {

    }


}
