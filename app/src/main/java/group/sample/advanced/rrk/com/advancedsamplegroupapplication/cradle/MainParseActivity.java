package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

import android.os.AsyncTask;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.BaseActivity;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class MainParseActivity extends BaseActivity implements MainParseUIView{


    MainParsePresenter mainParsePresenter;

    @BindView(R.id.tvContentResult)
    TextView tvContentResult;


    JSONObject resultObject = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parse);


        mainParsePresenter = new MainParsePresenterImpl( this );

        ButterKnife.bind(this);

        tvContentResult.setMovementMethod(new ScrollingMovementMethod());

        mainParsePresenter.initContent(()->{
            final StringBuilder builder = new StringBuilder();

            try{

                Connection connection = Jsoup.connect(Constants.PUBLIC_OPINION_BASE_URL);
                connection.timeout(5000);
                Document doc = connection.get();
                Elements classDivs = doc.select("div[class]");

                int i = 0;
                for( Element ele : classDivs ){

                    i++;

                    switch (ele.attr("class")){
                        case "cspb_info text":

                            builder.append("title " + ele.select("h5").text());
                            builder.append("title " + ele.select("p").text());

                            break;
                        default:
                            break;
                    }

//                                if( ele.attr("class").equals("csp_best")){
//
//                                    builder.append(  ele.attr("class") + " : " + ele.text() + "\n");
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Toast.makeText(MainParseActivity.this,ele.text()+ "got it!!! " ,Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }
//                                if( "csp_best".equals( ele.attr("csp_best") ) ){
//                                    builder.append(  ele.text() );
//                                }
//                                builder.append( "best : " + ele.attr("csp_best") ) ;
//                                builder.append("")
//                                builder.append( " ele : " + i + ": " + ele.text()+ " \n");


                }
                responseSuccess(builder.toString());
            } catch (MalformedInputException e){
                builder.append("MalformedInputException : " + e.getMessage());
                responseFailed(builder.toString());
                e.printStackTrace();
            } catch (IOException e ){
                builder.append("IOException : " + e.getMessage());
                responseFailed(builder.toString());
                e.printStackTrace();
            }catch (Exception e){
                builder.append("Exception : " + e.getMessage());
                responseFailed(builder.toString());
                e.printStackTrace();
            }

//            mainParsePresenter.onItemClicked(;;;);
        } );
        initContent();

    }


    // csp_best 베스트 청원
    //

    @Override
    public void initContent() {
        if( !progressBar.isShowing() ){
            progressBar.show();
        }

        Thread task = new Thread(
        );
        task.start();
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void responseFailed(String result) {
        progressBar.dismiss();

        runOnUiThread(()-> {
            Toast.makeText(MainParseActivity.this,result,Toast.LENGTH_SHORT).show();
        });

        // Fabric에 리포트 보내기 .
        // Connection Out 같은경우가 있을 수 있음 .
    }

    @Override
    public void responseSuccess(String result) {
        progressBar.dismiss();
        runOnUiThread( ()->{
            tvContentResult.setText(result);
        });

    }


    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showAlert() {

    }


}
