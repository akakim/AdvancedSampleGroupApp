package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class MainParseActivity extends BaseActivity implements MainParseUIView{


    MainParsePresenter mainParsePresenter;

    @BindView(R.id.tvContentResult)
    TextView tvContentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parse);


        mainParsePresenter = new MainParsePresenterImpl( this ,this);

        ButterKnife.bind(this);

        tvContentResult.setMovementMethod(new ScrollingMovementMethod());

        mainParsePresenter.initContent();


    }
    // csp_best 베스트 청원

    @Override
    public void onResumeRefresh() {

    }

    @Override
    public void showProgress(String message) {
        progressBar.setContent( message );

        if( !progressBar.isShowing() ){
            progressBar.show();
        }

    }

    @Override
    public void responseFailed(String result) {
        progressBar.dismiss();
        Toast.makeText(MainParseActivity.this,result,Toast.LENGTH_SHORT).show();
        // Fabric에 리포트 보내기 .
        // Connection Out 같은경우가 있을 수 있음 .
    }

    @Override
    public void responseSuccess(String result, BoardData boardData) {
        progressBar.dismiss();
        tvContentResult.setText(result);
    }


    @Override
    public void showMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert() {

    }


}
