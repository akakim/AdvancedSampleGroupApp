package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.HandleXML;

public class RSSActivity extends BaseActivity implements HandleXML.handlerInterface {

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

    private HandleXML handleXML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnFetch)
    public void fetch(){
        handleXML = new HandleXML("http://www.hani.co.kr/rss/politics/",this);
        handleXML.fetchXML();
    }

    @OnClick(R.id.btnResult)
    public void result(){
        Intent i = new Intent(this, WebViewTesterActivity.class);
        startActivity( i );
    }

    @Override
    public void fetchEnded() {
        edTitle.setText( handleXML.getTitle());
        edLink.setText( handleXML.getLink());
        edDescription.setText( handleXML.getDescription());
    }
}
