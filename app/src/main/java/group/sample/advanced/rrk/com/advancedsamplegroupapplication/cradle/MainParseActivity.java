package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

import android.os.AsyncTask;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;
import java.nio.charset.MalformedInputException;

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


        mainParsePresenter = new MainParsePresenterImpl( this );

        ButterKnife.bind(this);


//        progressBar.setScrollBar;
        initContent();

    }



    @Override
    public void initContent() {
        if( !progressBar.isShowing() ){
            progressBar.show();
        }

//        Thread task = new Thread(
//                ()->{
//                        final StringBuilder builder = new StringBuilder();
//
//                        try{
//                            Document doc = Jsoup.connect(Constants.PUBLIC_OPINION_BASE_URL).get();
//
//
//                            int i = 0;
//                            for( Element ele : doc.getElementsByAttribute( "href")){
//                                i++;
//                                builder.append( " ele : " + i + " \n");
//                            }
//                            responseSuccess(builder.toString());
//                        } catch (MalformedInputException e){
//                            builder.append("MalformedInputException : " + e.getMessage());
//                            responseFailed(builder.toString());
//                            e.printStackTrace();
//                        }
//                        catch (IOException e ){
//                            builder.append("IOException : " + e.getMessage());
//                            responseFailed(builder.toString());
//                            e.printStackTrace();
//                        }catch (Exception e){
//                            builder.append("Exception : " + e.getMessage());
//                            responseFailed(builder.toString());
//                            e.printStackTrace();
//                        }
//
//                     }
//        );
//        task.run();



//        Jsoup jsoup/

    }

    @Override
    public void responseFailed(String result) {
        progressBar.dismiss();

        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        // Fabric에 리포트 보내기 .
        // Connection Out 같은경우가 있을 수 있음 .
    }

    @Override
    public void responseSuccess(String result) {
        progressBar.dismiss();
        tvContentResult.setText(result);
    }


    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showAlert() {

    }


}
