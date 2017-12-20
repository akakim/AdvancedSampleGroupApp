package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.HanldeXML;

public class RSSActivity extends BaseActivity implements HanldeXML.handlerInterface {

    public static final String RSS_FEED_KEY = "rssFeed";
    @BindView(R.id.edBaseRSSFeedUrl)
    TextInputEditText edBaseRSSFeedUrl;

    @BindView(R.id.edTitle)
    TextInputEditText edTitle;

    @BindView(R.id.edLink)
    TextInputEditText edLink;

    @BindView(R.id.edDescription)
    TextInputEditText edDescription;

    @BindView(R.id.btnFetch)
    Button btnFetch;

    @BindView(R.id.btnResult)
    Button btnResult;

    private HanldeXML hanldeXML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss);

        ButterKnife.bind(this);

        edBaseRSSFeedUrl.setText( "http://showmethemoney.or.kr/보도자료" );
    }

    @OnClick(R.id.btnFetch)
    public void fetch(){
        try {
            hanldeXML = new HanldeXML("http://showmethemoney.or.kr/","보도자료",this);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        hanldeXML.fetchXML();
    }

    @OnClick(R.id.btnResult)
    public void result(){
        Intent i = new Intent(this, WebViewTesterActivity.class);
        i.putExtra(RSS_FEED_KEY,edBaseRSSFeedUrl.getText().toString());
        startActivity( i );
    }

    @Override
    public void fetchEnded() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edTitle.setText( hanldeXML.getTitle());
                edLink.setText( hanldeXML.getLink());
                edDescription.setText( hanldeXML.getDescription());
            }
        });

    }

    @Override
    public void fetchError() {

    }
}
