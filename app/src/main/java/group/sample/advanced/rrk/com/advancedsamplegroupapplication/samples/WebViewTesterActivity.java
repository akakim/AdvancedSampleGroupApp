package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class WebViewTesterActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.mainWeb)
    WebView mainWeb;

//    @BindView( R.id. edInputURL)
//    AppCompatEditText edInputURL;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    WebViewClient webViewClient;
    WebChromeClient webChromeClient;

    ButterKnife.Action<WebView> initAction = new ButterKnife.Action<WebView>() {
        @Override
        public void apply(@NonNull WebView view, int index) {


            WebSettings webSettings = view.getSettings();

            view.setWebViewClient(webViewClient );
            view.setWebChromeClient( webChromeClient);

            view.loadUrl("https://www.youtube.com/channel/UCsFfQoEIsQnCGMRzyZEXOIQ/live");


//            view.loadUrl("http://showmethemoney.or.kr/%EB%B3%B4%EB%8F%84%EC%9E%90%EB%A3%8C/");
        }
    };

    ButterKnife.Action<AppCompatEditText> searchEditAction = new ButterKnife.Action<AppCompatEditText>() {
        @Override
        public void apply(@NonNull AppCompatEditText view, int index) {
            view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (KeyEvent.KEYCODE_ENTER == event.getAction() ){

                        mainWeb.loadUrl(v.getText().toString());
                    }

                    return false;
                }
            });
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_tester);

        ButterKnife.bind(this);
        webChromeClient = new WebChromeClient();
        webViewClient = new WebViewClient();

        WebSettings webSettings = mainWeb.getSettings();

        webSettings.setJavaScriptEnabled( true );


        mainWeb.setWebViewClient(webViewClient );
        mainWeb.setWebChromeClient( webChromeClient);



//        mainWeb.loadUrl("https://stackoverflow.com/questions/17882474/running-adb-commands-on-all-connected-devices");
        mainWeb.loadUrl("https://www.youtube.com/channel/UCsFfQoEIsQnCGMRzyZEXOIQ/live?app=mobile");
        swipeRefreshLayout.setOnRefreshListener( this );





//        ButterKnife.apply(mainWeb, initAction);
//        if( getIntent() != null) {
//
////            String RSS_FEED_KEY = getIntent().getExtras().getString(RSSActivity.RSS_FEED_KEY);
//
//            ButterKnife.apply(mainWeb, new ButterKnife.Action<WebView>() {
//                @Override
//                public void apply(@NonNull WebView view, int index) {
//                    WebSettings webSettings = view.getSettings();
//                    view.setWebViewClient(webViewClient );
//                    view.setWebChromeClient( webChromeClient);
//
//                    view.loadUrl(RSS_FEED_KEY);
//                }
//            });
//
//        }else {
//            ButterKnife.apply(mainWeb, initAction);
//        }
//        ButterKnife.apply(edInputURL,searchEditAction);
    }


    @Override
    public void onRefresh() {
        mainWeb.reload();
    }
}
