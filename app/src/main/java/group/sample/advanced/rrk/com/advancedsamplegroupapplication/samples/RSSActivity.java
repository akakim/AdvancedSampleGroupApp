package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.HanldeXML;

public class RSSActivity extends BaseActivity implements HanldeXML.handlerInterface {


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

    }

    @OnClick(R.id.btnFetch)
    public void fetch(){
        hanldeXML = new HanldeXML("http://www.hani.co.kr/rss/politics/",this);
        hanldeXML.fetchXML();
    }

    @OnClick(R.id.btnResult)
    public void result(){
        Intent i = new Intent(this, WebViewTesterActivity.class);
        startActivity( i );
    }

    @Override
    public void fetchEnded() {
        edTitle.setText( hanldeXML.getTitle());
        edLink.setText( hanldeXML.getLink());
        edDescription.setText( hanldeXML.getDescription());
    }
}
